package com.asksira.imagepickersheetdemo.activity;

import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

public class RagiIdupActivity extends AppCompatActivity {
    private ImageView ivImage1,imgbg, ivImage2, imgview6,cancelimg,undoimg,redoimg,saveimg,garbage;
    SeekBar hueBar, satBar, valBar;
    final int RQS_IMAGE1 = 1;
    Uri source;
    Bitmap bitmapMaster;
    Button SelectMotifbtn;
    static boolean btnstatus=false;

    private ImageView img1,img2,img3,img4,img5,img6,img7,img8,img9,img10,img11,img12,img13,img14,img15,img16,img17,img18,img19,img20,img21,img22,img23,img24,img25,img26;

    ImageView image;
    RelativeLayout relativeimages;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ragi_idup);
        initial();
        touchAndDrag();
        //Edit text container tidak muncul


        findViewById(R.id.garbage_imgview).setVisibility(View.GONE);
        //Chooose color for Background
        findViewById(R.id.btnDarkRed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeimages.setBackgroundResource(R.color.purple);
            }
        });
        findViewById(R.id.btnBlack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeimages.setBackgroundResource(R.color.black);
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



        cancelimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // exit function

                AlertDialog.Builder builder = new AlertDialog.Builder(RagiIdupActivity.this);
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

                AlertDialog.Builder builder = new AlertDialog.Builder(RagiIdupActivity.this);
                builder.setTitle("Simpan Desain");
                //builder.setIcon(R.drawable.ic_cancel);
                builder.setMessage("Anda ingin menyimpan gambar?")
                        .setCancelable(false)
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                relativeimages.setDrawingCacheEnabled(true);
                                Bitmap mybitmap = relativeimages.getDrawingCache();
                                startSave(mybitmap);
                                startActivity(new Intent(RagiIdupActivity.this, DashboardActivity.class));
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
        garbage = findViewById(R.id.garbage_imgview);

        //ucapan
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




    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 0 ) {
            fragmentManager.popBackStack();
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

    public void touchAndDrag(){
        ivImage2.setOnTouchListener(new RagiIdupActivity.ChoiceTouchListener());ivImage2.setOnDragListener(new RagiIdupActivity.ChoiceDragListener());
        img1.setOnTouchListener(new RagiIdupActivity.ChoiceTouchListener());img1.setOnDragListener(new RagiIdupActivity.ChoiceDragListener());
        img2.setOnTouchListener(new RagiIdupActivity.ChoiceTouchListener());img2.setOnDragListener(new RagiIdupActivity.ChoiceDragListener());
        img3.setOnTouchListener(new RagiIdupActivity.ChoiceTouchListener());img3.setOnDragListener(new RagiIdupActivity.ChoiceDragListener());
        img4.setOnTouchListener(new RagiIdupActivity.ChoiceTouchListener());img4.setOnDragListener(new RagiIdupActivity.ChoiceDragListener());
        img5.setOnTouchListener(new RagiIdupActivity.ChoiceTouchListener());img5.setOnDragListener(new RagiIdupActivity.ChoiceDragListener());
        img6.setOnTouchListener(new RagiIdupActivity.ChoiceTouchListener());img6.setOnDragListener(new RagiIdupActivity.ChoiceDragListener());
        img7.setOnTouchListener(new RagiIdupActivity.ChoiceTouchListener());img7.setOnDragListener(new RagiIdupActivity.ChoiceDragListener());
        img8.setOnTouchListener(new RagiIdupActivity.ChoiceTouchListener());img8.setOnDragListener(new RagiIdupActivity.ChoiceDragListener());
        img9.setOnTouchListener(new RagiIdupActivity.ChoiceTouchListener());img9.setOnDragListener(new RagiIdupActivity.ChoiceDragListener());
        img10.setOnTouchListener(new RagiIdupActivity.ChoiceTouchListener());img10.setOnDragListener(new RagiIdupActivity.ChoiceDragListener());

        img11.setOnTouchListener(new RagiIdupActivity.ChoiceTouchListener());img11.setOnDragListener(new RagiIdupActivity.ChoiceDragListener());
        img12.setOnTouchListener(new RagiIdupActivity.ChoiceTouchListener());img12.setOnDragListener(new RagiIdupActivity.ChoiceDragListener());
        img13.setOnTouchListener(new RagiIdupActivity.ChoiceTouchListener());img13.setOnDragListener(new RagiIdupActivity.ChoiceDragListener());
        img14.setOnTouchListener(new RagiIdupActivity.ChoiceTouchListener());img14.setOnDragListener(new RagiIdupActivity.ChoiceDragListener());
        img15.setOnTouchListener(new RagiIdupActivity.ChoiceTouchListener());img15.setOnDragListener(new RagiIdupActivity.ChoiceDragListener());
        img16.setOnTouchListener(new RagiIdupActivity.ChoiceTouchListener());img16.setOnDragListener(new RagiIdupActivity.ChoiceDragListener());
        img17.setOnTouchListener(new RagiIdupActivity.ChoiceTouchListener());img17.setOnDragListener(new RagiIdupActivity.ChoiceDragListener());
        img18.setOnTouchListener(new RagiIdupActivity.ChoiceTouchListener());img18.setOnDragListener(new RagiIdupActivity.ChoiceDragListener());
        img19.setOnTouchListener(new RagiIdupActivity.ChoiceTouchListener());img19.setOnDragListener(new RagiIdupActivity.ChoiceDragListener());
        img20.setOnTouchListener(new RagiIdupActivity.ChoiceTouchListener());img20.setOnDragListener(new RagiIdupActivity.ChoiceDragListener());

        img21.setOnTouchListener(new RagiIdupActivity.ChoiceTouchListener());img21.setOnDragListener(new RagiIdupActivity.ChoiceDragListener());
        img22.setOnTouchListener(new RagiIdupActivity.ChoiceTouchListener());img22.setOnDragListener(new RagiIdupActivity.ChoiceDragListener());
        img23.setOnTouchListener(new RagiIdupActivity.ChoiceTouchListener());img23.setOnDragListener(new RagiIdupActivity.ChoiceDragListener());
        img24.setOnTouchListener(new RagiIdupActivity.ChoiceTouchListener());img24.setOnDragListener(new RagiIdupActivity.ChoiceDragListener());
        img25.setOnTouchListener(new RagiIdupActivity.ChoiceTouchListener());img25.setOnDragListener(new RagiIdupActivity.ChoiceDragListener());
        img26.setOnTouchListener(new RagiIdupActivity.ChoiceTouchListener());img26.setOnDragListener(new RagiIdupActivity.ChoiceDragListener());
        garbage.setOnTouchListener(new RagiIdupActivity.ChoiceTouchListener());garbage.setOnDragListener(new RagiIdupActivity.ChoiceDragListener());

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
                return false;
            }
        }
    }


    private class ChoiceDragListener implements View.OnDragListener{

        @Override
        public boolean onDrag(View view, DragEvent dragEvent) {

            switch (dragEvent.getAction()){
                case DragEvent.ACTION_DRAG_STARTED:

                    //Saat gambar di drag
                    findViewById(R.id.iv_image2).setVisibility(View.GONE);
                    SelectMotifbtn.setVisibility(View.GONE);
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
                        findViewById(R.id.seekcontainer).setVisibility(View.GONE);
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
                            findViewById(R.id.iv_image2).setVisibility(View.VISIBLE);
                            SelectMotifbtn.setVisibility(View.VISIBLE);
                            findViewById(R.id.topbtn).setVisibility(View.VISIBLE);
                            findViewById(R.id.bgColor).setVisibility(View.VISIBLE);
                            findViewById(R.id.seekcontainer).setVisibility(View.VISIBLE);
                            garbage.setVisibility(View.GONE);
                            hideSystemUI();
                        }
                    });
                    break;
            }
            return true;
        }
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
