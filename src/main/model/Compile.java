package model;

import java.io.*;

public class Compile {
    //Complie and run the file
    //Expect to run a whole project after next part

    public String build(String command) throws IOException, InterruptedException {
        //javac is the command to build java class file
        //-encoding UTF-8
        //TBD:May change to javac -d . to build a whole project
        Runtime runtime = Runtime.getRuntime();
        Process process;
        process = runtime.exec("javac -encoding UTF-8 " + command + " -J-Duser.language=en");
        System.out.println("javac -encoding UTF-8 " + command + " -J-Duser.language=en");
        return getRes(process,"Complie");
    }

    public String run(String loc,String classname) throws IOException, InterruptedException {
        //java + packagename + classname is the order that make the .class or jar run
        Runtime runtimerun = Runtime.getRuntime();
        Process processrun;
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {
            processrun = runtimerun.exec("cmd.exe /c cd "
                    + loc + " & java -Duser.language=en -Dfile.encoding=UTF-8 " + classname);
        } else {
            String tmpLoclinux = loc.replaceAll("\\\\","/");
            String cmd = "cd " + tmpLoclinux
                    + " && java -Duser.language=en -Dfile.encoding=UTF-8 " + classname;
            String[] command = new String[]{"sh","-c",cmd};
            processrun = runtimerun.exec(command);

        }
        return getRes(processrun,"Run");
    }

    private String getRes(Process process, String str) throws IOException, InterruptedException {
        InputStream outputStream = process.getInputStream();
        InputStream errorstream = process.getErrorStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(outputStream, "gb2312"));
        BufferedReader br2 = new BufferedReader(new InputStreamReader(errorstream, "gb2312"));
        StringBuffer resultText = new StringBuffer();
        String line;
        String line2;
        while ((line = br.readLine()) != null | (line2 = br2.readLine()) != null) {
            if (line != null) {
                resultText.append(line).append('\n');
            } else {
                resultText.append(line2).append('\n');
            }
        }
        int result = process.waitFor();
        if (result == 0) {
            resultText.append(str).append(" Success");
        } else {
            resultText.append(str).append(" Failed");
        }
        process.destroy();
        return String.valueOf(resultText);
    }
}

