package com.example.a1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Lessons extends AppCompatActivity {
    private ListView lsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessons);
        lsList = findViewById(R.id.lsList);
        List<site> ls = new ArrayList<site>();
        ls.add(new site("Basic Addtion", "https://youtu.be/tp9n4kMTuQo?si=HZAfR0xldGvf0y6i"));
        ls.add(new site("Basic subtraction", "https://youtu.be/ug0gs8kLE48?si=gqKqxdBek_6qpNVL"));
        ls.add(new site("Basic multiplication", "https://youtu.be/CFDCG1b4ahk?si=xErultz2WiTkF1R2"));
        ls.add(new site("Basic division", "https://youtu.be/rGMecZ_aERo?si=nH_9-m4TRXLk0TZ2"));

        site[] arr = ls.toArray(new site[ls.size()]);
        ArrayAdapter<site> adapter = new ArrayAdapter<site>(Lessons.this,
                android.R.layout.simple_list_item_1, arr);
        lsList.setAdapter(adapter);
        lsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String s= ls.get(i).getUrl();
                Uri url = Uri.parse(s);
                startActivity(new Intent(Intent.ACTION_VIEW,url));

            }
        });

    }
}