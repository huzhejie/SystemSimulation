package View.Frame;


import twaver.Element;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
public class RollChangeFrame extends JFrame {
    private JPanel basicPanel = new JPanel();//基本参数
//    private JPanel voltagePanel = new JPanel();//电压参数
//    private JPanel switchPanel = new JPanel();//主变侧开关
    private JPanel nameplatePanel = new JPanel();//铭牌参数
    private JPanel buttonPanel = new JPanel();//按钮

    private JTextField aa = new JTextField();
    private JTextField bb= new JTextField();
    private JTextField cc = new JTextField();
    private JComboBox dd = new JComboBox();

//    private JComboBox vaa = new JComboBox();
//    private JComboBox vbb = new JComboBox();
//    private JComboBox vcc = new JComboBox();
//
//    private JComboBox saa = new JComboBox();
//    private JComboBox sbb = new JComboBox();
//    private JComboBox scc = new JComboBox();

    private JTextField naa = new JTextField();
    private JComboBox nbb = new JComboBox();
    private JTextField ncc = new JTextField();
    private JTextField ndd = new JTextField();
    private JTextField nee = new JTextField();

    private JButton vertify = new JButton("确定");
    private JButton cancel = new JButton("取消");

    private SimpleDateFormat format =  new SimpleDateFormat("MM:dd hh:mm:ss");

