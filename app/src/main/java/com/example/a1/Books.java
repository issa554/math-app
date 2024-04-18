package com.example.a1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class Books extends AppCompatActivity {
    private ListView lsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        lsList = findViewById(R.id.lsList);
        List<site> ls = new ArrayList<site>();
        ls.add(new site("Math Work Book", "https://www.edudel.nic.in/welcome_folder/CRM_class_1_2_dt_22052018/math_class_1_eng.pdf"));
        ls.add(new site("Childrens maths", "https://www.arvindguptatoys.com/arvindgupta/childrensmaths.pdf"));
        site[] arr = ls.toArray(new site[ls.size()]);
        ArrayAdapter<site> adapter = new ArrayAdapter<site>(Books.this,
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