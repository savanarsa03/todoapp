package com.example.todoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class EditorActivity extends AppCompatActivity {

    private EditText editName, editEmail;
    private Button btnSave;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ProgressDialog progressDialog;
    private String id = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        editName = findViewById(R.id.name);
        editEmail = findViewById(R.id.email);
        btnSave = findViewById(R.id.btn_save);

        progressDialog = new ProgressDialog(EditorActivity.this);
        progressDialog.setTitle("Loading..");
        progressDialog.setMessage("Menyimpan..");

        btnSave.setOnClickListener(v -> {
            if (editName.getText().length()>0 && editEmail.getText().length()>0){
                saveData(editName.getText().toString(),editEmail.getText().toString());
            } else{
                Toast.makeText(getApplicationContext(),"Silahkan isi sesuai data", Toast.LENGTH_LONG).show();
            }
        });
    }
    private void saveData(String name, String email){
        Map<String,Object> user = new HashMap<>();
        user.put("name", name);
        user.put("email", email);

        progressDialog.show();
        if (id!=null){
            db.collection("users").document(id)
                    .set(user)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(getApplicationContext(),"Berhasil!", Toast.LENGTH_SHORT).show();
                                finish();
                            }else{
                                Toast.makeText(getApplicationContext(), "Gagal", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
    }
}