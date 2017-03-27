package View;

import Control.BoxChange;
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
 * Created by Administrator on 2017-03-02.
 */
public class BoxChangeUI extends NodeUI {
    private BoxChange boxChange = null;

    protected BorderUI editableBorder = null;
    public BoxChangeUI(TNetwork network, Node element) {
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
            if(boxChange !=null){
                boxChange.setRotate(!boxChange.isRotate());
            }
        }
    }
    public void paintBody(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setStroke(TWaverConst.DOUBLE_WIDTH_STROKE);
        boxChange = (BoxChange) this.getElement();

        //get position
        final Point location = boxChange.getLocation();
        final Dimension size = boxChange.getBounds().getSize();
        boolean isRotate = boxChange.isRotate();
        int x = location.x;
        int y = location.y;
        int width = size.width;
        int height = size.height;

        //画箱变
        if(!isRotate){
            boxChange.setFromPoint(width/2,0);
            boxChange.setToPoint(-width/2,0);
            g.setColor(boxChange.getRenderColor());
            g.drawOval(x,y+height*3/16,width/2+width/16,height/2+height/16);
            g.setColor(color.white);
            g.drawOval(x+width/2-width/16,y+height*3/16,width/2+width/16,height/2+height/16);
            g.drawRect(x,y+height*3/16,width,height/2+height/16);

        }else
        {
            boxChange.setFromPoint(0,height/2);
            boxChange.setToPoint(0,-height/2);
            int temp1=x;
            x=y;
            y=temp1;
            int temp2=width;
            width=height;
            height=temp2;
            Graphics2Dextend g1=new Graphics2Dextend(g);
            g1.setColor(boxChange.getRenderColor());
            g1.drawOval(x,y+height*3/16,width/2+width/16,height/2+height/16);
            g1.setColor(color.white);
            g1.drawOval(x+width/2-width/16,y+height*3/16,width/2+width/16,height/2+height/16);
            g1.drawRect(x,y+height*3/16,width,height/2+height/16);
        }
    }


}


