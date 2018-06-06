package com.example.dell.cabs_pos20.utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

public class SharedPref {
    private static final String APP_PREFS = "com.example.dell.cabs_pos20.utilities.SharedPref";

        private Context mContext;
        private SharedPreferences mSharedPreferences;
        private SharedPreferences.Editor mEditor;

        public SharedPref(Context mContext) {
            this.mContext = mContext;
        }

        /**
         * Save a string into shared preference
         *
         * @param key   The name of the preference to modify
         * @param value The new value for the preference
         */
        public void saveString(String key, String value) {
            mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
            mEditor = mSharedPreferences.edit();
            mEditor.putString(key, value);
            mEditor.apply();
        }

        /**
         * Save a int into shared preference
         *
         * @param key   The name of the preference to modify
         * @param value The new value for the preference
         */
        public void saveInt(String key, int value) {
            mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
            mEditor = mSharedPreferences.edit();
            mEditor.putInt(key, value);
            mEditor.apply();
        }

        /**
         * Save a boolean into shared preference
         *
         * @param key   The name of the preference to modify
         * @param value The new value for the preference
         */
        public void saveBoolean(String key, boolean value) {
            mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
            mEditor = mSharedPreferences.edit();
            mEditor.putBoolean(key, value);
            mEditor.apply();
        }

        /**
         * Retrieve a String value from the preferences.
         *
         * @param key The name of the preference to retrieve.
         * @return Returns the preference value if it exists, or null.
         * Throws ClassCastException if there is a preference with this name that is not a String.
         */
        @Nullable
        public String getString(String key) {
            mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
            return mSharedPreferences.getString(key, null);
        }

        /**
         * Retrieve a String value from the preferences.
         *
         * @param key          The name of the preference to retrieve.
         * @param defaultValue Value to return if this preference does not exist.
         * @return Returns the preference value if it exists, or defaultValue.
         * Throws ClassCastException if there is a preference with this name that is not a String.
         */
        @Nullable
        public String getString(String key, String defaultValue) {
            mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
            return mSharedPreferences.getString(key, defaultValue);
        }

        /**
         * Retrieve a int value from the preferences.
         *
         * @param key The name of the preference to retrieve.
         * @return Returns the preference value if it exists, or 0.
         * Throws ClassCastException if there is a preference with this name that is not a int.
         */
        public int getInt(String key) {
            mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
            return mSharedPreferences.getInt(key, 0);
        }

        /**
         * Retrieve a int value from the preferences.
         *
         * @param key          The name of the preference to retrieve.
         * @param defaultValue Value to return if this preference does not exist.
         * @return Returns the preference value if it exists, or defaultValue.
         * Throws ClassCastException if there is a preference with this name that is not a int.
         */
        public int getInt(String key, int defaultValue) {
            mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
            return mSharedPreferences.getInt(key, defaultValue);
        }

        /**
         * Retrieve a boolean value from the preferences.
         *
         * @param key The name of the preference to retrieve.
         * @return Returns the preference value if it exists, or false.
         * Throws ClassCastException if there is a preference with this name that is not a boolean.
         */
        public boolean getBoolean(String key) {
            mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
            return mSharedPreferences.getBoolean(key, false);
        }

        /**
         * Retrieve a boolean value from the preferences.
         *
         * @param key          The name of the preference to retrieve.
         * @param defaultValue Value to return if this preference does not exist.
         * @return Returns the preference value if it exists, or defaultValue.
         * Throws ClassCastException if there is a preference with this name that is not a boolean.
         */
        public boolean getBoolean(String key, boolean defaultValue) {
            mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
            return mSharedPreferences.getBoolean(key, defaultValue);
        }

        /**
         * Clears the shared preference file
         */
        public void clear() {
            mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
            mSharedPreferences.edit().clear().apply();
        }



    }





