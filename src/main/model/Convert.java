package model;

import javax.imageio.IIOException;
import java.io.*;

public class Convert {
    //Convert Java Source from Chinese-Java Language to Normal Java Code
    //Edit the dictionary and get the rules from dictionary

    private StringBuffer tmpText;
    private StringBuffer tmpDict;
    private String[] dictSource = new String[100];
    private String[] dictResult = new String[100];
    //TBD
    private String dictName;
    private int count = 0;

    public Convert() throws IOException {
        this.dictName = "dict.txt";
        dictionaryReader();
    }

    public Convert(StringBuffer stringBuffer, String dictName) throws IOException {
        this.tmpText = stringBuffer;
        this.dictName = dictName;
        dictionaryReader();
    }

    void dictionaryReader() throws IOException {
        String os = System.getProperty("os.name");
        InputStream inputS;
        if (os.toLowerCase().startsWith("win")) {
            inputS = new FileInputStream(".\\data\\" + dictName);
        } else {
            inputS = new FileInputStream("data/" + dictName);
        }
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

    public void dictionaryWritter(String dictname) throws IOException {
        tmpDict = new StringBuffer();
        tmpDict.append(dictname + "\n");
        for (int n = 0; n < count - 2; n++) {
            tmpDict.append(dictSource[n] + "\n");
            tmpDict.append(dictResult[n] + "\n");
        }
        tmpDict.append(dictSource[count - 2] + "\n");
        tmpDict.append(dictResult[count - 2]);
        //Make sure there is no blank last line
        String os = System.getProperty("os.name");
        FileWriter fileWriter;
        if (os.toLowerCase().startsWith("win")) {
            fileWriter = new FileWriter(".\\data\\" + dictname);
        } else {
            fileWriter = new FileWriter("data/" + dictname);
        }

        fileWriter.write(String.valueOf(tmpDict));
        fileWriter.close();
    }

    public boolean addrule(String source,String result) {
        if (count <= 99) {
            dictSource[count - 1] = source;
            dictResult[count - 1] = result;
            count++;
            return true;
        } else {
            return false;
        }
    }

    public void dictionaryConvert() {
        //Convert using rules
        for (int n = 0; n < count - 1; n++) {
            String tmpString = tmpText.toString();
            tmpString = tmpString.replaceAll(dictSource[n],dictResult[n]);
            StringBuffer sbtmp = new StringBuffer();
            sbtmp.append(tmpString);
            tmpText = sbtmp;
            //System.out.println("Replace" + dictSource[n] + "to" + dictResult[n]);
        }
    }

    public StringBuffer showResult() {
        return tmpText;
    }

    public String getDicContain() {
        tmpDict = new StringBuffer();
        tmpDict.append(dictName + "\n");
        for (int n = 0; n < count - 2; n++) {
            tmpDict.append(dictSource[n] + "\n");
            tmpDict.append(dictResult[n] + "\n");
            n++;
        }
        tmpDict.append(dictSource[count - 2] + "\n");
        tmpDict.append(dictResult[count - 2]);
        return String.valueOf(tmpDict);
    }
}
