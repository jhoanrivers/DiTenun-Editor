package com.asksira.imagepickersheetdemo.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.asksira.imagepickersheetdemo.R;
import com.asksira.imagepickersheetdemo.gestures.MoveGestureDetector;
import com.asksira.imagepickersheetdemo.gestures.RotateGestureDetector;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Sadum1Activity extends AppCompatActivity implements View.OnTouchListener{


    private ImageView ivImage1,imgbg, ivImage2, imgview6,cancelimg,undoimg,redoimg,saveimg;
    SeekBar hueBar, satBar, valBar;
    EditText edtUcapan;
    TextView textUcapan;
    final int RQS_IMAGE1 = 1;
    Uri source;
    Bitmap bitmapMaster;
    Button SelectMotifbtn,Ucapanbtn,ShowUcapanBtn;
    static boolean btnstatus=false;

    ImageView img1,img2,img3,img4,img5,img6,img7,img8,img9,img10,img11,img12,img13,img14,img15,img16,img17,img18,img19,img20,img21,img22,img23,img24,img25,img26,img27,img28,img29,img30,img31,img32,img33,img34,img35,img36,img37,img38,img39,img40,img41,img42,img43,img44,img45;

    private float mscaleFactor = 0.5f;
    private float mrotationDegree =0.f;
    private float mFocusX =0.f;
    private float mFocusY =0.f;
    private int mScreenHeight;
    private int mScreenWidth;

    private Matrix matrix =new Matrix();
    private int mImagewidth,mImageheight;
    private ScaleGestureDetector scaleDetector;
    private RotateGestureDetector rotateGestureDetector;
    private MoveGestureDetector moveGestureDetector;

    ImageView image;
    RelativeLayout relativeimages,ContainerUcapan,containerCenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sadum1);
        initial();

        //Edit text container tidak muncul
        ContainerUcapan.setVisibility(View.GONE);


        //Chooose color for Background
        findViewById(R.id.btnDarkRed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeimages.setBackgroundResource(R.color.darkRed);
            }
        });
        findViewById(R.id.btnCalmRed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeimages.setBackgroundResource(R.color.calmRed);
            }
        });
        findViewById(R.id.btnGreen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeimages.setBackgroundResource(R.color.green);
            }
        });
        findViewById(R.id.btnBlue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeimages.setBackgroundResource(R.color.blueberry);
            }
        });

        findViewById(R.id.btnYellow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeimages.setBackgroundResource(R.color.yellow);
            }
        });

        findViewById(R.id.btnBlack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeimages.setBackgroundResource(R.color.black);
            }
        });



        //container center background color

        findViewById(R.id.btnDarkRedCenter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.containerCenter).setBackgroundResource(R.color.darkRed);
            }
        });
        findViewById(R.id.btnCalmRedCenter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.containerCenter).setBackgroundResource(R.color.calmRed);
            }
        });
        findViewById(R.id.btnGreenCenter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.containerCenter).setBackgroundResource(R.color.green);
            }
        });
        findViewById(R.id.btnBlueCenter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.containerCenter).setBackgroundResource(R.color.blueberry);
            }
        });

        findViewById(R.id.btnYellowCenter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.containerCenter).setBackgroundResource(R.color.yellow);
            }
        });

        findViewById(R.id.btnBlackCenter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.containerCenter).setBackgroundResource(R.color.black);
            }
        });




        //end of choose background color
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        mScreenHeight = displayMetrics.heightPixels;
        mScreenWidth = displayMetrics.widthPixels;


        //Ontouch
        //ivImage2.setOnTouchListener(this);
        //image.setOnTouchListener(this);

        scaleDetector = new ScaleGestureDetector(getApplicationContext(),new ScaleListener());
        rotateGestureDetector = new RotateGestureDetector(getApplication(), new RotateListener());
        moveGestureDetector = new MoveGestureDetector(getApplication(),new MoveListener());



        ShowUcapanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContainerUcapan.setVisibility(View.VISIBLE);
                btnstatus= true;
                edtUcapan.requestFocus();
                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,0);
            }
        });

        textUcapan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContainerUcapan.setVisibility(View.VISIBLE);
                btnstatus= true;
                edtUcapan.requestFocus();
                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,0);
            }
        });

        Ucapanbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ucapan = edtUcapan.getText().toString();
                if(ucapan.length()==0){
                    textUcapan.setText("Kata Ucapan");
                    textUcapan.setTextSize(24);
                }

                if(ucapan.length() > 15 && ucapan.length() < 20){
                    textUcapan.setTextSize(20);
                }
                else if(ucapan.length() > 20){
                    textUcapan.setTextSize(15);
                }
                else
                    textUcapan.setTextSize(24);

                textUcapan.setText(ucapan);
                InputMethodManager inputManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                hideSystemUI();
                ContainerUcapan.setVisibility(View.GONE);
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

        hueBar = (SeekBar) findViewById(R.id.huebar);
        satBar = (SeekBar) findViewById(R.id.satbar);
        valBar = (SeekBar) findViewById(R.id.valbar);

        //Saat belum ada foto
        hueBar.setVisibility(View.GONE);
        satBar.setVisibility(View.GONE);
        valBar.setVisibility(View.GONE);

        hueBar.setOnSeekBarChangeListener(seekBarChangeListener);
        satBar.setOnSeekBarChangeListener(seekBarChangeListener);
        valBar.setOnSeekBarChangeListener(seekBarChangeListener);
        //Hue end


        cancelimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // exit function

                AlertDialog.Builder builder = new AlertDialog.Builder(Sadum1Activity.this);
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

        undoimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // undo function


            }

        });

        redoimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //redo function


            }
        });

        saveimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(Sadum1Activity.this);
                builder.setTitle("Simpan Desain");
                //builder.setIcon(R.drawable.ic_cancel);
                builder.setMessage("Anda ingin menyimpan gambar?")
                        .setCancelable(false)
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                relativeimages.setDrawingCacheEnabled(true);
                                Bitmap mybitmap = relativeimages.getDrawingCache();
                                startSave(mybitmap);
                                startActivity(new Intent(Sadum1Activity.this, DashboardActivity.class));
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


    private void initial() {

        imgbg =findViewById(R.id.img_bg);
        ivImage2 = findViewById(R.id.iv_image2);
        SelectMotifbtn = findViewById(R.id.motif_image);

        //ivImage3 = findViewById(R.id.iv_image3);
        relativeimages = findViewById(R.id.containertemplate);

        cancelimg = findViewById(R.id.cancel_imgview);
        undoimg = findViewById(R.id.undo_imgview);
        redoimg = findViewById(R.id.redo_imgview);
        saveimg = findViewById(R.id.save_imgview);

        //ucapan
        ContainerUcapan = findViewById(R.id.edtucapancontainer);
        Ucapanbtn = findViewById(R.id.btn_ucapan);
        edtUcapan = findViewById(R.id.edt_ucapan);
        ShowUcapanBtn = findViewById(R.id.btnshowucapan);
        textUcapan = findViewById(R.id.txt_ucapan);
        containerCenter = findViewById(R.id.containerCenter);
        img1 = findViewById(R.id.imageView1);
        img2 = findViewById(R.id.imageView2);
        img3 = findViewById(R.id.imageView3);
        img4 = findViewById(R.id.imageView4);
        img5 = findViewById(R.id.imageView5);
        img6 = findViewById(R.id.imageView6);
        img7 = findViewById(R.id.imageView7);
        img8 = findViewById(R.id.imageView8);
        img9 = findViewById(R.id.imageView9);
        img10 = findViewById(R.id.imageView10);
        img11 = findViewById(R.id.imageView11);
        img12 = findViewById(R.id.imageView12);
        img13 = findViewById(R.id.imageView13);
        img14 = findViewById(R.id.imageView14);
        img15 = findViewById(R.id.imageView15);
        img16 = findViewById(R.id.imageView16);
        img17 = findViewById(R.id.imageView17);
        img18 = findViewById(R.id.imageView18);
        img19 = findViewById(R.id.imageView19);
        img20 = findViewById(R.id.imageView20);
        img21 = findViewById(R.id.imageView21);
        img22 = findViewById(R.id.imageView22);
        img23 = findViewById(R.id.imageView23);
        img24 = findViewById(R.id.imageView24);
        img25 = findViewById(R.id.imageView25);
        img26 = findViewById(R.id.imageView26);
        img27 = findViewById(R.id.imageView27);
        img28 = findViewById(R.id.imageView28);
        img29 = findViewById(R.id.imageView29);
        img30 = findViewById(R.id.imageView30);
        img31 = findViewById(R.id.imageView31);
        img32 = findViewById(R.id.imageView32);
        img33 = findViewById(R.id.imageView33);
        img34 = findViewById(R.id.imageView34);
        img35 = findViewById(R.id.imageView35);
        img36 = findViewById(R.id.imageView36);
        img37 = findViewById(R.id.imageView37);
        img38 = findViewById(R.id.imageView38);
        img39 = findViewById(R.id.imageView39);
        img40 = findViewById(R.id.imageView40);
        img41 = findViewById(R.id.imageView41);
        img42 = findViewById(R.id.imageView42);
        img43 = findViewById(R.id.imageView43);
        img44 = findViewById(R.id.imageView44);
        img45 = findViewById(R.id.imageView45);
        

    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {

        scaleDetector.onTouchEvent(event);
        rotateGestureDetector.onTouchEvent(event);
        moveGestureDetector.onTouchEvent(event);

        float scaleImageCenterX =(mImagewidth * mscaleFactor)/6;
        float scaleImageCenterY =(mImageheight * mscaleFactor)/4;

        matrix.reset();
        matrix.postScale(mscaleFactor,mscaleFactor);
        matrix.postRotate(mrotationDegree,scaleImageCenterX,scaleImageCenterY);
        matrix.postTranslate(mFocusX - scaleImageCenterX, mFocusY - scaleImageCenterY);

        ImageView view = (ImageView)v;
        view.setImageMatrix(matrix);

        return true;
    }



    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 0 ) {
            fragmentManager.popBackStack();

            if(btnstatus){
                findViewById(R.id.layoutsize).setVisibility(View.GONE);
            }
            else{
                findViewById(R.id.layoutsize).setVisibility(View.VISIBLE);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case RQS_IMAGE1:
                    source = data.getData();

                    try {
                        bitmapMaster = BitmapFactory.decodeStream(getContentResolver().openInputStream(source));

                        mImageheight =bitmapMaster.getHeight();
                        mImagewidth = bitmapMaster.getWidth();

                        //Reset HSV value
                        hueBar.setVisibility(View.VISIBLE);
                        satBar.setVisibility(View.VISIBLE);
                        valBar.setVisibility(View.VISIBLE);

                        hueBar.setProgress(256);
                        satBar.setProgress(256);
                        valBar.setProgress(256);

                        image = new ImageView(this);
                        image.setLayoutParams(new LinearLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
                        image.setScaleType(ImageView.ScaleType.MATRIX);

                        image.setOnTouchListener(this);

                        matrix.postScale(mscaleFactor,mscaleFactor);
                        image.setImageMatrix(matrix);

                        relativeimages.addView(image);

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


    @SuppressLint("ClickableViewAccessibility")
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

            image.setImageBitmap(updateHSV(bitmapMaster, hue, sat, val));

        }
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            mscaleFactor*= detector.getScaleFactor();
            mscaleFactor = Math.max(0.1f, Math.min(mscaleFactor, 1.0f));
            return true;
        }
    }

    private class RotateListener extends RotateGestureDetector.SimpleOnRotateGestureListener{

        @Override
        public boolean onRotate(RotateGestureDetector detector){
            mrotationDegree -=detector.getRotationDegreesDelta();
            return true;
        }

    }

    private class MoveListener extends MoveGestureDetector.SimpleOnMoveGestureListener{

        @Override
        public boolean onMove(MoveGestureDetector detector){
            PointF d = detector.getFocusDelta();
            mFocusX +=d.x;
            mFocusY += d.y;

            return true;

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


    //Menampilkan notifikasi window...

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

    // Shows the system bars by removing all the flags
// except for the ones that make the content appear under the system bars.
    private void showSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }


}
