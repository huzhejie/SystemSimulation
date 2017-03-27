package View;

import Control.PTA;
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
public class PTAUI  extends NodeUI {
    private PTA pta = null;
    protected BorderUI editableBorder = null;
    public PTAUI(TNetwork network, Node element) {
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
            if(pta !=null){
                pta.setRotate(!pta.isRotate());
            }
        }
    }
    public void paintBody(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setStroke(TWaverConst.DOUBLE_WIDTH_STROKE);
        pta = (PTA) this.getElement();

        //get position
        final Point location = pta.getLocation();
        final Dimension size = pta.getBounds().getSize();
        boolean isRotate = pta.isRotate();
        int x = location.x;
        int y = location.y;
        int width = size.width;
        int height = size.height;

        g.setColor(pta.getRenderColor());

        //画PTA
        if(!isRotate){
            pta.setFromPoint(width/2,0);
            pta.setToPoint(-width/2,0);
            g.drawOval(x,y+height*3/16,width/2+width/16,height/2+height/16);
            g.drawOval(x+width/2-width/16,y+height*3/16,width/2+width/16,height/2+height/16);

        }else
        {
            pta.setFromPoint(0,height/2);
            pta.setToPoint(0,-height/2);
            int temp1=x;
            x=y;
            y=temp1;
            int temp2=width;
            width=height;
            height=temp2;
            Graphics2Dextend g1=new Graphics2Dextend(g);
            g1.drawOval(x,y+height*3/16,width/2+width/16,height/2+height/16);
            g1.drawOval(x+width/2-width/16,y+height*3/16,width/2+width/16,height/2+height/16);
        }
    }


}

