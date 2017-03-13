package View;

import Control.DoubleRollChange;
import twaver.Node;
import twaver.TWaverConst;
import twaver.network.TNetwork;
import twaver.network.ui.NodeUI;

import java.awt.*;

/**
 * Created by Administrator on 2017-02-16.
 */
public class DoubleRollChangeUI extends NodeUI {
    private DoubleRollChange switzh = null;

    public DoubleRollChangeUI(TNetwork network, Node element) {
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
        switzh = (DoubleRollChange) this.getElement();

        //get position
        final Point location = switzh.getLocation();
        final Dimension size = switzh.getBounds().getSize();
        boolean trunOn = switzh.isTurnOn();
        final int x = location.x;
        final int y = location.y;
        final int width = size.width;
        final int height = size.height;

        g.setColor(new Color(255, 0, 0));//双卷变
        g.drawOval(x, y, width / 2, width / 2);
        g.drawLine(x + width / 4, y + width / 4, x + width / 4, y + width / 8);
        g.drawLine(x + width / 4, y + width / 4, x + width / 8, y + width / 4 + width / 8);
        g.drawLine(x + width / 4, y + width / 4, x + width / 2 - width / 8, y + width / 4 + width / 8);
        //箭头
        g.drawLine(x - width / 8, y + width / 2 - width / 8, x + width / 2 + width / 8, y);
        g.drawLine(x + width / 2, y, x + width / 2 + width / 8, y);
        g.drawLine(x + width / 2 + width / 12, y + width / 10, x + width / 2 + width / 8, y);
        g.drawLine(x + width / 2, y, x + width / 2 + width / 12, y + width / 10);
        //蓝圆
        g.setColor(new Color(0, 2, 255));
        g.drawOval(x, y + width / 2 - width / 8, width / 2, width / 2);
        g.drawLine(x + width / 8, y + width / 2, x + width / 2 - width / 8, y + width / 2 - width / 8 + width / 4);
        g.drawLine(x + width / 8, y + width * 3 / 4, x + width / 2 - width / 8, y + width / 2 - width / 8 + width / 4);
        g.drawLine(x + width / 8, y + width / 2, x + width / 8, y + width * 3 / 4);


    }
}