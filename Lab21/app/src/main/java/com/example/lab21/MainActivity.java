package com.example.lab21;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // Khai báo danh sách để hiển thị dữ liệu
    ListView lv;
    ArrayList<String> mylist;
    ArrayAdapter<String> myadapter;
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

        lv = findViewById(R.id.lv);

        // Khởi tạo danh sách và adapter
        mylist = new ArrayList<>();
        myadapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mylist);
        lv.setAdapter(myadapter);

        // Thiết lập sự kiện click cho từng mục trong ListView
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                parseJSON();
            }

            private void parseJSON() {
                String json = null;
                try {
                    // Đọc dữ liệu JSON từ tệp assets
                    InputStream inputStream = getAssets().open("Untitled-1.json");
                    int size = inputStream.available();
                    byte[] buffer = new byte[size];
                    inputStream.read(buffer);
                    inputStream.close();

                    // Chuyển dữ liệu thành chuỗi JSON
                    json = new String(buffer, "UTF-8");
                    JSONObject reader = new JSONObject(json);

                    // Lấy mảng JSON có tên "SamPham"
                    JSONArray myarray = reader.getJSONArray("Samphams");

                    // Duyệt qua từng phần tử trong mảng JSON
                    for (int i = 0; i < myarray.length(); i++) {
                        JSONObject myobj = myarray.getJSONObject(i);
                        // Lấy giá trị từ các trường trong JSON
                        mylist.add("Tên SP: " + myobj.getString("TenSP") + "\n" +
                                "Đơn giá: " + myobj.getString("DonGia") + "\n" +
                                "Số lượng: " + myobj.getString("ThanhTien"));
                        // Cập nhật adapter để hiển thị dữ liệu
                        myadapter.notifyDataSetChanged();
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
        });
    }
}