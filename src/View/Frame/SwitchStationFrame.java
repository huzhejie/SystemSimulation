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

/**
 * Created by user on 2017/3/15.
 */
public class SwitchStationFrame extends JFrame {
    private JPanel mainPanel  =  new JPanel();
    private JPanel buttonPanel = new JPanel();

    private JTextField aa = new JTextField();
    private JTextField bb = new JTextField();
    private JTextField cc = new JTextField();
    private JComboBox  dd = new JComboBox();
    private JComboBox ee = new JComboBox();

    private JButton vertify = new JButton("确定");
    private JButton cancel = new JButton("取消");
    private SimpleDateFormat format =  new SimpleDateFormat("MM:dd hh:mm:ss");

    public SwitchStationFrame(final Element element, final Connection connection){
        super();
        this.setVisible(true);
        this.setTitle("开闭所");
        this.setResizable(false);
        this.setSize(600,300);
        this.setLocation((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2-300,(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2-150);
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
        JLabel d = new JLabel("设备类别:");
        JLabel e = new JLabel("所属区域:");
//        JButton f = new JButton("管理");

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
//        g1.gridwidth = 1;
//        g1.gridx = 3;
//        mainPanel.add(f,g1);

        aa.setText(element.getName());
        aa.setEditable(false);
        if(element.getClientProperty("numberID")!=null)
            bb.setText(element.getClientProperty("numberID").toString());
        else
            bb.setText("");
        bb.setEditable(false);

        dd.addItem("无");
        dd.addItem("双电源切换/带接地");
        dd.addItem("带接地/拔插式");
        dd.addItem("不带接地");
        dd.setSelectedItem("无");

        ee.addItem("武昌区");
        ee.addItem("洪山区");
        ee.addItem("蔡甸区");
        ee.addItem("东湖风景区");
        ee.addItem("江夏区");
        ee.addItem("汉阳区");
        ee.setSelectedItem("武昌区");

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
                JOptionPane.showMessageDialog(null,"开闭所资料修改成功!");
            }
        });
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwitchStationFrame.this.setVisible(false);
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
            String query = "Select * FROM switchstation WHERE DeviceID='"+element.getClientProperty("ID")+"'";
            ResultSet rt = stmt.executeQuery(query);
            while(rt.next()){
                aa.setText(rt.getString(2));
                bb.setText(element.getClientProperty("numberID").toString());
                cc.setText(rt.getString(3));
                dd.setSelectedItem(rt.getObject(4));
                ee.setSelectedItem(rt.getObject(5));
            }
            rt.close();
            stmt.close();
        }catch (Exception e){
            System.out.println("开闭所数据载入错误");
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
                String query = "INSERT INTO switchstation(DeviceID,PrimitiveName,Description,DeviceType,BelongArea)"+
                        "VALUES ('"+element.getClientProperty("ID")+"','"+aa.getText().trim()+"','"+cc.getText().trim()+"','"+dd.getSelectedItem().toString()+"','"+ee.getSelectedItem().toString()+"')";
                stmt.execute(query);
                stmt.close();
            }catch (Exception e){
                System.out.println("开闭所数据插入错误");
                e.printStackTrace();
            }
        else
            try{
                Statement stmt = connection.createStatement();
                String query = "UPDATE switchstation SET PrimitiveName="+aa.getText().trim()+",Description="+cc.getText().trim()+",DeviceType="+dd.getSelectedItem().toString()+",BelongArea="+ee.getSelectedItem().toString()+" WHERE DeviceID='"+element.getClientProperty("ID")+"'";
                stmt.execute(query);
                stmt.close();
            }catch (Exception e){
                System.out.println("开闭所数据写入错误");
                e.printStackTrace();
            }

    }

}
