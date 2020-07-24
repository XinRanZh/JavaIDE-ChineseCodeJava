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
        FileSync testing = new FileSync("C:\\Users\\79917\\OneDrive\\学习\\CPSC210\\project_d8x2b\\data"
                + "\\JavaProjectTest\\","test");
        FileSync.getFile();
        assertEquals("C:\\Users\\79917\\OneDrive\\学习\\CPSC210\\project_d8x2b\\data"
                + "\\JavaProjectTest\\", testing.getLoc());
        assertEquals("test.java",testing.getFilename());
        StringBuffer sb = new StringBuffer();
        sb.append(testing.getCon());
        FileSync testnewing = new FileSync(sb,"C:\\Users\\79917\\OneDrive\\学习\\CPSC210\\project_d8x2b\\data"
                + "\\JavaProjectTest\\","testnew");
        testnewing.setFile();
        FileSync testt = new FileSync("C:\\Users\\79917\\OneDrive\\学习\\CPSC210\\project_d8x2b\\data"
                + "\\JavaProjectTest\\","testnew");
        testt.setFilename("abc");
        testt.setFilename("testnew");
        testt.setLoc(".\\");
        testt.setLoc("C:\\Users\\79917\\OneDrive\\学习\\CPSC210\\project_d8x2b\\data"
                + "\\JavaProjectTest\\");
        testt.setFile();
        testt.setCon(sb.append("权限公开 类 test{\n" +
                "    权限公开 静态 无返回值 main(String[] args){\n" +
                "        System.out.println(\"Hello, World!\");\n" +
                "    }\n" +
                "}\n"));
        assertEquals(testing.getCon(),testt.getCon());

    }

}
