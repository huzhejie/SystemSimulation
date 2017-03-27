package View;

import Control.Junction;
import twaver.Node;
import twaver.TWaverConst;
import twaver.network.TNetwork;
import twaver.network.ui.BorderUI;
import twaver.network.ui.DefaultBorderUI;
import twaver.network.ui.EditableBorderUI;
import twaver.network.ui.NodeUI;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Administrator on 2017-02-17.
 */
public class JunctionUI extends NodeUI {
    private Junction junction = null;
    protected BorderUI editableBorder = null;
    public JunctionUI(TNetwork network, Node element) {
        super(network, element);
    }
    public BorderUI getBorder() {
        if(this.network.isResizable(this.element)) {
            if(this.editableBorder == null) {
                this.editableBorder = new EditableBorderUI(this);
            }

            return this.editableBorder;
        } else {
            if(this.defaultBorder == null) {
                this.defaultBorder = new DefaultBorderUI(this);
            }

            return this.defaultBorder;
        }
    }
    public void performAction(int gesture, MouseEvent e){
        if(gesture== TWaverConst.MOUSE_RIGHT_CLICKED){
            if(junction !=null){
                junction.setRotate(!junction.isRotate());
            }
        }
    }
    public void paintBody(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setStroke(TWaverConst.DOUBLE_WIDTH_STROKE);
        junction = (Junction) this.getElement();

        //get position
        final Point location = junction.getLocation();
        final Dimension size = junction.getBounds().getSize();
        boolean isRotate = junction.isRotate();
        int x = location.x;
        int y = location.y;
        int width = size.width;
        int height = size.height;

        g.setColor(junction.getRenderColor());//设置画笔,设置Paint属性
        junction.setFromPoint(0,0);
        junction.setToPoint(0,0);
        g.drawOval(x + width / 4, y + height / 4, width / 2, height / 2);
        g.fillOval(x + width / 4, y + height / 4, width / 2, height / 2);


    }
}

