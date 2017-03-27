package View;

import Control.BreakerB;
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
 * Created by Administrator on 2017-02-16.
 */
public class BreakerBUI extends NodeUI {
    private BreakerB breakerB = null;
    protected BorderUI editableBorder = null;
    public BreakerBUI(TNetwork network, Node element) {
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
        if(gesture==TWaverConst.MOUSE_RIGHT_CLICKED){
            if(breakerB !=null){
                breakerB.setRotate(!breakerB.isRotate());
            }
        }
    }
    public void paintBody(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setStroke(TWaverConst.DOUBLE_WIDTH_STROKE);
        breakerB = (BreakerB) this.getElement();

        //get position
        final Point location = breakerB.getLocation();
        final Dimension size = breakerB.getBounds().getSize();
        boolean isRotate = breakerB.isRotate();
        final int x = location.x;
        final int y = location.y;
        final int width = size.width;
        final int height = size.height;

        g.setColor(breakerB.getRenderColor());

        if(!isRotate){
            breakerB.setFromPoint(width/2,0);
            breakerB.setToPoint(-width/2,0);
            g.drawRect(x+height/3, y + height / 4, width-height*2/3, height / 2);
            g.drawLine(x+height/3, y + height / 2, x +  height / 6, y + height  / 2);
            g.drawLine(x + height / 6, y + height / 2, x+height/3, y + height / 20+height/8);
            g.drawLine(x + height / 6, y + height / 2, x+height/3, y + 3 * height / 4 + height / 12);
            g.drawLine(x + width-height/3, y + height / 2, x + width - height / 6, y + height / 2);
            g.drawLine(x + width , y + height / 2, x + width- height / 6, y + height / 20+height/8);
            g.drawLine(x + width , y + height /2 , x + width- height / 6, y +3 * height / 4 + height / 12);
            g.drawLine(x, y + height  / 2, x + height / 6, y + height / 20+height/8);
            g.drawLine(x, y + height  / 2, x + height / 6, y + 3 * height / 4 + height / 12);
            g.drawLine(x + width - height / 6, y + height / 2, x + width - height /3, y + height / 20+height/8);
            g.drawLine(x + width -height / 6, y + height  / 2, x + width - height /3, y + 3 * height / 4 + height / 12);
        }
        else{
            breakerB.setFromPoint(0,height/2);
            breakerB.setToPoint(0,-height/2);
            g.drawRect( x + width / 4,y+width/3, width / 2,height-width*2/3 );
            g.drawLine( x + width / 2,y+width/3,x  + width  / 2, y +  width / 6 );
            g.drawLine( x + width / 2,y + width / 6,  x +width / 20+width/8,y+width/3);
            g.drawLine(x + width / 2,y + width / 6, x + 3 * width / 4 + width / 12, y+width/3 );
            g.drawLine( x + width / 2,y + height-width/3, x + width / 2, y +height - width / 6);
            g.drawLine( x + width / 2,y + height , x + width / 20+width/8,y + height- width / 6 );
            g.drawLine( x + width /2 ,y + height , x +3 * width / 4 +width / 12,y + height- width / 6);
            g.drawLine(x + width  / 2,y, x + width / 20+width/8, y + width / 6 );
            g.drawLine( x + width  / 2,y,x + 3 * width / 4 + width / 12, y + width / 6 );
            g.drawLine(x + width / 2,y + height - width / 6, x + width / 20+width/8, y + height - width /3 );
            g.drawLine( x + width  / 2,y + height -width / 6, x + 3 * width / 4 + width / 12,y + height - width /3);
        }
    }


}
