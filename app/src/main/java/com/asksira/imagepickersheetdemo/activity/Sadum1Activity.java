package com.asksira.imagepickersheetdemo.activity;

import android.annotation.SuppressLint;
import android.content.ClipData;
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
import android.view.DragEvent;
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

public class Sadum1Activity extends AppCompatActivity{


    private ImageView ivImage1,imgbg, ivImage2, imgview6,cancelimg,undoimg,redoimg,saveimg,garbage;
    SeekBar hueBar, satBar, valBar;
    EditText edtUcapan;
    TextView textUcapan;
    final int RQS_IMAGE1 = 1;
    Uri source;
    Bitmap bitmapMaster;
    Button SelectMotifbtn,Ucapanbtn,ShowUcapanBtn;
    static boolean btnstatus=false;

    ImageView img1,img2,img3,img4,img5,img6,img7,img8,img9,img10,img11,img12,img13,img14,img15,img16,img17,img18,img19,img20,img21,img22,img23,img24,img25,img26,img27,img28,img29,img30,img31,img32,img33,img34,img35,img36,img37,img38,img39,img40,img41,img42,img43,img44,img45,img46,img47, img48, img49, img50, img51, img52, img53, img54, img55, img56, img57, img58, img59, img60, img61, img62, img63, img64, img65, img66, img67, img68, img69, img70, img71, img72, img73, img74, img75, img76, img77, img78, img79, img80, img81, img82, img83, img84, img85, img86, img87, img88, img89, img90, img91, img92, img93, img94, img95, img96, img97, img98, img99, img100, img101, img102, img103, img104, img105, img106, img107, img108, img109, img110, img111, img112, img113, img114, img115, img116, img117, img118, img119, img120, img121, img122, img123, img124, img125, img126, img127, img128, img129, img130, img131, img132, img133, img134, img135, img136, img137, img138, img139, img140, img141, img142, img143, img144, img145, img146, img147, img148, img149, img150, img151, img152, img153, img154, img155, img156, img157, img158, img159, img160, img161, img162, img163, img164, img165;

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
        touchAndDrag();
        //Edit text container tidak muncul
        ContainerUcapan.setVisibility(View.GONE);
        garbage.setVisibility(View.GONE);


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
        garbage = findViewById(R.id.garbage_imgview);

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
        img46 = findViewById(R.id.imageView46);
        img47 = findViewById(R.id.imageView47);
        img48 = findViewById(R.id.imageView48);
        img49 = findViewById(R.id.imageView49);
        img50 = findViewById(R.id.imageView50);

        img51 = findViewById(R.id.imageView51);
        img52 = findViewById(R.id.imageView52);
        img53 = findViewById(R.id.imageView53);
        img54 = findViewById(R.id.imageView54);
        img55 = findViewById(R.id.imageView55);
        img56 = findViewById(R.id.imageView56);
        img57 = findViewById(R.id.imageView57);
        img58 = findViewById(R.id.imageView58);
        img59 = findViewById(R.id.imageView59);
        img60 = findViewById(R.id.imageView60);

        img61 = findViewById(R.id.imageView61);
        img62 = findViewById(R.id.imageView62);
        img63 = findViewById(R.id.imageView63);
        img64 = findViewById(R.id.imageView64);
        img65 = findViewById(R.id.imageView65);
        img66 = findViewById(R.id.imageView66);
        img67 = findViewById(R.id.imageView67);
        img68 = findViewById(R.id.imageView68);
        img69 = findViewById(R.id.imageView69);
        img70 = findViewById(R.id.imageView70);

        img71 = findViewById(R.id.imageView71);
        img72 = findViewById(R.id.imageView72);
        img73 = findViewById(R.id.imageView73);
        img74 = findViewById(R.id.imageView74);
        img75 = findViewById(R.id.imageView75);
        img76 = findViewById(R.id.imageView76);
        img77 = findViewById(R.id.imageView77);
        img78 = findViewById(R.id.imageView78);
        img79 = findViewById(R.id.imageView79);
        img80 = findViewById(R.id.imageView80);

        img81 = findViewById(R.id.imageView81);
        img82 = findViewById(R.id.imageView82);
        img83 = findViewById(R.id.imageView83);
        img84 = findViewById(R.id.imageView84);
        img85 = findViewById(R.id.imageView85);
        img86 = findViewById(R.id.imageView86);
        img87 = findViewById(R.id.imageView87);
        img88 = findViewById(R.id.imageView88);
        img89 = findViewById(R.id.imageView89);
        img90 = findViewById(R.id.imageView90);

