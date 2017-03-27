package View;

import Control.Grounded;
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
public class GroundedUI extends NodeUI {
    private Grounded grounded = null;
    protected BorderUI editableBorder = null;
    public GroundedUI(TNetwork network, Node element) {
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
            if(grounded !=null){
                grounded.setRotate(!grounded.isRotate());
            }
        }
    }
    public void paintBody(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setStroke(TWaverConst.DOUBLE_WIDTH_STROKE);
        grounded = (Grounded) this.getElement();

        //get position
        final Point location = grounded.getLocation();
        final Dimension size = grounded.getBounds().getSize();
        boolean isRotate = grounded.isRotate();
        final int x = location.x;
        final int y = location.y;
        final int width = size.width;
        final int height = size.height;

        g.setColor(Color.white);


        if(!isRotate){
            grounded.setFromPoint(0,-height/2);
            grounded.setToPoint(0,-height/2);
            g.drawLine(x+width/2,y,x+width/2,y+height/2);
            g.drawLine(x+width/4,y+height/2,x+width*3/4,y+height/2);
            g.drawLine(x+width*3/8,y+height*3/4,x+width*5/8,y+height*3/4);
            g.drawLine(x+width*7/16,y+height-height/16,x+width*9/16,y+height-height/16);

        }else
        {
            grounded.setFromPoint(-width/2,0);
            grounded.setToPoint(-width/2,0);
            g.drawLine(x,y+height/2,x+width/2,y+height/2);
            g.drawLine(x+width/2,y+height/4,x+width/2,y+height*3/4);
            g.drawLine(x+width*3/4,y+height*3/8,x+width*3/4,y+height*5/8);
            g.drawLine(x+width-width/16,y+height*7/16,x+width-width/16,y+height*9/16);
        }
    }


}

