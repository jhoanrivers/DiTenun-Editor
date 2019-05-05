package com.asksira.imagepickersheetdemo.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.asksira.imagepickersheetdemo.R;
import com.asksira.imagepickersheetdemo.adapter.CustomListAdapter;
import com.asksira.imagepickersheetdemo.adapter.ViewPagerAdapter;
import com.asksira.imagepickersheetdemo.fragment.DisimpanFragment;
import com.asksira.imagepickersheetdemo.fragment.KristikFragment;

public class DashboardActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    GridView gridViewImage;
    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        tabLayout = (TabLayout) findViewById(R.id.tabs);
        appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        viewPager = findViewById(R.id.viewpager);


        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        //Adding fragment
        adapter.AddFragment(new KristikFragment(),"Kristik");
        adapter.AddFragment(new DisimpanFragment(),"Disimpan");

        //adapter setup
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(DashboardActivity.this);
                builder.setTitle("Pilih jenis template");

                LayoutInflater inflater = (LayoutInflater) DashboardActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View row = inflater.inflate(R.layout.row_item_template,null);
                final ListView li = (ListView)row.findViewById(R.id.list_row_item);
                li.setTextFilterEnabled(true);
                li.setAdapter(new CustomListAdapter(DashboardActivity.this));

                li.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        switch (i){
                            case 0:
                                Intent intent1 = new Intent(DashboardActivity.this, ChooseBasicSadum.class);
                                startActivity(intent1);
                                break;
                            case 1:
                                Intent intent2 = new Intent(DashboardActivity.this, BM1Activity.class);
                                startActivity(intent2);
                                break;
                            case 2:
                                Intent intent3 = new Intent(DashboardActivity.this,RagiIdupActivity.class);
                                startActivity(intent3);
                                break;
                            case 3:
                                Intent intent4 = new Intent(DashboardActivity.this,MangiringActivity.class);
                                startActivity(intent4);
                                break;
                            case 4:
                                Intent intent5 = new Intent(DashboardActivity.this,CustomActivity.class);
                                startActivity(intent5);
                                break;

                        }
                    }
                });

                builder.setView(row);

                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

    }


}