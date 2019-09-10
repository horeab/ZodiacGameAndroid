package libgdx.screens.mainmenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

import libgdx.constants.Contrast;
import libgdx.constants.Language;
import libgdx.constants.Zodiac;
import libgdx.constants.ZodiacCompStatus;
import libgdx.controls.animations.ActorAnimation;
import libgdx.controls.button.ButtonBuilder;
import libgdx.controls.button.MyButton;
import libgdx.controls.label.MyLabel;
import libgdx.controls.label.MyWrappedLabel;
import libgdx.controls.label.MyWrappedLabelConfigBuilder;
import libgdx.dbapi.GameStatsDbApiService;
import libgdx.game.Game;
import libgdx.game.ScreenManager;
import libgdx.game.model.BaseUserInfo;
import libgdx.graphics.GraphicUtils;
import libgdx.implementations.skelgame.SkelGameLabel;
import libgdx.implementations.skelgame.SkelGameRatingService;
import libgdx.implementations.skelgame.SkelGameSpecificResource;
import libgdx.resources.FontManager;
import libgdx.resources.MainResource;
import libgdx.resources.dimen.MainDimen;
import libgdx.screen.AbstractScreen;
import libgdx.utils.DateUtils;
import libgdx.utils.ScreenDimensionsManager;
import libgdx.utils.Utils;
import libgdx.utils.model.FontColor;
import libgdx.utils.model.RGBColor;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

public class MainMenuScreen extends AbstractScreen<ScreenManager> {

    private BaseUserInfo currentUser;

    private List<String> SMALL_FONT_LANGS = Arrays.asList(Language.th.name());

    public static final float EXTRA_INFO_DIMEN = MainDimen.horizontal_general_margin.getDimen() * 6;
    private BirthDatePopup birthDatePopup;

    private Zodiac myZodiac;
    private Zodiac partnerZodiac;

    private Table allZodiacTable;

    public MainMenuScreen(Zodiac myZodiac) {
        this.myZodiac = myZodiac;
    }

    @Override
    protected void setBackgroundColor(RGBColor backgroundColor) {
        super.setBackgroundColor(RGBColor.DARK_BLUE);
    }

    @Override
    public void buildStage() {
        currentUser = Game.getInstance().getCurrentUser();
        if (myZodiac == null) {
            new SkelGameRatingService(this).appLaunched();
        }
        createAllZodiacTable();

//        myZodiac = Zodiac.aries;
//        partnerZodiac = Zodiac.cancer;
//        createCompTable();
    }

    public void setPartnerZodiac(Zodiac partnerZodiac) {
        this.partnerZodiac = partnerZodiac;
        changeAllTableView(new Runnable() {
            @Override
            public void run() {
                createCompTable();
            }
        });
        birthDatePopup = null;
    }

    private void changeAllTableView(Runnable newView) {
        allZodiacTable.addAction(Actions.sequence(Actions.fadeOut(0.5f), Utils.createRunnableAction(newView)));
    }

    public void setMyZodiac(Zodiac myZodiac) {
        this.myZodiac = myZodiac;
        changeAllTableView(new Runnable() {
            @Override
            public void run() {
                createAllZodiacTable();
            }
        });
        birthDatePopup = null;
    }

    private void createCompTable() {
        if (currentUser != null) {
            new GameStatsDbApiService().incrementGameStatsQuestionsWon(currentUser.getId(), Long.valueOf(DateUtils.getNowMillis()).toString());
        }
        allZodiacTable = new Table();
        allZodiacTable.setFillParent(true);

        int zPercent = smallFontLang() ? 60 : 50;
        allZodiacTable.add(createCompZodiacContainer(myZodiac)).height(ScreenDimensionsManager.getScreenHeightValue(zPercent)).growX();
        allZodiacTable.add(createCompZodiacContainer(partnerZodiac)).height(ScreenDimensionsManager.getScreenHeightValue(zPercent)).growX();
        allZodiacTable.row();
        ZodiacCompStatus zodiacCompStatus = myZodiac.getZodiacComp().forZodiac(partnerZodiac);
        float marginDimen = MainDimen.horizontal_general_margin.getDimen();
        float compDimen = smallFontLang() ? marginDimen * 13 : marginDimen * 20;
        Table compTable = new Table();
        compTable.add(new MyWrappedLabel(new MyWrappedLabelConfigBuilder().setTextColor(FontColor.WHITE).setText(SkelGameLabel.valueOf("comp_" + zodiacCompStatus.name()).getText()).setFontScale(FontManager.getBigFontDim()).build()))
                .row();
        Image compImg = GraphicUtils.getImage(SkelGameSpecificResource.valueOf(zodiacCompStatus.name()));
        compImg.setOrigin(Align.center);
        compImg.setHeight(compDimen);
        compImg.setWidth(compDimen);
        if (zodiacCompStatus != ZodiacCompStatus.bd) {
            new ActorAnimation(compImg, getAbstractScreen()).animateZoomInZoomOut();
        }
        Table imgTable = new Table();
        imgTable.add(compImg).padTop(marginDimen).width(compDimen).height(compDimen);
        compTable.add(imgTable).width(compDimen);
        allZodiacTable.add(compTable).height(ScreenDimensionsManager.getScreenHeightValue(smallFontLang() ? 40 : 50)).growX().colspan(2);
        addActor(allZodiacTable);
    }

