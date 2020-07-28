package ui;

import model.Compile;
import model.Convert;
import model.FileSync;

import java.io.*;

public class GUI {
    private StringBuffer nowdir = new StringBuffer();
    private String ops;

    //The "GUI" class, or command UI class for now is basically user interface
    //Need to be rewrite when comming to the GUI part

    public GUI() throws IOException, InterruptedException {
        render("mainmenu");
    }

    private void render(String ops) throws IOException, InterruptedException {

        //Render will render menu based on the entered options

        if (ops == "mainmenu") {
            mainmenu();
        }
        switch (ops) {
            case "open file": {
                openfile();
            }
            case "open direction": {
                opendir();
            }
            case "create file": {
                //TBD: I do not do this because it seems like what I need to be done at GUI part
            }
            case "edit dictionary": {
                dictruleadder();
            }
            default: {
                System.out.println("Enter Wrong Please ReTry");
                render("mainmenu");
            }
        }

    }

    private String getRes(Process process,String errormsg) throws IOException {
        //After running command in Linux shell or windows CMD, this function allow us to get result/error message
        InputStream outputStream = process.getInputStream();
        InputStream errorstream = process.getErrorStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(outputStream, "gb2312"));
        BufferedReader br2 = new BufferedReader(new InputStreamReader(errorstream, "gb2312"));
        StringBuffer resultText = new StringBuffer();
        String line = null;
        String line2 = null;
        while ((line = br.readLine()) != null | (line2 = br2.readLine()) != null) {
            if (line != null) {
                resultText.append(line + '\n');
            } else {
                resultText.append(line2 + '\n');
                System.out.println(errormsg);
                nowdir.setLength(0);
            }
        }
        process.destroy();
        return String.valueOf(resultText);
    }

    private void openfile() throws IOException, InterruptedException {
        System.out.println("Enter Name of the File, DO NOT INCLUDE .java");
        BufferedReader brinof = new BufferedReader(new InputStreamReader(System.in));
        String fname = brinof.readLine();
        FileSync fileSync = null;
        try {
            fileSync = new FileSync(String.valueOf(nowdir), fname);
            fileSync.getFile();
        } catch (IOException ioe) {
            System.out.println("File Not Exist or other issue, backing to main menu");
            render("mainmenu");
        }
        Editer editer = new Editer(fileSync.getCon());
        editer.showtext();
        switch (ops = filemenurender()) {
            case "edit file": {
                //TBD I do not do this because it's the part of GUI not for now
            }
            case "compile and run file": {
                candrun(editer,fname);
            }
            case "mainmenu": {
                render("mainmenu");
            }
        }
    }

    private void mainmenu() throws IOException, InterruptedException {
        nowdir.setLength(0);
        System.out.println("There are following files and directions in this folder\n");
        Runtime runtimemainmenu = Runtime.getRuntime();
        Process processmainmenu;
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {
            processmainmenu = runtimemainmenu.exec("cmd /c dir");
        } else {
            processmainmenu = runtimemainmenu.exec("ls");
        }
        System.out.println(getRes(processmainmenu,""));
        System.out.println("Please choose following options\n");
        BufferedReader brin = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("A.open file\nB.open direction\nC.edit dictionary");
        System.out.println("please enter your options name, such as open file: ");
        ops = brin.readLine();
        render(ops);
    }

    private void opendir() throws IOException, InterruptedException {
        System.out.println("Enter Name of the Direction");
        BufferedReader brinof = new BufferedReader(new InputStreamReader(System.in));
        String dirname = brinof.readLine();
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {
            nowdir.append(dirname).append("\\");
        } else {
            nowdir.append(dirname).append("\\");
            String nowdirtmp = String.valueOf(nowdir).replaceAll("\\\\","/");
            nowdir.setLength(0);
            nowdir.append(nowdirtmp);
        }
        FileSync fileSync = null;
        dirrender();
        ops = getInputselector();
        render(ops);
    }

    private String getInputselector() throws IOException {
        System.out.println("Please choose following options\n");
        BufferedReader brin = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("A.open file\nB.creat file\nC.open direction");
        System.out.println("please enter your options name, such as open file: ");
        ops = brin.readLine();
        return ops;
    }

    private int dirrender() throws IOException, InterruptedException {
        try {
            System.out.println("There are following files and directions in this folder\n");
            Runtime runtimediropen = Runtime.getRuntime();
            Process processdiropen;
            String os = System.getProperty("os.name");
            String ndir = String.valueOf(nowdir);
            if (os.toLowerCase().startsWith("win")) {
                System.out.println(ndir);
                processdiropen = runtimediropen.exec("cmd /c cd " + ndir + " & dir");
            } else {
                processdiropen = runtimediropen.exec("cd " + ndir + " && ls");
            }
            System.out.println(getRes(processdiropen,"Dir Not Exist or other issue, backing to main menu"));
            return 0;
        } catch (IOException ioe) {
            System.out.println("Dir Not Exist or other issue, backing to main menu");
            nowdir.setLength(0);
            render("mainmenu");
            return 0;
        }
    }

    private String filemenurender() throws IOException {
        System.out.println("Please choose following options\n");
        BufferedReader brin = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("A.edit file\nB.compile and run file\nC.mainmenu");
        System.out.println("please enter your options name, such as open file: ");
        ops = brin.readLine();
        return ops;
    }

    private void candrun(Editer editer, String fname) throws IOException, InterruptedException {
        Convert convert = new Convert(editer.gettext(), "dict.txt");
        convert.dictionaryConvert();
        Compile compile = new Compile(convert.showResult(),String.valueOf(nowdir),fname);
        System.out.println(compile.build());
        System.out.println(compile.run());
    }

    private int dictruleadder() throws IOException, InterruptedException {
        System.out.println("Please enter the Source of the rule or exit for exit");
        BufferedReader dirod = new BufferedReader(new InputStreamReader(System.in));
        String dirodt = dirod.readLine();
        String source = "";
        String result = "";
        if (dirodt == "exit") {
            System.out.println("Back to mainmenu");
            render("mainmenu");
            return 0;
        } else {
            source = dirodt;
            System.out.println("Please enter the Source of the rule or exit for exit");
            dirodt = dirod.readLine();
            result = dirodt;
        }
        Convert convertdic = new Convert();
        convertdic.addrule(source,result);
        convertdic.dictionaryWritter("dict.txt");
        render("mainmenu");
        return 0;
    }

}
