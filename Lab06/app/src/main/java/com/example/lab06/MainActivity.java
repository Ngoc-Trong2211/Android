package com.example.lab06;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.window.OnBackInvokedDispatcher;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText editFullName, editId, editAddtional;
    Button button;
    RadioGroup rdGroup;
    CheckBox cbReadBook, cbReadNewspaper, cbReadCoding;

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

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                AlertDialog.Builder dialogBack = new AlertDialog.Builder(MainActivity.this);
                dialogBack.setTitle("Question");
                dialogBack.setMessage("Are you sure you want to exit");
                dialogBack.setCancelable(false);
                dialogBack.setIcon(R.drawable.img);
                dialogBack.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                dialogBack.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                         dialog.cancel();
                    }
                });
                dialogBack.create().show();
            }
        };
        getOnBackPressedDispatcher().addCallback(MainActivity.this, callback);

        editFullName = findViewById(R.id.editFullName);
        editId = findViewById(R.id.editId);
        editAddtional = findViewById(R.id.editAdditional);
        button = findViewById(R.id.button);
        cbReadBook = findViewById(R.id.cbReadBook);
        cbReadNewspaper = findViewById(R.id.cbReadNewspaper);
        cbReadCoding = findViewById(R.id.cbReadCoding);
        rdGroup = findViewById(R.id.rdGroup);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullName = editFullName.getText().toString().trim();
                String identification = editId.getText().toString().trim();
                // Kiem tra dieu kien ho ten
                if (fullName.isEmpty() || fullName.length() < 3){
                    Toast.makeText(MainActivity.this, "Ho Ten phai co it nhat 3 ky tu va khong duoc de trong!", Toast.LENGTH_SHORT).show();
                    editFullName.requestFocus();
                    editFullName.selectAll();
                    return;
                }
                // Kiem tra dieu kien cmnd
                if (identification.length()!=9 || !checkIsAlpha(identification)){
                    Toast.makeText(MainActivity.this, "CMND phai co 9 chu so!", Toast.LENGTH_SHORT).show();
                    editId.requestFocus();
                    editId.selectAll();
                    return;
                }
                // lay thong tin radioButton
                int idSelect = rdGroup.getCheckedRadioButtonId();
                RadioButton radSelect = findViewById(idSelect);
                String degree = radSelect.getText().toString();
                // lay thong tin checkBox
                String hobby = "";
                if (cbReadBook.isChecked()) hobby += cbReadBook.getText().toString() + "-";
                if (cbReadNewspaper.isChecked()) hobby += cbReadNewspaper.getText().toString() + "-";
                if (cbReadCoding.isChecked()) hobby += cbReadCoding.getText().toString();
                // Lay thong tin bo sung
                String additional = editAddtional.getText().toString();
                // Inflate layout
                View dialogView = getLayoutInflater().inflate(R.layout.layout_dialog, null);
                // Gán dữ liệu vào layout
                TextView tvContent = dialogView.findViewById(R.id.tvContent);
                Button btnClose = dialogView.findViewById(R.id.btnClose);
                // in ra dialog
                String personInf = fullName + "\n" + identification + "\n" + degree + "\n" + hobby + "\n" +
                        "-------------------------------------------\n" + "Thong tin bo sung:\n" +
                        additional + "\n-------------------------------------------";
                tvContent.setText(personInf);
                // Tao dialog
                AlertDialog.Builder myDialog = new AlertDialog.Builder(MainActivity.this);
                myDialog.setView(dialogView);
                AlertDialog dialog = myDialog.create();
                btnClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }

    public static boolean checkIsAlpha(String iden){
        for (char c : iden.toCharArray()){
            if (Character.isLetter(c)) return false;
        }
        return true;
    }
}