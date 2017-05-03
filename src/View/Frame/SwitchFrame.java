package View.Frame;

import twaver.Element;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 2017/3/15.
 */
public class SwitchFrame extends JFrame{
    private JPanel mainPanel  =  new JPanel();
    private JPanel buttonPanel = new JPanel();

    private JTextField aa = new JTextField();
    private JTextField bb = new JTextField();
    private JTextField cc = new JTextField();
    private JComboBox  dd = new JComboBox();
    private JComboBox ee = new JComboBox();
    private JComboBox ff = new JComboBox();

    private JButton vertify = new JButton("确定");
    private JButton cancel = new JButton("取消");

    private SimpleDateFormat format =  new SimpleDateFormat("MM:dd hh:mm:ss");

    public SwitchFrame(final Element element, final Connection connection){
        super();
        this.setTitle("开关参数设置");
        this.setResizable(false);
        this.setVisible(true);
        this.setSize(600,400);
        this.setLocation((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2-300,(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2-200);
        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.BOTH;
        gc.gridy = 0;
        gc.ipady = 100;
        this.add(mainPanel,gc);
        gc.gridy = 1;
        gc.ipady = 50;
        this.add(buttonPanel,gc);

        //按钮面板
        vertify.setPreferredSize(new Dimension(120,30));
        cancel.setPreferredSize(new Dimension(120,30));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER,30,20));
        buttonPanel.add(vertify);
        buttonPanel.add(cancel);

        //主面板
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints g1 = new GridBagConstraints();
        JLabel a = new JLabel("图元名称:");
        JLabel b = new JLabel("设备ID:");
        JLabel c = new JLabel("设备描述:");
        JLabel d = new JLabel("电压等级:");
        JLabel e = new JLabel("所属区域:");
        JLabel f = new JLabel("所属厂站:");

        g1.insets = new Insets(10,0,10,5);
        g1.fill =GridBagConstraints.BOTH;
        g1.anchor = GridBagConstraints.CENTER;
        g1.gridheight = 1;
        g1.gridy = 0;
        g1.gridx = 0;
        g1.gridwidth = 1;
        mainPanel.add(a,g1);
        g1.gridwidth = 2;
        g1.gridx = 1;
        g1.ipadx = 100;
        mainPanel.add(aa,g1);
        g1.gridx = 3;
        g1.gridwidth = 1;
        g1.ipadx = 0;
        mainPanel.add(b,g1);
        g1.gridx = 4;
        g1.gridwidth = 2;
        g1.ipadx = 100;
        mainPanel.add(bb,g1);
        g1.gridy = 1;
        g1.gridx = 0;
        g1.gridwidth = 1;
        g1.ipadx = 0;
        mainPanel.add(c,g1);
        g1.gridwidth = 2;
        g1.gridx = 1;
        mainPanel.add(cc,g1);
        g1.gridx = 3;
        g1.gridwidth = 1;
        mainPanel.add(d,g1);
        g1.gridx = 4;
        g1.gridwidth = 2;
        mainPanel.add(dd,g1);
        g1.gridy = 2;
        g1.gridx = 0;
        g1.gridwidth = 1;
        mainPanel.add(e,g1);
        g1.gridwidth = 2;
        g1.gridx = 1;
        mainPanel.add(ee,g1);
        g1.gridx = 3;
        g1.gridwidth = 1;
        mainPanel.add(f,g1);
        g1.gridx = 4;
        g1.gridwidth = 2;
        mainPanel.add(ff,g1);

        aa.setText(element.getName());
        aa.setEditable(false);
        if(element.getClientProperty("numberID")!=null)
            bb.setText(element.getClientProperty("numberID").toString());
        else
            bb.setText("");
        bb.setEditable(false);

        dd.addItem("10kv");
        dd.addItem("35kv");
        dd.addItem("110kv");
        dd.addItem("220kv");
        dd.addItem("500kv");
        dd.setSelectedItem("10kv");

        ee.addItem("武昌区");
        ee.addItem("洪山区");
        ee.addItem("蔡甸区");
        ee.addItem("东湖风景区");
        ee.addItem("江夏区");
        ee.addItem("汉阳区");
        ee.setSelectedItem("武昌区");
        ff.addItem("武昌配电站");
        ff.addItem("洪山配电站");
        ff.addItem("蔡甸配电站");
        ff.addItem("东湖配电站");
        ff.addItem("江夏配电站");
        ff.addItem("汉阳配电站");
        ff.setSelectedItem("武昌配电站");


        /**
         * 载入数据
         */
        loadData(element,connection);

        vertify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //更新开关电压等级数据
                element.putClientProperty("rootVoltage",dd.getSelectedItem());
                //插入或更新数据
                writeData(element,connection);
                JOptionPane.showMessageDialog(null,"开关资料修改成功!");
                SwitchFrame.this.setVisible(false);
            }
        });
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwitchFrame.this.setVisible(false);
            }
        });


    }
    /**
     * 写入数据
     * @param element
     * @param connection
     */
    public void loadData(Element element,Connection connection){
        if(element.getClientProperty("ID")==null)
            return;
        try {
            Statement stmt = connection.createStatement();
            String query = "Select * FROM switch WHERE DeviceID='"+element.getClientProperty("ID")+"'";
            ResultSet rt = stmt.executeQuery(query);
            while(rt.next()){
                aa.setText(rt.getString(2));
                bb.setText(element.getClientProperty("numberID").toString());
                cc.setText(rt.getString(3));
                dd.setSelectedItem(rt.getObject(4));
                ee.setSelectedItem(rt.getObject(5));
                ff.setSelectedItem(rt.getObject(6));
            }
            rt.close();
            stmt.close();
        }catch (Exception e){
            System.out.println("开关数据载入错误");
            e.printStackTrace();
        }
    }

    /**
     * 插入或更新数据
     * @param element
     * @param connection
     */

    public void writeData(Element element,Connection connection){
        if(element.getClientProperty("numberID")!=null)
            try {
                element.putClientProperty("ID",format.format(new Date())+aa.getText().trim());
                Statement stmt = connection.createStatement();
                String query = "INSERT INTO switch(DeviceID,PrimitiveName,Description,VoltageLevel,BelongArea,BelongStation)"+
                        "VALUES ('"+element.getClientProperty("ID")+"','"+aa.getText().trim()+"','"+cc.getText().trim()+"','"+dd.getSelectedItem().toString()+"','"+ee.getSelectedItem().toString()+"','"+
                        ff.getSelectedItem().toString()+"')";
                stmt.execute(query);
                stmt.close();
            }catch (Exception e){
                System.out.println("开关数据插入错误");
                e.printStackTrace();
            }
        else
            try{
                Statement stmt = connection.createStatement();
                String query = "UPDATE switch SET PrimitiveName='"+aa.getText().trim()+"',Description='"+cc.getText().trim()+"',VoltageLevel='"+dd.getSelectedItem().toString()+"',BelongArea='"+ee.getSelectedItem().toString()+"',BelongStation='"+
                        ff.getSelectedItem().toString()+"' WHERE DeviceID='"+element.getClientProperty("ID")+"'";
                stmt.execute(query);
                stmt.close();
            }catch (Exception e){
                System.out.println("开关数据写入错误");
                e.printStackTrace();
            }

    }

}
