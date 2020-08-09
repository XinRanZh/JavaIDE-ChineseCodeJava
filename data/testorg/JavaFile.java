package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JavaFile {
    ArrayList<String> packagename;
    String filename;
    String location;
    FileSync fileSync;
    Convert convert;
    StringBuffer fileContain;

    public JavaFile(String filename,ArrayList<String> packagename,Convert convert) {
        this.filename = filename;
        this.packagename = packagename;
        this.convert = convert;
        location = "";
        for (String s : packagename) {
            location = location + s + "\\";
        }
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {
            this.location = location;
        } else {
            String loclinux = location.replaceAll("\\\\","/");
            this.location = loclinux;
        }
    }

    public JavaFile() {
        filename = "";
        packagename = new ArrayList<String>();
    }

    public void setname(String filename) {
        this.filename = filename;
    }

    //public void addPakageName(String packagename) {
    //    this.packagename.add(packagename);
    //}

    public void setLocation(String location) {
        this.location = location;
    }

    public void readFile() {
        try {
            fileSync = new FileSync(location,filename + ".javaCH");
            fileSync.getFile();
            setFileContain();
        } catch (IOException e) {
            e.printStackTrace();
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
            convert.dictionaryConvert();
            fileSync.setCon(convert.showResult());
            fileSync.setFile(filename + ".java");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
