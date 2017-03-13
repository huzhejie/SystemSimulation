package View;

import Control.Junction;
import twaver.Node;
import twaver.TWaverConst;
import twaver.network.TNetwork;
import twaver.network.ui.NodeUI;

import java.awt.*;

/**
 * Created by Administrator on 2017-02-17.
 */
public class JunctionUI extends NodeUI {
    private Junction switzh = null;

    public JunctionUI(TNetwork network, Node element) {
        super(network, element);
    }

    //    public void performAction(int gesture, MouseEvent e){
//        if(gesture==TWaverConst.MOUSE_RIGHT_CLICKED){
//             if(switzh!=null) {
//                 switzh.setTurnOn(!switzh.isTurnOn());
//             }
//        }
//    }
    public void paintBody(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setStroke(TWaverConst.DOUBLE_WIDTH_STROKE);
        switzh = (Junction) this.getElement();

        //get position
        final Point location = switzh.getLocation();
        final Dimension size = switzh.getBounds().getSize();
        boolean trunOn = switzh.isTurnOn();
        final int x = location.x;
        final int y = location.y;
        final int width = size.width;
        final int height = size.height;
        //设置背景色
        g.setColor(Color.white);//设置画笔,设置Paint属性
        g.drawOval(x + width / 4, y + height / 4, width / 2, height / 2);
        g.fillOval(x + width / 4, y + height / 4, width / 2, height / 2);


    }
}