        img91 = findViewById(R.id.imageView91);
        img92 = findViewById(R.id.imageView92);
        img93 = findViewById(R.id.imageView93);
        img94 = findViewById(R.id.imageView94);
        img95 = findViewById(R.id.imageView95);
        img96 = findViewById(R.id.imageView96);
        img97 = findViewById(R.id.imageView97);
        img98 = findViewById(R.id.imageView98);
        img99 = findViewById(R.id.imageView99);
        img100 = findViewById(R.id.imageView100);

        img101 = findViewById(R.id.imageView101);
        img102 = findViewById(R.id.imageView102);
        img103 = findViewById(R.id.imageView103);
        img104 = findViewById(R.id.imageView104);
        img105 = findViewById(R.id.imageView105);
        img106 = findViewById(R.id.imageView106);
        img107 = findViewById(R.id.imageView107);
        img108 = findViewById(R.id.imageView108);
        img109 = findViewById(R.id.imageView109);
        img110 = findViewById(R.id.imageView110);
        img111 = findViewById(R.id.imageView111);
        img112 = findViewById(R.id.imageView112);
        img113 = findViewById(R.id.imageView113);
        img114 = findViewById(R.id.imageView114);
        img115 = findViewById(R.id.imageView115);
        img116 = findViewById(R.id.imageView116);
        img117 = findViewById(R.id.imageView117);
        img118 = findViewById(R.id.imageView118);
        img119 = findViewById(R.id.imageView119);
        img120 = findViewById(R.id.imageView120);
        img121 = findViewById(R.id.imageView121);
        img122 = findViewById(R.id.imageView122);
        img123 = findViewById(R.id.imageView123);
        img124 = findViewById(R.id.imageView124);
        img125 = findViewById(R.id.imageView125);
        img126 = findViewById(R.id.imageView126);
        img127 = findViewById(R.id.imageView127);
        img128 = findViewById(R.id.imageView128);
        img129 = findViewById(R.id.imageView129);
        img130 = findViewById(R.id.imageView130);
        img131 = findViewById(R.id.imageView131);
        img132 = findViewById(R.id.imageView132);
        img133 = findViewById(R.id.imageView133);
        img134 = findViewById(R.id.imageView134);
        img135 = findViewById(R.id.imageView135);
        img136 = findViewById(R.id.imageView136);
        img137 = findViewById(R.id.imageView137);
        img138 = findViewById(R.id.imageView138);
        img139 = findViewById(R.id.imageView139);
        img140 = findViewById(R.id.imageView140);
        img141 = findViewById(R.id.imageView141);
        img142 = findViewById(R.id.imageView142);
        img143 = findViewById(R.id.imageView143);
        img144 = findViewById(R.id.imageView144);
        img145 = findViewById(R.id.imageView145);
        img146 = findViewById(R.id.imageView146);
        img147 = findViewById(R.id.imageView147);
        img148 = findViewById(R.id.imageView148);
        img149 = findViewById(R.id.imageView149);
        img150 = findViewById(R.id.imageView150);
        img151 = findViewById(R.id.imageView151);
        img152 = findViewById(R.id.imageView152);
        img153 = findViewById(R.id.imageView153);
        img154 = findViewById(R.id.imageView154);
        img155 = findViewById(R.id.imageView155);
        img156 = findViewById(R.id.imageView156);
        img157 = findViewById(R.id.imageView157);
        img158 = findViewById(R.id.imageView158);
        img159 = findViewById(R.id.imageView159);
        img160 = findViewById(R.id.imageView160);
        img161 = findViewById(R.id.imageView161);
        img162 = findViewById(R.id.imageView162);
        img163 = findViewById(R.id.imageView163);
        img164 = findViewById(R.id.imageView164);
        img165 = findViewById(R.id.imageView165);

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

    public void touchAndDrag(){
        ivImage2.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());ivImage2.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img1.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img1.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img2.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img2.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img3.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img3.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img4.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img4.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img5.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img5.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img6.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img6.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img7.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img7.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img8.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img8.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img9.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img9.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img10.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img10.setOnDragListener(new Sadum1Activity.ChoiceDragListener());

        img11.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img11.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img12.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img12.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img13.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img13.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img14.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img14.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img15.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img15.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img16.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img16.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img17.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img17.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img18.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img18.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img19.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img19.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img20.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img20.setOnDragListener(new Sadum1Activity.ChoiceDragListener());

        img21.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img21.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img22.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img22.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img23.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img23.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img24.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img24.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img25.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img25.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img26.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img26.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img27.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img27.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img28.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img28.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img29.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img29.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img30.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img30.setOnDragListener(new Sadum1Activity.ChoiceDragListener());

        img31.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img31.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img32.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img32.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img33.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img33.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img34.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img34.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img35.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img35.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img36.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img36.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img37.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img37.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img38.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img38.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img39.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img39.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img40.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img40.setOnDragListener(new Sadum1Activity.ChoiceDragListener());

