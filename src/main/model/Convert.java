package model;

import javax.imageio.IIOException;
import java.io.*;

public class Convert {
    //Convert Java Source from Chinese to English
    //Rules from dictionary
    private StringBuffer tmpText;
    private String[] dictSource = new String[100];
    private String[] dictResult = new String[100];
    private int count = 0;

    public Convert() throws IOException {
        dictionaryReader();
    }

    public Convert(StringBuffer stringBuffer) throws IOException {
        this.tmpText = stringBuffer;
        dictionaryReader();
    }

    void dictionaryReader() throws IOException {
        InputStream inputS = new FileInputStream(".\\data\\dict.txt");
        String tmpLine;
        count = 0;
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputS));
        tmpLine = reader.readLine();
        while (tmpLine != null) {
            tmpLine = reader.readLine();
            dictSource[count] = tmpLine;
            tmpLine = reader.readLine();
            dictResult[count] = tmpLine;
            count++;
        }
        reader.close();
        inputS.close();
    }

    public void dictionaryConvert() {
        //stub
        for (int n = 0; n < count - 1; n++) {
            String tmpString = tmpText.toString();
            tmpString = tmpString.replaceAll(dictSource[n],dictResult[n]);
            StringBuffer sbtmp = new StringBuffer();
            sbtmp.append(tmpString);
            tmpText = sbtmp;
            System.out.println("Replace" + dictSource[n] + "to" + dictResult[n]);
        }
    }

    public StringBuffer showResult() {
        return tmpText;
    }
}
