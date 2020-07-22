package model;

import javax.imageio.IIOException;
import java.io.*;

public class Convert {
    //Convert Java Source from Chinese to English
    //Rules from dictionary
    private StringBuffer tmpText;
    private String[]
    void dictionaryReader() throws IOException {
        InputStream inputS = new FileInputStream(".\\data\\dict.txt");
        String tmpLine;
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputS));
        tmpLine = reader.readLine();
        while (tmpLine != null) {
            tmpText.append(tmpLine);
            tmpText.append("\n");
            tmpLine = reader.readLine();
        }
        reader.close();
        inputS.close();
    }
    void dictionaryprocess() {

    }
}
