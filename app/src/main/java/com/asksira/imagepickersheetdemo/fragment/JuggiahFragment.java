package com.asksira.imagepickersheetdemo.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.asksira.imagepickersheetdemo.R;


public class JuggiahFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    TextView exptext;
    EditText amountedt;
    Button previewbtn, cancelbtn,selesaibtn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_juggiah, container, false);
        exptext = v.findViewById(R.id.tvexp);
        amountedt = v.findViewById(R.id.edtamountJug);
        cancelbtn = v.findViewById(R.id.btncancel);
        selesaibtn = v.findViewById(R.id.btnselesai);
        previewbtn = v.findViewById(R.id.btnpreview);

        exptext.setText("Anda dapat mengatur jumlah dan warna jugiah sesuai dengan yang anda inginkan." +
                "Anda dapat mengatur warna yang anda inginkan pada jugiah dengan menyentuh jugiah");

        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        previewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amount = amountedt.getText().toString();
                int am = Integer.parseInt(amount);

                LinearLayout layout = (LinearLayout)v.findViewById(R.id.linearimgprev);

                for(int i=0;i<am;i++){

                    ImageView img = new ImageView(getActivity());
                    img.setLayoutParams(new android.view.ViewGroup.LayoutParams(150,60));
                    img.setMaxHeight(80);
                    img.setMaxWidth(20);

                    layout.addView(img);
                }

            }
        });



//        selesaibtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                String amount = amountedt.getText().toString();
//
//            }
//        });


        return v;
    }




}
