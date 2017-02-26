package SystemSimulation;

import twaver.TWaverUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Created by huzhejie on 2017/2/15.
 */
public class MenuBar extends JMenuBar {
    private JMenu fileMenu = new JMenu("文件(F)");
    private JMenu editMenu = new JMenu("编辑(E)");
    private JMenu viewMenu = new JMenu("设置(S)");
    private JMenu topologyMenu = new JMenu("拓扑(T)");
    private JMenu helpMenu = new JMenu("帮助(H)");
    public MenuBar(){
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
        JMenuItem item1=new JMenuItem("打开(O)",KeyEvent.VK_O);
        item1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
        fileMenu.add(item1);
        //保存
        JMenuItem item2=new JMenuItem("保存(S)",KeyEvent.VK_S);
        item2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
        fileMenu.add(item2);
        //导出图片
        JMenuItem item3 = new JMenuItem("导出图片");
        fileMenu.add(item3);
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
        JMenuItem item11 = new JMenuItem("工作区大小设置",KeyEvent.VK_G);
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
    }

}
