package libgdx.screens.mainmenu;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import libgdx.campaign.CampaignGame;
import libgdx.constants.Contrast;
import libgdx.constants.Zodiac;
import libgdx.controls.MyTextField;
import libgdx.controls.animations.WordAnimation;
import libgdx.controls.button.ButtonBuilder;
import libgdx.controls.button.MyButton;
import libgdx.controls.label.MyWrappedLabel;
import libgdx.controls.popup.MyPopup;
import libgdx.controls.textfield.MyTextFieldBuilder;
import libgdx.game.Game;
import libgdx.game.ScreenManager;
import libgdx.implementations.skelgame.SkelGameLabel;
import libgdx.resources.FontManager;
import libgdx.screen.AbstractScreen;
import libgdx.screen.AbstractScreenManager;
import libgdx.utils.ScreenDimensionsManager;
import libgdx.utils.model.FontColor;
import org.apache.commons.lang3.StringUtils;

public class BirthDatePopup extends MyPopup<MainMenuScreen, ScreenManager> {

    private MyTextField dayMyTextField;
    private MyTextField monthMyTextField;
    private boolean myZodiac;

    BirthDatePopup(MainMenuScreen abstractScreen, boolean myZodiac) {
        super(abstractScreen);
        this.myZodiac = myZodiac;
        TextField.TextFieldFilter textFieldFilter = new TextField.TextFieldFilter() {
            public boolean acceptChar(TextField textField, char c) {
                return textField.getText().length() < 2 && StringUtils.isNumeric(String.valueOf(c));
            }
        };
        float height = ScreenDimensionsManager.getScreenHeightValue(7);
        float width = ScreenDimensionsManager.getScreenWidthValue(10);
        dayMyTextField = new MyTextFieldBuilder().setHeight(height).setWidth(width).setTextFieldFilter(textFieldFilter).build();
        monthMyTextField = new MyTextFieldBuilder().setHeight(height).setWidth(width).setTextFieldFilter(textFieldFilter).build();
    }

    @Override
    public void addButtons() {
        MyButton button = new ButtonBuilder().setContrast(Contrast.DARK).setSingleLineText("OK", FontManager.getSmallFontDim()).setDefaultButton().build();
        button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                int day = -1;
                int month = -1;
                try {
                    day = Integer.parseInt(dayMyTextField.getTextField().getText());
                    month = Integer.parseInt(monthMyTextField.getTextField().getText());
                } catch (Exception e) {
                    //Ignore e
                }
                Zodiac zodiac = Zodiac.getZodiac(day, month);
                if (zodiac == null) {
                    addActorsToFront(new WordAnimation().createLabelToAnimate(SkelGameLabel.bd_birth_date_wrong.getText(), FontColor.RED));
                } else {
                    hide();
                    if (myZodiac) {
                        getScreen().setMyZodiac(zodiac);
                    } else {
                        getScreen().setPartnerZodiac(zodiac);
                    }
                }
            }
        });
        addButton(button);
    }

    @Override
    protected void addText() {
        super.addText();
        getContentTable().add(new MyWrappedLabel(SkelGameLabel.bd_day.getText()));
        getContentTable().add(new MyWrappedLabel(SkelGameLabel.bd_month.getText())).row();
        getContentTable().add(dayMyTextField);
        getContentTable().add(monthMyTextField).row();
    }

    @Override
    protected String getLabelText() {
        return "";
    }
}
