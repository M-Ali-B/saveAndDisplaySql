package com.github.ali.saveanddisplaysql;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
//SQLiteDatabase mSQLiteDatabase;
    DatabaseHelper mDatabaseHelper;
    private Button mAddBtn, mViewBtn;
    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAddBtn = findViewById(R.id.addbutton);
        mViewBtn = findViewById(R.id.viewbutton);
        mEditText = findViewById(R.id.editText);
        mDatabaseHelper = new DatabaseHelper(MainActivity.this);
//mDatabaseHelper.onCreate(mSQLiteDatabase);
        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String entry = mEditText.getText().toString();

                if (entry.length() != 0) {
                    addData(entry);
                    mEditText.setText("");
                } else {
                    Toast.makeText(MainActivity.this, "Please write something", Toast.LENGTH_SHORT).show();
                }


            }
        });

        mViewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,DataListActivity.class);
            startActivity(intent);
            }
        });
    }


    public void addData(String entry) {

        boolean input = mDatabaseHelper.addData(entry);
        if (input) {
            Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();

        }
    }
}
