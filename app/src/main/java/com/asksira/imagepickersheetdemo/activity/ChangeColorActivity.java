package com.asksira.imagepickersheetdemo.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.asksira.imagepickersheetdemo.R;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class ChangeColorActivity extends AppCompatActivity {

    ImageView imgSource, imgTarget;
    ImageView imgHue, imgSat, imgVal;
    TextView textHue, textSat, textVal;
    SeekBar barHue, barSat, barVal;
    Button buttonProcess, buttonReset;

    Bitmap bitmapSource = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgSource = (ImageView)findViewById(R.id.imgsource);
        imgTarget = (ImageView)findViewById(R.id.imgtarget);
        imgHue = (ImageView)findViewById(R.id.imghue);
        imgSat = (ImageView)findViewById(R.id.imgsat);
        imgVal = (ImageView)findViewById(R.id.imgval);

        textHue = (TextView)findViewById(R.id.huetext);
        textSat = (TextView)findViewById(R.id.sattext);
        textVal = (TextView)findViewById(R.id.valtext);
        barHue = (SeekBar)findViewById(R.id.huebar);
        barSat = (SeekBar)findViewById(R.id.satbar);
        barVal = (SeekBar)findViewById(R.id.valbar);
        buttonProcess = (Button)findViewById(R.id.process);
        buttonReset = (Button)findViewById(R.id.reset);

        barHue.setOnSeekBarChangeListener(seekBarChangeListener);
        barSat.setOnSeekBarChangeListener(seekBarChangeListener);
        barVal.setOnSeekBarChangeListener(seekBarChangeListener);

        buttonProcess.setOnClickListener(buttonProcessOnClickListener);
        buttonReset.setOnClickListener(buttonResetOnClickListener);

        //Load bitmap from internet
        String onLineImgSource = "http://goo.gl/yxNeG";

        URL urlImgSource;

        try {
            urlImgSource = new URL(onLineImgSource);
            new MyNetworkTask(imgSource, imgTarget, imgHue, imgSat, imgVal)
                    .execute(urlImgSource);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    View.OnClickListener buttonProcessOnClickListener
            = new View.OnClickListener(){

        @Override
        public void onClick(View arg0) {

            if(bitmapSource != null){
                buttonProcess.setEnabled(false);
                GroupBitmap groupBMResult = updateHSV(bitmapSource);
                imgTarget.setImageBitmap(groupBMResult.bitmapDest);
                imgHue.setImageBitmap(groupBMResult.bitmapHue);
                imgSat.setImageBitmap(groupBMResult.bitmapSat);
                imgVal.setImageBitmap(groupBMResult.bitmapVal);
                buttonProcess.setEnabled(true);
            }
        }};

    View.OnClickListener buttonResetOnClickListener
            = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            barHue.setProgress(256);
            barSat.setProgress(256);
            barVal.setProgress(256);

        }

    };

    SeekBar.OnSeekBarChangeListener seekBarChangeListener
            = new SeekBar.OnSeekBarChangeListener(){

        @Override
        public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
            int progressHue = barHue.getProgress() - 256;
            int progressSat = barSat.getProgress() - 256;
            int progressVal = barVal.getProgress() - 256;

            /*
             * Hue (0 .. 360)
             * Saturation (0...1)
             * Value (0...1)
             */

            float settingHue = (float)progressHue * 360 / 256;
            float settingSat = (float)progressSat / 256;
            float settingVal = (float)progressVal / 256;

            textHue.setText("Hue: " + String.format("%.02f", settingHue));
            textSat.setText("Sat: " + String.format("%.02f", settingSat));
            textVal.setText("Val: " + String.format("%.02f", settingVal));

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // TODO Auto-generated method stub

        }};

    private class MyNetworkTask extends AsyncTask<URL, Void, Bitmap> {

        ImageView ivSource, ivTarget;
        ImageView ivHue, ivSat, ivVal;

        public MyNetworkTask(ImageView iSource, ImageView iTarget,
                             ImageView iHue, ImageView iSat, ImageView iVal){
            ivSource = iSource;
            ivTarget = iTarget;
            ivHue = iHue;
            ivSat = iSat;
            ivVal = iVal;
        }

        @Override
        protected Bitmap doInBackground(URL... urls) {
            Bitmap networkBitmap = null;

            URL networkUrl = urls[0]; //Load the first element
            try {
                networkBitmap = BitmapFactory.decodeStream(
                        networkUrl.openConnection().getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }

            //To save space, scale bitmap by 1/2
            //return networkBitmap;
            Bitmap shrinkedBitmap =  Bitmap.createScaledBitmap(
                    networkBitmap,
                    networkBitmap.getWidth()/2,
                    networkBitmap.getHeight()/2,
                    false);

            return shrinkedBitmap;

        }

        @Override
        protected void onPostExecute(Bitmap result) {

            bitmapSource = result;

            ivSource.setImageBitmap(result);

            GroupBitmap groupBMResult = convertColorHSVColor(result);

            ivTarget.setImageBitmap(groupBMResult.bitmapDest);
            ivHue.setImageBitmap(groupBMResult.bitmapHue);
            ivSat.setImageBitmap(groupBMResult.bitmapSat);
            ivVal.setImageBitmap(groupBMResult.bitmapVal);
        }

    }

    class GroupBitmap {
        Bitmap bitmapHue;
        Bitmap bitmapSat;
        Bitmap bitmapVal;
        Bitmap bitmapDest;
    };

    //Convert Bitmap from Color to HSV, then HSV to Color
    private GroupBitmap convertColorHSVColor(Bitmap src){

        GroupBitmap convertedGroupBitmap = new GroupBitmap();

        int w = src.getWidth();
        int h = src.getHeight();

        int[] mapSrcColor = new int[w * h];
        int[] mapDestColor= new int[w * h];

        int[] mapHue = new int[w * h];
        int[] mapSat = new int[w * h];
        int[] mapVal = new int[w * h];

        float[] pixelHSV = new float[3];
        /*
         * pixelHSV[0] : Hue (0 .. 360)
         * pixelHSV[1] : Saturation (0...1)
         * pixelHSV[2] : Value (0...1)
         */

        src.getPixels(mapSrcColor, 0, w, 0, 0, w, h);
        /*
         * getPixels (int[] pixels, int offset, int stride, int x, int y, int width, int height)
         * - Returns in pixels[] a copy of the data in the bitmap. Each value is a packed int representing a Color.
         *
         * pixels: The array to receive the bitmap's colors
         * offset: The first index to write into pixels[]
         * stride: The number of entries in pixels[] to skip between rows (must be >= bitmap's width). Can be negative.
         * x:  The x coordinate of the first pixel to read from the bitmap
         * y:  The y coordinate of the first pixel to read from the bitmap
         * width: The number of pixels to read from each row
         * height: The number of rows to read
         *
         */

        int index = 0;
        for(int y = 0; y < h; ++y) {
            for(int x = 0; x < w; ++x) {

                //Convert from Color to HSV
                Color.colorToHSV(mapSrcColor[index], pixelHSV);

                /*
                 * Represent Hue, Saturation and Value in separated color
                 * of R, G, B.
                 */
                mapHue[index] = Color.rgb((int)(pixelHSV[0] * 255/360), 0, 0);
                mapSat[index] = Color.rgb(0, (int)(pixelHSV[1] * 255), 0);
                mapVal[index] = Color.rgb(0, 0, (int)(pixelHSV[2] * 255));

                //Convert back from HSV to Color
                mapDestColor[index] = Color.HSVToColor(pixelHSV);

                index++;
            }
        }

        Bitmap.Config destConfig = src.getConfig();
        /*
         * If the bitmap's internal config is in one of the public formats, return that config,
         * otherwise return null.
         */

        if (destConfig == null){
            destConfig = Bitmap.Config.RGB_565;
        }

        convertedGroupBitmap.bitmapHue = Bitmap.createBitmap(mapHue, w, h, Bitmap.Config.RGB_565);
        convertedGroupBitmap.bitmapSat = Bitmap.createBitmap(mapSat, w, h, Bitmap.Config.RGB_565);
        convertedGroupBitmap.bitmapVal = Bitmap.createBitmap(mapVal, w, h, Bitmap.Config.RGB_565);
        convertedGroupBitmap.bitmapDest = Bitmap.createBitmap(mapDestColor, w, h, destConfig);

        return convertedGroupBitmap;
    }

    //Update HSV according to SeekBar setting
    private GroupBitmap updateHSV(Bitmap src){

        int progressHue = barHue.getProgress() - 256;
        int progressSat = barSat.getProgress() - 256;
        int progressVal = barVal.getProgress() - 256;

        float settingHue = (float)progressHue * 360 / 256;
        float settingSat = (float)progressSat / 256;
        float settingVal = (float)progressVal / 256;

        GroupBitmap convertedGroupBitmap = new GroupBitmap();

        int w = src.getWidth();
        int h = src.getHeight();

        int[] mapSrcColor = new int[w * h];
        int[] mapDestColor= new int[w * h];

        int[] mapHue = new int[w * h];
        int[] mapSat = new int[w * h];
        int[] mapVal = new int[w * h];

        float[] pixelHSV = new float[3];

        src.getPixels(mapSrcColor, 0, w, 0, 0, w, h);

        int index = 0;
        for(int y = 0; y < h; ++y) {
            for(int x = 0; x < w; ++x) {

                //Convert from Color to HSV
                Color.colorToHSV(mapSrcColor[index], pixelHSV);

                //Adjust HSV
                pixelHSV[0] = pixelHSV[0] + settingHue;
                if(pixelHSV[0] < 0){
                    pixelHSV[0] = 0;
                }else if (pixelHSV[0] > 360){
                    pixelHSV[0] = 360;
                }

                pixelHSV[1] = pixelHSV[1] + settingSat;
                if(pixelHSV[1] < 0){
                    pixelHSV[1] = 0;
                }else if (pixelHSV[1] > 1){
                    pixelHSV[1] = 1;
                }

                pixelHSV[2] = pixelHSV[2] + settingVal;
                if(pixelHSV[2] < 0){
                    pixelHSV[2] = 0;
                }else if (pixelHSV[2] > 1){
                    pixelHSV[2] = 1;
                }

                /*
                 * Represent Hue, Saturation and Value in separated color
                 * of R, G, B.
                 */
                mapHue[index] = Color.rgb((int)(pixelHSV[0] * 255/360), 0, 0);
                mapSat[index] = Color.rgb(0, (int)(pixelHSV[1] * 255), 0);
                mapVal[index] = Color.rgb(0, 0, (int)(pixelHSV[2] * 255));

                //Convert back from HSV to Color
                mapDestColor[index] = Color.HSVToColor(pixelHSV);

                index++;
            }
        }

        Bitmap.Config destConfig = src.getConfig();
        /*
         * If the bitmap's internal config is in one of the public formats, return that config,
         * otherwise return null.
         */

        if (destConfig == null){
            destConfig = Bitmap.Config.RGB_565;
        }

        convertedGroupBitmap.bitmapHue = Bitmap.createBitmap(mapHue, w, h, Bitmap.Config.RGB_565);
        convertedGroupBitmap.bitmapSat = Bitmap.createBitmap(mapSat, w, h, Bitmap.Config.RGB_565);
        convertedGroupBitmap.bitmapVal = Bitmap.createBitmap(mapVal, w, h, Bitmap.Config.RGB_565);
        convertedGroupBitmap.bitmapDest = Bitmap.createBitmap(mapDestColor, w, h, destConfig);

        return convertedGroupBitmap;
    }
}
