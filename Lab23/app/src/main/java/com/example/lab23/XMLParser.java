package com.example.lab23;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class XMLParser {
    public String getXmlFromUrl(String url) {
        StringBuilder xml = new StringBuilder();
        HttpURLConnection connection = null;

        try {
            // Tạo kết nối đến URL
            URL urlObject = new URL(url);
            connection = (HttpURLConnection) urlObject.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000); // Thời gian chờ 5 giây
            connection.setReadTimeout(5000);    // Thời gian đọc 5 giây

            // Đọc dữ liệu từ kết nối
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                xml.append(line).append("\n");
            }
            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Lỗi khi tải XML: " + e.getMessage());
        } finally {
            if (connection != null) {
                connection.disconnect(); // Đóng kết nối
            }
        }

        return xml.toString();
    }
}