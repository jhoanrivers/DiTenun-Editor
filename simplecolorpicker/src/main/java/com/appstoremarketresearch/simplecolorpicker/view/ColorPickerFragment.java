package com.appstoremarketresearch.simplecolorpicker.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appstoremarketresearch.simplecolorpicker.R;

/**
 * A placeholder fragment containing a simple view.
 */
/*
 * Extends android.app.Fragment not android.support.v4.app.Fragment, otherwise get:
 * android.app.Fragment$InstantiationException: Trying to instantiate a class
 * com.appstoremarketresearch.simplecolorpicker.view.ColorPickerFragment that is not a Fragment
 */
public class ColorPickerFragment extends Fragment {

    public ColorPickerFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_color_picker, container, false);
    }
}
