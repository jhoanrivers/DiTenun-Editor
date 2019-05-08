package com.asksira.imagepickersheetdemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public final class FileUtils {

    public static String CreateTempFile(String prefix, String suffix, File directory, byte[] bytes) {
        try {
            File tempFile = File.createTempFile(prefix, suffix, directory);
            FileOutputStream outputStream = new FileOutputStream(tempFile);
            outputStream.write(bytes);
            outputStream.close();

            return tempFile.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    public static byte[] LoadImageFile(String path, boolean needDeleted) {
        try {
            File file = new File(path);
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] motifBytes = new byte[(int) file.length()];

            fileInputStream.read(motifBytes);

            fileInputStream.close();

            if (needDeleted) {
                file.delete();
            }

            return motifBytes;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
