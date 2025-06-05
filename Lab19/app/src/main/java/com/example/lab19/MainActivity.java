package com.example.lab19;

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

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity {

    private Button btnparse;
    private ListView listView;
    private ArrayList<String> employeeList;
    private ArrayAdapter<String> adapter;

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

        // Khởi tạo các thành phần giao diện
        btnparse = findViewById(R.id.btnparse);
        listView = findViewById(R.id.lv);

        // Khởi tạo danh sách và adapter
        employeeList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, employeeList);
        listView.setAdapter(adapter);

        // Xử lý sự kiện khi nhấn nút Parse
        btnparse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parseXML();
            }
        });
    }

    // Phương thức phân tích file XML và hiển thị dữ liệu lên ListView
    private void parseXML() {
        try {
            // Mở file XML từ thư mục raw
            InputStream inputStream = getResources().openRawResource(R.raw.employee);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputStream);

            // Chuẩn hóa tài liệu XML
            document.getDocumentElement().normalize();

            // Lấy danh sách các phần tử <employee>
            NodeList nodeList = document.getElementsByTagName("employee");
            employeeList.clear(); // Xóa danh sách cũ trước khi thêm mới

            // Duyệt qua từng phần tử <employee>
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                String id = element.getAttribute("id");

                // Lấy giá trị của các thẻ con
                String name = element.getElementsByTagName("name").item(0).getTextContent();
                String phone = element.getElementsByTagName("phone").item(0).getTextContent();

                // Kiểm tra giá trị và thêm vào danh sách
                if (id.equals("1")) {
                    employeeList.add("ID: " + id + " - Name: " + name + " - Phone: " + phone);
                } else if (name.equals("manager")) {
                    employeeList.add("Manager - ID: " + id + " - Phone: " + phone);
                } else if (phone.equals("0987654321")) {
                    employeeList.add("Special Phone - ID: " + id + " - Name: " + name);
                }
            }

            // Cập nhật giao diện
            adapter.notifyDataSetChanged();

        } catch (Exception e) {
            e.printStackTrace();
            try {
                // Xử lý ngoại lệ khác nếu cần
                throw new Exception("Error parsing XML: " + e.getMessage());
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}