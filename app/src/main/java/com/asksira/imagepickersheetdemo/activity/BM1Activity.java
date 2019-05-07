package com.asksira.imagepickersheetdemo.activity;

import android.app.Dialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.asksira.bsimagepicker.BSImagePicker;
import com.asksira.imagepickersheetdemo.R;
import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BM1Activity extends AppCompatActivity{



    private ImageView ivImage1,imgbg, ivImage2, imgview6, imgview7, imgview8,imgview9,imgview10,imgview11,imgview12,imgview13,imgview14,imgview15,imgview16,imgview17,imgview18,imgview19,cancelimg,undoimg,redoimg,saveimg;
    Context context;
    Button btnLoadImage;
    TextView textSource;
    ImageView imageMotif;
    SeekBar hueBar, satBar, valBar;
    TextView hueText, satText, valText;

    final int RQS_IMAGE1 = 1;

    Uri source;
    Bitmap bitmapMaster;
    Canvas canvasMaster;
    RelativeLayout containerBM,containercenterBM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bm1);
        initial();
        touchAndDrag();


        //button change color background
        findViewById(R.id.btnBlack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                containercenterBM.setBackgroundResource(R.color.black);
            }
        });

        findViewById(R.id.btnDarkRed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                containercenterBM.setBackgroundResource(R.color.BloodkRed);
            }
        });

        //Onclick Listener
        findViewById(R.id.motif_image).setOnClickListener(new View.OnClickListener() {
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

                AlertDialog.Builder builder = new AlertDialog.Builder(BM1Activity.this);
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

                AlertDialog.Builder builder = new AlertDialog.Builder(BM1Activity.this);
                builder.setTitle("Simpan Desain");
                //builder.setIcon(R.drawable.ic_cancel);
                builder.setMessage("Anda ingin menyimpan gambar?")
                        .setCancelable(false)
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                imgbg.buildDrawingCache();
                                imgview6.buildDrawingCache();
                                imgview7.buildDrawingCache();
                                imgview8.buildDrawingCache();
                                imgview9.buildDrawingCache();
                                imgview10.buildDrawingCache();
                                imgview11.buildDrawingCache();
                                imgview12.buildDrawingCache();
                                imgview13.buildDrawingCache();
                                imgview14.buildDrawingCache();
                                imgview15.buildDrawingCache();
                                imgview16.buildDrawingCache();

                                Bitmap imagebg = imgbg.getDrawingCache();
                                Bitmap image6= imgview6.getDrawingCache();
                                Bitmap image7 = imgview7.getDrawingCache();
                                Bitmap image8 = imgview8.getDrawingCache();
                                Bitmap image9 = imgview9.getDrawingCache();
                                Bitmap image10 = imgview10.getDrawingCache();
                                Bitmap image11 = imgview11.getDrawingCache();
                                Bitmap image12 = imgview12.getDrawingCache();
                                Bitmap image13 = imgview13.getDrawingCache();
                                Bitmap image14 = imgview14.getDrawingCache();
                                Bitmap image15 = imgview15.getDrawingCache();
                                Bitmap image16 = imgview16.getDrawingCache();

                                Bitmap mergeAllImage= (Bitmap) createSingleImageFromMultipleImage(imagebg,image6,image7,image8,image9,image10,image11,image12,image13,image14,image15,image16);

                                startSave(mergeAllImage);

                                //startSave();
                                startActivity(new Intent(BM1Activity.this, DashboardActivity.class));
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

    //Convert All image to be 1 image
    private Bitmap createSingleImageFromMultipleImage(Bitmap imagebg, Bitmap image6, Bitmap image7, Bitmap image8, Bitmap image9, Bitmap image10, Bitmap image11, Bitmap image12, Bitmap image13, Bitmap image14, Bitmap image15, Bitmap image16) {

        Bitmap result = Bitmap.createBitmap(imagebg.getWidth(),imagebg.getHeight(),imagebg.getConfig());
        Canvas canvas= new Canvas(result);
        canvas.drawBitmap(imagebg,0,0,null);
        canvas.drawBitmap(image6,50,55,null);
        canvas.drawBitmap(image7,100,55,null);
        canvas.drawBitmap(image8,150,55,null);
        canvas.drawBitmap(image9,200,55,null);
        canvas.drawBitmap(image10,250,55,null);
        canvas.drawBitmap(image11,300,55,null);
        canvas.drawBitmap(image12,350,55,null);
        canvas.drawBitmap(image13,400,55,null);
        canvas.drawBitmap(image14,450,55,null);
        canvas.drawBitmap(image15,500,55,null);
        canvas.drawBitmap(image16,550,55,null);

        return result;

    }

    public void startSave(Bitmap image){
        FileOutputStream fout = null;
        File filepath = getDisc();


        if(!filepath.exists() && !filepath.mkdir()){
            Toast.makeText(this,"cant create directory",Toast.LENGTH_SHORT).show();
            return;
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyymmsshhmmss");
        String date = simpleDateFormat.format(new Date());
        String name = "Img"+date+".jpg";
        String file_name = filepath.getAbsolutePath()+"/"+name;
        File newFile = new File(file_name);
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

    private File getDisc() {
        File file = new File(Environment.getExternalStorageDirectory()+File.separator + "DE disimpan" );
        return file;
    }

    // Untuk merefresh gallery setelah gambar disimpan
    public void refreshGallery(File file){
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intent.setData(Uri.fromFile(file));
        sendBroadcast(intent);
    }


    // Convert seluruh gambar ke bitmap
    public static Bitmap viewToBitmap(View view, int width, int height){
        Bitmap bitmap = Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }

    public void touchAndDrag(){
        ivImage2.setOnTouchListener(new ChoiceTouchListener()); ivImage2.setOnDragListener(new ChoiceDragListener());
        imgview6.setOnTouchListener(new ChoiceTouchListener()); imgview6.setOnDragListener(new ChoiceDragListener());
        imgview7.setOnTouchListener(new ChoiceTouchListener()); imgview7.setOnDragListener(new ChoiceDragListener());
        imgview8.setOnTouchListener(new ChoiceTouchListener()); imgview8.setOnDragListener(new ChoiceDragListener());
        imgview9.setOnTouchListener(new ChoiceTouchListener()); imgview9.setOnDragListener(new ChoiceDragListener());
        imgview10.setOnTouchListener(new ChoiceTouchListener()); imgview10.setOnDragListener(new ChoiceDragListener());
        imgview11.setOnTouchListener(new ChoiceTouchListener()); imgview11.setOnDragListener(new ChoiceDragListener());
        imgview12.setOnTouchListener(new ChoiceTouchListener()); imgview12.setOnDragListener(new ChoiceDragListener());
        imgview13.setOnTouchListener(new ChoiceTouchListener()); imgview13.setOnDragListener(new ChoiceDragListener());
        imgview14.setOnTouchListener(new ChoiceTouchListener()); imgview14.setOnDragListener(new ChoiceDragListener());
        imgview15.setOnTouchListener(new ChoiceTouchListener()); imgview15.setOnDragListener(new ChoiceDragListener());
        imgview16.setOnTouchListener(new ChoiceTouchListener()); imgview16.setOnDragListener(new ChoiceDragListener());
    }

    private void initial() {

        imgbg =findViewById(R.id.img_bg);
        ivImage2 = findViewById(R.id.iv_image2);
        //ivImage3 = findViewById(R.id.iv_image3);
        imgview6 = findViewById(R.id.imageView6);
        imgview7 = findViewById(R.id.imageView7);
        imgview8 = findViewById(R.id.imageView8);
        imgview9 = findViewById(R.id.imageView9);
        imgview10 = findViewById(R.id.imageView10);
        imgview11 = findViewById(R.id.imageView11);
        imgview12 = findViewById(R.id.imageView12);
        imgview13 = findViewById(R.id.imageView13);
        imgview14 = findViewById(R.id.imageView14);
        imgview15 = findViewById(R.id.imageView15);
        imgview16 = findViewById(R.id.imageView16);

        cancelimg = findViewById(R.id.cancel_imgview);
        undoimg = findViewById(R.id.undo_imgview);
        redoimg = findViewById(R.id.redo_imgview);
        saveimg = findViewById(R.id.save_imgview);

        //container background

        containerBM = findViewById(R.id.layoutcontainerBM);
        containercenterBM = findViewById(R.id.layoutcontainercenter);


    }

    private class ChoiceDragListener implements View.OnDragListener{

        @Override
        public boolean onDrag(View view, DragEvent dragEvent) {

            switch (dragEvent.getAction()){
                case DragEvent.ACTION_DRAG_STARTED:
                    cancelimg.setVisibility(View.GONE);
                    redoimg.setVisibility(View.GONE);
                    undoimg.setVisibility(View.GONE);
                    saveimg.setVisibility(View.GONE);
                    findViewById(R.id.motif_image).setVisibility(View.GONE);
                    ivImage2.setVisibility(View.GONE);
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    ImageView v = (ImageView) dragEvent.getLocalState();
                    ((ImageView)view).setImageDrawable(ivImage2.getDrawable());
                    //((ImageView)v).setImageDrawable(null);
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    cancelimg.setVisibility(View.VISIBLE);
                    redoimg.setVisibility(View.VISIBLE);
                    undoimg.setVisibility(View.VISIBLE);
                    saveimg.setVisibility(View.VISIBLE);
                    findViewById(R.id.motif_image).setVisibility(View.VISIBLE);
                    ivImage2.setVisibility(View.VISIBLE);
                    break;
            }
            return true;
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

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitByBackKey();
            //moveTaskToBack(false);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    //Saat force back ditekan
    protected void exitByBackKey() {

        AlertDialog alertbox = new AlertDialog.Builder(this)
                .setMessage("Anda ingin keluar dan membatalkan desain?")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {

                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) {

                        finish();
                        //close();
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {

                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                })
                .show();
    }

}
