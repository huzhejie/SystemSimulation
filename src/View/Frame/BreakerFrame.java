package View.Frame;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by user on 2017/3/15.
 */
public class BreakerFrame extends JFrame{
    private Color tempColor = Color.black;
    private JTextField textField1;
    private JLabel a;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    private JComboBox comboBox5;
    private JTextField textField2;
    private JComboBox comboBox6;
    private JTextField textField3;
    private JTextField textField4;
    private JLabel voltageColor;
    private JButton 确定Button;
    private JButton 取消Button;
    private JPanel mainPanel;
    private JPanel panel;
    private JLabel b;
    private JLabel c;
    private JLabel d;
    private JLabel e;
    private JLabel f;
    private JLabel g;
    private JLabel h;
    private JLabel i;
    private JLabel j;
    private JLabel k;
    private JLabel l;
    private JLabel m;
    private JLabel n;
    private JTextField textField5;

    public BreakerFrame(){
        this.setTitle("断路器参数设置");
        this.setResizable(false);
        this.setLocation((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2-300,(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2-300);

        tempColor = this.voltageColor.getBackground();
        voltageColor.setOpaque(true);

        this.comboBox3.addItem("10kv");
        this.comboBox3.addItem("35kv");
        this.comboBox3.addItem("110kv");
        this.comboBox3.addItem("220kv");
        this.comboBox3.addItem("500kv");

        this.comboBox5.addItem("线路开关");
        this.comboBox5.addItem("主变低压侧开关");
        this.comboBox5.addItem("母联开关");
        this.comboBox5.addItem("开闭所");
        this.comboBox5.addItem("分支箱<环网柜>");
        this.comboBox5.addItem("柱上");

        this.comboBox6.addItem("公用线");
        this.comboBox6.addItem("专用线");

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
            }
        });
        textField2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int keyChar = e.getKeyChar();
                if(keyChar>=KeyEvent.VK_0&&keyChar<=KeyEvent.VK_9){

                }
                else{
                    e.consume();
                }
            }
        });


    }
    public static void main(String[] args) {
        BreakerFrame frame = new BreakerFrame();
        frame.setContentPane(new BreakerFrame().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
