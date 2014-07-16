package com.trimble.storageoptionsapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.trimble.storageoptionsapp.R;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class InternalStorageActivity extends Activity {

    // Internal Storage is Stored in
    // /data/data/YOUR_PACKAGE_NAME/files/FILENAME

    public static final String FILENAME = "InternalStorageFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_storage);
    }

    public void save_data_button_onClick(View view) {
        String data = ((EditText)findViewById(R.id.edit_text)).getText().toString();

        try {
            FileOutputStream fos = openFileOutput(FILENAME, MODE_PRIVATE);
            fos.write(data.getBytes());
            fos.close();

            ((TextView)findViewById(R.id.text_view)).setText("Data Saved");
        }
        catch (Exception ex) {
            ((TextView)findViewById(R.id.text_view)).setText("Houston, We Have a Problem :(");
        }
    }

    public void load_data_button_onClick(View view) {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            StringBuffer data = new StringBuffer("");
            byte[] buffer = new byte[1024];
            int n;

            while ((n = fis.read(buffer)) != -1)
            {
                data.append(new String(buffer, 0, n));
            }
            fis.close();
            ((TextView)findViewById(R.id.text_view)).setText(data);
        }
        catch (Exception ex) {
            ((TextView)findViewById(R.id.text_view)).setText("Houston, We Have a Problem :(");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.internal_storage, menu);
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
