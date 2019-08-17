package com.thinkpalm.pixabay.utils;

import android.os.Environment;
import com.thinkpalm.pixabay.core.App;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * Created by Jemsheer K D on 14 December, 2018.
 * Package com.thinkpalm.pixabay.utils
 * Project Pixabay
 */
public class FileUtil {

    public static void copyDB(){

        try {
            File file = Environment.getExternalStorageDirectory();
            File data = Environment.getDataDirectory();

            File folder = new File(Environment.getExternalStorageDirectory()+"/RAT");
            if(!folder.exists()){
                folder.mkdirs();
            }
            if (file.canWrite()) {
                String currentPath = App.getInstance().getDatabasePath("rat_database.db").getAbsolutePath();
                String copyPath = "/RAT/rat_database.db";

                File currentDB = new File(currentPath);
                File backupDB = new File(file, copyPath);

                if (currentDB.exists()) {
                    copyFile(currentDB, backupDB);
                }

                currentPath = App.getInstance().getDatabasePath("rat_database.db-shm").getAbsolutePath();
                copyPath = "/RAT/rat_database.db-shm";

                currentDB = new File(currentPath);
                backupDB = new File(file, copyPath);

                if (currentDB.exists()) {
                    copyFile(currentDB, backupDB);
                }

                currentPath = App.getInstance().getDatabasePath("rat_database.db-wal").getAbsolutePath();
                copyPath = "/RAT/rat_database.db-wal";

                currentDB = new File(currentPath);
                backupDB = new File(file, copyPath);

                if (currentDB.exists()) {
                    copyFile(currentDB, backupDB);
                }
            }
        } catch (Exception e) {

        }
    }

    public static void copyFile(File src, File dst) {
        try {
            FileChannel inChannel = new FileInputStream(src).getChannel();
            FileChannel outChannel = new FileOutputStream(dst).getChannel();
            try {
                inChannel.transferTo(0, inChannel.size(), outChannel);
            } finally {
                if (inChannel != null)
                    inChannel.close();
                if (outChannel != null)
                    outChannel.close();
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public static void copyFile(String srcPath, String dstPath) {

        File src = new File(srcPath);
        File dst = new File(dstPath);

        try {
            FileChannel inChannel = new FileInputStream(src).getChannel();
            FileChannel outChannel = new FileOutputStream(dst).getChannel();
            try {
                inChannel.transferTo(0, inChannel.size(), outChannel);
            } finally {
                if (inChannel != null)
                    inChannel.close();
                if (outChannel != null)
                    outChannel.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
