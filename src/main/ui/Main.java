package ui;

import model.Compile;
import model.Convert;
import model.FileSync;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        String testLoc = "data\\JavaProjectTest\\";
        FileSync testing = null;
        testing = new FileSync(testLoc,"test");
        FileSync.getFile();
        StringBuffer sb = testing.getCon();
        Convert convert = null;
        convert = new Convert(sb,"dict.txt");
        convert.dictionaryConvert();
        Compile compile = null;
        compile = new Compile(convert.showResult(),testLoc,"test");
        compile.build();
        compile.run();
        StringBuffer sb2 = new StringBuffer();
        Convert convert2 = new Convert(sb,"dict.txt");
        convert2.addrule("主类","main");
        convert2.getDicContain();
        convert2.dictionaryWritter("dictnew.txt");
        Convert convertnew = new Convert(sb2,"dictnew.txt");
        convertnew.getDicContain();//test added
    }
}
