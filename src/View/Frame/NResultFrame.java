package View.Frame;

import Utils.DataSet;
import View.DrawPanel;
import twaver.TWaverUtil;
import twaver.table.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * Created by huzhejie on 2017/5/8.
 */
public class NResultFrame extends JFrame{
    private JPanel panel = new JPanel();
    private JButton vertify = new JButton("确定");
    public NResultFrame(List<DataSet> set){
        final TTable table = new TTable();
        //隐藏表头右击菜单
        table.setTableHeaderPopupMenuFactory (null);
        //隐藏表体的右击菜单
        table.setTableBodyPopupMenuFactory(null);
        table.addColumn(new TTableColumn("id","序号"));
//        table.addColumn(new TTableColumn("ID"));
        table.addColumn(new TTableColumn("name","设备名称"));
        table.addColumn(new TTableColumn("isOrNot","是否有N-1",80));
        table.addColumn(new TTableColumn("description","描述",150));
        table.setColumnResizable (true);
        table.setRowResizable(false);
        final TTableModel model = table.getTableModel();
        table.setFont(new Font("宋体", Font.BOLD,12));//设置字体

        model.getColumnByName("id").setSortable(false);
        model.getColumnByName("name").setSortable(false);
        model.getColumnByName("isOrNot").setSortable(false);
        model.getColumnByName("description").setSortable(false);


        /**
         * 设置表格数据
         */
        for(int i = 0;i<set.size();i++){
            Vector vector = new Vector();
            vector.addElement(new Integer(i));
            vector.addElement(set.get(i).getData().getName());
            vector.addElement(set.get(i).getList().size()==0?"是":"否");
            String s = "";
            if(set.get(i).getList().size()!=0)
                s+=set.get(i).getList().get(i)+";";
            vector.addElement(s);
            model.addRow(vector);
        }

        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.add(vertify);
        /**
         * 确定按钮的系统
         */
        vertify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NResultFrame.this.setVisible(false);
            }
        });

        this.setTitle("N-1检测结果");
        this.setSize(600,280);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.getContentPane().add(new JScrollPane(table),BorderLayout.CENTER);
        this.getContentPane().add(panel,BorderLayout.SOUTH);
        TWaverUtil.centerWindow(this);
        this.setVisible(true);
    }
}
