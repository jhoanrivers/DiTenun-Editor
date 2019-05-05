package com.asksira.imagepickersheetdemo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.asksira.imagepickersheetdemo.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChooseBasicSadum extends AppCompatActivity {


    String[] listviewTitle = new String[]{
            "Sadum Bonggit 1", "Sadum Bonggit 2", "Sadum Fleksibel",
    };


    int[] listviewImage = new int[]{
            R.drawable.image0,R.drawable.image0,R.drawable.image0,
    };

    String[] listviewShortDescription = new String[]{
            "Bonggit tipe 1 ukuran 180x90cm", "Bonggit tipe 2 ukuran 180x90cm", "Ukuran dan motif dapat diatur sesuai keinginan",
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_basic_sadum);

        //Menampilkan back button pada toobar
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();

        for (int i = 0; i < 3; i++) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("listview_title", listviewTitle[i]);
            hm.put("listview_discription", listviewShortDescription[i]);
            hm.put("listview_image", Integer.toString(listviewImage[i]));
            aList.add(hm);
        }

        String[] from = {"listview_image", "listview_title", "listview_discription"};
        int[] to = {R.id.listview_image, R.id.listview_item_title, R.id.listview_item_short_description};

        SimpleAdapter simpleAdapter = new SimpleAdapter(getBaseContext(), aList, R.layout.activity_sadum_basic_listview, from, to);
        ListView androidListView = (ListView) findViewById(R.id.list_view);
        androidListView.setAdapter(simpleAdapter);


        androidListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        startActivity(new Intent(ChooseBasicSadum.this,Sadum1Activity.class));
                        finish();
                        break;
                    case 1:
                        startActivity(new Intent(ChooseBasicSadum.this,Sadum2Activity.class));
                        finish();
                        break;
                    case 2:
                        startActivity(new Intent(ChooseBasicSadum.this, SadumFlexible.class));
                        finish();
                        break;

                }
            }
        });


    }


    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    }


