package com.trimble.storageoptionsapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class WelcomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void shared_prefs_button_onClick(View view) {
        Intent i = new Intent(this, SharedPrefsActivity.class);
        startActivity(i);
    }

    public void internal_storage_button_onClick(View view) {
        Intent i = new Intent(this, InternalStorageActivity.class);
        startActivity(i);
    }

    public void external_storage_button_onClick(View view) {
        Intent i = new Intent(this, ExternalStorageActivity.class);
        startActivity(i);
    }

    public void sqlite_button_onClick(View view) {
        Intent i = new Intent(this, SQLiteActivity.class);
        startActivity(i);
    }

    public void json_button_onClick(View view) {
        Intent i = new Intent(this, JSONActivity.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.welcome, menu);
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
