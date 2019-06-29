package com.habapps.service;

import libgdx.game.LettersGame;

public class LibGdxGameCalls {

    public void showMainMenuScreen() {
        LettersGame.getInstance().getScreenManager().showMainScreen();
    }

    public boolean hasInternet() {
        return LettersGame.getInstance().hasInternet();
    }

}
