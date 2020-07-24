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
        FileSync testing = null;
        try {
            testing = new FileSync("C:\\Users\\79917\\OneDrive\\学习\\CPSC210\\project_d8x2b\\data"
                    + "\\JavaProjectTest\\","test");
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
            compile = new Compile(convert.showResult(),"C:\\Users\\79917\\OneDrive\\学习\\CPSC210\\"
                    + "project_d8x2b\\data"
                    + "\\JavaProjectTest\\","test");
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
        try {
            testing = new FileSync("C:\\Users\\79917\\OneDrive\\学习\\CPSC210\\project_d8x2b\\data"
                    + "\\JavaProjectTest\\","test");
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
            compile = new Compile(convert.showResult(),"C:\\Users\\79917\\OneDrive\\学习\\CPSC210\\"
                    + "project_d8x2b\\data"
                    + "\\JavaProjectTest\\","testcompilefailed");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            assertEquals("C:\\Users\\79917\\OneDrive\\学习\\CPSC210\\project_d8x2b\\data\\" +
                    "JavaProjectTest\\debug\\testcompilefailed.java:1: 错误: 类test是公共的, 应在名为 test.java 的文件中声明\n" +
                    "public class test{\n" +
                    "       ^\n" +
                    "1 个错误\n" +
                    "Complie Failed",compile.build());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
}


    }

