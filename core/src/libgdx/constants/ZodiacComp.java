package libgdx.constants;

import static libgdx.constants.ZodiacCompStatus.*;

public enum ZodiacComp {

    aries(gr, gr, gr, bd, ok, bd, gr, gr, gr, bd, bd, ok),
    leo(gr, gr, gr, ok, bd, bd, gr, gr, ok, ok, ok, ok),
    sagittarius(gr, gr, gr, bd, bd, bd, gr, gr, gr, ok, ok, ok),

    taurus(bd, ok, bd, gr, gr, gr, bd, ok, bd, gr, gr, gr),
    virgo(bd, ok, bd, gr, gr, gr, bd, bd, ok, gr, gr, ok),
    capricorn(bd, ok, bd, gr, gr, gr, bd, ok, bd, gr, gr, gr),

    gemini(gr, gr, ok, bd, ok, ok, gr, gr, gr, bd, bd, bd),
    libra(ok, gr, gr, ok, bd, bd, gr, gr, gr, bd, bd, ok),
    aquarius(gr, gr, gr, bd, bd, bd, gr, gr, gr, bd, ok, ok),

    cancer(bd, ok, ok, gr, gr, gr, bd, bd, bd, gr, gr, gr),
    scorpio(ok, ok, bd, gr, gr, gr, bd, bd, bd, gr, gr, gr),
    pisces(ok, ok, ok, gr, ok, gr, bd, bd, bd, gr, gr, gr),
    ;

    private ZodiacCompStatus ariesComp;
    private ZodiacCompStatus leoComp;
    private ZodiacCompStatus sagittariusComp;
    private ZodiacCompStatus taurusComp;
    private ZodiacCompStatus virgoComp;
    private ZodiacCompStatus capricornComp;
    private ZodiacCompStatus geminiComp;
    private ZodiacCompStatus libraComp;
    private ZodiacCompStatus aquariusComp;
    private ZodiacCompStatus cancerComp;
    private ZodiacCompStatus scorpioComp;
    private ZodiacCompStatus piscesComp;

    ZodiacComp(ZodiacCompStatus ariesComp, ZodiacCompStatus leoComp, ZodiacCompStatus sagittariusComp, ZodiacCompStatus taurusComp, ZodiacCompStatus virgoComp, ZodiacCompStatus capricornComp, ZodiacCompStatus geminiComp, ZodiacCompStatus libraComp, ZodiacCompStatus aquariusComp, ZodiacCompStatus cancerComp, ZodiacCompStatus scorpioComp, ZodiacCompStatus piscesComp) {
        this.ariesComp = ariesComp;
        this.leoComp = leoComp;
        this.sagittariusComp = sagittariusComp;
        this.taurusComp = taurusComp;
        this.virgoComp = virgoComp;
        this.capricornComp = capricornComp;
        this.geminiComp = geminiComp;
        this.libraComp = libraComp;
        this.aquariusComp = aquariusComp;
        this.cancerComp = cancerComp;
        this.scorpioComp = scorpioComp;
        this.piscesComp = piscesComp;
    }

    public ZodiacCompStatus forZodiac(Zodiac zodiac) {
        switch (zodiac) {

            case aries:
                return ariesComp;
            case leo:
                return leoComp;
            case sagittarius:
                return sagittariusComp;

            case taurus:
                return taurusComp;
            case virgo:
                return virgoComp;
            case capricorn:
                return capricornComp;

            case gemini:
                return geminiComp;
            case libra:
                return libraComp;
            case aquarius:
                return aquariusComp;

            case cancer:
                return cancerComp;
            case scorpio:
                return scorpioComp;
            case pisces:
                return piscesComp;
            default:
                return null;
        }
    }

    public ZodiacCompStatus getAriesComp() {
        return ariesComp;
    }

    public ZodiacCompStatus getLeoComp() {
        return leoComp;
    }

    public ZodiacCompStatus getSagittariusComp() {
        return sagittariusComp;
    }

    public ZodiacCompStatus getTaurusComp() {
        return taurusComp;
    }

    public ZodiacCompStatus getVirgoComp() {
        return virgoComp;
    }

    public ZodiacCompStatus getCapricornComp() {
        return capricornComp;
    }

    public ZodiacCompStatus getGeminiComp() {
        return geminiComp;
    }

    public ZodiacCompStatus getLibraComp() {
        return libraComp;
    }

    public ZodiacCompStatus getAquariusComp() {
        return aquariusComp;
    }

    public ZodiacCompStatus getCancerComp() {
        return cancerComp;
    }

    public ZodiacCompStatus getScorpioComp() {
        return scorpioComp;
    }

    public ZodiacCompStatus getPiscesComp() {
        return piscesComp;
    }
}
