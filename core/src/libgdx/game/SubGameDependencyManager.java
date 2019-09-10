package libgdx.game;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.badlogic.gdx.Gdx;
import libgdx.constants.Contrast;
import libgdx.resources.IncrementingRes;
import libgdx.resources.SpecificResource;
import libgdx.resources.gamelabel.GameLabel;
import libgdx.resources.gamelabel.MainGameLabel;
import libgdx.resources.gamelabel.SpecificPropertiesUtils;
import libgdx.utils.EnumUtils;
import libgdx.utils.model.RGBColor;

public abstract class SubGameDependencyManager {

    public abstract <T extends Enum<T> & SpecificResource> Class<T> getSpecificResourceTypeEnum();

    public abstract List<? extends IncrementingRes> getIncrementResList();

    public Contrast getScreenContrast() {
        return Contrast.LIGHT;
    }

    public String getAllFontChars() {
        String allChars = collectAllLabelChars() + allQuestionText();
        Set<String> resultSet = new HashSet<>();
        for (int i = 0; i < allChars.length(); i++) {
            resultSet.add(Character.toString(allChars.charAt(i)));
        }
        return String.join("", resultSet);
    }

    private String collectAllLabelChars() {
        StringBuilder allChars = new StringBuilder();
        for (GameLabel label : MainGameLabel.values()) {
            allChars.append(label.getText());
        }
        for (GameLabel label : (GameLabel[]) EnumUtils.getValues(Game.getInstance().getMainDependencyManager().getGameLabelClass())) {
            allChars.append(label.getText());
        }
        StringBuilder specificText = new StringBuilder();
        Scanner scanner = new Scanner(Gdx.files.internal(SpecificPropertiesUtils.getLabelFilePath() + ".properties").readString());
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.startsWith(Game.getInstance().getAppInfoService().getLanguage() + "_")) {
                specificText.append(line);
            }
        }
        scanner.close();
        allChars.append(specificText.toString());
        return allChars.toString();
    }

    protected abstract String allQuestionText();

    public RGBColor getScreenBackgroundColor() {
        return getScreenContrast() == Contrast.LIGHT ? RGBColor.LIGHT_BLUE : RGBColor.DARK_BLUE;
    }
}
