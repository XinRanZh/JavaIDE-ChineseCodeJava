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

    public String getDictName() {
        return dictName;
    }
    //TBD

    private String dictName;

    public String getLocation() {
        return location;
    }

    private String location;
    private int count = 0;

    private String osdetector(String loc) {
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {
            return loc;
        } else {
            String loclinux = loc.replaceAll("\\\\","/");
            return loclinux;
        }
    }

    public Convert() throws IOException {
        this.dictName = "dict.txt";
        this.location = osdetector(".\\data\\");
        dictionaryReader();
    }

    public Convert(StringBuffer stringBuffer, String dictName, String location) throws IOException {
        this.tmpText = stringBuffer;
        this.dictName = dictName;
        this.location = location;
        dictionaryReader();
    }

    //Read the dictionary and save the file to the lists
    void dictionaryReader() throws IOException {
        String os = System.getProperty("os.name");
        InputStream inputS;
        inputS = new FileInputStream(osdetector(this.location) + dictName);
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

    //Write edited dictionary to file
    public void dictionaryWritter(String location, String dictname) throws IOException {
        tmpDict = new StringBuffer();
        tmpDict.append(dictname + "\n");
        for (int n = 0; n < count - 2; n++) {
            tmpDict.append(dictSource[n] + "\n");
            tmpDict.append(dictResult[n] + "\n");
        }
        tmpDict.append(dictSource[count - 2] + "\n");
        tmpDict.append(dictResult[count - 2]);
        //Make sure there is no blank last line
        FileWriter fileWriter;
        fileWriter = new FileWriter(osdetector(location) + dictname);
        fileWriter.write(String.valueOf(tmpDict));
        fileWriter.close();
    }

    //add an convert rule to the file
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
            System.out.println(dictSource[n]);
            StringBuffer sbtmp = new StringBuffer();
            sbtmp.append(tmpString);
            this.tmpText = sbtmp;
        }
    }

    public void setTmpText(StringBuffer stringBuffer) {
        this.tmpText = stringBuffer;
    }

    public StringBuffer showResult() {
        return tmpText;
    }

    //Output converted File
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

    public String[] getDictSource() {
        return dictSource;
    }

    public void setDictSource(String[] strings) {
        dictSource = strings;
    }

    public void setDictResult(String[] strings) {
        dictResult = strings;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
