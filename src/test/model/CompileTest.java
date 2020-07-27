package model;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CompileTest {
    // delete or rename this class!
    @Test
    //Test No.1
    //This whole test include:
    //Step1: Open filename Given
    //Step2: Convert This file from “Chinese-Java” to Java
    //Step3: Compile and Run and catch the result: this file work successfully/compile error
    void TestRunSuccess(){
        String testLoc = "data\\JavaProjectTest\\";
        FileSync testing = null;
        try {
            testing = new FileSync(testLoc,"test");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileSync.getFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuffer sb = testing.getCon();
        Convert convert = null;
        try {
            convert = new Convert(sb,"dict.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        convert.dictionaryConvert();
        Compile compile = null;
        try {
            compile = new Compile(convert.showResult(),testLoc,"test");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            assertEquals("Complie Success",compile.build());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            assertEquals("Hello, World!\nRun Success",compile.run());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test

    void TestComplieFailed(){
        FileSync testing = null;
        String testLoc = "data\\JavaProjectTest\\";
        try {
            testing = new FileSync(testLoc,"test");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileSync.getFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuffer sb = testing.getCon();
        Convert convert = null;
        try {
            convert = new Convert(sb,"dict.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        convert.dictionaryConvert();
        Compile compile = null;
        try {
            compile = new Compile(convert.showResult(),testLoc,"testcompilefailed");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            assertEquals("data\\JavaProjectTest\\debug\\testcompilefailed.java:1: error: class test is public, should be declared in a file named test.java\n" +
                    "public class test{\n" +
                    "       ^\n" +
                    "1 error\n" +
                    "Complie Failed",compile.build());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            assertEquals(
                    "Error: Could not find or load main class testcompilefailed\n" +
                    "Caused by: java.lang.ClassNotFoundException: testcompilefailed\n" +
                    "Run Failed",compile.run());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
}


    }

