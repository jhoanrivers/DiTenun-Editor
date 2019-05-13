package com.asksira.imagepickersheetdemo.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import com.asksira.imagepickersheetdemo.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MangiringActivity extends AppCompatActivity {

    Button SelectMotifbtn;
    ImageView templateimgsisi,cancelimg,undoimg,redoimg,saveimg;
    static boolean btnstatus=false;
    SeekBar hueBar, satBar, valBar;
    final int RQS_IMAGE1 = 1;
    Bitmap bitmapMaster;
    RelativeLayout mangiringContainer;
    Uri source;
    private int mImagewidth,mImageheight;
    ImageView img1,img2,img3,img4,img5,img6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mangiring);
        initial();



        //Mengatur warna dari background
        findViewById(R.id.btnDarkRed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mangiringContainer.setBackgroundResource(R.color.colordarkRed);
            }
        });

        findViewById(R.id.btnCalmRed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mangiringContainer.setBackgroundResource(R.color.calmRed);
            }
        });



        SelectMotifbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, RQS_IMAGE1);
            }
        });

        //Hue end
        cancelimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // exit function

                AlertDialog.Builder builder = new AlertDialog.Builder(MangiringActivity.this);
                builder.setTitle("Batalkan Desain");
                builder.setMessage("Anda yakin ingin membatalkan desain anda?")
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
        });
        saveimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MangiringActivity.this);
                builder.setTitle("Simpan Desain");
                //builder.setIcon(R.drawable.ic_cancel);
                builder.setMessage("Anda ingin menyimpan gambar?")
                        .setCancelable(false)
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                mangiringContainer.setDrawingCacheEnabled(true);
                                Bitmap mybitmap = mangiringContainer.getDrawingCache();
                                startSave(mybitmap);
                                startActivity(new Intent(MangiringActivity.this, DashboardActivity.class));
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
        });

    }


    public void startSave(Bitmap image){
        FileOutputStream fout = null;
        File filepath = Environment.getExternalStorageDirectory();

        File dirfile = new File(filepath.getAbsoluteFile()+"/DE disimpan/");
        dirfile.mkdirs();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyymmsshhmmss");
        String date = simpleDateFormat.format(new Date());
        String name = "Img"+date+".jpg";
//        String file_name = filepath.getAbsolutePath()+"/"+name;
        File newFile = new File(dirfile.getAbsolutePath()+"/"+name);
        try{
            fout = new FileOutputStream(newFile);

            //Bitmap bitmap = viewToBitmap(imgbg,imgbg.getWidth(),imgbg.getHeight());
            image.compress(Bitmap.CompressFormat.JPEG,100,fout);
            Toast.makeText(this, "Gambar telah disimpan", Toast.LENGTH_SHORT).show();
            fout.flush();
            fout.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        refreshGallery(newFile);
    }


    // Untuk merefresh gallery setelah gambar disimpan
    public void refreshGallery(File file){
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intent.setData(Uri.fromFile(file));
        sendBroadcast(intent);
    }


    public void initial(){
        templateimgsisi = findViewById(R.id.imgtemplate);
        SelectMotifbtn = findViewById(R.id.motif_image);
        mangiringContainer = findViewById(R.id.containermangiring);
        cancelimg = findViewById(R.id.cancel_imgview);
        undoimg = findViewById(R.id.undo_imgview);
        redoimg = findViewById(R.id.redo_imgview);
        saveimg = findViewById(R.id.save_imgview);

        //Untuk garis tengah
        img1=findViewById(R.id.imgjug1);
        img2=findViewById(R.id.imgjug2);
        img3=findViewById(R.id.imgjug3);
        img4=findViewById(R.id.imgjug4);
        img5=findViewById(R.id.imgjug5);
        img6=findViewById(R.id.imgjug6);


    }


//    public void fsize(String hi, String wi, String ht, String wt){
//        btnstatus = true;
//
//        int h = Integer.parseInt(hi);
//        int w = Integer.parseInt(wi);
//        int hht =Integer.parseInt(ht);
//        int wwt = Integer.parseInt(wt);
//
//        //Relative layout untuk image template
//        RelativeLayout relativeimages = (RelativeLayout) findViewById(R.id.relativeimage);
//        relativeimages.getLayoutParams().height = (int) h*6;
//        relativeimages.getLayoutParams().width = (int) w*8;
//
//        relativeimages.setBackgroundResource(R.color.green_900);
//        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams)relativeimages.getLayoutParams();
//        lp.addRule(RelativeLayout.CENTER_IN_PARENT);
//
//
//        //Ukuran template image
//        templateimgsisi.requestLayout();
//        templateimgsisi.getLayoutParams().height = (int) h*6;
//        templateimgsisi.getLayoutParams().width = w*8;
//        templateimgsisi.setScaleType(ImageView.ScaleType.FIT_XY);
//        templateimgsisi.setVisibility(View.VISIBLE);
//        templateimgsisi.setColorFilter(getBaseContext().getResources().getColor(R.color.darkRed));
//
//        //sembunyikan notifbar
//        hideSystemUI();
//        // change color
//        findViewById(R.id.backgroundalltemp).setBackgroundResource(R.color.black50);
//
//
//        //Visibility
//        findViewById(R.id.textsettem).setVisibility(View.GONE);
//        findViewById(R.id.settem).setVisibility(View.GONE);
//        findViewById(R.id.topmenu).setVisibility(View.VISIBLE);
//        findViewById(R.id.btnforpattern).setVisibility(View.VISIBLE);
//
//    }




    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 0 ) {
            fragmentManager.popBackStack();

            if(btnstatus){
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
