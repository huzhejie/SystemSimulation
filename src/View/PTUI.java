package View;

import Control.PT;
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
public class PTUI extends NodeUI {
    private PT pt = null;
    protected BorderUI editableBorder = null;
    public PTUI(TNetwork network, Node element) {
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
            if(pt !=null){
                pt.setRotate(!pt.isRotate());
            }
        }
    }
    public void paintBody(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setStroke(TWaverConst.DOUBLE_WIDTH_STROKE);
        pt = (PT) this.getElement();

        //get position
        final Point location = pt.getLocation();
        final Dimension size = pt.getBounds().getSize();
        boolean isRotate = pt.isRotate();
        int x = location.x;
        int y = location.y;
        int width = size.width;
        int height = size.height;

        g.setColor(pt.getRenderColor());

        //画PTA
        if(!isRotate){
            pt.setToPoint(0,-height/2);
            pt.setFromPoint(0,-height/2);
            g.drawOval(x+width/5,y,width*3/5,height*3/5);
            g.drawLine(x+width/2,y+height/5,x+width/2,y+height*3/10);
            g.drawLine(x+width/2,y+height*3/10,x+width*4/10,y+height*4/10);
            g.drawLine(x+width/2,y+height*3/10,x+width*6/10,y+height*4/10);
            //
            g.drawOval(x,y+height*2/5,width*3/5,height*3/5);
            g.drawLine(x+width*3/10,y+height*7/10,x+width*3/10,y+height*6/10);
            g.drawLine(x+width*3/10,y+height*7/10,x+width*4/10,y+height*8/10);
            g.drawLine(x+width*3/10,y+height*7/10,x+width*2/10,y+height*8/10);
            //
            g.drawOval(x+width*2/5,y+height*2/5,width*3/5,height*3/5);
            int[] xpoint={x+width*7/10,x+width*7/10,x+width*9/10};
            int[] ypoint={y+height*6/10,y+height*8/10,y+height*7/10};
            g.drawPolygon(xpoint,ypoint,3);

        }else
        {   pt.setToPoint(-width/2,0);
            pt.setFromPoint(-width/2,0);
            int temp1=x;
            x=y;
            y=temp1;
            int temp2=width;
            width=height;
            height=temp2;
            Graphics2Dextend g1=new Graphics2Dextend(g);
            g1.drawOval(x+width/5,y,width*3/5,height*3/5);
            g1.drawLine(x+width/2,y+height/5,x+width/2,y+height*3/10);
            g1.drawLine(x+width/2,y+height*3/10,x+width*4/10,y+height*4/10);
            g1.drawLine(x+width/2,y+height*3/10,x+width*6/10,y+height*4/10);
            //
            g1.drawOval(x,y+height*2/5,width*3/5,height*3/5);
            g1.drawLine(x+width*3/10,y+height*7/10,x+width*3/10,y+height*6/10);
            g1.drawLine(x+width*3/10,y+height*7/10,x+width*4/10,y+height*8/10);
            g1.drawLine(x+width*3/10,y+height*7/10,x+width*2/10,y+height*8/10);
            //
            g1.drawOval(x+width*2/5,y+height*2/5,width*3/5,height*3/5);
            int[] xpoint={x+width*7/10,x+width*7/10,x+width*9/10};
            int[] ypoint={y+height*6/10,y+height*8/10,y+height*7/10};
            g1.drawPolygon(xpoint,ypoint,3);
        }
    }


}

