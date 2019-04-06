package com.example.myapplication_jsonlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.myapplication_jsonlist.Modal.Student;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Student> studentsArrayList;
    private ListView lststuduent;
    private ArrayList<String> iname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        processJSON();
        lststuduent=findViewById(R.id.lst1);
        iname = new ArrayList<>();
        for (Student str : studentsArrayList) {
            iname.add(str.getSname());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, iname );
        lststuduent.setAdapter(adapter);

    }

    public String loadJSONFromAsset() {
        String json;
        try {
            InputStream is = getAssets().open("studentdetails.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
    private void processJSON() {
        String js = loadJSONFromAsset();
        if (js != null) {
            // Log.d("json",js);
            try {
                JSONArray mJSONArray = new JSONArray(js);
                studentsArrayList = new ArrayList<>();
                for (int i = 0; i < mJSONArray.length(); i++) {

                    JSONObject mJSONObj = mJSONArray.getJSONObject(i);
                    //  Log.d("mjson", mJSONObj.toString());
                    if (mJSONObj.has("sid")) {
                        String id = mJSONObj.getString("sid");
                        String sname = mJSONObj.getString("sname");
                        String gender = mJSONObj.getString("gender");
                        //   Log.d("mjson_ID", String.valueOf(id));
                        //   Log.d("mjson_name", String.valueOf(sname));
                        //   Log.d("mjson_gender", String.valueOf(gender));
                        studentsArrayList.add(new Student(id,sname,gender));
                        Log.d("mjson_ID", String.valueOf(id));

                    }






                }

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }
}
