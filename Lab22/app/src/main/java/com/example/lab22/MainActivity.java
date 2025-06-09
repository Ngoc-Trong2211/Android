package com.example.lab22;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

public class MainActivity extends Activity {

    Button btnParse;
    ListView lv1;
    ArrayAdapter<String> myAdapter;
    ArrayList<String> myList;
    String URL = "https://api.androidhive.info/pizza/?format=xml";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnParse = findViewById(R.id.btnparse);
        lv1 = findViewById(R.id.lv1);

        myList = new ArrayList<>();
        myAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, myList);
        lv1.setAdapter(myAdapter);

        btnParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LoadExampleTask().execute();
            }
        });
    }

    class LoadExampleTask extends AsyncTask<Void, Void, ArrayList<String>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            myAdapter.clear();
        }

        @Override
        protected ArrayList<String> doInBackground(Void... voids) {
            ArrayList<String> list = new ArrayList<>();

            try {
                // Tạo đối tượng Parser để chứa dữ liệu file XML
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                XmlPullParser parser = factory.newPullParser();

                // Tạo mới và gọi đến phương thức getXmlFromUrl
                XMLParser myParser = new XMLParser();
                String xml = myParser.getXmlFromUrl(URL); // lấy dữ liệu XML từ URL

                // Đặt dữ liệu XML vào parser
                parser.setInput(new StringReader(xml));

                int eventType = parser.getEventType();
                String nodeName;
                String dataShow = "";

                while (eventType != XmlPullParser.END_DOCUMENT) {
                    eventType = parser.next();
                    switch (eventType) {
                        case XmlPullParser.START_TAG:
                            nodeName = parser.getName();
                            if (nodeName.equals("id")) {
                                dataShow = parser.nextText() + " - ";
                            } else if (nodeName.equals("name")) {
                                dataShow += parser.nextText();
                            }
                            break;
                        case XmlPullParser.END_TAG:
                            nodeName = parser.getName();
                            if (nodeName.equals("item")) {
                                list.add(dataShow);
                                dataShow = "";
                            }
                            break;
                    }
                }

            } catch (XmlPullParserException | IOException e) {
                e.printStackTrace();
            }

            return list;
        }

        @Override
        protected void onPostExecute(ArrayList<String> result) {
            super.onPostExecute(result);
            myAdapter.clear();
            myAdapter.addAll(result);
        }
    }
}