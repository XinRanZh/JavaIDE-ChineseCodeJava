package model;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CompileTest {
    // delete or rename this class!

    @Test
    void TestRunSuccess() throws IOException, InterruptedException {
        String testLoc = "data\\test\\";
        JavaFile javaFile = new JavaFile(testLoc,new Convert());
        String os = System.getProperty("os.name");
        if (!os.toLowerCase().startsWith("win")) {
            testLoc = testLoc.replaceAll("\\\\","/");
        }
        Convert convert = new Convert();
        javaFile.setLocation(testLoc);
        javaFile.setname("test");
        javaFile.readFile();
        javaFile.convertandOutPut(convert);
        FileSync fileSync = new FileSync(testLoc,"test.java");
        fileSync.getFile();
        assertEquals("public class test{\n" +
                "    public static void main(String[] args){\n" +
                "        System.out.println(\"Hello, worlllllllllllllllllld!\");\n" +
                "    }\n" +
                "}\n",String.valueOf(fileSync.getCon()));
        Compile compile = new Compile();
        assertEquals("Complie Success",compile.build(testLoc + "test.java"));
        assertEquals("Hello, worlllllllllllllllllld!\nRun Success",compile.run(testLoc,"test"));

    }

    @Test

    void TestComplieFailed() throws IOException, InterruptedException {
        String testLoc = "data\\test\\";
        JavaFile javaFile = new JavaFile();
        String os = System.getProperty("os.name");
        if (!os.toLowerCase().startsWith("win")) {
            testLoc = testLoc.replaceAll("\\\\","/");
        }
        Convert convert = new Convert();
        javaFile.setLocation(testLoc);
        javaFile.setname("testfailed");
        javaFile.readFile();
        javaFile.convertandOutPut(convert);
        FileSync fileSync = new FileSync(testLoc,"testfailed.java");
        fileSync.getFile();
        assertEquals("public class testfailed{\n" +
                "    public static void main(String[] args){\n" +
                "        System.out.priasdfdasfsadfntln(\"Hello, worlllllllllllllllllld!\");\n" +
                "    }\n" +
                "}\n",String.valueOf(fileSync.getCon()));
        Compile compile = new Compile();
        assertEquals(osdetector("data\\test\\testfailed.java:3: error: cannot find symbol\n") +
                "        System.out.priasdfdasfsadfntln(\"Hello, worlllllllllllllllllld!\");\n" +
                "                  ^\n" +
                "  symbol:   method priasdfdasfsadfntln(String)\n" +
                "  location: variable out of type PrintStream\n" +
                "1 error\n" +
                "Complie Failed",compile.build(testLoc + "testfailed.java"));
}

    private String osdetector(String loc) {
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {
            return loc;
        } else {
            String loclinux = loc.replaceAll("\\\\","/");
            return loclinux;
        }
    }

    }

