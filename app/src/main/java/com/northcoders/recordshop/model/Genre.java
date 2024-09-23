package com.northcoders.recordshop.model;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum Genre {
    ROCK,
    HIPHOP,
    TRAP,
    CLASSICAL,
    BAROQUE,
    ROMANTIC,
    NEOCLASSICAL,
    POSTIMPRESSIONIST,
    JAZZ,
    POP,
    FUNK,
    RNB,
    FOLK,
    ELECTRONIC,
    AMBIENT,
    SOUNDTRACK,
    WORLD,
    SOUL,
    HOUSE,
    DNB,
    MOTOWN;

    private static final Map<String, Genre> stringGenreMap = new HashMap<String, Genre>();

    static {
        for (Genre g : EnumSet.allOf(Genre.class)) {
            stringGenreMap.put(g.name(), g);
        }
    }

    public static Genre forInput(String input){
        return stringGenreMap.get(input);
    }

}
