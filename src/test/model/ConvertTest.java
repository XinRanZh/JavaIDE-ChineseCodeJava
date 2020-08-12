package model;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ConvertTest {
    String testdata = "抽象\n" +
            "abstract\n" +
            "断言\n" +
            "assert\n" +
            "布尔类型\n" +
            "boolean\n" +
            "中断跳出\n" +
            "break\n" +
            "字节类型\n" +
            "byte\n" +
            "分支循环_分支\n" +
            "case\n" +
            "异常捕获\n" +
            "catch\n" +
            "字符类型\n" +
            "char\n" +
            "继续执行\n" +
            "continue\n" +
            "当循环_做\n" +
            "do\n" +
            "双精度类型\n" +
            "double\n" +
            "如果循环_或者\n" +
            "else\n" +
            "枚举\n" +
            "enum\n" +
            "继承\n" +
            "extends\n" +
            "最终\n" +
            "final\n" +
            "异常处理_最后执行\n" +
            "finally\n" +
            "浮点类型\n" +
            "float\n" +
            "次数循环\n" +
            "for\n" +
            "如果循环_如果\n" +
            "if\n" +
            "接口实现\n" +
            "implements\n" +
            "包导入\n" +
            "import\n" +
            "接口\n" +
            "interface\n" +
            "长整数类型\n" +
            "long\n" +
            "原生语言实现\n" +
            "native\n" +
            "新实例对象\n" +
            "new\n" +
            "包\n" +
            "package\n" +
            "权限私有\n" +
            "private\n" +
            "权限保护\n" +
            "protected\n" +
            "权限公开\n" +
            "public\n" +
            "短整数类型\n" +
            "short\n" +
            "静态\n" +
            "static\n" +
            "父类\n" +
            "super\n" +
            "分支循环_开始\n" +
            "switch\n" +
            "本对象\n" +
            "this\n" +
            "异常处理_抛出\n" +
            "throw\n" +
            "异常处理_尝试\n" +
            "try\n" +
            "无返回值\n" +
            "void\n" +
            "当循环_当\n" +
            "while\n" +
            "整数类型\n" +
            "int\n" +
            "类\n" +
            "class\n" +
            "返回\n" +
            "return\n";

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
        assertEquals("dict.txt\n" + testdata,convert.getDicContain());
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
        Convert convert = new Convert(sb,"dictnew.txt",osdetector(".\\data\\"));
        convert.addrule("主类","main");
        assertEquals("dictnew.txt\n" + testdata + "主类\nmain\n",convert.getDicContain());//test added
        convert.dictionaryWritter();
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
        assertEquals(osdetector(".\\data\\"),convert.getLocation());
    }



}
