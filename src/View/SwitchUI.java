package View;

import Control.Switch;
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
 * Created by huzhejie on 2017/2/7.
 */
public class SwitchUI extends NodeUI {
    private Switch switzh = null;
    protected BorderUI editableBorder = null;
    public SwitchUI(TNetwork network, Node element){
        super(network,element);
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
        if(gesture==TWaverConst.MOUSE_LEFT_DOUBLE_CLICKED){
             if(switzh!=null) {
                 switzh.setTurnOn(!switzh.isTurnOn());
             }
        }
        if(gesture==TWaverConst.MOUSE_RIGHT_CLICKED){
            if(switzh!=null){
                switzh.setRotate(!switzh.isRotate());
            }
        }
    }
    public void paintBody(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setStroke(TWaverConst.DOUBLE_WIDTH_STROKE);
        switzh = (Switch) this.getElement();

        //get position
        final Point location = switzh.getLocation();
        final Dimension size = switzh.getSize();
        boolean trunOn = switzh.isTurnOn();
        boolean isRotate = switzh.isRotate();
        int x = location.x;
        int y = location.y;
        int width = size.width;
        int height = size.height;


        //draw color frame
        g.setColor(switzh.getBodyColor());

        if (!isRotate) {
            if (trunOn) {
                g.drawOval(x + width / 2 - width / 6, y, width / 6, width / 6);
                g.drawOval(x + width / 2, y + height - 10, width / 6, width / 6);
                g.drawLine(x + width, y, x + width / 2, y + height - 10);
            } else {
                g.drawOval(x + width / 2 - width / 6, y, width / 6, width / 6);
                g.drawOval(x + width / 2, y + height - 10, width / 6, width / 6);
                g.drawLine(x + width / 2, y, x + width / 2, y + height - 10);
            }

        }else{
            if (trunOn) {
                g.drawOval(x, y+height/2-width/6, width / 6, width / 6);
                g.drawOval(x+width-width/6, y+height/2-width/6, width / 6, width / 6);
                g.drawLine(x+width/6, y+height/2, x + width-width/6, y);
            } else {
                g.drawOval(x, y+height/2-width/6, width / 6, width / 6);
                g.drawOval(x+width-width/6, y+height/2-width/6, width / 6, width / 6);
                g.drawLine(x+width/6, y+height/2, x + width-width/6, y+height/2-width/6);
            }

        }
    }
}
