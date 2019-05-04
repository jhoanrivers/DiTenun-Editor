package com.asksira.imagepickersheetdemo.activity;

import android.content.DialogInterface;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.asksira.imagepickersheetdemo.R;
import com.asksira.imagepickersheetdemo.fragment.FragmentMangiringTemp;
import com.asksira.imagepickersheetdemo.fragment.JuggiahFragment;

public class MangiringActivity extends AppCompatActivity {

    Button settemp,juggiahbtn,rowmotifbtn,selectmotif;
    ImageView templateimgsisi,templateimgtengah;
    static boolean btnstatus=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mangiring);
        initial();


        templateimgsisi.setVisibility(View.GONE);
        templateimgtengah.setVisibility(View.GONE);
        findViewById(R.id.topmenu).setVisibility(View.GONE);
        findViewById(R.id.btnforpattern).setVisibility(View.GONE);




        //Saat tomboh pilih ukuran ditekan
        settemp.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {

                FragmentTransaction fts = getSupportFragmentManager().beginTransaction();
                fts.replace(R.id.containerfragment, new FragmentMangiringTemp());
                settemp.setVisibility(View.GONE);
                fts.addToBackStack("optional tag");
                fts.commit();
            }
        });


        juggiahbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fts = getSupportFragmentManager().beginTransaction();
                fts.replace(R.id.containerfragmentjuggiah, new JuggiahFragment());
                settemp.setVisibility(View.GONE);
                fts.addToBackStack("optional tag");
                fts.commit();

            }
        });


    }
    public void initial(){
        settemp = findViewById(R.id.settem);
        templateimgsisi = findViewById(R.id.imgtemplate);
        juggiahbtn = findViewById(R.id.btnjuggiah);
        rowmotifbtn = findViewById(R.id.btnrowmotif);
        selectmotif = findViewById(R.id.btnselectmotif);
        templateimgtengah = findViewById(R.id.imgtemplatetgh);

    }


    public void fsize(String hi, String wi, String ht, String wt){
        btnstatus = true;

        int h = Integer.parseInt(hi);
        int w = Integer.parseInt(wi);
        int hht =Integer.parseInt(ht);
        int wwt = Integer.parseInt(wt);

        //Relative layout untuk image template
        RelativeLayout relativeimages = (RelativeLayout) findViewById(R.id.relativeimage);
        relativeimages.getLayoutParams().height = (int) h*6;
        relativeimages.getLayoutParams().width = (int) w*8;

        relativeimages.setBackgroundResource(R.color.green_900);
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams)relativeimages.getLayoutParams();
        lp.addRule(RelativeLayout.CENTER_IN_PARENT);


        //Ukuran template image
        templateimgsisi.requestLayout();
        templateimgsisi.getLayoutParams().height = (int) h*6;
        templateimgsisi.getLayoutParams().width = w*8;
        templateimgsisi.setScaleType(ImageView.ScaleType.FIT_XY);
        templateimgsisi.setVisibility(View.VISIBLE);
        templateimgsisi.setColorFilter(getBaseContext().getResources().getColor(R.color.darkRed));

        //Ukuran template tengah
        templateimgtengah.requestLayout();
        templateimgtengah.getLayoutParams().height = (int) hht*6;
        templateimgtengah.getLayoutParams().width = (int) wwt*8;
        templateimgtengah.setScaleType(ImageView.ScaleType.FIT_XY);
        templateimgtengah.setColorFilter(getBaseContext().getResources().getColor(R.color.black));
        templateimgtengah.setVisibility(View.VISIBLE);




        //sembunyikan notifbar
        hideSystemUI();
        // change color
        findViewById(R.id.backgroundalltemp).setBackgroundResource(R.color.black50);


        //Visibility
        findViewById(R.id.textsettem).setVisibility(View.GONE);
        findViewById(R.id.settem).setVisibility(View.GONE);
        findViewById(R.id.topmenu).setVisibility(View.VISIBLE);
        findViewById(R.id.btnforpattern).setVisibility(View.VISIBLE);


        for(int i=1;i<10;i++)
        {
            ImageView iv = new ImageView(this);
            iv.setImageResource(R.drawable.ic_cancel);
            iv.setPadding(0,0,0,20);
            relativeimages.addView(iv);
        }



    }




    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 0 ) {
            fragmentManager.popBackStack();

            if(btnstatus == true){
                findViewById(R.id.settem).setVisibility(View.GONE);
            }
            else{
                findViewById(R.id.settem).setVisibility(View.VISIBLE);
            }
        }

        else
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Batalkan dan Keluar");
            builder.setMessage("Anda yakin ingin keluar dari proses desain dan membatalkan desain?")
                    .setCancelable(false)
                    .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finish();
                        }
                    })
                    .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();


        }
    }



    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
        else
            showSystemUI();
    }

    private void hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }


    private void showSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }


}
