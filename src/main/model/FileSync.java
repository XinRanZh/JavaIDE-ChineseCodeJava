package model;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileSync {
    //Read and write the file
    protected static String location;
    protected static String filename;
    public static StringBuffer contain; //What in the .java file

    public FileSync(StringBuffer con, String loc,String filename) {
        contain = con;
        location = loc;
        FileSync.filename = filename;
    }

    public FileSync(String loc,String filename) {
        contain = new StringBuffer();
        location = loc;
        FileSync.filename = filename;
    }

    private static String osdetector(String loc) {
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {
            return loc;
        } else {
            return loc.replaceAll("\\\\","/");
        }
    }

    public StringBuffer getCon() {
        return contain;
    }

    public String getLoc() {
        return location;
    }

    public String getFilename() {
        return filename;
    }

    public void setCon(StringBuffer con) {
        contain = con;
    }

    public void setLoc(String loc) {
        location = loc;
    }

    public void setFilename(String filename) {
        FileSync.filename = filename;
    }

    public static void getFile() throws IOException {
        InputStream inputS;
        File tmpfile = new File(osdetector(location + filename));
        String tmpLoc = tmpfile.getCanonicalPath();
        inputS = new FileInputStream(tmpLoc);
        String tmpLine;
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputS, StandardCharsets.UTF_8));
        tmpLine = reader.readLine();
        while (tmpLine != null) {
            contain.append(tmpLine);
            contain.append("\n");
            tmpLine = reader.readLine();
        }
        reader.close();
        inputS.close();
    }

    public void setFile(String fname) throws IOException {
        FileWriter fileWriter;
        File tmpfile = new File(location + fname);
        String tmploc = tmpfile.getCanonicalPath();
        fileWriter = new FileWriter(tmploc);
        fileWriter.write(String.valueOf(contain));
        fileWriter.close();
    }
}

