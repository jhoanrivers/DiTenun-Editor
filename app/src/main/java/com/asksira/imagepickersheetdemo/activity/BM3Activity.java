package com.asksira.imagepickersheetdemo.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.asksira.imagepickersheetdemo.R;

public class BM3Activity extends AppCompatActivity {

    private ImageView collageImage;
    private ImageView img,img1,img2,img4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bm3);

        img = findViewById(R.id.imageView);
        img1 = findViewById(R.id.imageView1);
        img2 = findViewById(R.id.imageView2);
        img4 = findViewById(R.id.imageView4);



        collageImage = (ImageView)findViewById(R.id.imageView3);

        Button combineImage = (Button)findViewById(R.id.combineimage);
        combineImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img.buildDrawingCache();
                img1.buildDrawingCache();
                img2.buildDrawingCache();
                img4.buildDrawingCache();

                Bitmap image = img.getDrawingCache();
                Bitmap image1 = img1.getDrawingCache();
                Bitmap image2 = img2.getDrawingCache();
                Bitmap image4 = img4.getDrawingCache();
                Bitmap mergedImages = createSingleImageFromMultipleImages(image,image1,image2,image4);

                collageImage.setImageBitmap(mergedImages);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.spesific_image_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
// Handle action bar item clicks here. The action bar will
// automatically handle clicks on the Home/Up button, so long
// as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

//noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private Bitmap createSingleImageFromMultipleImages(Bitmap image, Bitmap image1,Bitmap image2,Bitmap image3){

        Bitmap result = Bitmap.createBitmap(image.getWidth(), image.getHeight(), image.getConfig());
        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(image, 0f, 0f, null);
        canvas.drawBitmap(image1, 50f, 0f, null);
        canvas.drawBitmap(image2,50f,50f,null);
        canvas.drawBitmap(image3,0f,50f,null);
        return result;
    }
}
