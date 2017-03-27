package View;

import Control.BreakerA;
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
 * Created by Administrator on 2017-02-28.
 */
public class BreakerAUI extends NodeUI {
    private BreakerA breakerA = null;
    protected BorderUI editableBorder = null;
    public BreakerAUI(TNetwork network, Node element) {
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
            if(breakerA !=null){
                breakerA.setRotate(!breakerA.isRotate());
            }
        }
    }
    public void paintBody(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setStroke(TWaverConst.DOUBLE_WIDTH_STROKE);
        breakerA = (BreakerA) this.getElement();

        //get position
        final Point location = breakerA.getLocation();
        final Dimension size = breakerA.getBounds().getSize();
        boolean isRotate = breakerA.isRotate();
        final int x = location.x;
        final int y = location.y;
        final int width = size.width;
        final int height = size.height;

        g.setColor(breakerA.getRenderColor());

        //画断路器A
        if(!isRotate){
            breakerA.setFromPoint(width/2,0);
            breakerA.setToPoint(-width/2,0);
            g.drawRect(x,y+height/3,width,height/3);
        }else
        {
            breakerA.setFromPoint(0,height/2);
            breakerA.setToPoint(0,-height/2);
            g.drawRect(x+width/3,y,width/3,height);
        }
    }


}
