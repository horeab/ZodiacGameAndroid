package libgdx.campaign;

public class CampaignStoreLevel {

    private int level;
    private int status;
    private int starsWon;

    public CampaignStoreLevel(CampaignLevel campaignLevel) {
        level = campaignLevel.getIndex();
        starsWon = 0;
        status = CampaignLevelStatusEnum.IN_PROGRESS.getStatus();
    }

    public int getStarsWon() {
        return starsWon;
    }

    public void setStarsWon(int starsWon) {
        this.starsWon = starsWon;
    }

    public int getLevel() {
        return level;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
