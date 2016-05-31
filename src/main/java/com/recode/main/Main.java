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
        System.out.println(args[0]);
        String dirPath = ".";
        if (args.length == 2) {
            dirPath = args[1];
            System.out.println(dirPath);
        }
        System.out.println("处理主路径：" + dirPath);
        File dir = new File(dirPath);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.invoke(new RecodeRecursiveAction(Folder.getFolderFromDirectory(dir)));
        System.out.println("解密完毕");
    }
}
