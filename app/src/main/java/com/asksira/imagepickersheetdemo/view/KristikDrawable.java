package com.asksira.imagepickersheetdemo.view;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class KristikDrawable extends Drawable {

    final static int CELL_WIDTH = 20;
    final static int SPACE = 2;
    final static int MARGIN = 2;

    final Bitmap kristik;
    final int columnCount;
    final int rowCount;

    final int intrinsicHeight;
    final int intrinsicWidth;

    final Paint cellPaint = new Paint();
    final Rect cellRect = new Rect(0, 0, CELL_WIDTH, CELL_WIDTH);

    public KristikDrawable(Bitmap kristik) {
        this.kristik = kristik;
        this.columnCount = kristik.getWidth();
        this.rowCount = kristik.getHeight();

        intrinsicHeight = rowCount * CELL_WIDTH + (rowCount - 1) * SPACE + MARGIN * 2;
        intrinsicWidth = columnCount * CELL_WIDTH + (columnCount - 1) * SPACE + MARGIN * 2;
    }

    @Override
    public void setAlpha(int alpha) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getOpacity() {
        return PixelFormat.OPAQUE;
    }

    @Override
    public int getIntrinsicHeight() {
        return intrinsicHeight;
    }

    @Override
    public int getIntrinsicWidth() {  return intrinsicWidth; }

    @Override
    public void draw(@NonNull Canvas canvas) {
        int rootRestoreId = canvas.save();
        canvas.translate(MARGIN, MARGIN);

        for (int r = 0; r < rowCount; r++) {

            canvas.translate(0, CELL_WIDTH + SPACE);

            int rowRestoreId = canvas.save();

            for (int c = 0; c < columnCount; c++) {
                cellPaint.setColor(kristik.getPixel(c, r));

                canvas.translate(CELL_WIDTH + SPACE, 0);
                canvas.drawRect(cellRect, cellPaint);
            }

            canvas.restoreToCount(rowRestoreId);
        }

        canvas.restoreToCount(rootRestoreId);
    }
}
