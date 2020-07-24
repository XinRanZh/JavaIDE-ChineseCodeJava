package ui;

import model.Compile;
import model.Convert;
import model.FileSync;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        FileSync testing = new FileSync("C:\\Users\\79917\\OneDrive\\学习\\CPSC210\\project_d8x2b\\data"
                + "\\JavaProjectTest\\","test");
        FileSync.getFile();
        StringBuffer sb = testing.getCon();
        Convert convert = new Convert(sb,"dict.txt");
        convert.dictionaryConvert();
        Compile compile = new Compile(convert.showResult(),"C:\\Users\\79917\\OneDrive\\学习\\CPSC210\\"
                + "project_d8x2b\\data"
                + "\\JavaProjectTest\\","test");
        compile.build();
        compile.run();
    }
}
