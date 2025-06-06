package com.example.lab20;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.loader.content.AsyncTaskLoader;

public class MyAsyncTask extends AsyncTask<Void, Integer, Void> {
    Activity contextCha;

    // Constructor nhận context từ MainActivity
    public MyAsyncTask(Activity context) {
        this.contextCha = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(contextCha, "onPreExecute!", Toast.LENGTH_LONG).show();
    }

    @Override
    protected Void doInBackground(Void... args) {
        for (int i = 0; i <= 100; i++) {
            // Nghỉ 100 mili giây để luồng chính cập nhật giao diện
            SystemClock.sleep(100);
            // Khi gọi hàm này thì onProgressUpdate sẽ thực thi
            publishProgress(i);
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        // TODO: Tự động tạo phương thức
        super.onProgressUpdate(values);

        // Thanh progressBar được lấy từ MainActivity
        ProgressBar paCha = contextCha.findViewById(R.id.progressBar1);

        // Vì publishProgress chỉ truyền 1 đối số, nên mảng values chỉ có 1 phần tử
        int giaTri = values[0];
        // Tăng giá trị của ProgressBar lên
        paCha.setProgress(giaTri);

        // Đồng thời hiển thị giá trị gia tăng lên TextView
        TextView txtMsg = (TextView) contextCha.findViewById(R.id.textView);
        txtMsg.setText(giaTri + "%");
    }

    // Hàm được gọi khi tiến trình thực hiện xong
    @Override
    protected void onPostExecute(Void result) {
        // TODO: Tự động tạo phương thức
        super.onPostExecute(result);
        Toast.makeText(contextCha, "Update xong rồi!", Toast.LENGTH_LONG).show();
    }
}
