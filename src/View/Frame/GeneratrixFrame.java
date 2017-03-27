package View.Frame;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 2017/3/15.
 */
public class GeneratrixFrame extends JFrame {
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

    private JButton vertify = new JButton("确定");
    private JButton cancel = new JButton("取消");
    //参数map
    private Map<String,Object> map = new HashMap<>();

    public GeneratrixFrame(){
        super();
        //        this.map = (HashMap)element.getClientProperty("switchPara");
        this.setTitle("母线段参数设置");
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
        JLabel b = new JLabel("设备ID:");
        JLabel c = new JLabel("母线描述:");
        JLabel d = new JLabel("母线类型:");
        JLabel e = new JLabel("所属区域:");
        JLabel f = new JLabel("所属厂站");
        JLabel g = new JLabel("电压等级");
        JLabel h = new JLabel("所属容器");

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

        dd.addItem("主母");
        dd.addItem("旁母");

        gg.addItem("10kv");
        gg.addItem("35kv");
        gg.addItem("110kv");
        gg.addItem("220kv");
        gg.addItem("500kv");

    }
    public static void main(String[] args){
        GeneratrixFrame frame = new GeneratrixFrame();
        frame.setVisible(true);
    }
}
