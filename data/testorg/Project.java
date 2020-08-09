package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Project {
    private String projectlocation;
    private String projectname;
    private ArrayList<JavaFile> listofClasses;
    private String startClassName;
    private String tmpLine;
    private String filename;
    private StringBuffer projectText = new StringBuffer();

    JavaFile javaFile;

    public Project(boolean creat,String location,String name) throws IOException {
        String os = System.getProperty("os.name");
        this.filename = name;
        if (os.toLowerCase().startsWith("win")) {
            this.projectlocation = location;
        } else {
            String loclinux = location.replaceAll("\\\\","/");
            this.projectlocation = loclinux;
        }
        //this.projectlocation = location.substring(0,location.length() - name.length());
        this.projectname = name;
        this.listofClasses = new ArrayList<JavaFile>();
        if (creat) {
            creatNewProject();
        } else {
            openProject();
        }
    }

    public void openProject() throws IOException {
        System.out.println("choose");
        FileInputStream inputS = new FileInputStream(projectlocation + filename);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputS));
        projectReader(reader);
        reader.close();
        inputS.close();
    }

    private void projectReader(BufferedReader reader) throws IOException {
        tmpLine = reader.readLine();
        while (tmpLine != null && !tmpLine.equals("")) {
            switch (tmpLine) {
                case "ProjectName":
                    tmpLine = reader.readLine();
                    projectname = tmpLine;
                    break;
                case "StartClassName":
                    tmpLine = reader.readLine();
                    startClassName = tmpLine;
                    break;
                case "FileInfo":
                    javaFile = new JavaFile();
                    tmpLine = reader.readLine();
                    javaFile.setname(tmpLine);
                    javaFile.location = projectlocation;
                    this.listofClasses.add(javaFile);
                    break;
            }
            tmpLine = reader.readLine();
        }
    }
                    /*case "PackageName":
                    tmpLine = reader.readLine();
                    javaFile.addPakageName(tmpLine);
                    break;*/


    public String genCompileOrder() {
        StringBuilder tmp = new StringBuilder("-d " + projectlocation + " ");
        for (JavaFile listofClass : listofClasses) {
            tmp.append(projectlocation).append(listofClass.filename).append(".java").append(" ");
        }
        return tmp.toString();
    }

    public void convertAll(Convert convert) {
        for (JavaFile listofClass : listofClasses) {
            listofClass.convertandOutPut(convert);
        }
    }

    public String getStartClassName() {
        return startClassName;
    }

    public String getProjectlocation() {
        return projectlocation;
    }

    public String getFileTree() {
        int n = 0;
        StringBuffer sb = new StringBuffer();
        sb.append("Project Name:").append(this.projectname).append("\n");
        sb.append("Project Location:").append(this.projectlocation).append("\n");
        for (JavaFile listofClass : listofClasses) {
            sb.append("No" + n + ":");
            sb.append(listofClass.filename);
            sb.append("\n");
            n++;
        }
        return String.valueOf(sb);
    }

    public String getSelectFile(int index) {
        listofClasses.get(index).readFile();
        return String.valueOf(listofClasses.get(index).fileContain);
    }

    public boolean setSelectFile(int index,String contain) {
        try {
            listofClasses.get(index).setFileContain(contain);
            listofClasses.get(index).setFile();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public void creatNewProject() throws IOException {
        projectText.append("ConfigStart\nProjectName\n");
        projectText.append(this.filename + "\n");
        writeProject();
    }

    public void writeProject() throws IOException {
        FileSync fileSync = new FileSync(projectText,projectlocation + "\\",projectname);
        fileSync.setFile(projectname + ".JCHprojectinfo");
    }

    public void addClass(String classname) {
        javaFile = new JavaFile();
        javaFile.setname(classname);
        javaFile.setLocation(this.projectlocation);
        this.listofClasses.add(javaFile);
    }

    public void setStartClassName(String className) {
        this.startClassName = className;
    }

    public void generateProjectText() {
        projectText = new StringBuffer();
        projectText.append("ConfigStart\nProjectName\n");
        projectText.append(this.filename + "\n");
        projectText.append("StartClassName\n");
        projectText.append(startClassName + "\n");
        for (JavaFile className : listofClasses) {
            projectText.append("FileInfo\n");
            projectText.append(className.filename + "\n");
        }
    }

    public void setProjectlocation(String projectlocation) {
        this.projectlocation = projectlocation;
    }
}
