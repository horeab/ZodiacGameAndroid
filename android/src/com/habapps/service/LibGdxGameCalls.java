package com.habapps.service;


public class LibGdxGameCalls {

    public void showMainMenuScreen() {
        SkelGame.getInstance().getScreenManager().showMainScreen();
    }

    public boolean hasInternet() {
        return SkelGame.getInstance().hasInternet();
    }

}
