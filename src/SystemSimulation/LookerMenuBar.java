package SystemSimulation;

import View.DrawPanel;
import View.Frame.BlackoutNoticeFrame;
import View.Frame.ExportImageFrame;
import View.Frame.OpenFrame;
import View.Frame.VoltageColorFrame;
import twaver.base.A.E.P;
import twaver.network.InteractionModeFactory;

import javax.management.JMException;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by user on 2017/3/27.
 */
public class LookerMenuBar extends JMenuBar {
    private JMenu fileMenu = new JMenu("文件(F)");
    private JMenu viewMenu = new JMenu("设置(S)");
    private JMenu operateMenu = new JMenu("操作(O)");
    private JMenu standingBookMenu = new JMenu("设备台帐信息(I)");
    private JMenu deviceInfoMenu = new JMenu("设备统计信息(D)");
    private JMenu runMenu = new JMenu("运行方式(R)");
    private JMenu runAdjustMenu = new JMenu("运行方式调整(A)");

    protected OpenFrame openFrame = null;

    public LookerMenuBar(final DrawPanel network){
        //为菜单设置快捷键（ALT+一个英文字母）
        fileMenu.setMnemonic(KeyEvent.VK_F);
        viewMenu.setMnemonic(KeyEvent.VK_S);
        operateMenu.setMnemonic(KeyEvent.VK_O);
        standingBookMenu.setMnemonic(KeyEvent.VK_I);
        deviceInfoMenu.setMnemonic(KeyEvent.VK_D);
        runMenu.setMnemonic(KeyEvent.VK_R);
        runAdjustMenu.setMnemonic(KeyEvent.VK_A);

        /**
         * 文件菜单
         */
        //打开文件
        JMenuItem item1=new JMenuItem("打开/查看历史图(O)",KeyEvent.VK_O);
        item1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        fileMenu.add(item1);
        //导出图片(png)
        JMenuItem item2 = new JMenuItem("导出图片(png)");
        fileMenu.add(item2);

        /**
         * 设置菜单
         */
        //设置电压等级颜色
        JMenuItem item3 = new JMenuItem("设置电压等级颜色");
        viewMenu.add(item3);
        //重连数据库
        JMenuItem item4 = new JMenuItem("重连数据库");
        item4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        viewMenu.add(item4);

        /**
         * 操作菜单
         */
        //适合窗口
        JMenuItem item5 = new JMenuItem("适合窗口");
        item5.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,ActionEvent.CTRL_MASK));
        operateMenu.add(item5);
        //拖动浏览
        JMenuItem item6 = new JMenuItem("拖动浏览");
        item6.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,ActionEvent.CTRL_MASK));
        operateMenu.add(item6);

        /**
         * 设备统计信息
         */
        //配电数量统计
        JMenuItem item7 = new JMenuItem("配电数量统计");
        deviceInfoMenu.add(item7);
        //配变数量统计
        JMenuItem item8 = new JMenuItem("配变数量统计");
        deviceInfoMenu.add(item8);
        //开关数量统计
        JMenuItem item9 = new JMenuItem("开关数量统计");
        deviceInfoMenu.add(item9);

        /**
         * 设备台帐信息
         */
        //配电信息
        JMenuItem item10 = new JMenuItem("配电信息");
        standingBookMenu.add(item10);
        //配变信息
        JMenuItem item11 = new JMenuItem("配变信息");
        standingBookMenu.add(item11);
        //开关信息
        JMenuItem item12 = new JMenuItem("开关信息");
        standingBookMenu.add(item12);

        /**
         * 运行方式
         */
        //新建运行方式
        JMenuItem item13 = new JMenuItem("新建运行方式");
        runMenu.add(item13);
        JMenuItem item14 = new JMenuItem("加载运行方式");
        runMenu.add(item14);
        JMenuItem item15 = new JMenuItem("运行方式管理");
        runMenu.add(item15);
        JMenuItem item16 = new JMenuItem("保存运行方式");
        runMenu.add(item16);
        JMenuItem item17 = new JMenuItem("另存运行方式");
        runMenu.add(item17);

        /**
         * 运行方式调整
         */
        JMenuItem item18 = new JMenuItem("开始运行方式调整");
        runAdjustMenu.add(item18);
        JMenuItem item19 = new JMenuItem("取消运行方式调整");
        runAdjustMenu.add(item19);
        JMenuItem item20 = new JMenuItem("完成运行方式调整");
        runAdjustMenu.add(item20);
        JMenuItem item21 = new JMenuItem("返回上一操作");
        runAdjustMenu.add(item21);
        JMenuItem item22 = new JMenuItem("生成停电通知单");
        runAdjustMenu.add(item22);

        this.add(fileMenu);
        this.add(viewMenu);
        this.add(operateMenu);
        this.add(standingBookMenu);
        this.add(deviceInfoMenu);
        this.add(runMenu);
        this.add(runAdjustMenu);

        /**
         * 菜单栏逻辑部分
         */
        //打开文件
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
        //导出图像
        item2.addActionListener(new ActionListener() {
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

        //设置电压颜色
        item3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VoltageColorFrame(network);
            }
        });
        //重连数据库
        item4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        //适合窗口
        item5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                network.forceAdjustCanvasSize();
            }
        });
        //拖动浏览
        item6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                network.setInteractionMode(InteractionModeFactory.getPanMode(network));
            }
        });


        //生成停电通知单
        item22.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BlackoutNoticeFrame();
            }
        });
    }
}
