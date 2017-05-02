package View;

import Control.Switch;
import twaver.Link;
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

    /**
     * 将元件的边框变成有节点的样式，可以用来改变元件长宽
     * @return
     */
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

    public void paintBody(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setStroke(TWaverConst.DOUBLE_WIDTH_STROKE);
        switzh = (Switch) this.getElement();

        //get position
        final Point location = switzh.getLocation();
        final Dimension size = switzh.getSize();
        boolean turnOn = switzh.isTurnOn();
        boolean isRotate = switzh.isRotate();
        int x = location.x;
        int y = location.y;
        int width = size.width;
        int height = size.height;


        //draw color frame
        g.setColor(switzh.getRenderColor());

        if (!isRotate) {
            switzh.setToPoint(0, -height/2);
            switzh.setFromPoint(0, height/2-8);
            if (!turnOn) {
                g.drawOval(x + width / 2 - width / 6, y, width / 6, width / 6);
                g.drawOval(x + width / 2, y + height - width / 6, width / 6, width / 6);
                g.drawLine(x + width, y, x + width*7 / 12, y + height - width / 6);
            } else {
                g.drawOval(x + width / 2 - width / 6, y, width / 6, width / 6);
                g.drawOval(x + width / 2, y + height - width / 6, width / 6, width / 6);
                g.drawLine(x + width / 2, y+width/12, x + width*7 / 12, y + height - width / 6);
            }

        }else{
            switzh.setToPoint(-width/2, 0);
            switzh.setFromPoint(width/2, 0);
            if (!turnOn) {
                g.drawOval(x, y+height/2-width/6, width / 6, width / 6);
                g.drawOval(x+width-width/6, y+height/2-width/6, width / 6, width / 6);
                g.drawLine(x+width/12, y+height/2, x + width-width/12, y+height/6);
            } else {
                g.drawOval(x, y+height/2-width/6, width / 6, width / 6);
                g.drawOval(x+width-width/6, y+height/2-width/6, width / 6, width / 6);
                g.drawLine(x+width/12, y+height/2, x + width-width/12, y+height/2-width/6);
            }

        }
    }
}
