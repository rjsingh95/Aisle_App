package in.example.aisle.Network;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefUtils {
    /**
     * Storing API Key in shared preferences to
     * add it in header part of every retrofit request
     */

    private static String KEY_AUTO_REFRESH;

    public PrefUtils() {
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences("APP_PREF", Context.MODE_PRIVATE);
    }

    public static void storeApiKey(Context context, String apiKey, String name) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(name, apiKey);
        editor.commit();
    }

    public static String getApiKey(Context context, String name) {
        return getSharedPreferences(context).getString(name, "");
    }

    public static boolean removeApiKey(Context context, String name) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        return editor.remove(name).commit();
    }//"API_KEY"

}
