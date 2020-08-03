package model;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;



public class FileSyncTest {
     @Test


  void FileSyncTest() throws IOException {
        String testLoc = "data\\test\\";
        StringBuffer sb = new StringBuffer();
        FileSync testing = new FileSync(sb,testLoc,"testFST.javaCH");
        FileSync.getFile();
        assertEquals(testLoc, testing.getLoc());
        assertEquals("testFST.javaCH",testing.getFilename());
        assertEquals("public class test{\n" +
                "    public static void main(String[] args){\n" +
                "        System.out.println(\"Hello, worlllllllllllllllllld!\");\n" +
                "    }\n" +
                "}\n",String.valueOf(testing.getCon()));
        assertEquals(testLoc,testing.getLoc());
        testing.setFilename("123");
        assertEquals("123",testing.getFilename());
        testing.setLoc("123");
        assertEquals("123",testing.getLoc());

    }

}