    public RollChangeFrame(final Element element, final Connection connection){
        super();
        this.setTitle("卷变参数设置");
        this.setVisible(true);
        this.setResizable(false);
        this.setSize(600,800);
        this.setLocation((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2-300,(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2-400);
        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.BOTH;
        gc.gridy = 0;
        gc.ipady = 100;
        this.add(basicPanel,gc);
//        gc.gridy = 1;
//        gc.ipady = 50;
//        this.add(voltagePanel,gc);
//        gc.gridy = 2;
//        gc.ipady = 50;
//        this.add(switchPanel,gc);
        gc.gridy = 1;
        gc.ipady = 100;
        this.add(nameplatePanel,gc);
        gc.gridy = 2;
        gc.ipady = 50;
        this.add(buttonPanel,gc);

        //按钮面板
        vertify.setPreferredSize(new Dimension(120,30));
        cancel.setPreferredSize(new Dimension(120,30));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,50));
        buttonPanel.add(vertify);
        buttonPanel.add(cancel);

        basicPanel.setBorder(BorderFactory.createTitledBorder("基本参数"));
//        voltagePanel.setBorder(BorderFactory.createTitledBorder("电压等级"));
//        switchPanel.setBorder(BorderFactory.createTitledBorder("主变侧开关"));
        nameplatePanel.setBorder(BorderFactory.createTitledBorder("铭牌参数"));

        //基本参数面板
        basicPanel.setLayout(new GridBagLayout());
        GridBagConstraints g1 = new GridBagConstraints();
        JLabel a = new JLabel("变压器代码:");
        JLabel b = new JLabel("所属变电站:");
        JLabel c = new JLabel("变压器名称:");
        JLabel d = new JLabel("绕组类型:");

        g1.insets = new Insets(10,0,10,5);
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
        basicPanel.add(bb,g1);
        g1.gridy = 1;
        g1.gridx = 0;
        g1.ipadx = 0;
        basicPanel.add(c,g1);
        g1.gridx = 1;
        g1.gridwidth = 2;
        g1.ipadx = 100;
        basicPanel.add(cc,g1);
        g1.gridx = 3;
        g1.gridwidth = 1;
        g1.ipadx = 0;
        basicPanel.add(d,g1);
        g1.gridx = 4;
        g1.gridwidth = 1;
        g1.ipadx = 100;
        basicPanel.add(dd,g1);

        //电压等级面板
//        voltagePanel.setLayout(new GridBagLayout());
//        GridBagConstraints g2 = new GridBagConstraints();
//        JLabel va = new JLabel("高压侧:");
//        JLabel vb = new JLabel("中压侧:");
//        JLabel vc = new JLabel("低压侧:");
//
//        g2.insets = new Insets(10,0,10,5);
//        g2.fill =GridBagConstraints.BOTH;
//        g2.anchor = GridBagConstraints.CENTER;
//        g2.gridheight = 1;
//        g2.gridy = 0;
//        g2.gridx = 0;
//        g2.gridwidth = 1;
//        voltagePanel.add(va,g2);
//        g2.gridx = 1;
//        g2.ipadx = 100;
//        voltagePanel.add(vaa,g2);
//        g2.gridx = 2;
//        g2.ipadx = 0;
//        voltagePanel.add(vb,g2);
//        g2.gridx = 3;
//        g2.ipadx = 100;
//        voltagePanel.add(vbb,g2);
//        g2.gridx = 4;
//        g2.ipadx = 0;
//        voltagePanel.add(vc,g2);
//        g2.gridx = 5;
//        g2.ipadx = 100;
//        voltagePanel.add(vcc,g2);

        //主变侧开关面板
//        switchPanel.setLayout(new GridBagLayout());
//        GridBagConstraints g3 = new GridBagConstraints();
//        JLabel sa = new JLabel("高压侧:");
//        JLabel sb = new JLabel("中压侧:");
//        JLabel sc = new JLabel("低压侧:");
//        g3.insets = new Insets(10,0,10,5);
//        g3.fill =GridBagConstraints.BOTH;
//        g3.anchor = GridBagConstraints.CENTER;
//        g3.gridheight = 1;
//        g3.gridy = 0;
//        g3.gridx = 0;
//        g3.gridwidth = 1;
//        switchPanel.add(sa,g3);
//        g3.ipadx = 100;
//        g3.gridx = 1;
//        switchPanel.add(saa,g3);
//        g3.gridx = 2;
//        g3.ipadx = 0;
//        switchPanel.add(sb,g3);
//        g3.gridx = 3;
//        g3.ipadx = 100;
//        switchPanel.add(sbb,g3);
//        g3.gridx = 4;
//        g3.ipadx = 0;
//        switchPanel.add(sc,g3);
//        g3.gridx = 5;
//        g3.ipadx = 100;
//        switchPanel.add(scc,g3);

        //铭牌参数面板
        nameplatePanel.setLayout(new GridBagLayout());
        GridBagConstraints g4 = new GridBagConstraints();
        JLabel na = new JLabel("变压器型号:");
        JLabel nb = new JLabel("调压方式:");
        JLabel nc = new JLabel("额定容量(MWA):");
        JLabel nd = new JLabel("空载电流(A):");
        JLabel ne = new JLabel("压变比例:");
        g4.insets = new Insets(10,0,10,5);
        g4.fill =GridBagConstraints.BOTH;
        g4.anchor = GridBagConstraints.EAST;
        g4.gridheight = 1;
        g4.gridy = 0;
        g4.gridx = 0;
        g4.gridwidth = 1;
        nameplatePanel.add(na,g4);
        g4.gridx = 1;
        g4.gridwidth = 3;
        nameplatePanel.add(naa,g4);
        g4.gridx = 4;
        g4.gridwidth = 1;
        nameplatePanel.add(nb,g4);
        g4.gridx = 5;
        g4.gridwidth = 3;
        nameplatePanel.add(nbb,g4);
        g4.gridy = 1;
        g4.gridx = 0;
        g4.gridwidth = 1;
        nameplatePanel.add(nc,g4);
        g4.gridx = 1;
        g4.gridwidth = 2;
        g4.ipadx = 80;
        nameplatePanel.add(ncc,g4);
        g4.gridx = 3;
        g4.ipadx = 0;
        g4.gridwidth = 1;
        nameplatePanel.add(nd,g4);
        g4.gridx = 4;
        g4.gridwidth = 2;
        nameplatePanel.add(ndd,g4);
        g4.gridx = 6;
        g4.gridwidth = 1;
        nameplatePanel.add(ne,g4);
        g4.gridx = 7;
        g4.gridwidth = 2;
        g4.ipadx = 80;
        nameplatePanel.add(nee,g4);

        aa.setText(element.getName());
        aa.setEditable(false);

        dd.addItem("双绕组");
        dd.addItem("三绕组");
        nbb.addItem("升压");
        nbb.addItem("降压");

        dd.setSelectedItem("双绕组");
        nbb.setSelectedItem("升压");
        /**
         * 给文本输入框添加过滤器
         */
        nee.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int keyChar = e.getKeyChar();
                if(keyChar>=KeyEvent.VK_0&&keyChar<=KeyEvent.VK_9){

                }
                else
                    e.consume();
            }
        });
        ncc.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int keyChar = e.getKeyChar();
                if((keyChar>=KeyEvent.VK_0&&keyChar<=KeyEvent.VK_9)||keyChar==KeyEvent.VK_DECIMAL){

                }
                else
                    e.consume();
            }
        });
        ndd.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int keyChar = e.getKeyChar();
                if((keyChar>=KeyEvent.VK_0&&keyChar<=KeyEvent.VK_9)||keyChar==KeyEvent.VK_DECIMAL){

                }
                else
                    e.consume();
            }
        });

        /**
         * 载入数据
         */
        loadData(element, connection);
        vertify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //更新比例数据
                if(nbb.getSelectedItem().equals("升压"))
                    element.putClientProperty("ratio",Double.parseDouble(nee.getText().trim()));
                else
                    element.putClientProperty("ratio",1.0d/Double.parseDouble(nee.getText().trim()));
                //插入或更新数据
                writeData(element,connection);
                JOptionPane.showMessageDialog(null,"卷变资料修改成功!");
                RollChangeFrame.this.setVisible(false);
            }
        });
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RollChangeFrame.this.setVisible(false);
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
            String query = "Select * FROM rollchange WHERE ID='"+(int)element.getClientProperty("ID")+"'";
            ResultSet rt = stmt.executeQuery(query);
            while(rt.next()){
                aa.setText(rt.getString(2));
                bb.setText(rt.getString(3));
                cc.setText(rt.getString(4));
                dd.setSelectedItem(rt.getObject(5));
                naa.setText(rt.getString(6));
                nbb.setSelectedItem(rt.getObject(7));
                ncc.setText(rt.getString(8));
                ndd.setText(rt.getString(9));
                nee.setText(String.valueOf(rt.getInt(10)));
            }
            rt.close();
            stmt.close();
        }catch (Exception e){
            System.out.println("卷变数据载入错误");
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
                String query = "INSERT INTO rollchange(ID,TansformerNum,BelongSub,SubName,WindType,SubType,RegulationMode,MMWA,NoloadCurrent,Ratio)"+
                        "VALUES ('"+element.getClientProperty("ID")+"','"+aa.getText().trim()+"','"+bb.getText().toString()+"','"+cc.getText().trim()+"','"+dd.getSelectedItem().toString()+"','"+naa.getText().trim()+"','"+
                        nbb.getSelectedItem().toString()+"',"+Double.parseDouble(ncc.getText().trim())+","+Double.parseDouble(ndd.getText().trim())+","+Integer.parseInt(nee.getText().trim())+")";
                stmt.execute(query);
                stmt.close();
            }catch (Exception e){
                System.out.println("卷变数据插入错误");
                e.printStackTrace();
            }
        else
            try{
                Statement stmt = connection.createStatement();
                String query = "UPDATE rollchange SET TansformerNum='"+aa.getText().trim()+"'BelongSub='"+bb.getText().toString()+"',SubName='"+cc.getText().trim()+"',WindType='"+dd.getSelectedItem().toString()+"',SubType='"+naa.getText().trim()+"',RegulationMode='"+
                        nbb.getSelectedItem().toString()+"',MMWA="+Double.parseDouble(ncc.getText().trim())+",NoloadCurrent="+Double.parseDouble(ndd.getText().trim())+",Ratio="+Integer.parseInt(nee.getText().trim())+" WHERE ID='"+element.getClientProperty("ID")+"'";
                stmt.execute(query);
                stmt.close();
            }catch (Exception e){
                System.out.println("卷变数据写入错误");
                e.printStackTrace();
            }

    }
}
