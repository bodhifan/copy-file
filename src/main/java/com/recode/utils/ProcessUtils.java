package com.recode.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * DESCRIPTION
 * TODO
 * <p>
 * NOTES
 * <other useful comments, qualifications, etc.>
 * <p>
 * MODIFIED    (MM/DD/YY)
 * bofan     2016/5/31 - Creation
 */
public class ProcessUtils {

    public static String execCommand(String command){
        Runtime runtime = Runtime.getRuntime();
        String errorMSG = "";

        try {
            String[] args = new String[]{"cmd","/c",command};

            Process pro = runtime.exec(args);

            InputStream in = pro.getErrorStream();
            InputStreamReader isr = new InputStreamReader(in);

            BufferedReader br = new BufferedReader(isr);

            String line = null;

            while ( (line = br.readLine()) != null){
                errorMSG += line+"\n";
                System.out.println(errorMSG);
            }

            //检查命令是否失败
            try {
                if(pro.waitFor()!=0){
                    System.err.println("exit value:" + pro.exitValue());
                }
            } catch (InterruptedException e) {
                System.err.println();
                e.printStackTrace();

            }

        } catch (IOException e) {
            System.out.println("error Message:"+e.getMessage());
            e.printStackTrace();
        } finally{
            return errorMSG;
        }

    }
}
