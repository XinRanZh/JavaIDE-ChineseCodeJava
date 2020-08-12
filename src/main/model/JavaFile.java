package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JavaFile {
    String filename;
    String location;
    FileSync fileSync;
    Convert convert;
    StringBuffer fileContain;

    public JavaFile(String filename,Convert convert) {
        this.filename = filename;
        this.convert = convert;
        location = "";
        String os = System.getProperty("os.name");
        if (!os.toLowerCase().startsWith("win")) {
            this.location = location.replaceAll("\\\\","/");
        }
    }

    public JavaFile() {
        filename = "";
    }

    public void setname(String filename) {
        this.filename = filename;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean readFile() {
        try {
            fileSync = new FileSync(location,filename + ".javaCH");
            fileSync.getFile();
            setFileContain();
            System.out.println("Found");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Not Found");
            return false;
        }
    }

    public void setFile() {
        try {
            fileSync.setCon(fileContain);
            fileSync.setFile(filename + ".javaCH");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setFileContain() {
        this.fileContain = fileSync.getCon();
    }

    public void setFileContain(String contain) {
        StringBuffer sb = new StringBuffer();
        sb.append(contain);
        this.fileContain = sb;
    }


    public void convertandOutPut(Convert convert) {
        try {
            readFile();
            convert.setTmpText(this.fileContain);
            convert.dictionaryConvert();
            fileSync.setCon(convert.showResult());
            fileSync.setFile(filename + ".java");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getFilename() {
        return this.filename;
    }

}
