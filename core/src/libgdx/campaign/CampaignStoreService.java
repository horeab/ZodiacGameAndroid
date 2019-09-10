package libgdx.campaign;

import java.util.ArrayList;
import java.util.List;

import libgdx.game.Game;
import libgdx.preferences.PreferencesService;
import libgdx.utils.EnumUtils;

class CampaignStoreService {

    private static final String CAMPAIGN_LEVEL = "CampaignLevel";
    private static final String PREF_NAME = "campaignStoreService";

    private PreferencesService preferencesService = new PreferencesService(PREF_NAME);

    CampaignStoreService() {
//        this.preferencesService.clear();
    }

    void createCampaignLevel(CampaignLevel campaignLevelEnum) {
        preferencesService.putInteger(formCampaignLevelKey(campaignLevelEnum), -1);
    }

    private String formCampaignLevelKey(CampaignLevel campaignLevelEnum) {
        return CAMPAIGN_LEVEL + campaignLevelEnum.getIndex();
    }

    List<CampaignStoreLevel> getAllCampaignLevels() {
        ArrayList<CampaignStoreLevel> levels = new ArrayList<>();
        for (CampaignLevel levelEnum : (CampaignLevel[]) EnumUtils.getValues(CampaignGame.getInstance().getSubGameDependencyManager().getCampaignLevelTypeEnum())) {
            int val = preferencesService.getPreferences().getInteger(formCampaignLevelKey(levelEnum), -1);
            if (val != -1) {
                CampaignStoreLevel level = new CampaignStoreLevel(levelEnum);
                level.setStarsWon(getStarsWon(levelEnum));
                level.setStatus(preferencesService.getPreferences().getInteger(formCampaignLevelStatusKey(levelEnum)));
                levels.add(level);
            }
        }
        return levels;
    }

    public int getStarsWon(CampaignLevel levelEnum) {
        return preferencesService.getPreferences().getInteger(formCampaignLevelStarsWonKey(levelEnum));
    }

    Integer getCrosswordLevel(CampaignLevel campaignLevelEnum) {
        return preferencesService.getPreferences().getInteger(formCampaignLevelKey(campaignLevelEnum), -1);
    }

    void updateLevel(CampaignLevel campaignLevelEnum) {
        preferencesService.putInteger(formCampaignLevelKey(campaignLevelEnum), campaignLevelEnum.getIndex());
    }

    void updateStatus(CampaignLevel campaignLevelEnum, CampaignLevelStatusEnum campaignLevelStatusEnum) {
        preferencesService.putInteger(formCampaignLevelStatusKey(campaignLevelEnum), campaignLevelStatusEnum.getStatus());
    }

    void updateStarsWon(CampaignLevel campaignLevelEnum, int starsWon) {
        preferencesService.putInteger(formCampaignLevelStarsWonKey(campaignLevelEnum), starsWon);
    }

    private String formCampaignLevelStarsWonKey(CampaignLevel campaignLevelEnum) {
        return formCampaignLevelKey(campaignLevelEnum) + "StarsWon";
    }

    private String formCampaignLevelStatusKey(CampaignLevel campaignLevelEnum) {
        return formCampaignLevelKey(campaignLevelEnum) + "CampaignLevelStatusEnum";
    }

}
