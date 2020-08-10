package model;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ConvertTest {
    String testdata = "抽象\n" +
            "abstract\n" +
            "布尔类型\n" +
            "boolean\n" +
            "字节类型\n" +
            "byte\n" +
            "异常捕获\n" +
            "catch\n" +
            "继续执行\n" +
            "continue\n" +
            "双精度类型\n" +
            "double\n" +
            "枚举\n" +
            "enum\n" +
            "最终\n" +
            "final\n" +
            "浮点类型\n" +
            "float\n" +
            "如果循环_如果\n" +
            "if\n" +
            "包导入\n" +
            "import\n" +
            "长整数类型\n" +
            "long\n" +
            "新实例对象\n" +
            "new\n" +
            "权限私有\n" +
            "private\n" +
            "权限公开\n" +
            "public\n" +
            "静态\n" +
            "static\n" +
            "分支循环_开始\n" +
            "switch\n" +
            "异常处理_抛出\n" +
            "throw\n" +
            "无返回值\n" +
            "void\n" +
            "整数类型\n" +
            "int\n" +
            "返回\n" +
            "return";

    private String osdetector(String loc) {
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {
            return loc;
        } else {
            String loclinux = loc.replaceAll("\\\\","/");
            return loclinux;
        }
    }

    @Test
    void testDefaultDic() throws IOException {
        Convert convert = new Convert();
        assertEquals("dict.txt\n" +
                testdata,convert.getDicContain());
    }

    @Test
    //Project2:Dictionary operation
    //This project contain:
    //1. Read Given Dictionary
    //2. Add new rule to Dictionary
    //3. Write the new dictionary to file with new name
    //4. Verify if it's the correct thing
    //PS the convert do not need to test because the Convert-Compile-Run Tool chain have been checked in Compile test
    void testdictOps() throws IOException {
        StringBuffer sb = new StringBuffer();
        Convert convert = new Convert(sb,"dict.txt",osdetector(".\\data\\"));
        convert.addrule("主类","main");
        assertEquals("dict.txt\n" + testdata + "\n主类\nmain",convert.getDicContain());//test added
        convert.dictionaryWritter(osdetector(".\\data\\"),"dictnew.txt");
        Convert convertnew = new Convert(sb,"dictnew.txt",osdetector(".\\data\\"));
        assertEquals("dictnew.txt\n" + testdata + "\n主类\nmain",convertnew.getDicContain());//test added
    }

    @Test
    void testdicConvert() throws IOException {
        StringBuffer sb = new StringBuffer();
        sb.append("权限公开 类 test{\n" +
                "    权限公开 静态 无返回值 main(String[] args){\n" +
                "        System.out.println(\"Hello, World!\");\n" +
                "    }\n" +
                "}\n");

        Convert convert = new Convert();
        convert.setTmpText(sb);
        convert.dictionaryConvert();
        assertEquals("public class test{\n" +
                "    public static void main(String[] args){\n" +
                "        System.out.println(\"Hello, World!\");\n" +
                "    }\n" +
                "}\n",convert.showResult().toString());
        assertEquals("dict.txt",convert.getDictName());
    }

    @Test
    void testGetDicSourceandLocation() throws IOException {
        Convert convert = new Convert();
        convert.getDictSource();
        assertEquals(".\\data\\",convert.getLocation());
    }


}
