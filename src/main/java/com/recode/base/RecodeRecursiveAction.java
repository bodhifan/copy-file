package com.recode.base;

import com.recode.commons.Document;
import com.recode.commons.Folder;
import com.recode.utils.FileUtils;
import com.recode.utils.ProcessUtils;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveAction;

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
@SuppressWarnings("Since15")
public class RecodeRecursiveAction extends RecursiveAction {


    private Folder folder;
    private boolean isDecode; // 是否解密
    public RecodeRecursiveAction(Folder folder,boolean decode)
    {
        this.folder = folder;
        this.isDecode = decode;
    }
    public RecodeRecursiveAction(Folder folder)
    {
        this(folder,true);
    }
    protected void compute() {

        String promptMsg = "解码文件:";
        if (!isDecode)
            promptMsg = "加密文件:";

        List<RecodeRecursiveAction> forks = new LinkedList<RecodeRecursiveAction>();
        for (Folder subFolder : folder.subFolders)
        {
            RecodeRecursiveAction newAction = new RecodeRecursiveAction(subFolder,isDecode);
            forks.add(newAction);
            newAction.fork();
        }

        for (Document document : folder.documents)
        {
            System.out.println(promptMsg+document.orignFile.getPath());
            String copyFile = FileUtils.getCopyFile(document.orignFile,".txt");
            File desFile = new File(FileUtils.getCopyFile(document.orignFile,".txt"));

            try {
                FileUtils.copyFile(document.orignFile,desFile);

                String srcFileName = document.orignFile.getName();

                document.orignFile.delete();
                if (isDecode)
                    ProcessUtils.execCommand(String.format("ren %s %s",copyFile,srcFileName));
                else {
                    File copyedFile = new File(copyFile);
                    File renamedFile = new File(document.orignFile.getPath());
                    copyedFile.renameTo(renamedFile);
                }
                document.orignFile = desFile;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for (RecodeRecursiveAction task : forks)
        {
            task.join();
        }
    }

}
