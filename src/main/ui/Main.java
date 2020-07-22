package ui;

import model.FileSync;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FileSync testing = new FileSync("E:\\test.java");
        FileSync.getFile();
        StringBuffer sb = testing.getCon();
        Editer editer = new Editer(sb);
        editer.showtext();
    }
}
