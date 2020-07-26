package model;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;



public class FileSyncTest {
    @Test
    //Test Project3
    //Open the File "test.java"
    //Changed the "main" to "主类"
    //Save the change, named testnew.java

    void FileSyncTest() throws IOException {
        String absoluteLoc = "C:\\Users\\79917\\OneDrive\\学习\\CPSC210\\project_d8x2b\\data"
                + "\\JavaProjectTest\\";
        String testLoc = "data\\JavaProjectTest\\";
        FileSync testing = new FileSync(testLoc,"test");
        FileSync.getFile();
        assertEquals(testLoc, testing.getLoc());
        assertEquals("test.java",testing.getFilename());
        StringBuffer sb = new StringBuffer();
        sb.append(testing.getCon());
        FileSync testnewing = new FileSync(sb,testLoc,"testnew");
        testnewing.setFile();
        FileSync testt = new FileSync(testLoc,"testnew");
        testt.setFilename("abc");
        testt.setFilename("testnew");
        testt.setLoc(".\\");
        testt.setLoc(testLoc);
        testt.setFile();
        testt.setCon(sb.append("权限公开 类 test{\n" +
                "    权限公开 静态 无返回值 main(String[] args){\n" +
                "        System.out.println(\"Hello, World!\");\n" +
                "    }\n" +
                "}\n"));
        assertEquals(testing.getCon(),testt.getCon());

    }

}
