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
public class SubstationFrame extends JFrame{
    private JPanel mainPanel  =  new JPanel();
    private JPanel buttonPanel = new JPanel();

    private JTextField aa = new JTextField();
    private JTextField bb = new JTextField();
    private JTextField cc = new JTextField();
    private JTextField  dd = new JTextField();
    private JComboBox ee = new JComboBox();
    private JTextField ff = new JTextField();
    private JComboBox gg = new JComboBox();

    private JButton vertify = new JButton("确定");
    private JButton cancel = new JButton("取消");

    private SimpleDateFormat format =  new SimpleDateFormat("MM:dd hh:mm:ss");

    public SubstationFrame(final Element element, final Connection connection){
        super();
        this.setVisible(true);
        this.setTitle("厂站参数设置");
        this.setResizable(false);
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
        JLabel b = new JLabel("厂站 ID:");
        JLabel c = new JLabel("厂站描述:");
        JLabel d = new JLabel("站端 RTU:");
        JLabel e = new JLabel("电压等级:");
        JLabel f = new JLabel("电源标识:");
        JLabel g = new JLabel("所属区域:");
//        JButton h = new JButton("管理");

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
        g1.gridy = 3;
        g1.gridx = 0;
        g1.gridwidth = 1;
        mainPanel.add(g,g1);
        g1.gridwidth = 2;
        g1.gridx = 1;
        mainPanel.add(gg,g1);
//        g1.gridx = 3;
//        g1.gridwidth = 1;
//        mainPanel.add(h,g1);

        aa.setText(element.getName());
        aa.setEditable(false);
        if(element.getClientProperty("numberID")!=null)
            bb.setText(element.getClientProperty("numberID").toString());
        else
            bb.setText("");
        bb.setEditable(false);

        ee.addItem("10kv");
        ee.addItem("35kv");
        ee.addItem("110kv");
        ee.addItem("220kv");
        ee.addItem("500kv");
        ee.setSelectedItem("10kv");

        gg.addItem("武昌区");
        gg.addItem("洪山区");
        gg.addItem("蔡甸区");
        gg.addItem("东湖风景区");
        gg.addItem("江夏区");
        gg.addItem("汉阳区");
        gg.setSelectedItem("武昌区");


        /**
         * 载入数据
         */
        loadData(element,connection);

        vertify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //更新开关电压等级数据
                element.putClientProperty("rootVoltage",ee.getSelectedItem());
                //插入或更新数据
                writeData(element,connection);
                JOptionPane.showMessageDialog(null,"变电所资料修改成功!");
            }
        });
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SubstationFrame.this.setVisible(false);
            }
        });



    }
    /**
     * 写入数据
     * @param element
     * @param connection
     */
    public void loadData(Element element,Connection connection){
        if(element.getClientProperty("numberID")==null)
            return;
        try {
            Statement stmt = connection.createStatement();
            String query = "Select * FROM TABLE substation WHERE FactoryID='"+element.getClientProperty("ID")+"'";
            ResultSet rt = stmt.executeQuery(query);
            while(rt.next()){
                aa.setText(rt.getString(2));
                bb.setText(element.getClientProperty("numberID").toString());
                cc.setText(rt.getString(3));
                dd.setText(rt.getString(4));
                ee.setSelectedItem(rt.getObject(5));
                ff.setText(rt.getString(6));
                gg.setSelectedItem(rt.getObject(7));
            }
            rt.close();
            stmt.close();
        }catch (Exception e){
            System.out.println("变电所数据载入错误");
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
                String query = "INSERT INTO substation(FactoryID,PrimitiveName,Description,ESRTU,VoltageLevel,PowerTag,BelongArea)"+
                        "VALUES ('"+element.getClientProperty("ID")+"','"+aa.getText().trim()+"','"+cc.getText().trim()+"','"+dd.getText().trim()+"','"+ee.getSelectedItem().toString()+"','"+
                        ff.getText().trim()+"','"+gg.getSelectedItem().toString()+"')";
                stmt.execute(query);
                stmt.close();
            }catch (Exception e){
                System.out.println("变电所数据插入错误");
                e.printStackTrace();
            }
        else
            try{
                Statement stmt = connection.createStatement();
                String query = "UPDATE substation SET PrimitiveName='"+aa.getText().trim()+"',Description='"+cc.getText().trim()+"',ESRTU='"+dd.getText().trim()+"',VoltageLevel='"+ee.getSelectedItem().toString()+"',BelongArea='"+gg.getSelectedItem().toString()+"',PowerTag='"+
                        ff.getText().trim()+"' WHERE FactoryID='"+element.getClientProperty("ID")+"'";
                stmt.execute(query);
                stmt.close();
            }catch (Exception e){
                System.out.println("变电所数据写入错误");
                e.printStackTrace();
            }

    }
}
