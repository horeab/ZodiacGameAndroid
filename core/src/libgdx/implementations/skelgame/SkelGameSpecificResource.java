package libgdx.implementations.skelgame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.I18NBundle;

import libgdx.game.Game;
import libgdx.resources.SpecificResource;

public enum SkelGameSpecificResource implements SpecificResource {

    // @formatter:off

    specific_labels("labels/labels", I18NBundle.class),

    aries("images/aries.png", Texture.class),
    leo("images/leo.png", Texture.class),
    sagittarius("images/sagittarius.png", Texture.class),
    taurus("images/taurus.png", Texture.class),
    virgo("images/virgo.png", Texture.class),
    capricorn("images/capricorn.png", Texture.class),
    gemini("images/gemini.png", Texture.class),
    libra("images/libra.png", Texture.class),
    aquarius("images/aquarius.png", Texture.class),
    cancer("images/cancer.png", Texture.class),
    scorpio("images/scorpio.png", Texture.class),
    pisces("images/pisces.png", Texture.class),

    sun("images/sun.png", Texture.class),
    mercury("images/mercury.png", Texture.class),
    venus("images/venus.png", Texture.class),
    moon("images/moon.png", Texture.class),
    mars("images/mars.png", Texture.class),
    jupiter("images/jupiter.png", Texture.class),
    saturn("images/saturn.png", Texture.class),
    uranus("images/uranus.png", Texture.class),
    neptune("images/neptune.png", Texture.class),
    pluto("images/pluto.png", Texture.class),

    wind("images/wind.png", Texture.class),
    water("images/water.png", Texture.class),
    fire("images/fire.png", Texture.class),
    earth("images/earth.png", Texture.class),

    gr("images/gr.png", Texture.class),
    ok("images/ok.png", Texture.class),
    bd("images/bd.png", Texture.class)

    ;
    // @formatter:on

    private String path;
    private Class<?> classType;

    SkelGameSpecificResource(String path, Class<?> classType) {
        this.path = path;
        this.classType = classType;
    }

    @Override
    public Class<?> getClassType() {
        return classType;
    }

    @Override
    public String getPath() {
        return Game.getInstance().getAppInfoService().getImplementationGameResourcesFolder() + path;
    }

}
