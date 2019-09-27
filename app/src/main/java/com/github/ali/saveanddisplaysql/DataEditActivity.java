package com.github.ali.saveanddisplaysql;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DataEditActivity extends AppCompatActivity {

    Button saveBtn, delBtn;
    EditText resultText;

    DatabaseHelper mDatabaseHelper;
    EditText result;
    private String selectedName;
    private int selectedID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_edit);

        saveBtn = findViewById(R.id.saveButton);
        delBtn = findViewById(R.id.delButton);
        result = findViewById(R.id.resultName);
        mDatabaseHelper = new DatabaseHelper(this);

        Intent recievedIntent = getIntent();

        selectedID = recievedIntent.getIntExtra("id", -1);

        selectedName = recievedIntent.getStringExtra("name");

        result.setText(selectedName);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = result.getText().toString();

                if (!item.equals("")) {

                    mDatabaseHelper.updateName(item,selectedID,selectedName);
                    Intent updateIntent = new Intent(DataEditActivity.this,MainActivity.class);
                    startActivity(updateIntent);



                } else {
                    Toast.makeText(DataEditActivity.this, "You must enter a name", Toast.LENGTH_SHORT).show();

                }
            }
        });

        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabaseHelper.deleteName(selectedID,selectedName);
                Intent updateIntent = new Intent(DataEditActivity.this,MainActivity.class);
                startActivity(updateIntent);
            }
        });



    }
}
