package ui;

import model.Compile;
import model.Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class GUI {

    JFrame mainFrame = new JFrame("Spark IDE");

    JPanel mainPanel = new JPanel();

    JMenuBar mainMenu = new JMenuBar();
    JTextPane editorPane = new JTextPane();
    JTextArea projectContainArea = new JTextArea();
    JTextArea resArea = new JTextArea();

    JMenu projectMenu = new JMenu("Project 工程");
    JMenu bandrMenu = new JMenu("Build Run 构建 运行");
    JMenu saveMenu = new JMenu("Save 保存");
    JMenu viewMenu = new JMenu("View 查看");

    JMenuItem openProject = new JMenuItem("Open Project 打开工程");
    JFileChooser projectFileChooser = new JFileChooser();
    JMenuItem closeProject = new JMenuItem("Close Project 关闭工程");
    JMenuItem newProject = new JMenuItem("New Project 新建工程");
    JMenuItem buildOnly = new JMenuItem("Build 仅构建");
    JMenuItem buildandRun = new JMenuItem("Build and Run 构建并运行");
    JMenuItem saveOnly = new JMenuItem("Save Only 仅保存");
    JMenuItem saveandExit = new JMenuItem("Save and Exit 保存并退出");
    JMenuItem chooseFile = new JMenuItem("Choose the File That need Edit 选择需要编辑的文件");


    Project pj;
    Compile cp;
    int index;

    public GUI() {
        mainFrame.setBounds(0,0,1280,720);


        mainPanel.setBackground(Color.white);
        mainPanel.setLayout(new BorderLayout());

        menuBuilder();
        mainMenu.add(projectMenu);
        mainMenu.add(bandrMenu);
        mainMenu.add(saveMenu);
        mainMenu.add(viewMenu);

        projectContainArea.insert("Not Opend Project Yet",0);
        setOpenProject();
        setBuildOnly();
        setBuildandRun();
        setSaveOnly();
        setSaveandExit();
        setchooseFile();

        mainPanel.add(mainMenu,BorderLayout.NORTH);
        mainPanel.add(editorPane,BorderLayout.CENTER);
        mainPanel.add(projectContainArea,BorderLayout.WEST);
        mainPanel.add(resArea,BorderLayout.SOUTH);

        mainFrame.add(mainPanel);

        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void menuBuilder() {
        projectMenu.add(openProject);
        projectMenu.add(closeProject);
        projectMenu.add(newProject);
        bandrMenu.add(buildOnly);
        bandrMenu.add(buildandRun);
        saveMenu.add(saveOnly);
        saveMenu.add(saveandExit);
        viewMenu.add(chooseFile);
    }

    private void setOpenProject() {
        openProject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setProjectFileChooserConfig();
                String projectlocation = "";
                if (projectFileChooser.showOpenDialog(openProject) == JFileChooser.APPROVE_OPTION) {
                    File file = projectFileChooser.getSelectedFile();
                    try {
                        projectlocation = file.getCanonicalPath();
                        String projectConfigName = file.getName();
                        pj = new Project(false,projectlocation,projectConfigName);
                        projectContainArea.setText(pj.getFileTree());
                        index = 0;
                        setEditorPane(index);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        });
    }

    private void setBuildOnly() {
        buildOnly.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cp = new Compile();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                try {
                    pj.convertAll("dict.txt");
                    String tmpRes = cp.build(pj.genCompileOrder());
                    resArea.setText(tmpRes);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }

            }
        });
    }

    private void setBuildandRun() {
        buildandRun.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cp = new Compile();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                try {
                    pj.convertAll("dict.txt");
                    String tmpRes = cp.build(pj.genCompileOrder());
                    tmpRes = tmpRes + "\n" + cp.run(pj.getProjectlocation(),pj.getStartClassName());
                    resArea.setText(tmpRes);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
        });
    }

    private void setSaveOnly() {
        saveOnly.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pj.setSelectFile(index,editorPane.getText());
                resArea.setText(resArea.getText() + "Save Success");
            }
        });
    }

    private void setSaveandExit() {
        saveandExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pj.setSelectFile(index,editorPane.getText());
                System.exit(0);
            }
        });
    }

    private void setchooseFile() {
        chooseFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = JOptionPane.showInputDialog("Please enter the File's Number\n 请输入文件编号:");
                index = Integer.valueOf(s);
                System.out.println(index);
                setEditorPane(index);
            }
        });
    }

    private void setProjectFileChooserConfig() {
        projectFileChooser.setMultiSelectionEnabled(false);
        projectFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        projectFileChooser.setAcceptAllFileFilterUsed(true);
    }

    private void setEditorPane(int index) {
        editorPane.setText(pj.getSelectFile(index));
    }


}
