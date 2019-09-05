/**
 * Deze class is gekopieerd van http://brainwashinc.com/2017/08/25/androidjava-sharedpreferences-anywhere-app/
 * om het mogelijk te maken om te state van de NotesList op te slaan in de SharedPreferences.
 */

package nl.macemuilman.privatenotes;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class Constants {

    static Constants _instance;

    Context context;
    SharedPreferences sharedPref;
    SharedPreferences.Editor sharedPrefEditor;

    public static Constants instance(Context context) {
        if (_instance == null) {
            _instance = new Constants();
            _instance.configSessionUtils(context);
        }
        return _instance;
    }

    public static Constants instance() {
        return _instance;
    }

    public void configSessionUtils(Context context) {
        this.context = context;
        sharedPref = context.getSharedPreferences("AppPreferences", Activity.MODE_PRIVATE);
        sharedPrefEditor = sharedPref.edit();
    }

    public void storeValueString(String key, String value) {
        sharedPrefEditor.putString(key, value);
        sharedPrefEditor.commit();
    }

    public String fetchValueString(String key) {
        return sharedPref.getString(key, null);
    }
}
