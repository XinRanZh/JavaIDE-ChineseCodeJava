package model;

import jdk.internal.util.xml.impl.Input;

import java.io.*;

public class FileSync {
    protected static String location;
    public static StringBuffer contain; //What in the .java file
    
    public FileSync(StringBuffer con, String loc) {
        this.contain = con;
        this.location = loc;
    }

    public FileSync(String loc) throws IOException {
        this.contain = new StringBuffer();
        this.location = loc;
    }


    public StringBuffer getCon() {
        return this.contain;
    }

    String getLoc() {
        return this.location;
    }

    void setCon(StringBuffer con) {
        this.contain = con;
    }

    void setLoc(String loc) {
        this.location = loc;
    }

    public static void getFile() throws IOException {
        InputStream inputS = new FileInputStream(location);
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