    private Table createCompZodiacContainer(Zodiac zodiac) {
        Table table = new Table();
        float marginDimen = MainDimen.horizontal_general_margin.getDimen();
        float zodiacDimen = marginDimen * 13;
        Table zodiacInfoTable = new Table();
        zodiacInfoTable.setBackground(GraphicUtils.getNinePatch(MainResource.popup_background));
        float zodiacPad = marginDimen * 2;
        MyWrappedLabel myWrappedLabel = new MyWrappedLabel(new MyWrappedLabelConfigBuilder().setFontScale(FontManager.getBigFontDim())
                .setText(StringUtils.capitalize(SkelGameLabel.valueOf("zod_" + zodiac.name()).getText())).setWidth(zodiacDimen / 1.01f).build());
        myWrappedLabel = myWrappedLabel.fitToContainer();
        zodiacInfoTable.add(myWrappedLabel).padLeft(zodiacPad).padRight(zodiacPad).width(zodiacDimen).row();
        zodiacInfoTable.add(getZodiacImage(zodiac)).padLeft(zodiacPad).padRight(zodiacPad).height(zodiacDimen).width(zodiacDimen);
        table.add(zodiacInfoTable).row();
        table.add(createPlanetElement(zodiac)).padBottom(marginDimen * 2).row();
        table.add(createAttrsTable(zodiac));
        return table;
    }

    private Table createAttrsTable(Zodiac zodiac) {
        Table table = new Table();
        for (SkelGameLabel attr : zodiac.getAttrs()) {
            table.add(new MyWrappedLabel(new MyWrappedLabelConfigBuilder().setTextColor(FontColor.WHITE).setText(StringUtils.capitalize(attr.getText())).setFontScale(FontManager.getSmallFontDim()).build())).width(EXTRA_INFO_DIMEN).row();
        }
        return table;
    }

    private Table createPlanetElement(Zodiac zodiac) {
        Table table = new Table();
        table.add(createExtraInfoTable(GraphicUtils.getImage(SkelGameSpecificResource.valueOf(zodiac.getElement().name())),
                SkelGameLabel.valueOf("el_" + zodiac.getElement().name()).getText()));
        table.add(createExtraInfoTable(GraphicUtils.getImage(SkelGameSpecificResource.valueOf(zodiac.getPlanet().name())),
                SkelGameLabel.valueOf("pl_" + zodiac.getPlanet().name()).getText()));
        return table;
    }

    private Table createExtraInfoTable(Image image, String label) {
        Table table = new Table();
        float marginDimen = MainDimen.horizontal_general_margin.getDimen();
        table.add(image).pad(marginDimen).width(EXTRA_INFO_DIMEN).height(EXTRA_INFO_DIMEN).row();
        MyWrappedLabel wrappedLabel = new MyWrappedLabel(new MyWrappedLabelConfigBuilder()
                .setWidth(EXTRA_INFO_DIMEN / 1.01f)
                .setText(StringUtils.capitalize(label))
                .setTextColor(FontColor.WHITE).build());
        wrappedLabel = wrappedLabel.fitToContainer();
        table.add(wrappedLabel).width(EXTRA_INFO_DIMEN);
        return table;
    }

