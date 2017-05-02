package Utils;

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
        g.setColor(Color.green.darker().darker());
        g.drawLine(width/2,0,width/2,height);


        //画图结束
        g.dispose();
        try {
            ImageIO.write(image, "png", new File("trunk.png"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
