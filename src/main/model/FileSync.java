package model;

import jdk.internal.util.xml.impl.Input;

import java.io.*;

public class FileSync {
    protected static String location;
    protected static String filename;
    public static StringBuffer contain; //What in the .java file
    
    public FileSync(StringBuffer con, String loc,String filename) {
        this.contain = con;
        this.location = loc;
        this.filename = filename + ".java";
    }

    public FileSync(String loc,String filename) throws IOException {
        this.contain = new StringBuffer();
        this.location = loc;
        this.filename = filename + ".java";
    }


    public StringBuffer getCon() {
        return this.contain;
    }

    public String getLoc() {
        return this.location;
    }

    public String getFilename() {
        return this.filename;
    }

    public void setCon(StringBuffer con) {
        this.contain = con;
    }

    public void setLoc(String loc) {
        this.location = loc;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public static void getFile() throws IOException {
        InputStream inputS = new FileInputStream(location + filename);
        String tmpLine;
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputS));
        tmpLine = reader.readLine();
        while (tmpLine != null) {
            contain.append(tmpLine);
            contain.append("\n");
            tmpLine = reader.readLine();
        }
        reader.close();
        inputS.close();
    }

    public void setFile() throws IOException {
        FileWriter fileWriter = new FileWriter(location);
        fileWriter.write(String.valueOf(contain));
        fileWriter.close();
    }
}

