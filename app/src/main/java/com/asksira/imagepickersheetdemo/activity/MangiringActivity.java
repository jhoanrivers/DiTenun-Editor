package com.asksira.imagepickersheetdemo.activity;

import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
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
    ImageView templateimgsisi,cancelimg,undoimg,redoimg,saveimg,garbage;
    static boolean btnstatus=false;
    SeekBar hueBar, satBar, valBar;
    final int RQS_IMAGE1 = 1;
    Bitmap bitmapMaster;
    RelativeLayout mangiringContainer;
    Uri source;
    private int mImagewidth,mImageheight;
    ImageView img1,img2,img3,img4,img5,img6,ivImage2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mangiring);
        initial();
        touchAndDrag();



        findViewById(R.id.seekcontainer).setVisibility(View.GONE);
        garbage.setVisibility(View.GONE);
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

        findViewById(R.id.btnBlack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mangiringContainer.setBackgroundResource(R.color.black);
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
        ivImage2 = findViewById(R.id.iv_image2);
        garbage = findViewById(R.id.garbage_imgview);
        hueBar = findViewById(R.id.huebar);
        satBar = findViewById(R.id.satbar);
        valBar = findViewById(R.id.valbar);

        //Untuk garis tengah
        img1=findViewById(R.id.imgjug1);
        img2=findViewById(R.id.imgjug2);
        img3=findViewById(R.id.imgjug3);
        img4=findViewById(R.id.imgjug4);
        img5=findViewById(R.id.imgjug5);
        img6=findViewById(R.id.imgjug6);


    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case RQS_IMAGE1:
                    source = data.getData();

                    try {
                        bitmapMaster = BitmapFactory.decodeStream(getContentResolver().openInputStream(source));
                        // Reset HSV value
                        hueBar.setVisibility(View.VISIBLE);
                        satBar.setVisibility(View.VISIBLE);
                        valBar.setVisibility(View.VISIBLE);
                        hueBar.setProgress(256);
                        satBar.setProgress(256);
                        valBar.setProgress(256);
                        loadBitmapHSV();

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }




    SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress,
                                      boolean fromUser) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            loadBitmapHSV();
        }
    };

    private void loadBitmapHSV() {
        if (bitmapMaster != null) {

            int progressHue = hueBar.getProgress() - 256;
            int progressSat = satBar.getProgress() - 256;
            int progressVal = valBar.getProgress() - 256;

            /*
             * Hue (0 .. 360) Saturation (0...1) Value (0...1)
             */

            float hue = (float) progressHue * 360 / 256;
            float sat = (float) progressSat / 256;
            float val = (float) progressVal / 256;

            ivImage2.setImageBitmap(updateHSV(bitmapMaster, hue, sat, val));

        }
    }


    private Bitmap updateHSV(Bitmap src, float settingHue, float settingSat,
                             float settingVal) {

        int w = src.getWidth();
        int h = src.getHeight();
        int[] mapSrcColor = new int[w * h];
        int[] mapDestColor = new int[w * h];

        float[] pixelHSV = new float[3];

        src.getPixels(mapSrcColor, 0, w, 0, 0, w, h);

        int index = 0;
        for (int y = 0; y < h; ++y) {
            for (int x = 0; x < w; ++x) {

                // Convert from Color to HSV
                Color.colorToHSV(mapSrcColor[index], pixelHSV);

                // Adjust HSV
                pixelHSV[0] = pixelHSV[0] + settingHue;
                if (pixelHSV[0] < 0.0f) {
                    pixelHSV[0] = 0.0f;
                } else if (pixelHSV[0] > 360.0f) {
                    pixelHSV[0] = 360.0f;
                }

                pixelHSV[1] = pixelHSV[1] + settingSat;
                if (pixelHSV[1] < 0.0f) {
                    pixelHSV[1] = 0.0f;
                } else if (pixelHSV[1] > 1.0f) {
                    pixelHSV[1] = 1.0f;
                }

                pixelHSV[2] = pixelHSV[2] + settingVal;
                if (pixelHSV[2] < 0.0f) {
                    pixelHSV[2] = 0.0f;
                } else if (pixelHSV[2] > 1.0f) {
                    pixelHSV[2] = 1.0f;
                }

                // Convert back from HSV to Color
                mapDestColor[index] = Color.HSVToColor(pixelHSV);

                index++;
            }
        }

        return Bitmap.createBitmap(mapDestColor, w, h, Bitmap.Config.ARGB_8888);

    }



    private class ChoiceDragListener implements View.OnDragListener{

        @Override
        public boolean onDrag(View view, DragEvent dragEvent) {

            switch (dragEvent.getAction()){
                case DragEvent.ACTION_DRAG_STARTED:

                    //Saat gambar di drag
                    SelectMotifbtn.setVisibility(View.GONE);
//                    hueBar.setVisibility(View.GONE);
                    findViewById(R.id.iv_image2).setVisibility(View.GONE);
                    findViewById(R.id.topbtn).setVisibility(View.GONE);
                    findViewById(R.id.bgColor).setVisibility(View.GONE);
                    findViewById(R.id.seekcontainer).setVisibility(View.GONE);
                    garbage.setVisibility(View.VISIBLE);
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:

                    break;
                case DragEvent.ACTION_DROP:
                    ImageView v = (ImageView) dragEvent.getLocalState();

                    if(view == garbage){
                        ((ImageView)v).setImageDrawable(null);
                        findViewById(R.id.seekcontainer).setVisibility(View.VISIBLE);
                    }
                    else {
                        ((ImageView) view).setImageDrawable(ivImage2.getDrawable());
                        ((ImageView)view).setScaleType(ImageView.ScaleType.CENTER_CROP);
                        findViewById(R.id.seekcontainer).setVisibility(View.VISIBLE);
                        //((ImageView)v).setImageDrawable(null);
                    }
                    break;
                case DragEvent.ACTION_DRAG_ENDED:

                    view.post(new Runnable() {
                        @Override
                        public void run() {
                            ImageView v = (ImageView) dragEvent.getLocalState();
                            findViewById(R.id.iv_image2).setVisibility(View.VISIBLE);
                            SelectMotifbtn.setVisibility(View.VISIBLE);
                            findViewById(R.id.topbtn).setVisibility(View.VISIBLE);
                            findViewById(R.id.bgColor).setVisibility(View.VISIBLE);
                            findViewById(R.id.seekcontainer).setVisibility(View.VISIBLE);
                            garbage.setVisibility(View.GONE);
                        }
                    });
                   hideSystemUI();
                    break;
            }
            return true;
        }
    }

    public void touchAndDrag() {
        ivImage2.setOnTouchListener(new MangiringActivity.ChoiceTouchListener());ivImage2.setOnDragListener(new MangiringActivity.ChoiceDragListener());
        img1.setOnTouchListener(new MangiringActivity.ChoiceTouchListener());img1.setOnDragListener(new MangiringActivity.ChoiceDragListener());
        img2.setOnTouchListener(new MangiringActivity.ChoiceTouchListener());img2.setOnDragListener(new MangiringActivity.ChoiceDragListener());
        img3.setOnTouchListener(new MangiringActivity.ChoiceTouchListener());img3.setOnDragListener(new MangiringActivity.ChoiceDragListener());
        img4.setOnTouchListener(new MangiringActivity.ChoiceTouchListener());img4.setOnDragListener(new MangiringActivity.ChoiceDragListener());
        img5.setOnTouchListener(new MangiringActivity.ChoiceTouchListener());img5.setOnDragListener(new MangiringActivity.ChoiceDragListener());
        img6.setOnTouchListener(new MangiringActivity.ChoiceTouchListener());img6.setOnDragListener(new MangiringActivity.ChoiceDragListener());
        garbage.setOnTouchListener(new MangiringActivity.ChoiceTouchListener());garbage.setOnDragListener(new MangiringActivity.ChoiceDragListener());
    }

    public final class ChoiceTouchListener implements View.OnTouchListener {

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {

            if ((motionEvent.getAction() == motionEvent.ACTION_DOWN) && ((ImageView) view).getDrawable() != null) {
                ClipData clipData = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(clipData, shadowBuilder, view, 0);
                return true;
            } else {
                // Disinilah event change color muncul.
                //Toast.makeText(SadumActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
                return true;
            }
        }
    }

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
