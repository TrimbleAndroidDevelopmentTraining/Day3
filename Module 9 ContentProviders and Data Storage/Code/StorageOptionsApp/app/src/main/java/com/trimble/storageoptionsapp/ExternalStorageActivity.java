package com.trimble.storageoptionsapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.trimble.storageoptionsapp.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ExternalStorageActivity extends Activity {

    // Internal Storage is Stored in
    // /mnt/sdcard/...

    public static final String FILENAME = "ExternalStorageFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_storage);
    }

    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    public File getDirectory() {
        File root = android.os.Environment.getExternalStorageDirectory();
        File dir = new File (root.getAbsolutePath() + "/Files");
        dir.mkdir();
        return dir;
    }

    public void save_data_button_onClick(View view) {
        // Don't forget permissions in the manifest file

        if (!isExternalStorageWritable()) {
            ((TextView)findViewById(R.id.text_view)).setText("No Writeable External Storage...");
            return;
        }

        String data = ((EditText)findViewById(R.id.edit_text)).getText().toString();
        File file = new File(getDirectory(), FILENAME);


        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(data.getBytes());
            fos.close();

            ((TextView)findViewById(R.id.text_view)).setText("Data Saved");
        }
        catch (Exception ex) {
            ((TextView)findViewById(R.id.text_view)).setText("Houston, We Have a Problem :(");
        }
    }

    public void load_data_button_onClick(View view) {
        if (!isExternalStorageReadable()) {
            ((TextView)findViewById(R.id.text_view)).setText("No Readable External Storage...");
            return;
        }

        File file = new File(getDirectory(), FILENAME);

        try {
            FileInputStream fis = new FileInputStream(file);
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
        getMenuInflater().inflate(R.menu.external_storage, menu);
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
