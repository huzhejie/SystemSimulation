package Utils;

import twaver.TWaverConst;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by huzhejie on 2017/2/7.
 */
public class PaintPNG {
    public static void main(String[] args){
        //设置图像长宽
        int width = 30;
        int height = 30;
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        image = g.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
        g.dispose();
        g = image.createGraphics();
        //画图
        Font ValueFont = new Font("黑体", Font.BOLD, 8);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setStroke(TWaverConst.DOUBLE_WIDTH_STROKE);

        g.setColor(Color.BLACK);
        g.setFont(ValueFont);
        g.drawString("用电用户",  0,  height / 2);


        //画图结束
        g.dispose();
        try {
            ImageIO.write(image, "png", new File("mainUser.png"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
