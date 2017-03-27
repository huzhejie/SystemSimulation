package View.Frame;

import View.DrawPanel;
import twaver.network.TNetwork;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 2017/3/16.
 */
public class SetSizeFrame extends JFrame {
    private JPanel mainPanel  =  new JPanel();
    private JPanel buttonPanel = new JPanel();

    private JTextField aa = new JTextField();
    private JTextField bb = new JTextField();

    private JButton vertify = new JButton("确定");
    private JButton cancel = new JButton("取消");


    public SetSizeFrame(final DrawPanel network){
        super();
        this.setTitle("工作区背景设置");
        this.setResizable(false);
        this.setSize(400,200);
        this.setLocation((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2-300,(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2-100);
        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.BOTH;
        gc.gridy = 0;
        gc.ipady = 50;
        this.add(mainPanel,gc);
        gc.gridy = 1;
        gc.ipady = 50;
        this.add(buttonPanel,gc);

        //按钮面板
        vertify.setPreferredSize(new Dimension(80,30));
        cancel.setPreferredSize(new Dimension(80,30));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,20));
        buttonPanel.add(vertify);
        buttonPanel.add(cancel);

        //主面板
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints g1 = new GridBagConstraints();
        JLabel a = new JLabel("长度:");
        JLabel b = new JLabel("宽度:");

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
        /**
         * 给文本输入框添加过滤器
         */
        aa.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int keyChar = e.getKeyChar();
                if(keyChar>=KeyEvent.VK_0&&keyChar<=KeyEvent.VK_9){

                }
                else
                    e.consume();
            }
        });
        bb.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int keyChar = e.getKeyChar();
                if(keyChar>=KeyEvent.VK_0&&keyChar<=KeyEvent.VK_9){

                }
                else
                    e.consume();
            }
        });

        /**
         * 确定按钮监听器
         */
        vertify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                network.setSize(Integer.parseInt(aa.getText()),Integer.parseInt(bb.getText()));
                network.repaint();
                SetSizeFrame.this.setVisible(false);
            }
        });
        /**
         * 取消按钮监听器
         */
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SetSizeFrame.this.setVisible(false);
            }
        });
    }
//    public static void main(String[] args){
//        SetSizeFrame frame = new SetSizeFrame();
//        frame.setVisible(true);
//    }
}
