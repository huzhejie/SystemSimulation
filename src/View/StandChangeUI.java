package View;

import Control.StandChange;
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
public class StandChangeUI extends NodeUI {
    private StandChange standChange = null;
    protected BorderUI editableBorder = null;
    public StandChangeUI(TNetwork network, Node element) {
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
            if(standChange !=null){
                standChange.setRotate(!standChange.isRotate());
            }
        }
    }
    public void paintBody(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setStroke(TWaverConst.DOUBLE_WIDTH_STROKE);
        standChange = ( StandChange) this.getElement();

        //get position
        final Point location = standChange.getLocation();
        final Dimension size = standChange.getBounds().getSize();
        boolean isRotate = standChange.isRotate();
        int x = location.x;
        int y = location.y;
        int width = size.width;
        int height = size.height;


        //画站用变
        //绿色部分
        if(!isRotate){
            standChange.setFromPoint(-5*width/24,-height/2);
            standChange.setToPoint(-5*width/24,height/2);
            g.setColor(standChange.getRenderColor());
            g.drawOval(x+width/8,y,width/3,height/3);
            g.drawOval(x+width/8,y+height/4,width/3,height/3);
            g.drawLine(x+width/6+width/8,y+height*7/12,x+width/6+width/8,y+height);
            g.drawLine(x+width/6+width/8,y+height/3,x+width*19/24,y+height/3);
            g.drawLine(x+width*19/24,y+height/3,x+width*19/24,y+height*35/48);
            g.drawLine(x+width*19/24,y+height*25/48,x+width/6+width/8,y+height*39/48);
            //白色部分
            g.setColor(color.white);
            int[] xpoints={x+width*5/24,x+width/6+width/8,x+width*3/8};
            int[] ypoints={y+height*10/12,y+height,y+height*10/12};
            g.drawPolygon(xpoints,ypoints,3);
            g.drawLine(x+width*7/12,y+height*35/48,x+width,y+height*35/48);
            g.drawLine(x+width*2/3,y+height*42/48,x+width*11/12,y+height*42/48);
            g.drawLine(x+width*3/4,y+height*47/48,x+width*5/6,y+height*47/48);


        }else
        {   standChange.setFromPoint(-width/2,-5*height/24);
            standChange.setToPoint(width/2,-5*height/24);
            int temp1=x;
            x=y;
            y=temp1;
            int temp2=width;
            width=height;
            height=temp2;
            Graphics2Dextend g1=new Graphics2Dextend(g);
            g.setColor(standChange.getRenderColor());
            g1.drawOval(x+width/8,y,width/3,height/3);
            g1.drawOval(x+width/8,y+height/4,width/3,height/3);
            g1.drawLine(x+width/6+width/8,y+height*7/12,x+width/6+width/8,y+height);
            g1.drawLine(x+width/6+width/8,y+height/3,x+width*19/24,y+height/3);
            g1.drawLine(x+width*19/24,y+height/3,x+width*19/24,y+height*35/48);
            g1.drawLine(x+width*19/24,y+height*25/48,x+width/6+width/8,y+height*39/48);
            //白色部分
            g1.setColor(color.white);
            int[] xpoints={x+width*5/24,x+width/6+width/8,x+width*3/8};
            int[] ypoints={y+height*10/12,y+height,y+height*10/12};
            g1.drawPolygon(xpoints,ypoints,3);
            g1.drawLine(x+width*7/12,y+height*35/48,x+width,y+height*35/48);
            g1.drawLine(x+width*2/3,y+height*42/48,x+width*11/12,y+height*42/48);
            g1.drawLine(x+width*3/4,y+height*47/48,x+width*5/6,y+height*47/48);

        }
    }


}

