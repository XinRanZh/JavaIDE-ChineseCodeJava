package model;

import java.io.*;

public class FileSync {
    //Read and write the file
    protected static String location;
    protected static String filename;
    public static StringBuffer contain; //What in the .java file

    public FileSync(StringBuffer con, String loc,String filename) {
        this.contain = con;
        this.location = loc;
        this.filename = filename;
    }

    public FileSync(String loc,String filename) throws IOException {
        this.contain = new StringBuffer();
        this.location = loc;
        this.filename = filename;
    }

    private static String osdetector(String loc) {
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {
            return loc;
        } else {
            String loclinux = loc.replaceAll("\\\\","/");
            return loclinux;
        }
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
        InputStream inputS;
        File tmpfile = new File(osdetector(location + filename));
        String tmpLoc = tmpfile.getCanonicalPath();
        inputS = new FileInputStream(tmpLoc);
        String tmpLine;
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputS,"UTF-8"));
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
        String os = System.getProperty("os.name");
        FileWriter fileWriter;
        File tmpfile = new File(location + fname);
        String tmploc = tmpfile.getCanonicalPath();
        fileWriter = new FileWriter(tmploc);
        fileWriter.write(String.valueOf(contain));
        fileWriter.close();
    }
}

