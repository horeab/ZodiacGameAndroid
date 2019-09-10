package libgdx.implementations.skelgame;

import libgdx.game.Game;
import libgdx.resources.gamelabel.GameLabelUtils;
import libgdx.resources.gamelabel.SpecificPropertiesUtils;

public enum SkelGameLabel implements libgdx.resources.gamelabel.GameLabel {

    level_finished,
    level_failed,
    game_finished,
    go_back,
    next_level,
    play_again,

    attr_determined,
    attr_effective,
    attr_ambitious,
    attr_security,
    attr_patience,
    attr_communicative,
    attr_intelligent,
    attr_changeable,
    attr_emotional,
    attr_diplomatic,
    attr_impulsive,
    attr_warm,
    attr_generous,
    attr_faithful,
    attr_analytical,
    attr_observant,
    attr_thoughtful,
    attr_truth,
    attr_beauty,
    attr_perfection,
    attr_restless,
    attr_purposeful,
    attr_philosophical,
    attr_optimist,
    attr_dominant,
    attr_practical,
    attr_intellectual,
    attr_humanitarian,
    attr_duplicitous,
    attr_imagination,
    attr_indecision,

    zod_aries,
    zod_leo,
    zod_sagittarius,
    zod_taurus,
    zod_virgo,
    zod_capricorn,
    zod_gemini,
    zod_libra,
    zod_aquarius,
    zod_cancer,
    zod_scorpio,
    zod_pisces,

    pl_sun,
    pl_mercury,
    pl_venus,
    pl_moon,
    pl_mars,
    pl_jupiter,
    pl_saturn,
    pl_uranus,
    pl_neptune,
    pl_pluto,

    el_fire,
    el_water,
    el_wind,
    el_earth,

    comp_gr,
    comp_ok,
    comp_bd,

    bd_day,
    bd_month,

    zd_my_zodiac,
    zd_partner_zodiac,

    label_or,
    bd_birth_date,
    bd_birth_date_wrong;

    @Override
    public String getText(Object... params) {
        String language = Game.getInstance().getAppInfoService().getLanguage();
        return GameLabelUtils.getText(getKey(), language, GameLabelUtils.getLabelRes(language).getPath(), params);
    }

    @Override
    public String getKey() {
        return name().toLowerCase();
    }
}
