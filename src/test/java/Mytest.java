import com.recode.utils.FileUtils;
import com.recode.utils.ProcessUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
public class Mytest {

    public static void main(String[] args) throws IOException {


        File file = new File("D:\\workspace\\projects\\copy-file\\src\\main\\java\\com\\recode\\utils\\Test1.java");
        System.out.println(FileUtils.getCopyFile(file));
        System.out.println(file.getParent());

        File desFile = new File(FileUtils.getCopyFile(file));

        FileUtils.copyFile(file,desFile);

        file.delete();


       // ProcessUtils.ExecuteCmd("cmd /c ren D:\\workspace\\projects\\copy-file\\src\\main\\java\\com\\recode\\utils\\Test1.txt Test1.java");

        System.out.println(ProcessUtils.execCommand("ren D:\\workspace\\projects\\copy-file\\src\\main\\java\\com\\recode\\utils\\Test1.txt Test1.java"));
    }
}
