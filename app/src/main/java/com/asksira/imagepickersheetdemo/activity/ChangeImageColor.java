package com.asksira.imagepickersheetdemo.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.asksira.imagepickersheetdemo.R;

import java.io.FileNotFoundException;

public class ChangeImageColor extends AppCompatActivity {

    Button btnLoadImage;
    TextView textSource;
    ImageView imageResult;
    SeekBar hueBar, satBar, valBar;
    TextView hueText, satText, valText;
    Button btnResetHSV;


    final int RQS_IMAGE1 = 1;

    Uri source;
    Bitmap bitmapMaster;
    Canvas canvasMaster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_image_color);


    }



}
