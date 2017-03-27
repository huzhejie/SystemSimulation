package View;

import Control.LightningRod;
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
public class LightningRodUI  extends NodeUI {
    private LightningRod lightningRod = null;
    protected BorderUI editableBorder = null;
    public LightningRodUI(TNetwork network, Node element) {
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
            if(lightningRod !=null){
                lightningRod.setRotate(!lightningRod.isRotate());
            }
        }
    }
    public void paintBody(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setStroke(TWaverConst.DOUBLE_WIDTH_STROKE);
        lightningRod = (LightningRod) this.getElement();

        //get position
        final Point location = lightningRod.getLocation();
        final Dimension size = lightningRod.getBounds().getSize();
        boolean isRotate = lightningRod.isRotate();
        int x = location.x;
        int y = location.y;
        int width = size.width;
        int height = size.height;

//        g.setColor(lightningRod.getRenderColor());

        if(!isRotate){
            lightningRod.setFromPoint(0,-height/2);
            lightningRod.setToPoint(0,-height/2);
            g.setColor(lightningRod.getRenderColor());
            g.drawRect(x+width*3/8,y,width/4,height/2);
            g.drawLine(x+width*3/8,y+height/6,x+width*5/8,y+height/6);
            g.drawLine(x+width*3/8,y+height/3,x+width*5/8,y+height/3);
            g.setColor(Color.white);
            g.drawLine(x+width/2,y+height/2,x+width/2,y+height*5/8);
            g.drawLine(x+width/4,y+height*5/8,x+width*3/4,y+height*5/8);
            g.drawLine(x+width*3/8,y+height*3/4,x+width*5/8,y+height*3/4);
            g.drawLine(x+width*7/16,y+height*7/8,x+width*9/16,y+height*7/8);

        }else
        {
            lightningRod.setFromPoint(-width/2,0);
            lightningRod.setToPoint(-width/2,0);
            int temp1=x;
            x=y;
            y=temp1;
            int temp2=width;
            width=height;
            height=temp2;
            Graphics2Dextend g1=new Graphics2Dextend(g);
            g1.setColor(lightningRod.getRenderColor());
            g1.drawRect(x+width*3/8,y,width/4,height/2);
            g1.drawLine(x+width*3/8,y+height/6,x+width*5/8,y+height/6);
            g1.drawLine(x+width*3/8,y+height/3,x+width*5/8,y+height/3);
            g1.setColor(Color.white);
            g1.drawLine(x+width/2,y+height/2,x+width/2,y+height*5/8);
            g1.drawLine(x+width/4,y+height*5/8,x+width*3/4,y+height*5/8);
            g1.drawLine(x+width*3/8,y+height*3/4,x+width*5/8,y+height*3/4);
            g1.drawLine(x+width*7/16,y+height*7/8,x+width*9/16,y+height*7/8);

        }
    }


}

