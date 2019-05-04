package com.asksira.imagepickersheetdemo.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.asksira.imagepickersheetdemo.R;

public class GridViewAdapter extends BaseAdapter {

    // Declare variables
    private FragmentActivity activity;
    private String[] filepath;
    private String[] filename;

    private static LayoutInflater inflater = null;

    public GridViewAdapter(FragmentActivity disimpanFragment, String[] filePathStrings, String[] fileNameStrings) {

        this.activity = disimpanFragment;
        this.filepath = filePathStrings;
        this.filename = fileNameStrings;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return filepath.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View vi = view;

        if (view == null) {
            vi = inflater.inflate(R.layout.item_single_grid, null);

            TextView text = (TextView) vi.findViewById(R.id.text);
            ImageView image = (ImageView) vi.findViewById(R.id.image);

            text.setText(filename[i]);
            Bitmap bmp = BitmapFactory.decodeFile(filepath[i]);
            image.setImageBitmap(bmp);

        }
        return vi;
    }

}
