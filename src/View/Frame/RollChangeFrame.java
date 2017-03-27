package View.Frame;


import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 2017/3/15.
 */
public class RollChangeFrame extends JFrame {
    private JPanel basicPanel = new JPanel();//基本参数
    private JPanel voltagePanel = new JPanel();//电压参数
    private JPanel switchPanel = new JPanel();//主变侧开关
    private JPanel nameplatePanel = new JPanel();//铭牌参数
    private JPanel buttonPanel = new JPanel();//按钮

    private JTextField aa = new JTextField();
    private JComboBox bb= new JComboBox();
    private JTextField cc = new JTextField();
    private JComboBox dd = new JComboBox();

    private JComboBox vaa = new JComboBox();
    private JComboBox vbb = new JComboBox();
    private JComboBox vcc = new JComboBox();

    private JComboBox saa = new JComboBox();
    private JComboBox sbb = new JComboBox();
    private JComboBox scc = new JComboBox();

    private JComboBox naa = new JComboBox();
    private JComboBox nbb = new JComboBox();
    private JTextField ncc = new JTextField();
    private JTextField ndd = new JTextField();
    private JTextField nee = new JTextField();

    private JButton vertify = new JButton("确定");
    private JButton cancel = new JButton("取消");
    //参数map
    private Map<String,Object> map = new HashMap<>();

    public RollChangeFrame(){
        super();
//        this.map = (HashMap)element.getClientProperty("rollChangePara");
        this.setTitle("主变参数设置");
        this.setResizable(false);
        this.setSize(600,800);
        this.setLocation((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2-300,(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2-400);
        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.BOTH;
        gc.gridy = 0;
        gc.ipady = 100;
        this.add(basicPanel,gc);
        gc.gridy = 1;
        gc.ipady = 50;
        this.add(voltagePanel,gc);
        gc.gridy = 2;
        gc.ipady = 50;
        this.add(switchPanel,gc);
        gc.gridy = 3;
        gc.ipady = 100;
        this.add(nameplatePanel,gc);
        gc.gridy = 4;
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
        voltagePanel.setLayout(new GridBagLayout());
        GridBagConstraints g2 = new GridBagConstraints();
        JLabel va = new JLabel("高压侧:");
        JLabel vb = new JLabel("中压侧:");
        JLabel vc = new JLabel("低压侧:");

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
        g2.gridx = 4;
        g2.ipadx = 0;
        voltagePanel.add(vc,g2);
        g2.gridx = 5;
        g2.ipadx = 100;
        voltagePanel.add(vcc,g2);

        //主变侧开关面板
        switchPanel.setLayout(new GridBagLayout());
        GridBagConstraints g3 = new GridBagConstraints();
        JLabel sa = new JLabel("高压侧:");
        JLabel sb = new JLabel("中压侧:");
        JLabel sc = new JLabel("低压侧:");
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
        g3.gridx = 4;
        g3.ipadx = 0;
        switchPanel.add(sc,g3);
        g3.gridx = 5;
        g3.ipadx = 100;
        switchPanel.add(scc,g3);

        //铭牌参数面板
        nameplatePanel.setLayout(new GridBagLayout());
        GridBagConstraints g4 = new GridBagConstraints();
        JLabel na = new JLabel("变压器型号:");
        JLabel nb = new JLabel("调压方式:");
        JLabel nc = new JLabel("额定容量(MWA):");
        JLabel nd = new JLabel("空载电流(%):");
        JLabel ne = new JLabel("空载损耗(KW):");
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

        dd.addItem("双绕组");
        dd.addItem("三绕组");
        nbb.addItem("无载");
        nbb.addItem("有载");

    }
    public static void main(String[] args){
        RollChangeFrame frame = new RollChangeFrame();
        frame.setVisible(true);
    }
}
