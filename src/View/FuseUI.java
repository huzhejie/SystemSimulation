package View;

import Control.Fuse;
import twaver.Node;
import twaver.TWaverConst;
import twaver.network.TNetwork;
import twaver.network.ui.NodeUI;

import java.awt.*;

/**
 * Created by Administrator on 2017-02-17.
 */
public class FuseUI extends NodeUI {
    private Fuse switzh = null;

    public FuseUI(TNetwork network, Node element) {
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
        switzh = (Fuse) this.getElement();

        //get position
        final Point location = switzh.getLocation();
        final Dimension size = switzh.getBounds().getSize();
        boolean trunOn = switzh.isTurnOn();
        final int x = location.x;
        final int y = location.y;
        final int width = size.width;
        final int height = size.height;

        //画保险丝
        g.setColor(Color.green);
        g.drawArc(x, y, width / 2, height * 3 / 4, -10, -200);
        g.drawArc(x + width / 2 - width / 25, y + height / 4, width / 2, height * 3 / 4, -20, 170);

    }
}
