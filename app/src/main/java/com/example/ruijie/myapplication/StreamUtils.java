package com.example.ruijie.myapplication;

import android.support.v4.view.ViewPager;
import android.view.ViewParent;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ruijie on 2016/6/14.
 */
public class StreamUtils {

    public static String streamToString(InputStream inputStream){
        ByteArrayOutputStream bs=new ByteArrayOutputStream();
        byte[] by=new byte[1024];
        int len=0;
        try {
            while((len=inputStream.read(by))!=-1){
               bs.write(by,0,len);
            }
            String str=bs.toString();
            return str;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;

    }


    /**
     * 用buffer
     * 还的用一个StringBuffer进缓存的
     * 而用byteArrayOutputStream 可以直接进行变成字符串
     * @param in
     * @return
     */
    public static String BrToString (InputStream in){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int len=0;
        byte[] by = new byte[1024];
        try {
            while((len=in.read(by))!=0){
               byteArrayOutputStream.write(by,0,len);
            }
            String string = byteArrayOutputStream.toString();

            return string;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