        img41.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img41.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img42.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img42.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img43.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img43.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img44.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img44.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img45.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img45.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img46.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img46.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img47.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img47.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img48.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img48.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img49.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img49.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img50.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img50.setOnDragListener(new Sadum1Activity.ChoiceDragListener());

        img51.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img51.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img52.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img52.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img53.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img53.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img54.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img54.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img55.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img55.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img56.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img56.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img57.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img57.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img58.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img58.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img59.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img59.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img60.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img60.setOnDragListener(new Sadum1Activity.ChoiceDragListener());

        img61.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img61.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img62.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img62.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img63.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img63.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img64.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img64.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img65.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img65.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img66.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img66.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img67.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img67.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img68.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img68.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img69.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img69.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img70.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img70.setOnDragListener(new Sadum1Activity.ChoiceDragListener());

        img71.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img71.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img72.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img72.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img73.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img73.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img74.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img74.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img75.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img75.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img76.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img76.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img77.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img77.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img78.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img78.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img79.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img79.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img80.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img80.setOnDragListener(new Sadum1Activity.ChoiceDragListener());

        img81.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img81.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img82.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img82.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img83.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img83.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img84.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img84.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img85.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img85.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img86.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img86.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img87.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img87.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img88.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img88.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img89.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img89.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img90.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img90.setOnDragListener(new Sadum1Activity.ChoiceDragListener());

        img91.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img91.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img92.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img92.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img93.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img93.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img94.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img94.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img95.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img95.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img96.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img96.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img97.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img97.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img98.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img98.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img99.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img99.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img100.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img100.setOnDragListener(new Sadum1Activity.ChoiceDragListener());

        img101.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img101.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img102.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img102.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img103.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img103.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img104.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img104.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img105.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img105.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img106.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img106.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img107.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img107.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img108.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img108.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img109.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img109.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img110.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img110.setOnDragListener(new Sadum1Activity.ChoiceDragListener());

        img111.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img111.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img112.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img112.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img113.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img113.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img114.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img114.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img115.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img115.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img116.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img116.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img117.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img117.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img118.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img118.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img119.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img119.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img120.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img120.setOnDragListener(new Sadum1Activity.ChoiceDragListener());

        img121.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img121.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img122.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img122.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img123.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img123.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img124.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img124.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img125.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img125.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img126.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img126.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img127.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img127.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img128.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img128.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img129.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img129.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img130.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img130.setOnDragListener(new Sadum1Activity.ChoiceDragListener());

        img131.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img131.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img132.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img132.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img133.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img133.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img134.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img134.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img135.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img135.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img136.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img136.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img137.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img137.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img138.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img138.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img139.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img139.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img140.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img140.setOnDragListener(new Sadum1Activity.ChoiceDragListener());

        img141.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img141.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img142.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img142.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img143.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img143.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img144.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img144.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img145.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img145.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img146.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img146.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img147.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img147.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img148.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img148.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img149.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img149.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img150.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img150.setOnDragListener(new Sadum1Activity.ChoiceDragListener());

        img151.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img151.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img152.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img152.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img153.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img153.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img154.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img154.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img155.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img155.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img156.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img156.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img157.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img157.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img158.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img158.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img159.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img159.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img160.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img160.setOnDragListener(new Sadum1Activity.ChoiceDragListener());

        img161.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img161.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img162.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img162.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img163.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img163.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img164.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img164.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        img165.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());img165.setOnDragListener(new Sadum1Activity.ChoiceDragListener());
        garbage.setOnTouchListener(new Sadum1Activity.ChoiceTouchListener());garbage.setOnDragListener(new Sadum1Activity.ChoiceDragListener());


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
                    SelectMotifbtn.setVisibility(View.INVISIBLE);
//                    hueBar.setVisibility(View.GONE);
                    findViewById(R.id.iv_image2).setVisibility(View.INVISIBLE);
                    findViewById(R.id.topbtn).setVisibility(View.INVISIBLE);
                    findViewById(R.id.bgColorCenter).setVisibility(View.INVISIBLE);
                    findViewById(R.id.bgColor).setVisibility(View.INVISIBLE);
                    findViewById(R.id.btnshowucapan).setVisibility(View.INVISIBLE);
                    findViewById(R.id.seekcontainer).setVisibility(View.INVISIBLE);
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
                        ((ImageView)view).setScaleType(ImageView.ScaleType.CENTER_INSIDE);
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
                            findViewById(R.id.bgColorCenter).setVisibility(View.VISIBLE);
                            findViewById(R.id.bgColor).setVisibility(View.VISIBLE);
                            findViewById(R.id.btnshowucapan).setVisibility(View.VISIBLE);
                            garbage.setVisibility(View.INVISIBLE);
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
