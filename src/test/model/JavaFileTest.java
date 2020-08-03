package model;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class JavaFileTest {
    @Test
    void testJavaFile(){
        String testLoc = "data\\test\\";
        String os = System.getProperty("os.name");
        if (!os.toLowerCase().startsWith("win")) {
            testLoc = testLoc.replaceAll("\\\\","/");
        }
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("fadsafdsadsfasdf");
        JavaFile javaFilex = new JavaFile("testJF",arrayList);
        javaFilex.setLocation(testLoc);
        javaFilex.readFile();
        javaFilex.setFileContain("public class test{\n" +
                "    public static void main(String[] args){\n" +
                "        System.out.println(\"Hello, worlllllllllllllllllld!\");\n" +
                "    }\n" +
                "}\n");
        javaFilex.setFile();
        javaFilex.readFile();
        assertEquals("public class test{\n" +
                "    public static void main(String[] args){\n" +
                "        System.out.println(\"Hello, worlllllllllllllllllld!\");\n" +
                "    }\n" +
                "}\n",String.valueOf(javaFilex.fileContain));
    }
}
