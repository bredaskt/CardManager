package com.example.bbreda.cardmanager.data.repository;

import android.content.Context;
import android.content.SharedPreferences;


import static android.content.Context.MODE_PRIVATE;

public final class SharedPreferenceManager {

    private static final String CARD_MANAGER_PREFERENCES = "com.example.bbreda.cardmanager";

    public static SharedPreferences getInstance (Context context) {
        return context.getSharedPreferences(CARD_MANAGER_PREFERENCES, MODE_PRIVATE);
    }

}
