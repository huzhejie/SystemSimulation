package View;


import Control.Fuses;
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
public class FusesUI  extends NodeUI {
    private Fuses fuses = null;
    protected BorderUI editableBorder = null;
    public FusesUI(TNetwork network, Node element) {
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
            if(fuses !=null){
                fuses.setRotate(!fuses.isRotate());
            }
        }
    }
    public void paintBody(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setStroke(TWaverConst.DOUBLE_WIDTH_STROKE);
        fuses = (Fuses) this.getElement();

        //get position
        final Point location = fuses.getLocation();
        final Dimension size = fuses.getBounds().getSize();
        boolean isRotate = fuses.isRotate();
        int x = location.x;
        int y = location.y;
        int width = size.width;
        int height = size.height;

        g.setColor(fuses.getRenderColor());
        //画熔断器
        if(!isRotate){
            fuses.setFromPoint(width/2,0);
            fuses.setToPoint(-width/2,0);
//            g.setColor(Color.green);
            g.drawRect(x+width/4,y+height*3/8,width/2,height/4);
            g.drawLine(x,y+height/2,x+width,y+height/2);

        }else
        {   fuses.setFromPoint(0,height/2);
            fuses.setToPoint(0,-height/2);
            int temp1=x;
            x=y;
            y=temp1;
            int temp2=width;
            width=height;
            height=temp2;
            Graphics2Dextend g1=new Graphics2Dextend(g);
//            g1.setColor(Color.green);
            g1.drawRect(x+width/4,y+height*3/8,width/2,height/4);
            g1.drawLine(x,y+height/2,x+width,y+height/2);
        }
    }


}


