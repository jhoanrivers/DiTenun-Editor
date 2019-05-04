package com.asksira.imagepickersheetdemo.activity;

import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public class Sadum3Activity extends AppCompatActivity implements BSImagePicker.OnSingleImageSelectedListener {


    private ImageView imgbg, ivImage2, imgview6, imgview7, imgview8,imgview9,imgview10,imgview11,imgview12,imgview13,imgview14,imgview15,imgview16,imgview17,imgview18,imgview19,cancelimg,undoimg,redoimg,saveimg;


    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sadum3);


        initial();
        touchAndDrag();


        //Onclick Listener

        findViewById(R.id.motif_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BSImagePicker pickerDialog = new BSImagePicker.Builder("com.asksira.imagepickersheetdemo.fileprovider")
                        .build();
                pickerDialog.show(getSupportFragmentManager(), "picker");
            }
        });


        cancelimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // exit function

                AlertDialog.Builder builder = new AlertDialog.Builder(Sadum3Activity.this);
                builder.setTitle(R.string.sadum_tarutung);
               // builder.setIcon(R.drawable.ic_delete);
                builder.setMessage("Anda yakin akan membatalkan desain anda?")
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

                AlertDialog.Builder builder = new AlertDialog.Builder(Sadum3Activity.this);
                builder.setTitle(R.string.sadum);
                //builder.setIcon(R.drawable.ic_cancel);
                //builder.setIcon(R.drawable.ic_saveicon);
                builder.setMessage("Anda ingin menyimpan gambar?")
                        .setCancelable(false)
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {


                                //Create new Imageview as a container to catch all the images

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
                                imgview17.buildDrawingCache();
                                imgview18.buildDrawingCache();
                                imgview19.buildDrawingCache();


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
                                Bitmap image17 = imgview17.getDrawingCache();
                                Bitmap image18 = imgview18.getDrawingCache();
                                Bitmap image19 = imgview19.getDrawingCache();


                                Bitmap mergeAllImage= createSingleImageFromMultipleImage(imagebg,image6,image7,image8,image9,image10,image11,image12,image13,image14,image15,image16,image17,image18,image19);

                                startSave(mergeAllImage);
                                startActivity(new Intent(Sadum3Activity.this, DashboardActivity.class));
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
        File file = getDisc();

        if(!file.exists() && !file.mkdir()){
            Toast.makeText(this,"cant create directory",Toast.LENGTH_SHORT).show();
            return;
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyymmsshhmmss");
        String date = simpleDateFormat.format(new Date());
        String name = "Img"+date+".jpg";
        String file_name = file.getAbsolutePath()+"/"+name;
        File newFile = new File(file_name);
        try{
            fout = new FileOutputStream(newFile);


            //Bitmap bitmap = viewToBitmap(imgbg,imgbg.getWidth(),imgbg.getHeight());

            image.compress(Bitmap.CompressFormat.JPEG,100,fout);
            Toast.makeText(this, "Gambar Telah disimpan", Toast.LENGTH_SHORT).show();
            fout.flush();
            fout.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        refreshGallery(newFile);
    }

    private Bitmap createSingleImageFromMultipleImage(Bitmap imagebg, Bitmap image6, Bitmap image7, Bitmap image8, Bitmap image9, Bitmap image10, Bitmap image11, Bitmap image12, Bitmap image13, Bitmap image14, Bitmap image15, Bitmap image16, Bitmap image17, Bitmap image18, Bitmap image19) {

    Bitmap result = Bitmap.createBitmap(imagebg.getWidth(),imagebg.getHeight(),imagebg.getConfig());
    Canvas canvas= new Canvas(result);
    canvas.drawBitmap(imagebg,0,0,null);
    canvas.drawBitmap(image6,0,0f,null);
    canvas.drawBitmap(image7,50,0f,null);
    canvas.drawBitmap(image8,100,0f,null);
    canvas.drawBitmap(image9,150,0f,null);
    canvas.drawBitmap(image10,200,0f,null);
    canvas.drawBitmap(image11,250,0f,null);
    canvas.drawBitmap(image12,300,0f,null);
    canvas.drawBitmap(image13,350,0f,null);
    canvas.drawBitmap(image14,400,0f,null);
    canvas.drawBitmap(image15,450,0f,null);
    canvas.drawBitmap(image16,500,0f,null);
    canvas.drawBitmap(image17,550,0f,null);
    canvas.drawBitmap(image18,600,0f,null);
    canvas.drawBitmap(image19,650,0f,null);

    return result;

    }

    // Untuk merefresh gallery setelah gambar disimpan
    public void refreshGallery(File file){
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intent.setData(Uri.fromFile(file));
        sendBroadcast(intent);
    }

    // Membuat file baru
    private File getDisc(){

        File file = new File(Environment.getExternalStorageDirectory()+File.separator + "DE disimpan" );
        return file;

    }


    // Convert seluruh gambar ke bitmap
    public static Bitmap viewToBitmap(View view, int width, int height){
        Bitmap bitmap = Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }

    public void touchAndDrag(){
        ivImage2.setOnTouchListener(new Sadum3Activity.ChoiceTouchListener()); ivImage2.setOnDragListener(new Sadum3Activity.ChoiceDragListener());
        imgview6.setOnTouchListener(new Sadum3Activity.ChoiceTouchListener()); imgview6.setOnDragListener(new Sadum3Activity.ChoiceDragListener());
        imgview7.setOnTouchListener(new Sadum3Activity.ChoiceTouchListener()); imgview7.setOnDragListener(new Sadum3Activity.ChoiceDragListener());
        imgview8.setOnTouchListener(new Sadum3Activity.ChoiceTouchListener()); imgview8.setOnDragListener(new Sadum3Activity.ChoiceDragListener());
        imgview9.setOnTouchListener(new Sadum3Activity.ChoiceTouchListener()); imgview9.setOnDragListener(new Sadum3Activity.ChoiceDragListener());
        imgview10.setOnTouchListener(new Sadum3Activity.ChoiceTouchListener()); imgview10.setOnDragListener(new Sadum3Activity.ChoiceDragListener());
        imgview11.setOnTouchListener(new Sadum3Activity.ChoiceTouchListener()); imgview11.setOnDragListener(new Sadum3Activity.ChoiceDragListener());
        imgview12.setOnTouchListener(new Sadum3Activity.ChoiceTouchListener()); imgview12.setOnDragListener(new Sadum3Activity.ChoiceDragListener());
        imgview13.setOnTouchListener(new Sadum3Activity.ChoiceTouchListener()); imgview13.setOnDragListener(new Sadum3Activity.ChoiceDragListener());
        imgview14.setOnTouchListener(new Sadum3Activity.ChoiceTouchListener()); imgview14.setOnDragListener(new Sadum3Activity.ChoiceDragListener());
        imgview15.setOnTouchListener(new Sadum3Activity.ChoiceTouchListener()); imgview15.setOnDragListener(new Sadum3Activity.ChoiceDragListener());
        imgview16.setOnTouchListener(new Sadum3Activity.ChoiceTouchListener()); imgview16.setOnDragListener(new Sadum3Activity.ChoiceDragListener());
        imgview17.setOnTouchListener(new Sadum3Activity.ChoiceTouchListener()); imgview17.setOnDragListener(new Sadum3Activity.ChoiceDragListener());
        imgview18.setOnTouchListener(new Sadum3Activity.ChoiceTouchListener()); imgview18.setOnDragListener(new Sadum3Activity.ChoiceDragListener());
        imgview19.setOnTouchListener(new Sadum3Activity.ChoiceTouchListener());
    }

    private void initial() {

        imgbg =findViewById(R.id.img_bg);
        ivImage2 = findViewById(R.id.iv_image2);
        //ivImage3 = findViewById(R.id.iv_image3);
//        ivImage4 = findViewById(R.id.iv_image4);
//        ivImage5 = findViewById(R.id.iv_image5);
//        ivImage6 = findViewById(R.id.iv_image6);
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
        imgview17 = findViewById(R.id.imageView17);
        imgview18 = findViewById(R.id.imageView18);
        imgview19 = findViewById(R.id.imageView19);

        cancelimg = findViewById(R.id.cancel_imgview);
        undoimg = findViewById(R.id.undo_imgview);
        redoimg = findViewById(R.id.redo_imgview);
        saveimg = findViewById(R.id.save_imgview);


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
    public void onSingleImageSelected(Uri uri, String tag) {
        Glide.with(Sadum3Activity.this).load(uri).into(ivImage2);
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


    public class onTouchListener implements View.OnTouchListener {

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



}
