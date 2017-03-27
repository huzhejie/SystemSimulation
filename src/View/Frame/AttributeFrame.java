package View.Frame;

import twaver.Element;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by huzhejie on 2017/3/13.
 */
public class AttributeFrame extends JFrame{
    private JPanel mainPanel = new JPanel();
    private JPanel attributePanel = new JPanel();
    private JPanel fontPanel = new JPanel();
    private JPanel buttonPanel = new JPanel();
    private JTextField aa = new JTextField(20);
    private JTextField bb = new JTextField(20);
    private JTextField cc = new JTextField(100);
    private JTextField dd = new JTextField(80);
    private JComboBox f = new JComboBox();
    private JComboBox j = new JComboBox();
    private JCheckBox c1 = new JCheckBox("粗体");
    private JCheckBox c2 = new JCheckBox("斜体");
    private JCheckBox c3 = new JCheckBox("下划线");
    //字体颜色
    private Color fontColor;
    //确定按钮
    private JButton vertify = new JButton("确定");
    //图形属性map
    private Map<String,Object> map = new HashMap<>();

    public AttributeFrame(final Element element){
        super();
        this.map = (HashMap)element.getClientProperty("attribute");
        this.setTitle("图形属性");
        this.setResizable(false);
        this.setLocation((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2-300,(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2-300);
        this.setSize(600,600);
        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.BOTH;
        gc.gridy = 0;
        gc.ipady = 100;
        this.add(mainPanel,gc);
        gc.gridy = 1;
        gc.ipady = 0;
        this.add(buttonPanel,gc);

        vertify.setPreferredSize(new Dimension(120,30));
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(vertify);

        mainPanel.setSize(500,500);
        mainPanel.add(attributePanel);
        mainPanel.add(fontPanel);
        mainPanel.setLayout(new GridLayout(2,0));

        attributePanel.setBorder(BorderFactory.createTitledBorder("基本属性"));
        fontPanel.setBorder(BorderFactory.createTitledBorder("字体属性"));

        //基本属性板块设置布局
        attributePanel.setLayout(new GridBagLayout());
        GridBagConstraints g1 = new GridBagConstraints();

        //设置字体板块设置布局
        fontPanel.setLayout(new GridBagLayout());
        GridBagConstraints g2 = new GridBagConstraints();


        //基本属性板块,按照(a对应aa)
        JLabel a = new JLabel("元件代码:");
        JLabel b = new JLabel("设备代号:");
        JLabel c = new JLabel("设备名称:");
        JLabel d = new JLabel("连接页面:");
        JButton button = new JButton("选择界面");


        //基本属性板块布局设置
        g1.insets = new Insets(10,0,10,5);
        g1.fill =GridBagConstraints.BOTH;
        g1.ipady = 10;
        g1.anchor = GridBagConstraints.WEST;
        g1.anchor = GridBagConstraints.CENTER;
        g1.gridheight = 1;
        g1.gridwidth = 1;
        g1.gridx = 0;
        g1.gridy = 0;
        attributePanel.add(a,g1);
        g1.gridwidth = 2;
        g1.gridx = 1;
        g1.ipadx = 100;
        attributePanel.add(aa,g1);
        g1.gridwidth = 1;
        g1.gridx = 3;
        g1.ipadx = 0;
        attributePanel.add(b,g1);
        g1.gridwidth = 2;
        g1.gridx = 4;
        g1.ipadx = 100;
        attributePanel.add(bb,g1);
        g1.gridwidth = 1;
        g1.gridy = 1;
        g1.gridx = 0;
        g1.ipadx = 0;
        attributePanel.add(c,g1);
        g1.gridwidth = 5;
        g1.gridx = 1;
        g1.ipadx = 400;
        attributePanel.add(cc,g1);
        g1.gridwidth = 1;
        g1.gridy = 2;
        g1.gridx = 0;
        g1.ipadx = 0;
        attributePanel.add(d,g1);
        g1.gridwidth = 4;
        g1.gridx = 1;
        g1.ipadx = 300;
        attributePanel.add(dd,g1);
        g1.gridwidth = 1;
        g1.gridx = 5;
        g1.ipadx = 0;
        attributePanel.add(button,g1);

        //初始化各个值
        if(map.get("元件代码")!=null)
            aa.setText((String)map.get("元件代码"));
        if(map.get("设备ID号")!=null)
            bb.setText((String)map.get("设备ID"));
        if(map.get("设备名称")!=null)
            cc.setText((String)map.get("设备名称"));
        if(map.get("链接界面")!=null)
            dd.setText((String)map.get("链接界面"));
        if(map.get("字体颜色")!=null)
            fontColor = (Color)map.get("字体颜色");
        else
            fontColor = Color.GREEN;


        //字体设置板块（e对应ee）
        JLabel e = new JLabel("字体:");
        GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontName = environment.getAvailableFontFamilyNames();
        for(int x = 0;x<fontName.length;x++){
            f.addItem(fontName[x]);
        }
        f.setSelectedItem("微软雅黑");
        JLabel g = new JLabel("颜色:");
        final JLabel h = new JLabel("点击选择颜色");
        h.setHorizontalAlignment(SwingConstants.CENTER);
        h.setBackground(fontColor);
        h.setOpaque(true);
        h.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!e.isPopupTrigger()) {
                    JColorChooser chooser = new JColorChooser();
                    Color color = chooser.showDialog(AttributeFrame.this, "调色板", Color.green);
                    if (color == null) {
                        fontColor = Color.green;
                    } else {
                        fontColor = color;
                        h.setBackground(fontColor);
                    }
                }
            }
        });
        JLabel i = new JLabel("大小:");
        for(int y = 6;y<50;y=y+2){
            j.addItem(y);
        }
        j.setSelectedIndex(2);

        if(map.get("字体")!=null)
            f.setSelectedItem(map.get("字体"));
        if(map.get("字体大小")!=null)
            j.setSelectedItem(Integer.parseInt((String)map.get("字体大小")));
        if(map.get("粗体")!=null)
            c1.setSelected((Boolean)map.get("粗体"));
        if(map.get("斜体")!=null)
            c2.setSelected((Boolean)map.get("斜体"));
        if(map.get("下划线")!=null)
            c3.setSelected((Boolean)map.get("下划线"));

        //字体设置板块布局设置
        g2.insets = new Insets(10,0,10,5);
        g2.anchor = GridBagConstraints.WEST;
        g2.fill = GridBagConstraints.BOTH;
        g2.gridheight = 1;
        g2.gridwidth = 1;
        g2.gridx = 0;
        g2.gridy = 0;
        g2.ipady = 10;
        fontPanel.add(e,g2);
        g2.gridx = 1;
        g2.gridwidth = 7;
        fontPanel.add(f,g2);
        g2.gridy = 1;
        g2.gridx = 0;
        g2.gridwidth = 1;
        fontPanel.add(g,g2);
        g2.gridx = 1;
        g2.ipadx = 90;
        fontPanel.add(h,g2);
        g2.ipadx = 0;
        g2.gridx = 2;
        fontPanel.add(i,g2);
        g2.gridx = 3;
        g2.ipadx = 50;
        fontPanel.add(j,g2);
        g2.gridx = 4;
        g2.ipadx = 0;
        fontPanel.add(c1,g2);
        g2.gridx = 5;
        fontPanel.add(c2,g2);
        g2.gridx  =6;
        fontPanel.add(c3,g2);


        //设置确定按钮的监听器
        vertify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AttributeFrame.this.setVisible(false);
                //将属性全部存入map中
                map.put("元件代码",aa.getText());
                map.put("设备ID号",bb.getText());
                map.put("设备名称",cc.getText());
                map.put("链接页面",dd.getText());

                map.put("字体",f.getSelectedItem().toString());
                map.put("字体颜色",fontColor);
                map.put("字体大小",j.getSelectedItem().toString());

                map.put("粗体",c1.isSelected());
                map.put("斜体",c2.isSelected());
                map.put("下划线",c3.isSelected());

                element.setName((String) map.get("设备名称"));
                Map<TextAttribute, Object> fontmap = new HashMap<>();
                fontmap.put(TextAttribute.FAMILY, map.get("字体"));
                fontmap.put(TextAttribute.FOREGROUND, map.get("字体颜色"));
                fontmap.put(TextAttribute.SIZE, Integer.parseInt((String) map.get("字体大小")));
                if ((Boolean) map.get("粗体"))
                    fontmap.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
                if ((Boolean) map.get("斜体"))
                    fontmap.put(TextAttribute.POSTURE, TextAttribute.POSTURE_OBLIQUE);
                if ((Boolean) map.get("下划线"))
                    fontmap.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                element.putLabelFont(Font.getFont(fontmap));
            }
        });
    }

}
