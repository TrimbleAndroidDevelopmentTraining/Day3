package com.trimble.storageoptionsapp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.trimble.storageoptionsapp.R;

public class SharedPrefsActivity extends Activity {

    // Shared Preferences are Stored in
    // /data/data/YOUR_PACKAGE_NAME/shared_prefs/YOUR_PREFS_NAME.xml

    public static final String PREFS_NAME = "PrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_prefs);
    }

    public void save_data_button_onClick(View view) {
        // Save Prefs
        // Possible Modes = MODE_PRIVATE, (MODE_WORLD_READABLE, MODE_WORLD_WRITEABLE = deprecated)
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();

        String data = ((EditText)findViewById(R.id.edit_text)).getText().toString();
        editor.putString("data", data);
        editor.commit();

        ((TextView)findViewById(R.id.text_view)).setText("Data Saved");
    }

    public void load_data_button_onClick(View view) {
        // Load Prefs
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        String data = settings.getString("data", "Pref Not Found!");
        ((TextView)findViewById(R.id.text_view)).setText(data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.shared_prefs, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
