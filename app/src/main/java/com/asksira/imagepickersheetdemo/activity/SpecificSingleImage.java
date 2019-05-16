package com.asksira.imagepickersheetdemo.activity;

import android.content.Context;
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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.asksira.imagepickersheetdemo.App;
import com.asksira.imagepickersheetdemo.FileUtils;
import com.asksira.imagepickersheetdemo.Model.APIModule;
import com.asksira.imagepickersheetdemo.Model.Kristik;
import com.asksira.imagepickersheetdemo.R;
import com.asksira.imagepickersheetdemo.adapter.PhotoAdapter;
import com.asksira.imagepickersheetdemo.network.TenunNetworkInterface;
import com.asksira.imagepickersheetdemo.remote.FileService;
import com.asksira.imagepickersheetdemo.util.BitmapUtils;
import com.asksira.imagepickersheetdemo.view.KristikDrawable;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Multipart;
import uk.co.senab.photoview.PhotoViewAttacher;

public class SpecificSingleImage extends AppCompatActivity {


    @BindView(R.id.progress_bar)
    public ProgressBar progressBar;

    @BindView(R.id.btnsavekristik)
    public Button buttonSave;

    @Inject
    TenunNetworkInterface tenunNetworkInterface;

    @Inject
    Realm realm;


    FileService fileService;
    TextView text;
    ImageView imageview;
    boolean status = false;
    String [] filepath;
    String[] filename;
    int position;
    PhotoViewAttacher mAtacher;

    private byte[] motifBytes;
    private Bitmap kristikBitmap;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_single_image);

        App.get(getApplicationContext()).getInjector().inject(this);

        ButterKnife.bind(this);
        progressBar.setVisibility(View.GONE);
        buttonSave.setVisibility(View.GONE);

        //showing back button in the toolbar
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


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

        //change filepath target menjadi gambar bitmap
        Bitmap bmp = BitmapFactory.decodeFile(filepath[position]);

        motifBytes = convertBitmapToByteArray(bmp);

        imageview.setImageBitmap(bmp);

        ContextCompat.getColor(this, R.color.white);


        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveImage(kristikBitmap);
                Intent intent = new Intent(SpecificSingleImage.this,DashboardActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public void saveImage(Bitmap image){
        FileOutputStream fout = null;
        File filepath = Environment.getExternalStorageDirectory();

        File dirfile = new File(filepath.getAbsoluteFile()+"/DE kristik/");
        dirfile.mkdirs();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyymmsshhmmss");
        String date = simpleDateFormat.format(new Date());
        String name = "kristik"+date+".jpg";
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

    public byte[] convertBitmapToByteArray( Bitmap bitmap) {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream(bitmap.getWidth() * bitmap.getHeight());
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, buffer);
        return buffer.toByteArray();
    }

    private void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideLoading() {
        progressBar.setVisibility(View.GONE);
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
                generateKristik();

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
    private void showKristikPreview(Bitmap bitmap) {
        imageview.setImageBitmap(bitmap);
    }


    private void generateKristik() {
        int kristikSize = 1;
        int colorSize = 20;
        requestKristikFromServer(kristikSize, colorSize, motifBytes);
        showLoading();
    }
    private void requestKristikFromServer(int squareSize, int colorAmount, byte[] motifBytes) {
        RequestBody photoBody = RequestBody.create(MediaType.parse("image/*"), motifBytes);
        MultipartBody.Part photoPart = MultipartBody.Part.createFormData("img_file", "motif.jpg", photoBody);

        tenunNetworkInterface.kristikEditor(APIModule.ACCESS_TOKEN_TEMP, squareSize, colorAmount, photoPart).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(SpecificSingleImage.this, "Berhasil Digenerate", Toast.LENGTH_SHORT).show();
                    kristikBitmap = BitmapFactory.decodeStream(response.body().byteStream());
                    imageview.setImageBitmap(kristikBitmap);


                }

                hideLoading();
                buttonSave.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(SpecificSingleImage.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                hideLoading();
            }
        });
    }




    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }


}
