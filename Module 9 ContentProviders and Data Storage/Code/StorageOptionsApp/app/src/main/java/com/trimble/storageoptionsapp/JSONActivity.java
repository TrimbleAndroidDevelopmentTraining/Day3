package com.trimble.storageoptionsapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.trimble.storageoptionsapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class JSONActivity extends Activity {

    public static final String FILENAME = "InternalStorageFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);
    }

    public class Person {
        private String firstName;
        private String lastName;
        private String data;

        public Person(String firstName, String lastName, String phoneNumber) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.data = phoneNumber;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getData() {
            return data;
        }

        public void setData(String phoneNumber) {
            this.data = data;
        }
    }

     public String PersonToJSon(Person person) {
        try {
            // Here we convert Java Object to JSON
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("firstName", person.getFirstName());
            jsonObj.put("lastName", person.getLastName());
            jsonObj.put("data", person.getData());

            return jsonObj.toString();
        }
        catch(JSONException ex) {
            ((TextView)findViewById(R.id.text_view)).setText("Houston, We Have a Problem :(");
        }

        return null;
    }

    public void save_data_button_onClick(View view) {
        String data = ((EditText)findViewById(R.id.edit_text)).getText().toString();
        Person person = new Person("Fred", "Flintstone", data);

        try {
            FileOutputStream fos = openFileOutput(FILENAME, MODE_PRIVATE);
            fos.write(PersonToJSon(person).getBytes());
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
        getMenuInflater().inflate(R.menu.json, menu);
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
