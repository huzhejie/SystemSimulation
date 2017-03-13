package View;

import Control.VariableField;
import twaver.Node;
import twaver.TWaverConst;
import twaver.network.TNetwork;
import twaver.network.ui.NodeUI;

import java.awt.*;

/**
 * Created by Administrator on 2017-02-17.
 */
public class VariableFieldUI extends NodeUI {
    private VariableField switzh = null;

    public VariableFieldUI(TNetwork network, Node element) {
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
        switzh = (VariableField) this.getElement();

        //get position
        final Point location = switzh.getLocation();
        final Dimension size = switzh.getBounds().getSize();
        boolean trunOn = switzh.isTurnOn();
        final int x = location.x;
        final int y = location.y;
        final int width = size.width;
        final int height = size.height;

        Font ValueFont = new Font("黑体", 12, height / 2);

        g.setColor(Color.white);
        g.setFont(ValueFont);
        g.drawString("变电场", x - width / 8, y + height / 2);


    }
}
