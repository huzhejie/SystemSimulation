package View.Frame;

import twaver.TSubNetwork;
import twaver.TWaverUtil;
import twaver.network.TNetwork;
import twaver.table.*;
import twaver.table.renderer.NumberRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * Created by user on 2017/3/20.
 */
public class OpenFrame extends JFrame {
    private JPanel panel = new JPanel();
    private JButton vertify = new JButton("关闭");
    private TTable table = new TTable();
    private SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public OpenFrame(List<String> fileName, List<Long> timeStamp, final String path, final TNetwork network){
        //隐藏表头右击菜单
        table.setTableHeaderPopupMenuFactory (null);

        table.addColumn(new TTableColumn("id","序号",new NumberRenderer()));
        table.addColumn(new TTableColumn("name","文件名称",100));
        table.addColumn(new TTableColumn("description","文件描述",100));
        table.addColumn(new TTableColumn("topology","是否拓扑",60));
        table.addColumn(new TTableColumn("isRegular","类型",80));
        table.addColumn(new TTableColumn("date","创建时间",180));
        table.setColumnResizable (false);
        table.setRowResizable(false);
        final TTableModel model = table.getTableModel();
        table.setFont(new Font("宋体", Font.BOLD,12));

        model.getColumnByName("id").setSortable(false);
        model.getColumnByName("name").setSortable(false);
        model.getColumnByName("description").setSortable(false);
        model.getColumnByName("isRegular").setSortable(false);
        model.getColumnByName("date").setSortable(false);
        model.getColumnByName("topology").setSortable(false);

        /**
         * 添加数据
         */
        for(int i = 0;i<fileName.size();i++){
            Vector vector = new Vector();
            vector.addElement(new Integer(i));
            vector.addElement(fileName.get(i));
            vector.addElement(fileName.get(i));
            vector.addElement("是");
            vector.addElement("正视图");
            vector.addElement(format.format(timeStamp.get(i)));

            model.addRow(vector);
        }
        //添加双击打开文件事件
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount()==2){
                    String fileName  = ((Vector)table.getTableModel().getSelectedRows().get(0)).get(3).toString();
                    File file = new File(path+File.separator+fileName+".xml");
                    if(file.exists()){
                        try{
                            String ex = file.toURI().toURL().toString();
                            if(ex!=null){
                                TSubNetwork currentNet = network.getCurrentSubNetwork();
                                network.getDataBox().clear();
                                network.getDataBox().parse(ex, currentNet);
                            }
                            OpenFrame.this.setVisible(false);
                        }catch (Exception e1){
                            TWaverUtil.handleError((String)null, e1);
                        }
                    }
                    else{
                        System.out.println("null");
                    }
                }
            }
        });


        //添加右键菜单的监听器
       table.setTableBodyPopupMenuFactory(new TTablePopupMenuFactory() {
           @Override
           public JPopupMenu getPopupMenu(final TTable tTable, MouseEvent mouseEvent) {
               JPopupMenu menu = new JPopupMenu();
               JMenuItem item_1 = new JMenuItem("标记为草图");
               JMenuItem item_2 = new JMenuItem("标记为正式图");
               JMenuItem item_3 = new JMenuItem("删除配网图");
               JMenuItem item_4 = new JMenuItem("复制配网图到当前");
               JMenuItem item_5 = new JMenuItem("未选中任何行");

               menu.add(item_1);
               menu.add(item_2);
               menu.add(item_3);
               menu.add(item_4);
               menu.add(item_5);

               if(tTable.getTableModel().getSelectedRows()==null){
                   item_1.setVisible(false);
                   item_2.setVisible(false);
                   item_3.setVisible(false);
                   item_4.setVisible(false);
                   item_5.setVisible(true);
               }
               else{
                   item_1.setVisible(true);
                   item_2.setVisible(true);
                   item_3.setVisible(true);
                   item_4.setVisible(true);
                   item_5.setVisible(false);
               }

               item_1.addActionListener(new ActionListener() {
                   @Override
                   public void actionPerformed(ActionEvent e) {
                       ((Vector)tTable.getTableModel().getSelectedRows().get(0)).set(6,"草图");
                       tTable.updateUI();
                   }
               });
               item_2.addActionListener(new ActionListener() {
                   @Override
                   public void actionPerformed(ActionEvent e) {
                       ((Vector)tTable.getTableModel().getSelectedRows().get(0)).set(6,"正式图");
                       tTable.updateUI();
                   }
               });
               //删除配网图，并删除相应的文件和对应表格上的行，若找不到文件则打印null
               item_3.addActionListener(new ActionListener() {
                   @Override
                   public void actionPerformed(ActionEvent e) {
                       String fileName  = ((Vector)tTable.getTableModel().getSelectedRows().get(0)).get(3).toString();
                       File file = new File(path+File.separator+fileName+".xml");
                       if(file.exists()){
                           file.delete();
                           tTable.getTableModel().removeRow((Vector)tTable.getTableModel().getSelectedRows().get(0));
                           tTable.updateUI();
                       }
                       else{
                           System.out.println("null");
                       }
                   }
               });
               //复制配网图到当前,若找不到相应文件则打印null
               item_4.addActionListener(new ActionListener() {
                   @Override
                   public void actionPerformed(ActionEvent e) {
                       String fileName  = ((Vector)tTable.getTableModel().getSelectedRows().get(0)).get(3).toString();
                       File file = new File(path+File.separator+fileName+".xml");
                       if(file.exists()){
                           try{
                               String ex = file.toURI().toURL().toString();
                               if(ex!=null){
                                   TSubNetwork currentNet = network.getCurrentSubNetwork();
                                   network.getDataBox().parse(ex, currentNet);
                               }
                           }catch (Exception e1){
                               TWaverUtil.handleError((String)null, e1);
                           }
                       }
                       else{
                           System.out.println("null");
                       }
                   }
               });

               return menu;
           }
       });


        //设置按照配网图创建时间的排序规则
        table.getTableModel().getColumnByName("date").setSortComparator(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                try {
                    if (format.parse(o1.toString()).before(format.parse(o2.toString()))){
                        return 1;
                    }
                    else
                        return -1;
                }catch (ParseException e){
                    e.printStackTrace();
                }
                System.out.println("文件读取出错");
                return 0;
            }
        });

        //关闭按钮监听事件
        vertify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OpenFrame.this.setVisible(false);
            }
        });


        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.add(vertify);

        this.setTitle("选择文件");
        this.setSize(600,350);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.getContentPane().add(new JScrollPane(table),BorderLayout.CENTER);
        this.getContentPane().add(new TTableNavigator(table.getTableModel()),
                BorderLayout.NORTH);
        this.getContentPane().add(panel,BorderLayout.SOUTH);
        TWaverUtil.centerWindow(this);
        this.setVisible(true);

    }
}
