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
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {

    private EditText editTextId, editTextName, editTextAddress;
    private Button btnSave, btnGet, btnUpdate, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Allow network operations on the main thread (not recommended for production)
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        editTextId = findViewById(R.id.editTextText);
        editTextName = findViewById(R.id.editTextText2);
        editTextAddress = findViewById(R.id.editTextText3);
        btnSave = findViewById(R.id.btn_save);
        btnGet = findViewById(R.id.btn_get);
        btnUpdate = findViewById(R.id.btn_update);
        btnDelete = findViewById(R.id.btn_delete);

        btnSave.setOnClickListener(new View.OnClickListener() {
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

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = editTextId.getText().toString();

                try {
                    Connection connection = DatabaseHelper.getConnection();
                    if (connection != null) {
                        String sql = "SELECT name, address FROM users WHERE id = ?";
                        PreparedStatement statement = connection.prepareStatement(sql);
                        statement.setString(1, id);
                        ResultSet resultSet = statement.executeQuery();
                        if (resultSet.next()) {
                            String name = resultSet.getString("name");
                            String address = resultSet.getString("address");
                            editTextName.setText(name);
                            editTextAddress.setText(address);
                        } else {
                            Log.d("DatabaseQuery", "No record found with the given ID");
                        }
                    } else {
                        Log.d("DatabaseConnection", "Connection failed!");
                    }
                } catch (SQLException e) {
                    Log.e("DatabaseConnection", "Error connecting to database", e);
                }
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = editTextId.getText().toString();
                String name = editTextName.getText().toString();
                String address = editTextAddress.getText().toString();

                try {
                    Connection connection = DatabaseHelper.getConnection();
                    if (connection != null) {
                        String sql = "UPDATE users SET name = ?, address = ? WHERE id = ?";
                        PreparedStatement statement = connection.prepareStatement(sql);
                        statement.setString(1, name);
                        statement.setString(2, address);
                        statement.setString(3, id);
                        int rowsUpdated = statement.executeUpdate();
                        if (rowsUpdated > 0) {
                            Log.d("DatabaseUpdate", "A row was updated successfully!");
                        }
                    } else {
                        Log.d("DatabaseConnection", "Connection failed!");
                    }
                } catch (SQLException e) {
                    Log.e("DatabaseConnection", "Error connecting to database", e);
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = editTextId.getText().toString();

                try {
                    Connection connection = DatabaseHelper.getConnection();
                    if (connection != null) {
                        String sql = "DELETE FROM users WHERE id = ?";
                        PreparedStatement statement = connection.prepareStatement(sql);
                        statement.setString(1, id);
                        int rowsDeleted = statement.executeUpdate();
                        if (rowsDeleted > 0) {
                            Log.d("DatabaseDelete", "A row was deleted successfully!");
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