    private void createAllZodiacTable() {
        if (myZodiac == null) {
            if (currentUser != null) {
                new GameStatsDbApiService().incrementGameStatsQuestionsStarted(currentUser.getId(), Long.valueOf(DateUtils.getNowMillis()).toString());
            }
        }

        allZodiacTable = new Table();
        allZodiacTable.setFillParent(true);
        float marginDimen = MainDimen.horizontal_general_margin.getDimen();
        String text = myZodiac == null ? SkelGameLabel.zd_my_zodiac.getText() : SkelGameLabel.zd_partner_zodiac.getText();
        float multiplier = smallFontLang() ? 1f : 1.25f;
        MyWrappedLabel label = new MyWrappedLabel(new MyWrappedLabelConfigBuilder().setTextColor(FontColor.WHITE).setText(text).setFontScale(FontManager.calculateMultiplierStandardFontSize(multiplier)).build());
        allZodiacTable.add(label).pad(marginDimen).colspan(3).row();
        for (Zodiac zodiac : Zodiac.values()) {
            allZodiacTable.add(createZodiacTable(zodiac)).pad(marginDimen);
            if ((zodiac.ordinal() + 1) % 3 == 0) {
                allZodiacTable.row();
            }
        }
        allZodiacTable.row();
        allZodiacTable.add(new MyWrappedLabel(new MyWrappedLabelConfigBuilder().setTextColor(FontColor.WHITE).setText(SkelGameLabel.label_or.getText()).build())).pad(marginDimen * 1.5f).colspan(3).row();
        final MainMenuScreen mainMenuScreen = this;
        MyButton useBirthDateButton = new ButtonBuilder().setContrast(Contrast.DARK)
                .setSingleLineText(SkelGameLabel.bd_birth_date.getText(), FontManager.getSmallFontDim()).setDefaultButton().build();

        useBirthDateButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (Game.getInstance().getAppInfoService().isScreenShotMode()) {
                    Utils.createChangeLangPopup();
                } else {
                    if (birthDatePopup == null) {
                        birthDatePopup = new BirthDatePopup(mainMenuScreen, myZodiac == null);
                    }
                    birthDatePopup.addToPopupManager();
                }
            }
        });
        allZodiacTable.add(useBirthDateButton)
                .width(marginDimen * 30).height(marginDimen * 6).colspan(3);
        allZodiacTable.setVisible(false);
        allZodiacTable.addAction(Actions.sequence(Actions.fadeOut(0f), Utils.createRunnableAction(new Runnable() {
            @Override
            public void run() {
                allZodiacTable.setVisible(true);
            }
        }), Actions.fadeIn(0.5f)));
        addActor(allZodiacTable);
    }

    private boolean smallFontLang() {
        return SMALL_FONT_LANGS.contains(Game.getInstance().getAppInfoService().getLanguage());
    }

    private Table createZodiacTable(final Zodiac zodiac) {
        final Table table = new Table();
        table.setTouchable(Touchable.enabled);
        table.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (myZodiac == null) {
                    setMyZodiac(zodiac);
                } else if (partnerZodiac == null) {
                    setPartnerZodiac(zodiac);
                    table.setTouchable(Touchable.disabled);
                }
            }
        });
        table.setBackground(GraphicUtils.getNinePatch(MainResource.popup_background));
        float marginDimen = MainDimen.horizontal_general_margin.getDimen();
        float zIconDimen = marginDimen * 8;
        MyWrappedLabel zodiacLabel = new MyWrappedLabel(new MyWrappedLabelConfigBuilder().setWidth(zIconDimen / 1.01f).setFontScale(FontManager.getSmallFontDim())
                .setText(StringUtils.capitalize(SkelGameLabel.valueOf("zod_" + zodiac.name()).getText())).build());
        zodiacLabel = zodiacLabel.fitToContainer();
        table.add(zodiacLabel).width(zIconDimen).row();
        table.add(getZodiacImage(zodiac)).pad(marginDimen / 2).height(zIconDimen).width(zIconDimen);
        return table;

    }

    private Image getZodiacImage(Zodiac zodiac) {
        return GraphicUtils.getImage(SkelGameSpecificResource.valueOf(zodiac.name()));
    }

    @Override
    public void onBackKeyPress() {
        if (myZodiac == null) {
            Gdx.app.exit();
        } else {
            myZodiac = null;
            partnerZodiac = null;
            changeAllTableView(new Runnable() {
                @Override
                public void run() {
                    createAllZodiacTable();
                }
            });
        }
    }

}
