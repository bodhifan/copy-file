package com.recode.commons;

import java.io.File;
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
 * bofan     2016/5/30 - Creation
 */
public class Folder {

    public List<Folder> subFolders = new ArrayList<Folder>();
    public List<Document> documents = new ArrayList<Document>();

    public Folder(List<Folder> folders, List<Document> documents)
    {
        this.subFolders=folders;
        this.documents=documents;
    }
    public static Folder getFolderFromDirectory(File dir)
    {
        List<Folder> subFolders = new ArrayList<Folder>();
        List<Document> documents = new ArrayList<Document>();

        for (File file : dir.listFiles())
        {
            if (file.isDirectory())
                subFolders.add(getFolderFromDirectory(file));
            else {
                if (file.getName().endsWith(".java"))
                    documents.add(Document.getDocFromFile(file));
            }
        }

        return new Folder(subFolders,documents);
    }
}
