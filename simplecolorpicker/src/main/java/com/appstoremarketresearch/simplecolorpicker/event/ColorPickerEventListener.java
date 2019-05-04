package com.appstoremarketresearch.simplecolorpicker.event;

/**
 * ColorPickerEventListener
 */
public interface ColorPickerEventListener {

    /**
     * onColorSelected
     *
     * @param colorValue the numeric value of the color (not to be confused with the resource
     *                   id), which can by converted to the hex value of the color by calling:
     *                   <pre>String hexColor = "#" + Integer.toHexString(colorValue);</pre>
     */
    void onColorSelected(int colorValue);
}
