package com.asksira.imagepickersheetdemo.fragment;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.asksira.imagepickersheetdemo.R;
import com.asksira.imagepickersheetdemo.activity.SpecificSingleImage;
import com.asksira.imagepickersheetdemo.adapter.GridViewAdapter;
import com.asksira.imagepickersheetdemo.adapter.PhotoAdapter;

import java.io.File;


public class DisimpanFragment extends Fragment {


    private String[] FilePathStrings;
    private String[] FileNameStrings;
    private File[] listFile;
    GridView grid;
    GridViewAdapter adapter;
    File file;



    public DisimpanFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_disimpan, container, false);

        file= new File(Environment.getExternalStorageDirectory(),"DE disimpan");

        if(!file.exists() && !file.mkdirs()){
            file = new File(Environment.getExternalStorageDirectory()+File.separator + "DE disimpan" );
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

            GridView grid = (GridView)view.findViewById(R.id.gridview);
            adapter = new GridViewAdapter(getActivity(),FilePathStrings,FileNameStrings);

            grid.setAdapter(adapter);


            grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    Uri imageUri = Uri.parse("android.resource://com.asksira.imagepickersheetdemo/"+adapter.getItem(i));
                    Intent intent = new Intent(getActivity(), SpecificSingleImage.class);
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
