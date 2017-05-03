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
public class ControlCenterFrame extends JFrame {
    private JPanel mainPanel  =  new JPanel();
    private JPanel buttonPanel = new JPanel();

    private JTextField aa = new JTextField();
    private JTextField bb = new JTextField();
    private JTextField cc = new JTextField();
    private JComboBox  dd = new JComboBox();
    private JComboBox ee = new JComboBox();
    private JComboBox ff = new JComboBox();
    private JComboBox gg = new JComboBox();
    private JComboBox hh = new JComboBox();
    private JTextField ii = new JTextField();
    private JComboBox jj = new JComboBox();

    private JTextField kk = new JTextField();
    private JTextField ll = new JTextField();
    private JButton vertify = new JButton("确定");
    private JButton cancel = new JButton("取消");

    private SimpleDateFormat format =  new SimpleDateFormat("MM:dd hh:mm:ss");

    public ControlCenterFrame(final Element element, final Connection connection){
        super();
        this.setVisible(true);
        this.setTitle("配变参数设置");
        this.setResizable(false);
        this.setSize(600,600);
        this.setLocation((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2-300,(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2-300);
        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.BOTH;
        gc.gridy = 0;
        gc.ipady = 50;
        this.add(mainPanel,gc);
        gc.gridy = 1;
        gc.ipady = 20;
        this.add(buttonPanel,gc);

        //按钮面板
        JLabel k = new JLabel("联系人:");
        JLabel l = new JLabel("联系方式:");
        vertify.setPreferredSize(new Dimension(120,30));
        cancel.setPreferredSize(new Dimension(120,30));
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints g2 = new GridBagConstraints();
        g2.insets = new Insets(10,0,10,5);
        g2.fill =GridBagConstraints.BOTH;
        g2.anchor = GridBagConstraints.CENTER;
        g2.gridheight = 1;
        g2.gridy = 0;
        g2.gridx = 0;
        g2.gridwidth = 1;
        buttonPanel.add(k,g2);
        g2.ipadx=100;
        g2.gridx = 1;
        g2.gridwidth = 2;
        buttonPanel.add(kk,g2);
        g2.ipadx = 0;
        g2.gridx = 3;
        g2.gridwidth = 1;
        buttonPanel.add(l,g2);
        g2.gridx = 100;
        g2.gridx = 4;
        g2.gridwidth = 2;
        buttonPanel.add(ll,g2);
        g2.gridy = 1;
        g2.gridx = 1;
        g2.gridwidth = 2;
        buttonPanel.add(vertify,g2);
        g2.gridx = 4;
        buttonPanel.add(cancel,g2);

        //主面板
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints g1 = new GridBagConstraints();
        JLabel a = new JLabel("配变名称:");
        JLabel b = new JLabel("配变ID:");
        JLabel c = new JLabel("配变描述:");
        JLabel d = new JLabel("配变类型:");
        JLabel e = new JLabel("所属区域:");
        JLabel f = new JLabel("电压等级:");
        JLabel g = new JLabel("主供电源:");
        JLabel h = new JLabel("安装位置:");
        JLabel i= new JLabel("容量 kVA:");
        JLabel j = new JLabel("配变台数:");

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
        g1.gridx = 3;
        g1.gridwidth = 1;
        mainPanel.add(h,g1);
        g1.gridx = 4;
        g1.gridwidth = 2;
        mainPanel.add(hh,g1);
        g1.gridy = 4;
        g1.gridx = 0;
        g1.gridwidth = 1;
        mainPanel.add(i,g1);
        g1.gridwidth = 2;
        g1.gridx = 1;
        mainPanel.add(ii,g1);
        g1.gridx = 3;
        g1.gridwidth = 1;
        mainPanel.add(j,g1);
        g1.gridx = 4;
        g1.gridwidth = 2;
        mainPanel.add(jj,g1);

        aa.setText(element.getName());
        aa.setEditable(false);
        bb.setEditable(false);
        if(element.getClientProperty("numberID")!=null)
            bb.setText(element.getClientProperty("numberID").toString());
        else
            bb.setText("");

        ee.addItem("武昌区");
        ee.addItem("洪山区");
        ee.addItem("蔡甸区");
        ee.addItem("东湖风景区");
        ee.addItem("江夏区");
        ee.addItem("汉阳区");
        ee.setSelectedItem("武昌区");

        ff.addItem("10kv");
        ff.addItem("35kv");
        ff.addItem("110kv");
        ff.addItem("220kv");
        ff.addItem("500kv");
        ff.setSelectedItem("10kv");

        gg.addItem("单电源");
        gg.addItem("多电源");
        gg.setSelectedItem("单电源");

        hh.addItem("室外");
        hh.addItem("室内");
        hh.setSelectedItem("室外");

        dd.addItem("公用");
        dd.addItem("私人");
        dd.setSelectedItem("公用");


        ii.setText("0.00");
        for(int x = 1;x<11;x++){
            jj.addItem(x);
        }

        /**
         * 给文本输入框添加过滤器
         */
        ii.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int keyChar = e.getKeyChar();
                if((keyChar>=KeyEvent.VK_0&&keyChar<=KeyEvent.VK_9)||keyChar==KeyEvent.VK_DECIMAL){
                }
                else
                    e.consume();
            }
        });
        jj.setSelectedItem(1);

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
                JOptionPane.showMessageDialog(null,"配变资料修改成功!");
                ControlCenterFrame.this.setVisible(false);
            }
        });
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControlCenterFrame.this.setVisible(false);
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
            String query = "Select * FROM mainuser WHERE MatchID='"+element.getClientProperty("ID")+"'";
            ResultSet rt = stmt.executeQuery(query);
            while(rt.next()){
                aa.setText(rt.getString(2));
                bb.setText(element.getClientProperty("numberID").toString());
                cc.setText(rt.getString(3));
                dd.setSelectedItem(rt.getObject(4));
                ee.setSelectedItem(rt.getObject(5));
                ff.setSelectedItem(rt.getObject(6));
                gg.setSelectedItem(rt.getObject(7));
                hh.setSelectedItem(rt.getObject(8));
                ii.setText(String.valueOf(rt.getInt(9)));
                jj.setSelectedItem(rt.getObject(10));
                kk.setText(rt.getString(11));
                ll.setText(rt.getString(12));
            }
            rt.close();
            stmt.close();
        }catch (Exception e){
            System.out.println("配变数据载入错误");
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
                String query = "INSERT INTO mainuser(MatchID,PrimitiveName,Description,MatchType,BelongArea,VoltageLevel,MPSupply,installStation,ContainerKVA,MatchNum,UserName,UserTeleNo)"+
                        "VALUES ('"+element.getClientProperty("ID").toString()+"','"+aa.getText().trim()+"','"+cc.getText().trim()+"','"+dd.getSelectedItem().toString()+"','"+ee.getSelectedItem().toString()+"','"+
                        ff.getSelectedItem().toString()+"','"+gg.getSelectedItem().toString()+"','"+hh.getSelectedItem().toString()+"',"+Double.parseDouble(ii.getText().trim())+","+Integer.parseInt(jj.getSelectedItem().toString())+",'"+kk.getText()+"','"+ll.getText()+"')";
                stmt.execute(query);
                stmt.close();
            }catch (Exception e){
                System.out.println("配变数据插入错误");
                e.printStackTrace();
            }
        else
            try{
                Statement stmt = connection.createStatement();
                String query = "UPDATE mainuser SET PrimitiveName='"+element.getName().trim()+"',Description='"+cc.getText().trim()+"',MatchType='"+dd.getSelectedItem().toString()+"',BelongArea='"+ee.getSelectedItem().toString()+"',VoltageLevel='"+
                        ff.getSelectedItem().toString()+"',MPSupply='"+gg.getSelectedItem().toString()+"',installStation='"+hh.getSelectedItem().toString()+"',ContainerKVA="+Double.parseDouble(ii.getText().trim())+",MatchNum="+Integer.parseInt(jj.getSelectedItem().toString())+
                        ",UserName='"+kk.getText().trim()+"',UserTeleNo='"+ll.getText().trim()+"' WHERE MatchID='"+element.getClientProperty("ID")+"'";
                stmt.execute(query);
                stmt.close();
            }catch (Exception e){
                System.out.println("配变数据写入错误");
                e.printStackTrace();
            }

    }
}
