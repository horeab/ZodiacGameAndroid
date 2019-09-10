package libgdx.campaign;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import libgdx.controls.button.ButtonBuilder;
import libgdx.controls.button.MyButton;
import libgdx.controls.labelimage.LabelImage;
import libgdx.graphics.GraphicUtils;
import libgdx.resources.*;
import libgdx.resources.dimen.MainDimen;
import org.apache.commons.lang3.StringUtils;

public class CampaignLevelButtonBuilder extends ButtonBuilder {

    private CampaignStoreLevel level;
    private CampaignLevel levelEnum;
    private float fontDimen = FontManager.calculateMultiplierStandardFontSize(0.8f);
    private boolean levelLocked;
    private CampaignLevelEnumService campaignLevelEnumService;

    public CampaignLevelButtonBuilder(CampaignLevel levelEnum, CampaignStoreLevel level) {
        this.level = level;
        this.levelEnum = levelEnum;
        this.campaignLevelEnumService = new CampaignLevelEnumService(levelEnum);
    }

    public CampaignLevelButtonBuilder setLevelLocked(boolean levelLocked) {
        this.levelLocked = levelLocked;
        return this;
    }

    @Override
    public MyButton build() {
        return null;
    }

    private void addLevelInfo() {
        Table table = new Table();
        float verticalGeneralMarginDimen = MainDimen.vertical_general_margin.getDimen();
        if (level != null && level.getStatus() == CampaignLevelStatusEnum.FINISHED.getStatus()) {
            Table starsBar = createStarsBar();
            table.add(starsBar).height(starsBar.getHeight()).width(starsBar.getWidth()).padBottom(verticalGeneralMarginDimen / 2).row();
        }
        if (!levelLocked && StringUtils.isNotBlank(campaignLevelEnumService.getLabelText())) {
            table.add(createTextTable()).padTop(verticalGeneralMarginDimen / 2).row();
        }
        addCenterTextImageColumn(table);
    }

    private LabelImage createTextTable() {
        LabelImage textTable = createTextTable(campaignLevelEnumService.getLabelText(), MainDimen.horizontal_general_margin.getDimen() * 17, fontDimen);
        textTable.setBackground(GraphicUtils.getNinePatch(MainResource.popup_background));
        return textTable;
    }

    private Table createStarsBar() {
        Table table = new StarsBarCreator(level.getStarsWon()).createHorizontalStarsBar(MainDimen.vertical_general_margin.getDimen() * 3f);
        table.setBackground(GraphicUtils.getNinePatch(Resource.stars_table_background));
        return table;
    }
}
