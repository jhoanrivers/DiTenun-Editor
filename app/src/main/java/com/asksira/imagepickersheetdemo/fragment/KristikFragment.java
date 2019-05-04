package com.asksira.imagepickersheetdemo.fragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.asksira.imagepickersheetdemo.R;
import com.asksira.imagepickersheetdemo.activity.SpecificSingleImage;
import com.asksira.imagepickersheetdemo.adapter.PhotoAdapter;

import java.util.ArrayList;

public class KristikFragment extends Fragment{




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_kristik, container, false);


        GridView gridview = (GridView) v.findViewById(R.id.gridview);

        // Set the ImageAdapter into GridView Adapter
        gridview.setAdapter(new PhotoAdapter(getActivity()));

        // Capture GridView item click
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                // Launch ViewImage.java using intent
                Intent i = new Intent(getActivity(), SpecificSingleImage.class);

                // Show the item position using toast
                Toast.makeText(getActivity(), "Position " + position,
                        Toast.LENGTH_SHORT).show();

                // Send captured position to ViewImage.java
                i.putExtra("id", position);

                // Start ViewImage.java
                startActivity(i);
            }
        });
        return v;
    }






}
