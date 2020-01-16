package com.example.javaalgorithmsrecipes;

import android.util.Log;

public class Constants {
    private static Constants obj;

    private Constants() {
        // restrict instantiation
    }

    //fake getInstance method
    public static Constants getInstance() {
        if (obj==null)
//            obj = new Constants();
            if (BuildConfig.DEBUG) {
                Log.e("QUIHABITERR", "HAHA FOOL, YOU CANNOT INSTANTIATE CONSTANTS CLASS LOL");
            }
        return null;
    }

    public interface SharedPreferences{
        String SAVINGS_FINAL = "SAVINGS_FINAL";
        String CHALLENGES_STRING = "CHALLENGES_FINAL";

    }
    public interface Stats{
        String LIFE = "Life: ";
        String INTELLIGENCE = "Intelligence: ";
        String CRIMES = "Crimes: ";
    }
    public interface Music{
        int MAX_VOLUME = 40;

    }
}
