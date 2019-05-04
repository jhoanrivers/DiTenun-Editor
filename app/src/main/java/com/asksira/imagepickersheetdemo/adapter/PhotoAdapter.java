package com.asksira.imagepickersheetdemo.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.asksira.imagepickersheetdemo.R;

public class PhotoAdapter extends BaseAdapter {

    private Context mContext;

    public PhotoAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }



    public Integer[] mThumbIds = {
            R.drawable.gambar1, R.drawable.gambar2,
            R.drawable.gambar1, R.drawable.gambar5,
            R.drawable.gambar1, R.drawable.gambar2,
            R.drawable.gambar4, R.drawable.gambar1,
            R.drawable.gambar4, R.drawable.gambar3,
            R.drawable.gambar4, R.drawable.gambar5,
            R.drawable.gambar1, R.drawable.gambar2,
            R.drawable.gambar5, R.drawable.gambar1,
            R.drawable.gambar2, R.drawable.gambar1,
            R.drawable.gambar2, R.drawable.gambar5,
            R.drawable.gambar1, R.drawable.gambar2
    };
}
