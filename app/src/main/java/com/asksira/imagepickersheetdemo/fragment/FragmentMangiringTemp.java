package com.asksira.imagepickersheetdemo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.asksira.imagepickersheetdemo.R;
import com.asksira.imagepickersheetdemo.activity.MangiringActivity;


public class FragmentMangiringTemp extends Fragment {

    EditText heightsisi,widthsisi,heighttgh,widthtgh;
    Button selesai;
    private View v;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_mangiring_size_temp,container,false);


        heightsisi = v.findViewById(R.id.heightsizeid);
        widthsisi = v.findViewById(R.id.widthsizeid);
        heighttgh = v.findViewById(R.id.heighttgh);
        widthtgh = v.findViewById(R.id.widthtgh);


        selesai = v.findViewById(R.id.btnselesai);

        selesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hs = heightsisi.getText().toString();
                String ws = widthsisi.getText().toString();

                String ht = heighttgh.getText().toString();
                String wt = widthtgh.getText().toString();

                if(TextUtils.isEmpty(heightsisi.getText()) && TextUtils.isEmpty(widthsisi.getText()) && TextUtils.isEmpty(heighttgh.getText()) && TextUtils.isEmpty(widthtgh.getText())){
                    heightsisi.setError("Required");
                    widthsisi.setError("Required");
                    heighttgh.setError("Required");
                    widthtgh.setError("Required");
                }
                else {
                    MangiringActivity rg = (MangiringActivity) getActivity();
                    rg.fsize(hs, ws, ht, wt);
                    rg.onBackPressed();
                }

            }
        });


        return v;
    }



}
