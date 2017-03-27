package View.Frame;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

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
    //参数map
    private Map<String,Object> map = new HashMap<>();

    public SwitchStationFrame(){
        super();
        //        this.map = (HashMap)element.getClientProperty("switchPara");
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
        JButton f = new JButton("管理");

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
        g1.gridwidth = 1;
        g1.gridx = 3;
        mainPanel.add(f,g1);

        dd.addItem("无");
        dd.addItem("双电源切换/带接地");
        dd.addItem("带接地/拔插式");
        dd.addItem("不带接地");

    }
    public static void main(String[] args){
        SwitchStationFrame frame = new SwitchStationFrame();
        frame.setVisible(true);
    }
}
