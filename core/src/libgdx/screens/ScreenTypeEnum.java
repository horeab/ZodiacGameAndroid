package libgdx.screens;

import libgdx.constants.Zodiac;
import libgdx.screen.AbstractScreen;
import libgdx.screen.ScreenType;
import libgdx.screens.mainmenu.MainMenuScreen;

public enum ScreenTypeEnum implements ScreenType {

    MAIN_MENU_SCREEN {
        public AbstractScreen getScreen(Object... params) {
            return new MainMenuScreen(params == null ? null : (Zodiac) params[0]);
        }
    },

}