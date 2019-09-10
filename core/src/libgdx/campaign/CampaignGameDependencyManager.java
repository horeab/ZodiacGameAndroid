package libgdx.campaign;

import java.util.List;

import libgdx.game.SubGameDependencyManager;
import libgdx.resources.IncrementingRes;
import libgdx.resources.SpecificResource;

public abstract class CampaignGameDependencyManager extends SubGameDependencyManager {

    public abstract <T extends Enum<T> & SpecificResource> Class<T> getSpecificResourceTypeEnum();

    public abstract List<? extends IncrementingRes> getIncrementResList();

    public abstract <T extends Enum<T>> Class<T> getCampaignLevelTypeEnum();

    public abstract <T extends Enum<T> & QuestionCategory> Class<T> getQuestionCategoryTypeEnum();

    public abstract <T extends Enum<T> & QuestionDifficulty> Class<T> getQuestionDifficultyTypeEnum();

}
