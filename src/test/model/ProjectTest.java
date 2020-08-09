package model;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectTest {
    /*
    @Test
    void testProject() throws IOException {
        String testLoc = "data\\testorg\\test.jCHprojectinfo";
        String os = System.getProperty("os.name");
        if (!os.toLowerCase().startsWith("win")) {
            testLoc = testLoc.replaceAll("\\\\","/");
        }
        Project project = new Project(false,testLoc,"test.jCHprojectinfo");
        project.openProject();
        assertEquals("-d data\\testorg\\ data\\testorg\\Compile.java data\\testorg" +
                "\\Convert.java data\\testorg\\Editer.java data\\testorg\\FileSync.java data" +
                "\\testorg\\GUI.java data\\testorg\\JavaFile.java data\\testorg\\Main.java data" +
                "\\testorg\\Project.java data\\testorg\\Compile.java data\\testorg\\Convert.java data" +
                "\\testorg\\Editer.java data\\testorg\\FileSync.java data\\testorg\\GUI.java data\\testorg" +
                "\\JavaFile.java data" +
                "\\testorg\\Main.java data\\testorg\\Project.java ",project.genCompileOrder());
        project.convertAll("dict.txt");
        assertEquals("package model;\n" +
                "\n" +
                "import java.io.*;\n" +
                "\n" +
                "public class Compile {\n" +
                "    //Complie and run the file\n" +
                "    //Expect to run a whole project after next part\n" +
                "    StringBuffer tmpText;\n" +
                "    String tmpLoc;\n" +
                "    String tmpfileName;\n" +
                "\n" +
                "    public Compile(/*StringBuffer stringBuffer, String tmpLoc, String fileName*//*) throws IOException {\n" +
                "      //  this.tmpText = stringBuffer;\n" +
                "      //  this.tmpLoc = tmpLoc + \"debug\\\\\";\n" +
                "      //  this.tmpfileName = fileName;\n" +
                "     //   creatTmpFile();\n" +
                "    }\n" +
                "\n" +
                "    void creatTmpFile() throws IOException {\n" +
                "        File tmploc = new File(tmpLoc);\n" +
                "        File tmpFile = new File(tmpLoc, tmpfileName + \".java\");\n" +
                "        tmploc.mkdirs();\n" +
                "        tmpFile.createNewFile();\n" +
                "        FileWriter fileWriter = new FileWriter(tmpLoc + tmpfileName + \".java\");\n" +
                "        fileWriter.write(String.valueOf(tmpText));\n" +
                "        fileWriter.close();\n" +
                "    }\n" +
                "\n" +
                "\n" +
                "    public String build(String command) throws IOException, InterruptedException {\n" +
                "        //javac is the command to build java class file\n" +
                "        //TBD:May change to javac -d . to build a whole project\n" +
                "        Runtime runtime = Runtime.getRuntime();\n" +
                "        Process process;\n" +
                "        process = runtime.exec(\"javac -encoding UTF-8 \" + command + \" -J-Duser.language=en\");\n" +
                "        System.out.println(\"javac \" + command + \" -J-Duser.language=en\");\n" +
                "        return getRes(process,\"Complie\");\n" +
                "    }\n" +
                "\n" +
                "    public String run(String loc,String classname) throws IOException, InterruptedException {\n" +
                "        //java + packagename + classname is the order that make the .class or jar run\n" +
                "        Runtime runtimerun = Runtime.getRuntime();\n" +
                "        Process processrun;\n" +
                "        String os = System.getProperty(\"os.name\");\n" +
                "        if (os.toLowerCase().startsWith(\"win\")) {\n" +
                "            processrun = runtimerun.exec(\"cmd.exe /c cd \"\n" +
                "                    + loc + \" & java -Duser.language=en \" + classname);\n" +
                "        } else {\n" +
                "            String tmpLoclinux = tmpLoc.replaceAll(\"\\\\\\\\\",\"/\");\n" +
                "            processrun = runtimerun.exec(\"cd \" + tmpLoclinux + \" && java -Duser.language=en \" + tmpfileName);\n" +
                "        }\n" +
                "        return getRes(processrun,\"Run\");\n" +
                "    }\n" +
                "\n" +
                "    private String getRes(Process process, String str) throws IOException, InterruptedException {\n" +
                "        InputStream outputStream = process.getInputStream();\n" +
                "        InputStream errorstream = process.getErrorStream();\n" +
                "        BufferedReader br = new BufferedReader(new InputStreamReader(outputStream, \"gb2312\"));\n" +
                "        BufferedReader br2 = new BufferedReader(new InputStreamReader(errorstream, \"gb2312\"));\n" +
                "        StringBuffer resultText = new StringBuffer();\n" +
                "        String line = null;\n" +
                "        String line2 = null;\n" +
                "        while ((line = br.readLine()) != null | (line2 = br2.readLine()) != null) {\n" +
                "            if (line != null) {\n" +
                "                resultText.append(line + '\\n');\n" +
                "            } else {\n" +
                "                resultText.append(line2 + '\\n');\n" +
                "            }\n" +
                "        }\n" +
                "        int result = process.waitFor();\n" +
                "        if (result == 0) {\n" +
                "            resultText.append(str + \" Success\");\n" +
                "        } else {\n" +
                "            resultText.append(str + \" Failed\");\n" +
                "        }\n" +
                "        process.destroy();\n" +
                "        return String.valueOf(resultText);\n" +
                "    }\n" +
                "}\n" +
                "\n",project.getSelectFile(0));
        assertEquals("Project Name:Main\n" +
                "Project Location:data\\testorg\\\n" +
                "No0:Compile\n" +
                "No1:Convert\n" +
                "No2:Editer\n" +
                "No3:FileSync\n" +
                "No4:GUI\n" +
                "No5:JavaFile\n" +
                "No6:Main\n" +
                "No7:Project\n" +
                "No8:Compile\n" +
                "No9:Convert\n" +
                "No10:Editer\n" +
                "No11:FileSync\n" +
                "No12:GUI\n" +
                "No13:JavaFile\n" +
                "No14:Main\n" +
                "No15:Project\n",project.getFileTree());
        project.setSelectFile(0,"package model;\n" +
                "\n" +
                "import java.io.*;\n" +
                "\n" +
                "public class Compile {\n" +
                "    //Complie and run the file\n" +
                "    //Expect to run a whole project after next part\n" +
                "    StringBuffer tmpText;\n" +
                "    String tmpLoc;\n" +
                "    String tmpfileName;\n" +
                "\n" +
                "    public Compile(/*StringBuffer stringBuffer, String tmpLoc, String fileName*//*) throws IOException {\n" +
                "      //  this.tmpText = stringBuffer;\n" +
                "      //  this.tmpLoc = tmpLoc + \"debug\\\\\";\n" +
                "      //  this.tmpfileName = fileName;\n" +
                "     //   creatTmpFile();\n" +
                "    }\n" +
                "\n" +
                "    void creatTmpFile() throws IOException {\n" +
                "        File tmploc = new File(tmpLoc);\n" +
                "        File tmpFile = new File(tmpLoc, tmpfileName + \".java\");\n" +
                "        tmploc.mkdirs();\n" +
                "        tmpFile.createNewFile();\n" +
                "        FileWriter fileWriter = new FileWriter(tmpLoc + tmpfileName + \".java\");\n" +
                "        fileWriter.write(String.valueOf(tmpText));\n" +
                "        fileWriter.close();\n" +
                "    }\n" +
                "\n" +
                "\n" +
                "    public String build(String command) throws IOException, InterruptedException {\n" +
                "        //javac is the command to build java class file\n" +
                "        //TBD:May change to javac -d . to build a whole project\n" +
                "        Runtime runtime = Runtime.getRuntime();\n" +
                "        Process process;\n" +
                "        process = runtime.exec(\"javac -encoding UTF-8 \" + command + \" -J-Duser.language=en\");\n" +
                "        System.out.println(\"javac \" + command + \" -J-Duser.language=en\");\n" +
                "        return getRes(process,\"Complie\");\n" +
                "    }\n" +
                "\n" +
                "    public String run(String loc,String classname) throws IOException, InterruptedException {\n" +
                "        //java + packagename + classname is the order that make the .class or jar run\n" +
                "        Runtime runtimerun = Runtime.getRuntime();\n" +
                "        Process processrun;\n" +
                "        String os = System.getProperty(\"os.name\");\n" +
                "        if (os.toLowerCase().startsWith(\"win\")) {\n" +
                "            processrun = runtimerun.exec(\"cmd.exe /c cd \"\n" +
                "                    + loc + \" & java -Duser.language=en \" + classname);\n" +
                "        } else {\n" +
                "            String tmpLoclinux = tmpLoc.replaceAll(\"\\\\\\\\\",\"/\");\n" +
                "            processrun = runtimerun.exec(\"cd \" + tmpLoclinux + \" && java -Duser.language=en \" + tmpfileName);\n" +
                "        }\n" +
                "        return getRes(processrun,\"Run\");\n" +
                "    }\n" +
                "\n" +
                "    private String getRes(Process process, String str) throws IOException, InterruptedException {\n" +
                "        InputStream outputStream = process.getInputStream();\n" +
                "        InputStream errorstream = process.getErrorStream();\n" +
                "        BufferedReader br = new BufferedReader(new InputStreamReader(outputStream, \"gb2312\"));\n" +
                "        BufferedReader br2 = new BufferedReader(new InputStreamReader(errorstream, \"gb2312\"));\n" +
                "        StringBuffer resultText = new StringBuffer();\n" +
                "        String line = null;\n" +
                "        String line2 = null;\n" +
                "        while ((line = br.readLine()) != null | (line2 = br2.readLine()) != null) {\n" +
                "            if (line != null) {\n" +
                "                resultText.append(line + '\\n');\n" +
                "            } else {\n" +
                "                resultText.append(line2 + '\\n');\n" +
                "            }\n" +
                "        }\n" +
                "        int result = process.waitFor();\n" +
                "        if (result == 0) {\n" +
                "            resultText.append(str + \" Success\");\n" +
                "        } else {\n" +
                "            resultText.append(str + \" Failed\");\n" +
                "        }\n" +
                "        process.destroy();\n" +
                "        return String.valueOf(resultText);\n" +
                "    }\n" +
                "}\n");
        assertEquals("ui.Main",project.getStartClassName());
        assertEquals("data\\testorg\\",project.getProjectlocation());
    }
    */

}