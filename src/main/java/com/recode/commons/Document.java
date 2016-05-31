package com.recode.commons;

import java.io.File;
import java.io.IOException;

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
public class Document {

    public File orignFile;

    public Document(File file)
    {
        orignFile = file;
    }
    public static Document getDocFromFile(File file)
    {
        return new Document(file);
    }
}
