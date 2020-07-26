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
        tmploc.mkdirs();
        tmpFile.createNewFile();
        FileWriter fileWriter = new FileWriter(tmpLoc + tmpfileName + ".java");
        fileWriter.write(String.valueOf(tmpText));
        fileWriter.close();
    }


    public String build() throws IOException, InterruptedException {
        Runtime runtime = Runtime.getRuntime();
        String os = System.getProperty("os.name");
        Process process;
        if (os.toLowerCase().startsWith("win")) {
            process = runtime.exec("javac " + tmpLoc + tmpfileName + ".java -J-Duser.language=en");
        } else {
            String tmpLoclinux = tmpLoc.replaceAll("\\\\","/");
            process = runtime.exec("javac " + tmpLoclinux + tmpfileName + ".java -J-Duser.language=en");
        }
        return getRes(process,"Complie");
    }

    public String run() throws IOException, InterruptedException {
        Runtime runtimerun = Runtime.getRuntime();
        Process processrun;
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {
            processrun = runtimerun.exec("cmd.exe /c cd " + tmpLoc + " & java " + tmpfileName);
        } else {
            String tmpLoclinux = tmpLoc.replaceAll("\\\\","/");
            processrun = runtimerun.exec("cd " + tmpLoclinux + " && java " + tmpfileName);
        }
        return getRes(processrun,"Run");
    }

    private String getRes(Process process, String str) throws IOException, InterruptedException {
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
            resultText.append(str + " Success");
        } else {
            resultText.append(str + " Failed");
        }
        process.destroy();
        return String.valueOf(resultText);
    }
}
//cd C:\java\ & java test.class
//java C:\java\test.class
