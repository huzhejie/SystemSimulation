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
public class FeederFrame extends JFrame {
    private JPanel basicPanel = new JPanel();//基本参数
    private JPanel voltagePanel = new JPanel();//首段开关
    private JPanel switchPanel = new JPanel();//末段开关
    private JPanel buttonPanel = new JPanel();//按钮

    private JTextField aa = new JTextField();
    private JTextField bb= new JTextField();
    private JTextField cc = new JTextField();
    private JComboBox dd = new JComboBox();
    private JTextField ee = new JTextField();
    private JComboBox ff = new JComboBox();
    private JTextField gg = new JTextField();

    private JTextField vaa = new JTextField();
    private JTextField vbb = new JTextField();

    private JTextField saa = new JTextField();
    private JTextField sbb = new JTextField();


    private JButton vertify = new JButton("确定");
    private JButton cancel = new JButton("取消");

    private SimpleDateFormat format =  new SimpleDateFormat("MM:dd hh:mm:ss");

    public FeederFrame(final Element element, final Connection connection){
        super();
        this.setVisible(true);
        this.setTitle("线路参数设置");
        this.setResizable(false);
        this.setSize(600,700);
        this.setLocation((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2-300,(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2-400);
        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.BOTH;
        gc.gridy = 0;
        gc.ipady = 150;
        this.add(basicPanel,gc);
        gc.gridy = 1;
        gc.ipady = 50;
        this.add(voltagePanel,gc);
        gc.gridy = 2;
        gc.ipady = 50;
        this.add(switchPanel,gc);
        gc.gridy = 3;
        gc.ipady = 50;
        this.add(buttonPanel,gc);

        //按钮面板
        vertify.setPreferredSize(new Dimension(120,30));
        cancel.setPreferredSize(new Dimension(120,30));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,50));
        buttonPanel.add(vertify);
        buttonPanel.add(cancel);

        basicPanel.setBorder(BorderFactory.createTitledBorder("基本参数"));
        voltagePanel.setBorder(BorderFactory.createTitledBorder("电压等级"));
        switchPanel.setBorder(BorderFactory.createTitledBorder("主变侧开关"));

        //基本参数面板
        basicPanel.setLayout(new GridBagLayout());
        GridBagConstraints g1 = new GridBagConstraints();
        JLabel a = new JLabel("线路代码:");
        JLabel b = new JLabel("线路名称:");
        JLabel c = new JLabel("线路型号:");
        JLabel d = new JLabel("电压等级:");
        JLabel e = new JLabel("控制电流:");
        JLabel f = new JLabel("线路类型:");
        JLabel g = new JLabel("线路长度:");
        JLabel h = new JLabel("A");
        h.setHorizontalAlignment(JTextField.LEFT);
        JLabel i = new JLabel("Km");
        i.setHorizontalAlignment(JTextField.LEFT);

        g1.insets = new Insets(10,10,10,5);
        g1.fill =GridBagConstraints.BOTH;
        g1.anchor = GridBagConstraints.CENTER;
        g1.gridheight = 1;
        g1.gridy = 0;
        g1.gridx = 0;
        g1.gridwidth = 1;
        basicPanel.add(a,g1);
        g1.gridx = 1;
        g1.gridwidth = 2;
        g1.ipadx = 100;
        basicPanel.add(aa,g1);
        g1.gridx = 3;
        g1.gridwidth = 1;
        g1.ipadx = 0;
        basicPanel.add(b,g1);
        g1.gridx = 4;
        g1.ipadx = 100;
        g1.gridwidth = 2;
        basicPanel.add(bb,g1);
        g1.gridy = 1;
        g1.gridx = 0;
        g1.ipadx = 0;
        g1.gridwidth = 1;
        basicPanel.add(c,g1);
        g1.gridx = 1;
        g1.gridwidth = 5;
        g1.ipadx = 100;
        basicPanel.add(cc,g1);
        g1.gridy= 2;
        g1.gridwidth = 1;
        g1.gridx = 0;
        g1.ipadx = 0;
        basicPanel.add(d,g1);
        g1.gridx = 1;
        g1.gridwidth = 2;
        g1.ipadx = 100;
        basicPanel.add(dd,g1);
        g1.gridx = 3;
        g1.gridwidth = 1;
        g1.ipadx = 0;
        basicPanel.add(e,g1);
        g1.gridx = 4;
        g1.gridwidth = 1;
        g1.ipadx = 80;
        basicPanel.add(ee,g1);
        g1.gridx = 5;
        g1.gridwidth = 1;
        g1.ipadx = 0;
        basicPanel.add(h,g1);
        g1.gridy = 3;
        g1.gridwidth = 1;
        g1.gridx = 0;
        g1.ipadx = 0;
        basicPanel.add(f,g1);
        g1.gridx = 1;
        g1.gridwidth = 2;
        g1.ipadx = 100;
        basicPanel.add(ff,g1);
        g1.gridx = 3;
        g1.gridwidth = 1;
        g1.ipadx = 0;
        basicPanel.add(g,g1);
        g1.gridx = 4;
        g1.gridwidth = 1;
        g1.ipadx = 80;
        basicPanel.add(gg,g1);
        g1.gridx = 5;
        g1.gridwidth = 1;
        g1.ipadx = 0;
        basicPanel.add(i,g1);


        //首段开关面板
        voltagePanel.setLayout(new GridBagLayout());
        GridBagConstraints g2 = new GridBagConstraints();
        JLabel va = new JLabel("变电站名:");
        JLabel vb = new JLabel("开关名称:");

        g2.insets = new Insets(10,0,10,5);
        g2.fill =GridBagConstraints.BOTH;
        g2.anchor = GridBagConstraints.CENTER;
        g2.gridheight = 1;
        g2.gridy = 0;
        g2.gridx = 0;
        g2.gridwidth = 1;
        voltagePanel.add(va,g2);
        g2.gridx = 1;
        g2.ipadx = 100;
        voltagePanel.add(vaa,g2);
        g2.gridx = 2;
        g2.ipadx = 0;
        voltagePanel.add(vb,g2);
        g2.gridx = 3;
        g2.ipadx = 100;
        voltagePanel.add(vbb,g2);

        //末段开关面板
        switchPanel.setLayout(new GridBagLayout());
        GridBagConstraints g3 = new GridBagConstraints();
        JLabel sa = new JLabel("变电站名:");
        JLabel sb = new JLabel("开关名称:");
        g3.insets = new Insets(10,0,10,5);
        g3.fill =GridBagConstraints.BOTH;
        g3.anchor = GridBagConstraints.CENTER;
        g3.gridheight = 1;
        g3.gridy = 0;
        g3.gridx = 0;
        g3.gridwidth = 1;
        switchPanel.add(sa,g3);
        g3.ipadx = 100;
        g3.gridx = 1;
        switchPanel.add(saa,g3);
        g3.gridx = 2;
        g3.ipadx = 0;
        switchPanel.add(sb,g3);
        g3.gridx = 3;
        g3.ipadx = 100;
        switchPanel.add(sbb,g3);


        ff.addItem("电缆线路");
        ff.addItem("架空线路");
        ff.addItem("混合线路");
        ff.setSelectedItem("电缆线路");

        if(element.getClientProperty("numberID")!=null)
            aa.setText(element.getClientProperty("numberID").toString());
        else
            aa.setText("");
        bb.setText(element.getName());
        aa.setEditable(false);
        bb.setEditable(false);

        /**
         * 载入数据
         */
        loadData(element,connection);

        vertify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //更新开关电压等级数据
                element.putClientProperty("rootVoltage",ff.getSelectedItem());
                //插入或更新数据
                writeData(element,connection);
                JOptionPane.showMessageDialog(null,"馈线资料修改成功!");
            }
        });
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FeederFrame.this.setVisible(false);
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
            String query = "Select * FROM line WHERE LineNum='"+element.getClientProperty("ID")+"'";
            ResultSet rt = stmt.executeQuery(query);
            while(rt.next()){
                aa.setText("馈线"+element.getClientProperty("numberID").toString());
                bb.setText(rt.getString(2));
                cc.setText(rt.getString(3));
                dd.setSelectedItem(rt.getObject(4));
                ee.setText(String.valueOf(rt.getInt(5)));
                ff.setSelectedItem(rt.getObject(6));
                gg.setText(String.valueOf(rt.getDouble(7)));
                vaa.setText(rt.getString(8));
                vbb.setText(rt.getString(9));
                saa.setText(rt.getString(10));
                sbb.setText(rt.getString(11));
            }
            rt.close();
            stmt.close();
        }catch (Exception e){
            System.out.println("馈线数据载入错误");
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
                String query = "INSERT INTO line(LineNum,LineName,LineModel,VoltageLevel,ControlCurrent,LineType,LineLength,FSubStationName,FSwitchName,TSubStationName,TSwitchName)"+
                        "VALUES ('"+element.getClientProperty("ID")+"','"+bb.getText().trim()+"','"+cc.getText().trim()+"','"+dd.getSelectedItem().toString()+"',"+Double.parseDouble(ee.getText().trim())+",'"+
                        ff.getSelectedItem().toString()+"',"+Double.parseDouble(gg.getText().trim())+",'"+vaa.getText().toString()+"','"+vbb.getText().toString()+"','"+saa.getText().toString()+","+sbb.getText().toString()+"')";
                stmt.execute(query);
                stmt.close();
            }catch (Exception e){
                System.out.println("馈线数据插入错误");
                e.printStackTrace();
            }
        else
            try{
                Statement stmt = connection.createStatement();
                String query = "UPDATE line SET LineName='"+bb.getText().trim()+"',LineModel='"+cc.getText().trim()+"',VoltageLevel='"+dd.getSelectedItem().toString()+"',ControlCurrent="+Double.parseDouble(ee.getText().trim())+",LineType='"+
                        ff.getSelectedItem().toString()+"',LineLength="+Double.parseDouble(gg.getText().trim())+",FSubStationName='"+vaa.getText().toString()+"',FSwitchName='"+vbb.getText().toString()+"',TSubStationName='"+saa.getText().toString()+
                        "',TSwitchName='"+sbb.getText().toString()+"' WHERE LineNum='"+element.getClientProperty("ID")+"'";
                stmt.execute(query);
                stmt.close();
            }catch (Exception e){
                System.out.println("馈线数据写入错误");
                e.printStackTrace();
            }

    }

}
