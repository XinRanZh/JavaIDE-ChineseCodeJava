package model;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Convert {
    //Convert Java Source from Chinese-Java Language to Normal Java Code
    //Edit the dictionary and get the rules from dictionary

    private StringBuffer tmpText;
    private StringBuffer tmpDict;
    private Map<String,String> dictmap = new LinkedHashMap<>();

    public String getDictName() {
        return dictName;
    }
    //TBD

    private String dictName;

    public String getLocation() {
        return location;
    }

    private String location;

    private String osdetector(String loc) {
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {
            return loc;
        } else {
            return loc.replaceAll("\\\\","/");
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
        this.location = osdetector(location);
        dictionaryReader();
    }

    //Read the dictionary and save the file to the lists
    void dictionaryReader() throws IOException {
        InputStream inputS;
        inputS = new FileInputStream(this.location + dictName);
        String tmpOrg;
        String tmpRes;
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputS));
        tmpOrg = reader.readLine();
        while (tmpOrg != null) {
            tmpOrg = reader.readLine();
            tmpRes = reader.readLine();
            this.dictmap.put(tmpOrg,tmpRes);
        }
        reader.close();
        inputS.close();
    }

    //Write edited dictionary to file
    public void dictionaryWritter() throws IOException {
        tmpDict = new StringBuffer();
        tmpDict.append(dictName + "\n");
        Set<String> keys = this.dictmap.keySet();
        for (String key : keys) {
            tmpDict.append(key + "\n");
            tmpDict.append(this.dictmap.get(key) + "\n");
        }
        //Make sure there is no blank last line
        FileWriter fileWriter;
        fileWriter = new FileWriter(osdetector(location) + dictName);
        fileWriter.write(String.valueOf(tmpDict));
        fileWriter.close();
    }

    //add an convert rule to the file
    public void addrule(String source,String result) {
        this.dictmap.put(source,result);
    }

    public void dictionaryConvert() {
        //Convert using rules
        Set<String> keys = dictmap.keySet();
        keys.remove("null");
        keys.remove(null);
        for (String key : keys) {
            String tmpString = tmpText.toString();
            tmpString = tmpString.replaceAll(key,dictmap.get(key));
            System.out.println(dictmap.get(key));
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
        Set<String> keys = dictmap.keySet();
        keys.remove("null");
        keys.remove(null);
        for (String key : keys) {
            tmpDict.append(key + "\n");
            tmpDict.append(dictmap.get(key) + "\n");
        }
        return String.valueOf(tmpDict);
    }

    public Set<String> getDictSource() {
        return dictmap.keySet();
    }

}
