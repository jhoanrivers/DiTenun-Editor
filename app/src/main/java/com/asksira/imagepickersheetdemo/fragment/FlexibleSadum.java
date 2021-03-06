package com.asksira.imagepickersheetdemo.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.asksira.imagepickersheetdemo.R;
import com.asksira.imagepickersheetdemo.activity.Sadum1Activity;
import com.asksira.imagepickersheetdemo.activity.SadumFlexible;


public class FlexibleSadum extends Fragment {
    EditText heightsisi,widthsisi;
    Button selesai;
    private View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_sadum_size, container, false);

        heightsisi = v.findViewById(R.id.heightsizeid);
        widthsisi = v.findViewById(R.id.widthsizeid);


        selesai = v.findViewById(R.id.btnselesai);

        selesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hs = heightsisi.getText().toString();
                String ws = widthsisi.getText().toString();

                if (TextUtils.isEmpty(heightsisi.getText()) && TextUtils.isEmpty(widthsisi.getText())) {
                    heightsisi.setError("Required");
                    widthsisi.setError("Required");

                } else {
                    SadumFlexible rg = (SadumFlexible) getActivity();
                    rg.fsize(hs, ws);
                    rg.onBackPressed();
                }

            }
        });


        return v;
    }


}
