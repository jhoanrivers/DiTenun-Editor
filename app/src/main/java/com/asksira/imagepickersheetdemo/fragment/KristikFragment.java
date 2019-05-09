package com.asksira.imagepickersheetdemo.fragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
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
import com.asksira.imagepickersheetdemo.activity.SpecificKristikActivity;
import com.asksira.imagepickersheetdemo.activity.SpecificSingleImage;
import com.asksira.imagepickersheetdemo.adapter.GridKristikAdapter;
import com.asksira.imagepickersheetdemo.adapter.GridViewAdapter;
import com.asksira.imagepickersheetdemo.adapter.PhotoAdapter;

import java.io.File;
import java.util.ArrayList;

public class KristikFragment extends Fragment{


    private String[] FilePathStrings;
    private String[] FileNameStrings;
    private File[] listFile;
    GridView grid;
    GridKristikAdapter adapter;
    File file;


    public KristikFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_disimpan, container, false);

        file= new File(Environment.getExternalStorageDirectory(),"DE kristik");

        if(!file.exists() && !file.mkdirs()){
            file = new File(Environment.getExternalStorageDirectory()+File.separator + "DE kristik" );
            file.mkdirs();
        }
        else{
            listFile = file.listFiles();
            FilePathStrings = new String[listFile.length];
            FileNameStrings = new String[listFile.length];

            for(int i=0;i<listFile.length;i++){

                FilePathStrings[i] = listFile[i].getAbsolutePath();
                FileNameStrings[i] = listFile[i].getName();
            }

            grid = view.findViewById(R.id.gridview);
            adapter = new GridKristikAdapter(getActivity(),FilePathStrings,FileNameStrings);

            grid.setAdapter(adapter);
            grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    Uri imageUri = Uri.parse("android.resource://com.asksira.imagepickersheetdemo/"+adapter.getItem(i));

                    Intent intent = new Intent(getActivity(), SpecificKristikActivity.class);
                    intent.putExtra("filepath",FilePathStrings);
                    intent.putExtra("filename",FileNameStrings);
                    intent.putExtra("position",i);
                    intent.putExtra("uriimg",imageUri.toString());

                    startActivity(intent);
                }
            });
        }
        if(file.isDirectory()){
        }
        return view;
    }

}
