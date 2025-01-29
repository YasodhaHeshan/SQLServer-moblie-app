package com.example.sqlserver;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {

    private EditText editTextId, editTextName, editTextAddress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         Button btn_save;

        // Allow network operations on the main thread (not recommended for production)
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        editTextId = findViewById(R.id.editTextText);
        editTextName = findViewById(R.id.editTextText2);
        editTextAddress = findViewById(R.id.editTextText3);
        btn_save = findViewById(R.id.btn_save);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = editTextId.getText().toString();
                String name = editTextName.getText().toString();
                String address = editTextAddress.getText().toString();

                try {
                    Connection connection = DatabaseHelper.getConnection();
                    if (connection != null) {
                        String sql = "INSERT INTO users (id, name, address) VALUES (?, ?, ?)";
                        PreparedStatement statement = connection.prepareStatement(sql);
                        statement.setString(1, id);
                        statement.setString(2, name);
                        statement.setString(3, address);
                        int rowsInserted = statement.executeUpdate();
                        if (rowsInserted > 0) {
                            Log.d("DatabaseInsert", "A new row was inserted successfully!");
                        }
                    } else {
                        Log.d("DatabaseConnection", "Connection failed!");
                    }
                } catch (SQLException e) {
                    Log.e("DatabaseConnection", "Error connecting to database", e);
                }
            }
        });
    }
}