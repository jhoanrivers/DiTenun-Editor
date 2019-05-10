package com.asksira.imagepickersheetdemo.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.asksira.imagepickersheetdemo.R;

import uk.co.senab.photoview.PhotoViewAttacher;

public class SpecificKristikActivity extends AppCompatActivity {


    TextView text;
    ImageView imageview;
    boolean status = false;
    String [] filepath;
    String[] filename;
    int position;
    PhotoViewAttacher mAtacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_kristik);

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        position = i.getExtras().getInt("position");
        filepath = i.getStringArrayExtra("filepath");
        filename = i.getStringArrayExtra("filename");
        getSupportActionBar().setTitle(filename[position]);


        text = findViewById(R.id.text);
        text.setText(filename[position]);
        imageview =findViewById(R.id.image);
        mAtacher = new PhotoViewAttacher(imageview);

        //change filepath target menjadi gambar bitmap
        Bitmap bmp = BitmapFactory.decodeFile(filepath[position]);

        ContextCompat.getColor(this, R.color.white);

        imageview.setImageBitmap(bmp);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.spesific_image_menu, menu);
        return true;
    }


    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

}
