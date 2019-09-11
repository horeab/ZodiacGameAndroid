package com.habapps.service;


import libgdx.implementations.skelgame.SkelGame;

public class LibGdxGameCalls {

    public void showMainMenuScreen() {
        SkelGame.getInstance().getScreenManager().showMainScreen();
    }

    public boolean hasInternet() {
        return SkelGame.getInstance().hasInternet();
    }

}
