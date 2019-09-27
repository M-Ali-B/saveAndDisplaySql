package com.github.ali.saveanddisplaysql;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class DataListActivity extends AppCompatActivity {

    DatabaseHelper mDatabaseHelper;
    ListView mListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datalist);

        mListView = findViewById(R.id.listView);
        mDatabaseHelper = new DatabaseHelper(this);
        populateListView();
    }

    private void populateListView() {
        Log.d("listView", "Displaying data ");
//get the data and append into a list
        final Cursor cursor = mDatabaseHelper.getData();
        ArrayList<String> arrayList = new ArrayList<>();


        while (cursor.moveToNext()) {


            //get the value from the database in column 1 (COL2) but technically column 1 as column numbering starts with 0
            //then add it to the ArrayList
            arrayList.add(cursor.getString(1));
            Log.d("cursor", "" + cursor.getCount());
        }


        // create a list adapter and set the adapter

        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = parent.getItemAtPosition(position).toString().trim();
                Log.d("name", "" + name);
                Cursor cursor1 = mDatabaseHelper.getItemID(name);

                int itemID = -1;

                while (cursor1.moveToNext()) {
                    itemID = cursor1.getInt(0);

                }

                if (itemID > -1) {
                    Log.d("id", "" + itemID);
                    Intent editIntent = new Intent(DataListActivity.this, DataEditActivity.class);
                    editIntent.putExtra("id", itemID);
                    editIntent.putExtra("name", name);
                    startActivity(editIntent);
                } else {
                    Toast.makeText(DataListActivity.this, "No ID found in the list", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
