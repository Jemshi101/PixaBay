package com.thinkpalm.pixabay.sharedpreference;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.thinkpalm.pixabay.core.App;

import static android.content.Context.MODE_PRIVATE;


/**
 * Created  on 08-03-2017.
 */

public class SharedPrefHandler {

    private static SharedPreferences preferences;
    private static String SHARED_PREF_NAME = "com.thinkpalm.pixabay";
    private static SharedPrefHandler sharedPrefHandler;

    public static SharedPrefHandler getInstance(Context context) {
        if (preferences == null) {
            preferences = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        }
        if (sharedPrefHandler == null)
            sharedPrefHandler = new SharedPrefHandler();
        return sharedPrefHandler;
    }

    public static SharedPrefHandler getInstance() {
        if (preferences == null) {
            preferences = App.getInstance().getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        }
        if (sharedPrefHandler == null)
            sharedPrefHandler = new SharedPrefHandler();
        return sharedPrefHandler;
    }

    @SuppressLint("ApplySharedPref")
    public void saveString(String key, String value) {
        try {
            preferences.edit().putString(key, value).commit();
        } catch (Exception ignored) {
        }
    }

    @SuppressLint("ApplySharedPref")
    public void saveInt(String key, int value) {
        try {
            preferences.edit().putInt(key, value).commit();
        } catch (Exception ignored) {
        }
    }

    @SuppressLint("ApplySharedPref")
    public void saveBoolean(String key, Boolean value) {
        try {
            preferences.edit().putBoolean(key, value).commit();
        } catch (Exception ignored) {
        }
    }

    public String getString(String key) {
        try {
            return preferences.getString(key, "");
        } catch (Exception ignored) {
        }
        return "";
    }

    public String getString(String key, String defValue) {
        try {
            return preferences.getString(key, defValue);
        } catch (Exception ignored) {
        }
        return defValue;
    }

    public float getFloat(String key) {
        try {
            return preferences.getFloat(key, 0f);
        } catch (Exception e) {

        }
        return 0f;
    }

    public Boolean getBoolean(String key) {
        try {
            return preferences.getBoolean(key, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean getBoolean(String key, boolean defValue) {
        try {
            return preferences.getBoolean(key, defValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defValue;
    }

    public Integer getInt(String key) {
        try {
            return preferences.getInt(key, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void delete(String key) {
        try {
            if (preferences.contains(key)) {
                preferences.edit().remove(key).commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save(String key, Object value) {
        try {
            SharedPreferences.Editor editor = preferences.edit();
            if (value instanceof Boolean) {
                editor.putBoolean(key, (Boolean) value);
            } else if (value instanceof Integer) {
                editor.putInt(key, (Integer) value);
            } else if (value instanceof Float) {
                editor.putFloat(key, (Float) value);
            } else if (value instanceof Long) {
                editor.putLong(key, (Long) value);
            } else if (value instanceof String) {
                editor.putString(key, (String) value);
            } else if (value instanceof Enum) {
                editor.putString(key, value.toString());
            } else if (value != null) {
                throw new RuntimeException("Attempting to save non-supported preference");
            }

            editor.commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    public int getInt(String key, int defaultValue) {
        try {
            return preferences.getInt(key, defaultValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
