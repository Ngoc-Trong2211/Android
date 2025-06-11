package com.example.lab23;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lv1;
    private ArrayList<List> mylist;
    private MyArrayAdapter myadapter;
    private String nodeName;
    private String title = "";
    private String link = "";
    private String description = "";
    private String uriStr = "";
    private Bitmap mIcon_val = null;
    private String URL = "https://vnexpress.net/khoa-hoc-cong-nghe";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lv1 = findViewById(R.id.lv1);
        mylist = new ArrayList<List>();
        myadapter = new MyArrayAdapter(this, R.layout.layout_listview, mylist);
        lv1.setAdapter(myadapter);

        // Thực hiện tải dữ liệu nền
        LoadExampleTask task = new LoadExampleTask();
        task.execute();
    }

    class LoadExampleTask extends AsyncTask<Void, Void, ArrayList<List>> {
        @Override
        protected ArrayList<List> doInBackground(Void... voids) {
            mylist = new ArrayList<List>();
            try {
                // Tạo đối tượng parser để xử lý file XML
                XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
                XMLParser xmlParser = new XMLParser();
                // Lấy dữ liệu XML từ URL
                String xml = xmlParser.getXmlFromUrl(URL);
                parser.setInput(new StringReader(xml));

                // Bắt đầu phân tích XML
                int eventType = parser.getEventType();
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    switch (eventType) {
                        case XmlPullParser.START_DOCUMENT:
                            break;
                        case XmlPullParser.END_DOCUMENT:
                            break;
                        case XmlPullParser.START_TAG:
                            nodeName = parser.getName();
                            if (nodeName.equals("title")) {
                                title = parser.nextText();
                            } else if (nodeName.equals("link")) {
                                link = parser.nextText();
                            } else if (nodeName.equals("description")) {
                                description = parser.nextText();
                                uriStr = description.substring(description.indexOf("src=") + 5,
                                        description.indexOf("\"", description.indexOf("src=") + 5));
                            }
                            break;
                        case XmlPullParser.END_TAG:
                            if (parser.getName().equals("item")) {
                                mylist.add(new List(mIcon_val, title, description, link));
                            }
                            break;
                    }
                    eventType = parser.next();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return mylist;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            myadapter.clear();
        }

        @Override
        protected void onPostExecute(ArrayList<List> result) {
            super.onPostExecute(result);
            myadapter.clear();
            myadapter.addAll(result);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
}