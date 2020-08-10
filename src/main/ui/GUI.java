package ui;

import com.sun.scenario.effect.impl.sw.java.JSWBlend_MULTIPLYPeer;
import model.Compile;
import model.Convert;
import model.Project;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class GUI {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

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
    JMenu dictionaryMenu = new JMenu("Dictionary 字典设置");

    JMenuItem openProject = new JMenuItem("Open Project 打开工程");
    JFileChooser projectFileChooser = new JFileChooser();
    JMenuItem closeProject = new JMenuItem("Close Project 关闭工程");
    JMenuItem newProject = new JMenuItem("New Project 新建工程");
    JFileChooser newProjectLocChooser = new JFileChooser();
    JMenuItem addFiletoProject = new JMenuItem("Add File to Project 将文件加入工程");
    JMenuItem deleteFileFromProject = new JMenuItem("Delete File from Project 从工程中删除文件");
    JMenuItem setStartClassName = new JMenuItem("Set the Start Class Name 更改启动类的名称");
    JMenuItem buildOnly = new JMenuItem("Build 仅构建");
    JMenuItem buildandRun = new JMenuItem("Build and Run 构建并运行");
    JMenuItem saveOnly = new JMenuItem("Save Only 仅保存");
    JMenuItem saveandExit = new JMenuItem("Save and Exit 保存并退出");
    JMenuItem chooseFile = new JMenuItem("Choose the File That need Edit 选择需要编辑的文件");
    JMenuItem refresh = new JMenuItem("Refresh 刷新文本");
    JMenuItem chooseDictionary = new JMenuItem("Choose Dictionary 选择字典");
    JFileChooser dictionaryChooser = new JFileChooser();
    JMenuItem addrulestoDictionary = new JMenuItem("Add Rule to Dictionary 向字典中加入规则");

    Project pj;
    Compile cp;
    int index;
    Convert convert;

    private String osdetector(String loc) {
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {
            return loc;
        } else {
            String loclinux = loc.replaceAll("\\\\","/");
            return loclinux;
        }
    }

    public GUI() throws IOException {

        mainFrame.setBounds(0,0,screenSize.width,screenSize.height);

        mainPanel.setBackground(Color.white);
        mainPanel.setLayout(new BorderLayout());
        menuBuilder();

        projectContainArea.insert("Not Opend Project Yet",0);
        submenubuilder();

        JScrollPane editorPanel = new JScrollPane(editorPane);
        JScrollPane resPanel = new JScrollPane(resArea);
        JScrollPane projectContainPanel = new JScrollPane(projectContainArea);

        mainPanel.add(mainMenu,BorderLayout.NORTH);
        mainPanel.add(editorPanel,BorderLayout.CENTER);
        mainPanel.add(projectContainPanel,BorderLayout.WEST);
        projectContainArea.setPreferredSize(new Dimension((int) (0.2 * (mainFrame.getSize().width)),
                (int) (0.9 * (mainFrame.getSize().height))));
        projectContainArea.setLineWrap(true);
        mainPanel.add(resPanel,BorderLayout.SOUTH);
        resArea.setPreferredSize(new Dimension((int) (0.8 * (mainFrame.getSize().width)),
                (int) (0.2 * (mainFrame.getSize().height))));
        resArea.setLineWrap(true);

        mainFrame.add(mainPanel);

        setMainFrame();


    }

    private void menuBuilder() {
        projectMenu.add(openProject);
        projectMenu.add(closeProject);
        projectMenu.add(newProject);
        projectMenu.add(addFiletoProject);
        projectMenu.add(setStartClassName);
        projectMenu.add(deleteFileFromProject);
        bandrMenu.add(buildOnly);
        bandrMenu.add(buildandRun);
        dictionaryMenu.add(chooseDictionary);
        dictionaryMenu.add(addrulestoDictionary);
        saveMenu.add(saveOnly);
        saveMenu.add(saveandExit);
        viewMenu.add(chooseFile);
        viewMenu.add(refresh);
        mainMenu.add(projectMenu);
        mainMenu.add(dictionaryMenu);
        mainMenu.add(bandrMenu);
        mainMenu.add(saveMenu);
        mainMenu.add(viewMenu);

    }

    private void submenubuilder() {
        setOpenProject();
        setBuildOnly();
        setBuildandRun();
        setSaveOnly();
        setSaveandExit();
        setchooseFile();
        setChooseDictionary();
        setAddrulestoDictionary();
        setRefresh();
        setCloseProject();
        setNewProject();
        setAddFiletoProject();
        setSetStartClassName();
        setDeleteFileFromProject();
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
                        projectlocation = projectlocation.substring(0,projectlocation.length()
                                - projectConfigName.length());
                        pj = new Project(false,projectlocation,projectConfigName);
                        System.out.println(pj.getProjectlocation());
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

    private void setNewProject() {
        newProject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newProjectLocation = "";
                newProjectLocChooser.setMultiSelectionEnabled(false);
                newProjectLocChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                newProjectLocChooser.setAcceptAllFileFilterUsed(true);
                if (newProjectLocChooser.showOpenDialog(newProject) == JFileChooser.APPROVE_OPTION) {
                    File file = newProjectLocChooser.getSelectedFile();
                    try {
                        newProjectLocation = file.getCanonicalPath() + "\\";
                        System.out.println(newProjectLocation);
                        String s = JOptionPane.showInputDialog("Please Enter the Name of Project\n 请输入工程名称:");
                        pj = new Project(true,newProjectLocation,s);
                        projectContainArea.setText(pj.getFileTree());
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        });
    }

    private void setCloseProject() {
        closeProject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pj = null;
                editorPane.setText("");
                resArea.setText("");
                projectContainArea.setText("Not Opend Project Yet");
            }
        });
    }

    private void setAddFiletoProject() {
        addFiletoProject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = JOptionPane.showInputDialog("Please Enter the Name of File"
                        + "\n Do not include .javaCH"
                        + "\n 请输入文件名称，不包括后缀名:");
                try {
                    pj.addClass(s);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                pj.generateProjectText();
                try {
                    pj.writeProject();
                    projectContainArea.setText(pj.getFileTree());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }

    private void setSetStartClassName() {
        setStartClassName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = JOptionPane.showInputDialog("Please Enter the Start Class"
                        + "\n Using Package Name + . + ClassName\n Such as UI.Main"
                        + "\n 请输入启动的类名称\n使用包名+点+类名的形式,比如\n UI.Main:");
                try {
                    pj.setStartClassName(s);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }

    private void setDeleteFileFromProject() {
        deleteFileFromProject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = JOptionPane.showInputDialog("Please enter the class that need to be delete\n"
                        + "请输入待删除的类名");
                if (pj.deleteClass(s)) {
                    JOptionPane.showMessageDialog(null,"Delete success 删除成功");
                    projectContainArea.setText(pj.getFileTree());
                } else {
                    JOptionPane.showMessageDialog(null,"Class not exist 工程内没有这个类");
                }
            }
        });
    }

    private void setBuildOnly() {
        buildOnly.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
                try {
                    cp = new Compile();
                    if (pj.ifNoStartClassName()) {
                        String s = JOptionPane.showInputDialog("Please Enter the Start Class"
                                + "\n Using Package Name + . + ClassName\n Such as UI.Main"
                                + "\n 请输入启动的类名称\n使用包名+点+类名的形式,比如\n UI.Main:");
                        pj.setStartClassName(s);
                    }
                    pj.convertAll(convert);
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
                    save();
                    if (pj.ifNoStartClassName()) {
                        String s = JOptionPane.showInputDialog("Please Enter the Start Class"
                                + "\n Using Package Name + . + ClassName\n Such as UI.Main"
                                + "\n 请输入启动的类名称\n使用包名+点+类名的形式,比如\n UI.Main:");
                        pj.setStartClassName(s);
                    }
                    cp = new Compile();
                    pj.convertAll(convert);
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
                save();
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

    private void setChooseDictionary() {
        chooseDictionary.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dictionaryLocation = "";
                dictionaryChooser.setMultiSelectionEnabled(false);
                dictionaryChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                dictionaryChooser.setAcceptAllFileFilterUsed(true);
                if (dictionaryChooser.showOpenDialog(openProject) == JFileChooser.APPROVE_OPTION) {
                    File file = dictionaryChooser.getSelectedFile();
                    try {
                        dictionaryLocation = file.getCanonicalPath();
                        String dictionaryName = file.getName();
                        convert = new Convert(new StringBuffer(),dictionaryName,
                                dictionaryLocation.substring(0,dictionaryLocation.length()
                                        - dictionaryName.length()));
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }

            }
        });
    }

    private void setAddrulestoDictionary() {
        String source = null;
        String result = null;
        addrulestoDictionary.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String source = JOptionPane.showInputDialog("Please enter the rule's source \n 请输入规则的源本");
                String result = JOptionPane.showInputDialog("Please enter the rule's result \n 请输入规则的转换结果");
                convert.addrule(source,result);
                try {
                    convert.dictionaryWritter(convert.getLocation(),convert.getDictName());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

    }

    private void setchooseFile() {
        chooseFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
                String s = JOptionPane.showInputDialog("Please enter the File's Number\n 请输入文件编号:");
                index = Integer.valueOf(s);
                System.out.println(index);
                setEditorPane(index);
            }
        });
    }

    private void setRefresh() {
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
                setEditorPane(index);
                setHighlighter();
            }
        });
    }

    private void setProjectFileChooserConfig() {
        projectFileChooser.setMultiSelectionEnabled(false);
        projectFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        projectFileChooser.setAcceptAllFileFilterUsed(true);
    }

    private void setHighlighter() {
        Highlighter highlighter = editorPane.getHighlighter();
        String text = editorPane.getText();
        DefaultHighlighter.DefaultHighlightPainter p = new DefaultHighlighter.DefaultHighlightPainter(Color.yellow);
        String[] source = convert.getDictSource();
        int pos;
        for (String string : source) {
            pos = 0;
            while ((pos = text.indexOf(string, pos)) >= 0) {
                try {
                    highlighter.addHighlight(pos, pos + string.length(), p);
                    pos += string.length();
                } catch (BadLocationException e) {
                    e.printStackTrace();
                }
            }
            pos = 0;
        }
    }

    private void save() {
        pj.setSelectFile(index,editorPane.getText());
        resArea.setText(resArea.getText() + "Save Success");
    }

    private void setEditorPane(int index) {
        editorPane.setText(pj.getSelectFile(index));
        setHighlighter();
    }

    private void setMainFrame() throws IOException {
        String location = ".\\data\\";
        convert = new Convert(new StringBuffer(),"dict.txt",osdetector(location));


        mainFrame.addComponentListener(new ComponentAdapter() {
            @Override public void componentResized(ComponentEvent e) {
                resArea.setPreferredSize(new Dimension((int) (0.8 * (mainFrame.getSize().width)),
                        (int) (0.2 * (mainFrame.getSize().height))));
                projectContainArea.setPreferredSize(new Dimension((int) (0.2 * (mainFrame.getSize().width)),
                        (int) (0.9 * (mainFrame.getSize().height))));
            }
        });
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }



}

