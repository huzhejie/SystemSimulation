package View.Frame;


import twaver.Element;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by user on 2017/3/15.
 */
public class BreakerFrame extends JFrame{
    private Color tempColor = Color.black;
    private JTextField aa;
    private JLabel a;
    private JComboBox cc;
    private JTextField dd;
    private JComboBox ee;
    private JComboBox ff;
    private JTextField gg;
    private JComboBox hh;
    private JTextField ii;
    private JLabel voltageColor;
    private JButton vertify;
    private JButton cancel;
    private JPanel mainPanel;
    private JPanel panel;
    private JLabel b;
    private JLabel c;
    private JLabel d;
    private JLabel e;
    private JLabel g;
    private JLabel h;
    private JLabel i;
    private JLabel j;
    private JLabel k;
    private JLabel m;
    private JLabel n;
    private JTextField bb;
    private SimpleDateFormat format =  new SimpleDateFormat("MM:dd hh:mm:ss");


    public BreakerFrame(final Element element, final Connection connection){
        this.setTitle("断路器参数设置");
        this.setResizable(false);
        this.setLocation((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2-300,(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2-300);

        tempColor = this.voltageColor.getBackground();
        voltageColor.setOpaque(true);

        this.ee.addItem("10kv");
        this.ee.addItem("35kv");
        this.ee.addItem("110kv");
        this.ee.addItem("220kv");
        this.ee.addItem("500kv");
        this.ee.setSelectedItem("10kv");

        cc.addItem("武昌区");
        cc.addItem("洪山区");
        cc.addItem("蔡甸区");
        cc.addItem("东湖风景区");
        cc.addItem("江夏区");
        cc.addItem("汉阳区");
        cc.setSelectedItem("武昌区");

        this.ff.addItem("线路开关");
        this.ff.addItem("主变低压侧开关");
        this.ff.addItem("母联开关");
        this.ff.addItem("开闭所");
        this.ff.addItem("分支箱<环网柜>");
        this.ff.addItem("柱上");
        this.ff.setSelectedItem("线路开关");

        this.hh.addItem("公用线");
        this.hh.addItem("专用线");
        this.hh.setSelectedItem("公用线");

        aa.setText(element.getName());
        aa.setEditable(false);

        voltageColor.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JColorChooser chooser = new JColorChooser();
                Color color = chooser.showDialog(BreakerFrame.this,"调色板", tempColor);
                if (color == null) {
                    tempColor = Color.green;
                } else {
                    tempColor = color;
                }
                voltageColor.setBackground(tempColor);
                element.putRenderColor(tempColor);
            }
        });
        gg.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int keyChar = e.getKeyChar();
                if((keyChar>=KeyEvent.VK_0&&keyChar<=KeyEvent.VK_9)||keyChar==KeyEvent.VK_DECIMAL){

                }
                else{
                    e.consume();
                }
            }
        });

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
                JOptionPane.showMessageDialog(null,"断路器资料修改成功!");
            }
        });
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BreakerFrame.this.setVisible(false);
            }
        });


    }

    public JPanel getMainPanel() {
        return mainPanel;
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
            String query = "Select * FROM breaker WHERE ID='"+element.getClientProperty("ID")+"'";
            ResultSet rt = stmt.executeQuery(query);
            while(rt.next()){
                aa.setText(rt.getString(2));
                bb.setText(rt.getString(3));
                cc.setSelectedItem(rt.getObject(4));
                dd.setText(rt.getString(5));
                ee.setSelectedItem(rt.getObject(6));
                ff.setSelectedItem(rt.getObject(7));
                gg.setText(rt.getString(8));
                hh.setSelectedItem(rt.getObject(9));
                ii.setText(rt.getString(10));
                voltageColor.setBackground(Color.decode(rt.getString(11)));
            }
            rt.close();
            stmt.close();
        }catch (Exception e){
            System.out.println("断路器数据载入错误");
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
                String query = "INSERT INTO breaker(ID,PrimitiveName,Description,BelongArea,BelongStation,VoltageLevel,FuncLocation,LimitCurrent,LineType,CTRatio,PsColor)"+
                        "VALUES ('"+element.getClientProperty("ID")+"','"+aa.getText().trim()+"','"+bb.getText().trim()+"','"+cc.getSelectedItem().toString()+"','"+dd.getText().trim()+"','"+ee.getSelectedItem().toString()+"','"+ff.getSelectedItem().toString()
                        +"',"+Double.parseDouble(gg.getText().trim())+",'"+hh.getSelectedItem().toString()+"','"+ii.getText().trim()+"','"+voltageColor.getBackground().toString()+"')";
                stmt.execute(query);
                stmt.close();
            }catch (Exception e){
                System.out.println("断路器数据插入错误");
                e.printStackTrace();
            }
        else
            try{
                Statement stmt = connection.createStatement();
                String query = "UPDATE breaker SET PrimitiveName='"+aa.getText().trim()+"',Description='"+bb.getText().trim()+"',BelongArea='"+cc.getSelectedItem().toString()+"',BelongStation='"+dd.getText().trim()+"',VoltageLevel='"+ee.getSelectedItem().toString()
                        +"',FuncLocation='"+ff.getSelectedItem().toString()+"',LimitCurrent="+Double.parseDouble(gg.getText().trim())+",LineType='"+hh.getSelectedItem().toString()+"',CTRatio='"+ii.getText().trim()+"',PsColor='"+voltageColor.getBackground().toString()+"' WHERE ID='"+element.getClientProperty("ID")+"'";
                stmt.execute(query);
                stmt.close();
            }catch (Exception e){
                System.out.println("断路器数据写入错误");
                e.printStackTrace();
            }

    }
}
