package model;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CompileTest {
    // delete or rename this class!
    /*
    @Test
    void TestRunSuccess() throws IOException, InterruptedException {
        String testLoc = "data\\test\\";
        JavaFile javaFile = new JavaFile();
        String os = System.getProperty("os.name");
        if (!os.toLowerCase().startsWith("win")) {
            testLoc = testLoc.replaceAll("\\\\","/");
        }
        javaFile.setLocation(testLoc);
        javaFile.setname("test");
        javaFile.readFile();
        javaFile.convertandOutPut(javaFile.convert);
        FileSync fileSync = new FileSync(testLoc,"test.java");
        fileSync.getFile();
        assertEquals("public class test{\n" +
                "    public static void main(String[] args){\n" +
                "        System.out.println(\"Hello, worlllllllllllllllllld!\");\n" +
                "    }\n" +
                "}\n",String.valueOf(fileSync.getCon()));
        Compile compile = new Compile();
        assertEquals("Complie Success",compile.build(testLoc + "test.java"));
        assertEquals("Hello, worlllllllllllllllllld!\n" +
                "Run Success",compile.run(testLoc, "test"));

    }

    @Test

    void TestComplieFailed() throws IOException, InterruptedException {
        String testLoc = "data\\test\\";
        JavaFile javaFile = new JavaFile();
        String os = System.getProperty("os.name");
        if (!os.toLowerCase().startsWith("win")) {
            testLoc = testLoc.replaceAll("\\\\","/");
        }
        javaFile.setLocation(testLoc);
        javaFile.setname("testfailed");
        javaFile.readFile();
        javaFile.convertandOutPut("dict.txt");
        FileSync fileSync = new FileSync(testLoc,"testfailed.java");
        fileSync.getFile();
        assertEquals("public class testfailed{\n" +
                "    public static void main(String[] args){\n" +
                "        System.out.priasdfdasfsadfntln(\"Hello, worlllllllllllllllllld!\");\n" +
                "    }\n" +
                "}\n",String.valueOf(fileSync.getCon()));
        Compile compile = new Compile();
        assertEquals("data\\test\\testfailed.java:3: error: cannot find symbol\n" +
                "        System.out.priasdfdasfsadfntln(\"Hello, worlllllllllllllllllld!\");\n" +
                "                  ^\n" +
                "  symbol:   method priasdfdasfsadfntln(String)\n" +
                "  location: variable out of type PrintStream\n" +
                "1 error\n" +
                "Complie Failed",compile.build(testLoc + "testfailed.java"));
}

*/
    }

