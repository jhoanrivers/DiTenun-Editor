package com.asksira.imagepickersheetdemo.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.asksira.imagepickersheetdemo.R;
import com.asksira.imagepickersheetdemo.adapter.PhotoAdapter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import uk.co.senab.photoview.PhotoViewAttacher;

public class SpecificSingleImage extends AppCompatActivity {


    TextView text;
    ImageView imageview;
    boolean status = false;
    String [] filepath;
    String[] filename;
    int position;
    PhotoViewAttacher mAtacher;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_single_image);

        //showing back button in the toolbar

        //Toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


        // Retrieve data from MainActivity on GridView item click
        Intent i = getIntent();
        position = i.getExtras().getInt("position");
        filepath = i.getStringArrayExtra("filepath");
        filename = i.getStringArrayExtra("filename");

        getSupportActionBar().setTitle(filename[position]);

        //above API 23
        //myToolbar.setTitleTextColor(0xFFFFFFF);
        text = (TextView) findViewById(R.id.text);
        text.setText(filename[position]);
        imageview = (ImageView) findViewById(R.id.image);
        mAtacher = new PhotoViewAttacher(imageview);

        Bitmap bmp = BitmapFactory.decodeFile(filepath[position]);
        imageview.setImageBitmap(bmp);

        ContextCompat.getColor(this, R.color.white);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.spesific_image_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.ubahkristik:
                Toast.makeText(SpecificSingleImage.this, "Ubah Kristik Pressed", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.share:
                //Toast.makeText(SpecificSingleImage.this, "Ubah Kristik Pressed", Toast.LENGTH_SHORT).show();

                final Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("image/jpg");
                final File photoFile = new File(getFilesDir(), filepath[position]);
                shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(photoFile));
                startActivity(Intent.createChooser(shareIntent, "Share image using"));
                return true;
            case R.id.detail:
                Toast.makeText(SpecificSingleImage.this, "Ubah Kristik Pressed", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
