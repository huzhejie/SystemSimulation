package View;

import Control.GroundSwitch;
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
 * Created by Administrator on 2017-03-01.
 */
public class GroundSwitchUI extends NodeUI {
    private GroundSwitch groundSwitch = null;
    protected BorderUI editableBorder = null;
    public GroundSwitchUI(TNetwork network, Node element) {
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
            if(groundSwitch !=null){
                groundSwitch.setRotate(!groundSwitch.isRotate());
            }
        }
    }
    public void paintBody(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setStroke(TWaverConst.DOUBLE_WIDTH_STROKE);
        groundSwitch = (GroundSwitch) this.getElement();

        //get position
        final Point location = groundSwitch.getLocation();
        final Dimension size = groundSwitch.getBounds().getSize();
        boolean isRotate = groundSwitch.isRotate();
        boolean isTurnOn = groundSwitch.isTurnOn();
        int x = location.x;
        int y = location.y;
        int width = size.width;
        int height = size.height;

        if(!isRotate){
            groundSwitch.setFromPoint(0,-height/2);
            groundSwitch.setToPoint(0,-height/2);
            g.setColor(groundSwitch.getRenderColor());
            if(!isTurnOn) {
                g.drawLine(x + width * 3 / 8, y + height / 4, x + width * 5 / 8, y + height / 4);
                g.drawLine(x + width / 2, y, x + width / 2, y + height / 4);
                g.drawLine(x + width / 4, y + height / 4, x + width / 2, y + height / 2);
                g.setColor(Color.white);
                g.drawLine(x + width / 2, y + height / 2, x + width / 2, y + height * 5 / 8);
                g.drawLine(x + width / 4, y + height * 5 / 8, x + width * 3 / 4, y + height * 5 / 8);
                g.drawLine(x + width * 3 / 8, y + height * 3 / 4, x + width * 5 / 8, y + height * 3 / 4);
                g.drawLine(x + width * 7 / 16, y + height * 7 / 8, x + width * 9 / 16, y + height * 7 / 8);
            }
            else{
                g.drawLine(x + width * 3 / 8, y + height / 4, x + width * 5 / 8, y + height / 4);
                g.drawLine(x + width / 2, y, x + width / 2, y + height / 4);
                g.drawLine(x + width / 2, y + height / 4, x + width / 2, y + height / 2);
                g.setColor(Color.white);
                g.drawLine(x + width / 2, y + height / 2, x + width / 2, y + height * 5 / 8);
                g.drawLine(x + width / 4, y + height * 5 / 8, x + width * 3 / 4, y + height * 5 / 8);
                g.drawLine(x + width * 3 / 8, y + height * 3 / 4, x + width * 5 / 8, y + height * 3 / 4);
                g.drawLine(x + width * 7 / 16, y + height * 7 / 8, x + width * 9 / 16, y + height * 7 / 8);
            }

        }else
        {
            groundSwitch.setFromPoint(-width/2,0);
            groundSwitch.setToPoint(-width/2,0);
//            int temp1=x;
//            x=y;
//            y=temp1;
//            int temp2=width;
//            width=height;
//            height=temp2;
            g.setColor(groundSwitch.getRenderColor());
            if(!isTurnOn) {
                g.drawLine(y + height / 4, x + width * 3 / 8, y + height / 4, x + width * 5 / 8);
                g.drawLine(y, x + width / 2, y + height / 4, x + width / 2);
                g.drawLine(y + height / 4, x + width / 4, y + height / 2, x + width / 2);
                g.setColor(Color.white);
                g.drawLine(y + height / 2, x + width / 2, y + height * 5 / 8, x + width / 2);
                g.drawLine(y + height * 5 / 8, x + width / 4, y + height * 5 / 8, x + width * 3 / 4);
                g.drawLine(y + height * 3 / 4, x + width * 3 / 8, y + height * 3 / 4, x + width * 5 / 8);
                g.drawLine(y + height * 7 / 8, x + width * 7 / 16, y + height * 7 / 8, x + width * 9 / 16);
            }else{
                g.drawLine(y + height / 4, x + width * 3 / 8, y + height / 4, x + width * 5 / 8);
                g.drawLine(y, x + width / 2, y + height / 4, x + width / 2);
                g.drawLine(y + height / 2, x + width / 4, y + height / 2, x + width / 2);
                g.setColor(Color.white);
                g.drawLine(y + height / 2, x + width / 2, y + height * 5 / 8, x + width / 2);
                g.drawLine(y + height * 5 / 8, x + width / 4, y + height * 5 / 8, x + width * 3 / 4);
                g.drawLine(y + height * 3 / 4, x + width * 3 / 8, y + height * 3 / 4, x + width * 5 / 8);
                g.drawLine(y + height * 7 / 8, x + width * 7 / 16, y + height * 7 / 8, x + width * 9 / 16);
            }


        }
    }


}

