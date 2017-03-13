package View;

import Control.Alternator;
import twaver.Node;
import twaver.TWaverConst;
import twaver.network.TNetwork;
import twaver.network.ui.NodeUI;

import java.awt.*;

/**
 * Created by Administrator on 2017-02-17.
 */
public class AlternatorUI extends NodeUI {
    private Alternator switzh = null;

    public AlternatorUI(TNetwork network, Node element) {
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
        switzh = (Alternator) this.getElement();


        //get position
        final Point location = switzh.getLocation();
        final Dimension size = switzh.getBounds().getSize();
        boolean trunOn = switzh.isTurnOn();
        final int x = location.x;
        final int y = location.y;
        final int width = size.width;
        final int height = size.height;
        //设置背景色
        g.setColor(Color.green);
        Font ValueFont = new Font("Times NewRoman", width / 2, height / 4);
        //画发电机
        g.setFont(ValueFont);
        g.drawString("G", x + width / 2 - width / 12, y + height / 3);
        g.drawOval(x, y, width, height);
        g.drawArc(x + width / 8, y + height * 5 / 12, width * 3 / 8, height * 3 / 8, 0, -200);
        g.drawArc(x + width / 2, y + height * 5 / 12, width * 3 / 8, height * 3 / 8, -20, 200);


    }
}
