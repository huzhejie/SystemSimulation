package SystemSimulation;

import View.DrawPanel;
import View.Frame.ExportImageFrame;
import View.Frame.OpenFrame;
import View.Frame.VoltageColorFrame;
import twaver.*;
import twaver.base.A.E.P;
import twaver.network.background.ColorBackground;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by huzhejie on 2017/2/15.
 */
public class MenuBar extends JMenuBar {
    private JMenu fileMenu = new JMenu("文件(F)");
    private JMenu editMenu = new JMenu("编辑(E)");
    private JMenu viewMenu = new JMenu("设置(S)");
    private JMenu topologyMenu = new JMenu("拓扑(T)");
    private JMenu helpMenu = new JMenu("帮助(H)");
    
    protected OpenFrame openFrame = null;
    public MenuBar(final DrawPanel network){
        //为菜单设置快捷键（ALT+一个英文字母）
        fileMenu.setMnemonic(KeyEvent.VK_F);
        editMenu.setMnemonic(KeyEvent.VK_E);
        viewMenu.setMnemonic(KeyEvent.VK_S);
        topologyMenu.setMnemonic(KeyEvent.VK_T);
        helpMenu.setMnemonic(KeyEvent.VK_H);
        /**
         * 文件菜单
         */
        //新建
        JMenuItem item=new JMenuItem("新建(N)",KeyEvent.VK_N);
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
        fileMenu.add(item);
        //打开
        JMenuItem item1=new JMenuItem("打开/查看历史图(O)",KeyEvent.VK_O);
        item1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
        fileMenu.add(item1);
        //保存
        JMenuItem item2=new JMenuItem("保存(S)",KeyEvent.VK_S);
        item2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
        fileMenu.add(item2);
        //导出图片
        JMenuItem item3 = new JMenuItem("导出图片(png)");
        fileMenu.add(item3);
        //导出SVG
        JMenuItem item0 = new JMenuItem("导出图片(SVG)");
        fileMenu.add(item0);
        /**
         * 编辑菜单
         */
        //撤销
        JMenuItem item4 = new JMenuItem("撤销",KeyEvent.VK_Z);
        item4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,ActionEvent.CTRL_MASK));
        editMenu.add(item4);
        //恢复
        JMenuItem item5 = new JMenuItem("恢复",KeyEvent.VK_Y);
        item5.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y,ActionEvent.CTRL_MASK));
        editMenu.add(item5);
        //复制
        JMenuItem item6 = new JMenuItem("复制",KeyEvent.VK_C);
        item6.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK));
        editMenu.add(item6);
        //粘贴
        JMenuItem item7 = new JMenuItem("粘贴",KeyEvent.VK_V);
        item7.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,ActionEvent.CTRL_MASK));
        editMenu.add(item7);
        //删除
        JMenuItem item8 = new JMenuItem("删除",KeyEvent.VK_DELETE);
        editMenu.add(item8);
        //全选
        JMenuItem item9 = new JMenuItem("全选",KeyEvent.VK_A);
        item9.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK));
        editMenu.add(item9);
        /**
         * 设置菜单
         */
        //工作区背景色设置
        JMenuItem item10 = new JMenuItem("工作区背景设置",KeyEvent.VK_B);
        item10.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B,ActionEvent.CTRL_MASK));
        viewMenu.add(item10);
        //工作区大小设置
        JMenuItem item11 = new JMenuItem("自动校正工作区大小",KeyEvent.VK_G);
        item11.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G,ActionEvent.CTRL_MASK));
        viewMenu.add(item11);
        //设置电压等级颜色
        JMenuItem item12 = new JMenuItem("设置电压等级颜色");
        viewMenu.add(item12);
        //重连数据库
        JMenuItem item13 = new JMenuItem("重连数据库",KeyEvent.VK_R);
        item13.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,ActionEvent.CTRL_MASK));
        viewMenu.add(item13);
        /**
         * 拓扑菜单
         */
        //拓扑当前项目
        JMenuItem item14 = new JMenuItem("拓扑当前项目",KeyEvent.VK_F1);
        item14.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1,ActionEvent.CTRL_MASK));
        topologyMenu.add(item14);
        //设备资料完整性检查
        JMenuItem item15 = new JMenuItem("设备资料完整性检查",KeyEvent.VK_F2);
        item15.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2,ActionEvent.CTRL_MASK));
        topologyMenu.add(item15);
        //图元入库
        JMenuItem item16 = new JMenuItem("图元入库");
        topologyMenu.add(item16);
        /**
         * 帮助菜单
         */




        this.add(fileMenu);
        this.add(editMenu);
        this.add(viewMenu);
        this.add(topologyMenu);
        this.add(helpMenu);

        /**
         * 菜单栏逻辑部分
         */
        //新建文件
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = null;
                int option = JOptionPane.showConfirmDialog(null,
                        "是否保存当前文件？", "保存文件？", JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE, null);
                switch (option) {
                    case JOptionPane.YES_NO_OPTION: {
                        network.clearSelection();
                        if(chooser == null) {
                            FileSystemView fsv = FileSystemView.getFileSystemView();
                            chooser = new JFileChooser(fsv);
                            chooser.setFileFilter(new FileFilter() {
                                public boolean accept(File f) {
                                    return f.isDirectory() || f.getName().toLowerCase().endsWith(".xml");
                                }
                                public String getDescription() {
                                    return "XML File";
                                }
                            });
                        }

                        String fileName = null;
                        int returnVal = chooser.showSaveDialog(P.B(network));
                        if(returnVal == 0) {
                            fileName = chooser.getSelectedFile().getAbsolutePath();
                            if(fileName != null) {
                                if(!fileName.toUpperCase().endsWith(".XML")) {
                                    fileName = fileName + ".xml";
                                }

                                try {
                                    DataBoxOutputSetting ex = new DataBoxOutputSetting();
                                    ex.setWithAlarmState(true);
                                    ex.setWithLayers(true);
                                    network.getDataBox().output(fileName, ex);
                                } catch (IOException exx) {
                                    TWaverUtil.handleError((String)null, exx);
                                }
                            }
                        }
                        network.getDataBox().clear();
                        break;
                    }
                    case JOptionPane.NO_OPTION:
                        network.getDataBox().clear();
                        break;
                }
            }
        });
        //打开文件夹目录
        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String path;
                FileSystemView fsv = FileSystemView.getFileSystemView();
                JFileChooser chooser = new JFileChooser(fsv);
                chooser.setCurrentDirectory(fsv.getHomeDirectory());
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int result = chooser.showOpenDialog(chooser);
                chooser.setDialogTitle("选择文件目录");
                chooser.setApproveButtonText("确定");
                if(result==JFileChooser.APPROVE_OPTION){
                    path = chooser.getSelectedFile().getAbsolutePath();
                    File file = new File(path);
                    File[] listFile = file.listFiles();
                    java.util.List<String> fileNames = new ArrayList<>();
                    java.util.List<Long> timeStamp = new ArrayList<>();
                    for(int i = 0;i<listFile.length;i++){
                        if(listFile[i].getName().toLowerCase().endsWith(".xml")) {
                            fileNames.add(listFile[i].getName().substring(0, listFile[i].getName().lastIndexOf(".")));
                            timeStamp.add(listFile[i].lastModified());
                        }
                    }
                    if(openFrame==null)
                        openFrame = new OpenFrame(fileNames,timeStamp,path,network);
                    else
                        openFrame.setVisible(true);
                }
            }
        });
        //保存文件
        item2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = null;
                network.clearSelection();
                if(chooser == null) {
                    FileSystemView fsv = FileSystemView.getFileSystemView();
                    chooser = new JFileChooser(fsv);
                    chooser.setFileFilter(new FileFilter() {
                        public boolean accept(File f) {
                            return f.isDirectory() || f.getName().toLowerCase().endsWith(".xml");
                        }

                        public String getDescription() {
                            return "XML File";
                        }
                    });
                }

                String fileName = null;
                int returnVal = chooser.showSaveDialog(P.B(network));
                if(returnVal == 0) {
                    fileName = chooser.getSelectedFile().getAbsolutePath();
                    if(fileName != null) {
                        if(!fileName.toUpperCase().endsWith(".XML")) {
                            fileName = fileName + ".xml";
                        }

                        try {
                            DataBoxOutputSetting ex = new DataBoxOutputSetting();
//                            ex.setWithElementId(this.F);
                            ex.setWithAlarmState(true);
                            ex.setWithLayers(true);
//                            if(this.B) {
//                                TSubNetwork images = network.getCurrentSubNetwork();
//                                ex.setElementFilter(new SubNetworkPersistentFilter(images));
//                            }
//
//
//                                HashMap images1 = new HashMap();
//                                Iterator it = network.getDataBox().iterator();
//
//                                while(it.hasNext()) {
//                                    Element element = (Element)it.next();
//                                    MenuBar.this.A(images1, element.getImageURL());
//                                    MenuBar.this.A(images1, element.getIconURL());
//                                }
//
//                                ex.setImages(images1);

                            network.getDataBox().output(fileName, ex);
                        } catch (IOException exx) {
                            TWaverUtil.handleError((String)null, exx);
                        }

                    }
                }

            }
        });
        //导出图片(png)
        item3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Window parent = P.B(network);
                if(parent instanceof Frame) {
                    new ExportImageFrame((Frame)parent,network);
                } else {
                    new ExportImageFrame((Dialog)parent,network);
                }
            }
        });
        //导出图片(svg)
        item0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = null;
                if(chooser == null) {
                    chooser = new JFileChooser();
                    chooser.setFileFilter(new FileFilter() {
                        public boolean accept(File f) {
                            return f.isDirectory() || f.getName().toLowerCase().endsWith(".svg");
                        }

                        public String getDescription() {
                            return "SVG File";
                        }
                    });
                }

                int returnVal = chooser.showSaveDialog(P.B(network));
                if(returnVal == 0) {
                    String fileName = chooser.getSelectedFile().getAbsolutePath();
                    network.exportSVG(fileName);
                }
            }
        });
        //设置背景色
        item10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JColorChooser chooser = new JColorChooser();
                Color color = chooser.showDialog(network,"背景色选择",network.getBackground());
                if(color!=null){
                    network.setBackground(new ColorBackground(color));
                }
            }
        });
        //调整画布大小
        item11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                network.forceAdjustCanvasSize();
            }
        });
        //电压等级颜色调整
        item12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               new VoltageColorFrame(network);
            }
        });



    }
}
