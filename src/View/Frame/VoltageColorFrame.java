package View.Frame;

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
 * Created by user on 2017/3/16.
 */
public class VoltageColorFrame extends JFrame {
    private JPanel panel = new JPanel();
    private JButton vertify = new JButton("确定");
    public VoltageColorFrame(final DrawPanel network){
        final TTable table = new TTable();
        //隐藏表头右击菜单
        table.setTableHeaderPopupMenuFactory (null);
        //隐藏表体的右击菜单
        table.setTableBodyPopupMenuFactory(null);
        table.addColumn(new TTableColumn("id","序号"));
//        table.addColumn(new TTableColumn("ID"));
        table.addColumn(new TTableColumn("name","名称"));
        table.addColumn(new TTableColumn("color","颜色",150));
        table.addColumn(new TTableColumn("description","描述",150));
        table.setColumnResizable (false);
        table.setRowResizable(false);
        final TTableModel model = table.getTableModel();
        table.setFont(new Font("宋体", Font.BOLD,12));//设置字体

        model.getColumnByName("id").setSortable(false);
        model.getColumnByName("name").setSortable(false);
        model.getColumnByName("color").setSortable(false);
        model.getColumnByName("description").setSortable(false);


        //添加颜色改变监听器
        model.addTableListener(new TTableListener() {
            @Override
            public void rowClicked(int i, Vector vector, int i1) {
                JColorChooser chooser = new JColorChooser();
                Color color = chooser.showDialog(VoltageColorFrame.this,"选择颜色",Color.white);
                if(color!=null){
                    vector.setElementAt(color,4);
                    table.updateUI();
                }
            }

            @Override
            public void rowSelectionChanged(List list, boolean b) {

            }

            @Override
            public boolean beforeCellValueChanged(Vector vector, int i, Object o, Object o1) {
                return false;
            }

            @Override
            public void lockedChanged() {

            }

            @Override
            public void tableDataChanged(TTableModelEvent tTableModelEvent) {

            }

            @Override
            public void pageChanged() {

            }
        });


        /**
         * 设置表格数据
         */
        for(int i = 0;i<8;i++){
            Vector vector = new Vector();
            vector.addElement(new Integer(i));
            switch (i) {
                case 0:
                    vector.addElement("0kV");
                    vector.addElement(network.getVoltageColor_0());
                    vector.addElement("中性点");
                    break;
                case 1:
                    vector.addElement("6kV");
                    vector.addElement(network.getVoltageColor_6());
                    vector.addElement("交流6kV");
                    break;
                case 2:
                    vector.addElement("10kV");
                    vector.addElement(network.getVoltageColor_10());
                    vector.addElement("交流10kV");
                    break;
                case 3:
                    vector.addElement("35kV");
                    vector.addElement(network.getVoltageColor_35());
                    vector.addElement("交流35kV");
                    break;
                case 4:
                    vector.addElement("110kV");
                    vector.addElement(network.getVoltageColor_110());
                    vector.addElement("交流110kV");
                    break;
                case 5:
                    vector.addElement("220kV");
                    vector.addElement(network.getVoltageColor_220());
                    vector.addElement("交流220kV");
                    break;
                case 6:
                    vector.addElement("500kV");
                    vector.addElement(network.getVoltageColor_500());
                    vector.addElement("交流500kV");
                    break;
                case 7:
                    vector.addElement("750kV");
                    vector.addElement(network.getVoltageColor_750());
                    vector.addElement("交流750kV");
                    break;
            }
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
                network.setVoltageColor_0((Color)(model.getValueAt(0,2)));
                network.setVoltageColor_6((Color)(model.getValueAt(1,2)));
                network.setVoltageColor_10((Color)(model.getValueAt(2,2)));
                network.setVoltageColor_35((Color)(model.getValueAt(3,2)));
                network.setVoltageColor_110((Color)(model.getValueAt(4,2)));
                network.setVoltageColor_220((Color)(model.getValueAt(5,2)));
                network.setVoltageColor_500((Color)(model.getValueAt(6,2)));
                network.setVoltageColor_750((Color)(model.getValueAt(7,2)));
                VoltageColorFrame.this.setVisible(false);
            }
        });

        this.setTitle("选择颜色");
        this.setSize(460,280);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.getContentPane().add(new JScrollPane(table),BorderLayout.CENTER);
        this.getContentPane().add(panel,BorderLayout.SOUTH);
        TWaverUtil.centerWindow(this);
        this.setVisible(true);
    }
}
