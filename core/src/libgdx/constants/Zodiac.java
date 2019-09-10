package libgdx.constants;

import libgdx.implementations.skelgame.SkelGameLabel;

import java.util.Arrays;
import java.util.List;

import static libgdx.constants.Element.*;
import static libgdx.implementations.skelgame.SkelGameLabel.*;

public enum Zodiac {

    aries(ZodiacComp.aries, fire, Planet.mars, Arrays.asList(attr_determined, attr_effective, attr_ambitious)),
    leo(ZodiacComp.leo, fire, Planet.sun, Arrays.asList(attr_warm, attr_generous, attr_faithful)),
    sagittarius(ZodiacComp.sagittarius, fire, Planet.jupiter, Arrays.asList(attr_philosophical, attr_optimist)),

    taurus(ZodiacComp.taurus, earth, Planet.venus, Arrays.asList(attr_security, attr_patience)),
    virgo(ZodiacComp.virgo, earth, Planet.mercury, Arrays.asList(attr_analytical, attr_observant, attr_thoughtful)),
    capricorn(ZodiacComp.capricorn, earth, Planet.saturn, Arrays.asList(attr_determined, attr_dominant, attr_practical)),

    gemini(ZodiacComp.gemini, wind, Planet.mercury, Arrays.asList(attr_communicative, attr_changeable, attr_intelligent)),
    libra(ZodiacComp.libra, wind, Planet.venus, Arrays.asList(attr_truth, attr_beauty, attr_perfection)),
    aquarius(ZodiacComp.aquarius, wind, Planet.uranus, Arrays.asList(attr_intellectual, attr_humanitarian, attr_duplicitous)),

    cancer(ZodiacComp.cancer, water, Planet.moon, Arrays.asList(attr_emotional, attr_diplomatic, attr_impulsive)),
    scorpio(ZodiacComp.scorpio, water, Planet.pluto, Arrays.asList(attr_purposeful, attr_restless)),
    pisces(ZodiacComp.pisces, water, Planet.neptune, Arrays.asList(attr_imagination, attr_indecision)),
    ;

    private ZodiacComp zodiacComp;
    private Element element;
    private Planet planet;
    private List<SkelGameLabel> attrs;

    Zodiac(ZodiacComp zodiacComp, Element element, Planet planet, List<SkelGameLabel> attrs) {
        this.zodiacComp = zodiacComp;
        this.element = element;
        this.planet = planet;
        this.attrs = attrs;
    }

    public static Zodiac getZodiac(int day, int month) {
        Zodiac sign = null;
        if (month == 1) {
            if (day < 20)
                sign = capricorn;
            else if (day < 31)
                sign = aquarius;
        } else if (month == 2) {
            if (day < 19)
                sign = aquarius;
            else if (day < 29)
                sign = pisces;
        } else if (month == 3) {
            if (day < 21)
                sign = pisces;
            else if (day < 31)
                sign = aries;
        } else if (month == 4) {
            if (day < 20)
                sign = aries;
            else if (day < 30)
                sign = taurus;
        } else if (month == 5) {
            if (day < 21)
                sign = taurus;
            else if (day < 31)
                sign = gemini;
        } else if (month == 6) {
            if (day < 21)
                sign = gemini;
            else if (day < 30)
                sign = cancer;
        } else if (month == 7) {
            if (day < 23)
                sign = cancer;
            else if (day < 31)
                sign = leo;
        } else if (month == 8) {
            if (day < 23)
                sign = leo;
            else if (day < 31)
                sign = virgo;
        } else if (month == 9) {
            if (day < 23)
                sign = virgo;
            else if (day < 30)
                sign = libra;
        } else if (month == 10) {
            if (day < 23)
                sign = libra;
            else if (day < 31)
                sign = scorpio;
        } else if (month == 11) {
            if (day < 22)
                sign = scorpio;
            else if (day < 30)
                sign = sagittarius;
        } else if (month == 12) {
            if (day < 22)
                sign = sagittarius;
            else if (day < 31)
                sign = capricorn;
        }
        return sign;
    }

    public List<SkelGameLabel> getAttrs() {
        return attrs;
    }

    public Element getElement() {
        return element;
    }

    public Planet getPlanet() {
        return planet;
    }

    public ZodiacComp getZodiacComp() {
        return zodiacComp;
    }
}
