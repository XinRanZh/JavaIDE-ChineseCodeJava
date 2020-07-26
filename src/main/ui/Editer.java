package ui;

import model.FileSync;

public class Editer {
    public StringBuffer strBuffer;
    public String[] editingtmp = new String[1000];

    public Editer(StringBuffer stringBuffer) {
        this.strBuffer = stringBuffer;
    }

    public void showtext() {
        System.out.println(strBuffer);
    }

    public StringBuffer gettext() {
        return strBuffer;
    }
}

