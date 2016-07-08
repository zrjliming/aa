package com.example.ruijie.myapplication.myimagedemo;

import org.apache.http.util.ByteArrayBuffer;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Created by ruijie on 2016/6/22.
 */
public class IoUtils {
    public static String  getJson(InputStream in){


        try {
            ByteArrayOutputStream mbo=new ByteArrayOutputStream();
            byte[] by =new byte[1204];
            int len=0;
            while((len=in.read(by))!=-1){
               mbo.write(by,0,len);
            }
            return  mbo.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
