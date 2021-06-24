package com.example.memorableplaces;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;




public class MainActivity extends AppCompatActivity
{

    static ArrayList<String> memPlaces = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mapButton = findViewById(R.id.mapButton);
        ListView listView = findViewById(R.id.lister);
        Intent intentt = getIntent();
        String favplace = intentt.getStringExtra("Clicked location");

        //memPlaces.add(favplace);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, memPlaces);
        listView.setAdapter(arrayAdapter);

    }




    public void tomap(View view)
    {
        Intent intent = new Intent(getApplicationContext(),Mapper.class);
        startActivity(intent);
    }
}