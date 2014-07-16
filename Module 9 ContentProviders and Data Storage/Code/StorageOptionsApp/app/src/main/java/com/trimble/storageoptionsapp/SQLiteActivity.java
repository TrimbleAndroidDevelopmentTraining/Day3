package com.trimble.storageoptionsapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.trimble.storageoptionsapp.R;

public class SQLiteActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
    }

    public void save_data_button_onClick(View view) {
        SQLiteHelper db = new SQLiteHelper(this);

        String data = ((EditText)findViewById(R.id.edit_text)).getText().toString();
        db.saveData(data);

        ((TextView)findViewById(R.id.text_view)).setText("Data Saved");
    }

    public void load_data_button_onClick(View view) {
        SQLiteHelper db = new SQLiteHelper(this);

        // Reading all contacts
        String data = db.loadData(1);
        ((TextView)findViewById(R.id.text_view)).setText(data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sqlite, menu);
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
