package com.appstoremarketresearch.simplecolorpicker.controller;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.appstoremarketresearch.simplecolorpicker.R;
import com.appstoremarketresearch.simplecolorpicker.event.ColorPickerEventType;

import java.lang.reflect.Method;

/*
 * Change extension from AppCompatActivity to Activity, otherwise get:
 * java.lang.IllegalStateException: You need to use a Theme.AppCompat
 * theme (or descendant) with this activity.
 */
public class ColorPickerActivity extends Activity {

    /**
     * onColorSelected
     */
    public void onColorSelected(View view) {

        Drawable background = view.getBackground();

        if (background instanceof ColorDrawable) {

            int colorValue = ((ColorDrawable)background).getColor();

            Intent requestIntent = getIntent();
            String responseMethodName = requestIntent.getStringExtra("responseMethod");

            Intent responseIntent = new Intent();
            responseIntent.setAction(ColorPickerEventType.COLOR_SELECTED.name());
            responseIntent.putExtra("colorValue", colorValue);

            if (responseMethodName == null ||
                responseMethodName.equals("sendBroadcast")) {
                sendBroadcast(responseIntent);
            }
            else {
                setResult(RESULT_OK, responseIntent);
            }

            // close the color picker
            this.finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_picker);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_color_picker, menu);
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
}
