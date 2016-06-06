package com.recode.main;

import com.recode.base.RecodeRecursiveAction;
import com.recode.commons.Folder;

import java.io.File;
import java.util.concurrent.ForkJoinPool;

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
public class Main {
    public static void main(String[] args)
    {

        String dirPath = "";
        boolean decode = true;

        if (args.length >= 1) {
            dirPath = args[0];
            if (!new File(dirPath).exists())
            {
                System.out.println("目录不存在：" + dirPath);
                return;
            }
        }
        if (args.length >= 2)
        {
            String isDecode = args[1];
            if (isDecode.matches("[-]?[0-9]")) {
                Integer flag = Integer.parseInt(isDecode);
                if (flag < 0) decode = false;
            }
            if (isDecode.equals("false"))
                decode = false;
        }

        String msg = "解密";
        if (!decode) msg="加密";
       // dirPath="D:\\workspace\\projects\\HttpLogin";
        System.out.println(msg + "目录:\r\n"+ dirPath);
        File dir = new File(dirPath);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.invoke(new RecodeRecursiveAction(Folder.getFolderFromDirectory(dir),decode));
        System.out.println(msg+"完成！");
    }
}
