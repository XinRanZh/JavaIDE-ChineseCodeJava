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


    public String build() throws IOException, InterruptedException {
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec("javac " + tmpLoc + tmpfileName + ".java");
        InputStream outputStream = process.getInputStream();
        InputStream errorstream = process.getErrorStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(outputStream, "gb2312"));
        BufferedReader br2 = new BufferedReader(new InputStreamReader(errorstream, "gb2312"));
        StringBuffer resultText = new StringBuffer();
        String line = null;
        String line2 = null;
        while ((line = br.readLine()) != null | (line2 = br2.readLine()) != null) {
            if (line != null) {
                resultText.append(line + '\n');
            } else {
                resultText.append(line2 + '\n');
            }
        }
        int result = process.waitFor();
        if (result == 0) {
            resultText.append("Complie Success");
        } else {
            resultText.append("Complie Failed");
        }
        return String.valueOf(resultText);
    }

    public String run() throws IOException, InterruptedException {
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec("cmd.exe /c cd " + tmpLoc + " & java " + tmpfileName);
        InputStream outputStream = process.getInputStream();
        InputStream errorstream = process.getErrorStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(outputStream, "gb2312"));
        BufferedReader br2 = new BufferedReader(new InputStreamReader(errorstream, "gb2312"));
        StringBuffer resultText = new StringBuffer();
        String line = null;
        String line2 = null;
        while ((line = br.readLine()) != null | (line2 = br2.readLine()) != null) {
            if (line != null) {
                resultText.append(line + '\n');
            } else {
                resultText.append(line2 + '\n');
            }
        }
        int result = process.waitFor();
        if (result == 0) {
            resultText.append("Run Success");
        } else {
            resultText.append("Run Failed");
        }
        return String.valueOf(resultText);

    }
}
