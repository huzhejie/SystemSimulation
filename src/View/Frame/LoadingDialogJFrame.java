package View.Frame;

/**
 * Created by user on 2017/3/22.
 */
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * 对话框界面
 *
 * @author maomaojun
 *
 */
public class LoadingDialogJFrame extends JFrame {

    private static final long serialVersionUID = -890504412924432966L;

    private JLabel loadingImg; // 加载时的动态图片
    private JLabel loadingLabel; // 加载时的提示文字

    /**
     * 加载界面时的对话框
     *
     * @param loadingText
     *            加载时的提示文字
     */
    public LoadingDialogJFrame(String loadingText) {
        setUndecorated(true); // 去掉title框
        setBackground(Color.WHITE);
        setBounds(0, 0, 250, 40);
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        loadingImg = new JLabel();
//        loadingImg.setIcon(new ImageIcon(LoadingDialogJFrame.class.getResource("resource/loading_img.gif")));
        loadingImg.setIcon(null);
        loadingLabel = new JLabel(loadingText);

        add(loadingImg);
        add(loadingLabel);
        setLocationRelativeTo(null); // 位置不依赖任何控件，即永远居中显示
        setVisible(true);
    }

    public void setLoadingText(String loadingText) {
        if (!isVisible())
            setVisible(true);
        if (!loadingImg.isVisible())
            loadingImg.setVisible(true);
        loadingLabel.setText(loadingText);
    }

    /**
     * 设置操作成功的提示文字
     *
     * @param successText
     *            提示文字
     */
    public void setSuccessText(String successText) {
        if (!isVisible())
            setVisible(true);
        loadingImg.setVisible(false);
        loadingLabel.setText(successText);
        new Timer().schedule(new TimerTask() {

            @Override
            public void run() {
                dispose();
            }
        }, 2 * 1000); // 2s后自动关闭
    }

    /**
     * 程序异常，报错并退出
     *
     * @param errorText
     *            错误提示
     */
    public void shutdown(String errorText) {
        setVisible(false);
        JOptionPane.showMessageDialog(null, errorText, "错误", JOptionPane.ERROR_MESSAGE);
        System.exit(0);
    }
}