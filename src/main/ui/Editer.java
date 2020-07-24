package ui;

import model.FileSync;

public class Editer {
    public StringBuffer strBuffer;

    public Editer(StringBuffer stringBuffer) {
        this.strBuffer = stringBuffer;
    }

    public void showtext() {
        System.out.println(strBuffer);
    }
}
