package com.appstoremarketresearch.simplecolorpicker.event;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.AbstractSet;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * ColorPickerEventNotifier
 */
public class ColorPickerEventNotifier
    extends android.content.BroadcastReceiver
    implements ColorPickerEventListener
{
    private static AbstractSet<ColorPickerEventListener> mColorPickerEventListeners =
            new CopyOnWriteArraySet<>();

    @Override
    public void onColorSelected(int colorValue) {
        for (ColorPickerEventListener listener : mColorPickerEventListeners) {
            listener.onColorSelected(colorValue);
        }
    }

    @Override
    public void onReceive(
        Context context,
        Intent intent) {

        String action = intent.getAction();
        Bundle extras = intent.getExtras();

        if (ColorPickerEventType.COLOR_SELECTED.name().equals(action)) {
            int colorValue = extras.getInt("colorValue");
            onColorSelected(colorValue);
        }
    }

    /**
     * mAppEventListeners
     */
    public static void subscribe(ColorPickerEventListener listener) {
        if (listener != null) {
            mColorPickerEventListeners.add(listener);
        }
    }

    /**
     * unsubscribe
     */
    public static void unsubscribe(ColorPickerEventListener listener) {
        if (listener != null) {
            mColorPickerEventListeners.remove(listener);
        }
    }
}
