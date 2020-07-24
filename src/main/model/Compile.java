package model;

import java.io.*;

public class Compile {
    StringBuffer tmpText;
    String tmpLoc;
    String tmpfileName;

    public Compile(StringBuffer stringBuffer, String tmpLoc, String fileName) throws IOException {
        this.tmpText = stringBuffer;
        this.tmpLoc = tmpLoc + "debug\\";
        this.tmpfileName = fileName;
        creatTmpFile();
    }

    void creatTmpFile() throws IOException {
        File tmploc = new File(tmpLoc);
        File tmpFile = new File(tmpLoc, tmpfileName + ".java");
        System.out.println(tmpLoc + tmpfileName);
        tmploc.mkdirs();
        tmpFile.createNewFile();
        FileWriter fileWriter = new FileWriter(tmpLoc + tmpfileName + ".java");
        fileWriter.write(String.valueOf(tmpText));
        fileWriter.close();
    }


    public void build() throws IOException, InterruptedException {
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec("javac " + tmpLoc + tmpfileName + ".java");
        InputStream outputStream = process.getInputStream();
        InputStream errorstream = process.getErrorStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(outputStream, "gb2312"));
        BufferedReader br2 = new BufferedReader(new InputStreamReader(errorstream, "gb2312"));
        String line = null;
        String line2 = null;
        while ((line = br.readLine()) != null | (line2 = br2.readLine()) != null) {
            if (line != null) {
                System.out.println(line);
            } else {
                System.out.println(line2);
            }
        }
        int result = process.waitFor();
        if (result == 0) {
            System.out.println("Complie Finished");
        } else {
            System.out.println("Complie Failed");
        }
    }

    public void run() throws IOException, InterruptedException {
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec("cmd.exe /c cd " + tmpLoc + " & java " + tmpfileName);
        InputStream outputStream = process.getInputStream();
        InputStream errorstream = process.getErrorStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(outputStream, "gb2312"));
        BufferedReader br2 = new BufferedReader(new InputStreamReader(errorstream, "gb2312"));
        String line = null;
        String line2 = null;
        while ((line = br.readLine()) != null | (line2 = br2.readLine()) != null) {
            if (line != null) {
                System.out.println(line);
            } else {
                System.out.println(line2);
            }
        }
        int result = process.waitFor();
        if (result == 0) {
            System.out.println("Run Finished");
        } else {
            System.out.println("Run Failed");
        }

    }
}
