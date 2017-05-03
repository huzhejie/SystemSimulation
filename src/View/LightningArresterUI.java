package View;

import Control.LightningArrester;
import Utils.Graphics2Dextend;
import twaver.Node;
import twaver.TWaverConst;
import twaver.network.TNetwork;
import twaver.network.ui.BorderUI;
import twaver.network.ui.DefaultBorderUI;
import twaver.network.ui.EditableBorderUI;
import twaver.network.ui.NodeUI;

import java.awt.*;

/**
 * Created by Administrator on 2017-03-01.
 */
public class LightningArresterUI  extends NodeUI {
    private LightningArrester lightningArrester = null;
    protected BorderUI editableBorder = null;
    public LightningArresterUI(TNetwork network, Node element) {
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

    public void paintBody(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setStroke(TWaverConst.DOUBLE_WIDTH_STROKE);
        lightningArrester = (LightningArrester) this.getElement();

        //get position
        final Point location = lightningArrester.getLocation();
        final Dimension size = lightningArrester.getBounds().getSize();
        boolean isRotate = lightningArrester.isRotate();
        int x = location.x;
        int y = location.y;
        int width = size.width;
        int height = size.height;

        //画避雷器
        if(!isRotate){
            lightningArrester.setFromPoint(0,-height/2);
            lightningArrester.setToPoint(0,-height/2);
            g.setColor(lightningArrester.getRenderColor());
            int[] xpoints={x+width*7/16,x+width/2,x+width*9/16};
            int[] ypoints={y+height/4,y+height*3/8,y+height/4};
            g.drawPolygon(xpoints,ypoints,3);
            g.fillPolygon(xpoints,ypoints,3);
            g.drawLine(x+width/2,y,x+width/2,y+height/4);
            g.drawRect(x+width*3/8,y,width/4,height/2);
            g.setColor(color.white);
            g.drawLine(x+width/2,y+height/2,x+width/2,y+height*5/8);
            g.drawLine(x+width/4,y+height*5/8,x+width*3/4,y+height*5/8);
            g.drawLine(x+width*3/8,y+height*3/4,x+width*5/8,y+height*3/4);
            g.drawLine(x+width*7/16,y+height*7/8,x+width*9/16,y+height*7/8);

        }else
        {   lightningArrester.setFromPoint(-width/2,0);
            lightningArrester.setToPoint(-width/2,0);
            int temp1=x;
            x=y;
            y=temp1;
            int temp2=width;
            width=height;
            height=temp2;
            Graphics2Dextend g1=new Graphics2Dextend(g);
            g1.setColor(lightningArrester.getRenderColor());
            int[] xpoints={x+width*7/16,x+width/2,x+width*9/16};
            int[] ypoints={y+height/4,y+height*3/8,y+height/4};
            g1.drawPolygon(xpoints,ypoints,3);
            g1.fillPolygon(xpoints,ypoints,3);
            g1.drawLine(x+width/2,y,x+width/2,y+height/4);
            g1.drawRect(x+width*3/8,y,width/4,height/2);
            g1.setColor(color.white);
            g1.drawLine(x+width/2,y+height/2,x+width/2,y+height*5/8);
            g1.drawLine(x+width/4,y+height*5/8,x+width*3/4,y+height*5/8);
            g1.drawLine(x+width*3/8,y+height*3/4,x+width*5/8,y+height*3/4);
            g1.drawLine(x+width*7/16,y+height*7/8,x+width*9/16,y+height*7/8);
        }
    }


}

