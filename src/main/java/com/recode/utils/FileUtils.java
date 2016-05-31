package com.recode.utils;

import java.io.*;

/**
 * DESCRIPTION
 * TODO
 * <p>
 * NOTES
 * <other useful comments, qualifications, etc.>
 * <p>
 * MODIFIED    (MM/DD/YY)
 * bofan     2016/5/30 - Creation
 */
public class FileUtils {

    private static String COPY_EXT_NAME = ".txt";
    public static String getFileContent(File file) throws IOException {
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while((line=reader.readLine()) != null)
            {
                builder.append(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    public static boolean copyFile(File src,File des) throws IOException {

        BufferedReader reader = null;
        PrintWriter writer = null;
        try {
            reader = new BufferedReader(new FileReader(src));
            writer = new PrintWriter(des);
            String line;
            while((line=reader.readLine()) != null)
            {
                writer.write(line);
                writer.write("\r\n");
            }

        } catch (FileNotFoundException e) {
            return  false;
        }
        finally {
            if (reader != null)
                reader.close();
            if (writer != null)
                writer.close();
        }

        return  true;
    }

    public static String getFilePath(File file)
    {
        return file.getPath();
    }

    public static String getFileName(File file)
    {
        return file.getName();
    }


    public static String getCopyFile(File orignFile) {

        String path = orignFile.getParent();
        String name = orignFile.getName();
        name = name.substring(0,name.indexOf("."));
        name += COPY_EXT_NAME;
        return  path+File.separatorChar+name;
    }

}
