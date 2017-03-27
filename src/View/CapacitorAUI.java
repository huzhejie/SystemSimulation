package View;

import Control.CapacitorA;
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
public class CapacitorAUI extends NodeUI {
    private CapacitorA capacitorA = null;
    protected BorderUI editableBorder = null;
    public  CapacitorAUI(TNetwork network, Node element) {
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
            if(capacitorA !=null){
                capacitorA.setRotate(!capacitorA.isRotate());
            }
        }
    }
    public void paintBody(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setStroke(TWaverConst.DOUBLE_WIDTH_STROKE);
        capacitorA = ( CapacitorA) this.getElement();

        //get position
        final Point location = capacitorA.getLocation();
        final Dimension size = capacitorA.getBounds().getSize();
        boolean isRotate = capacitorA.isRotate();
        int x = location.x;
        int y = location.y;
        int width = size.width;
        int height = size.height;

        g.setColor(capacitorA.getRenderColor());

        //画箱变
        if(!isRotate){
            capacitorA.setFromPoint(0,-3*height/16);
            capacitorA.setToPoint(0,3*height/16);
//            g.setColor(Color.green);
            g.drawRect(x,y+height*5/16,width,height/8);
            g.fillRect(x,y+height*5/16,width,height/8);
            g.drawRect(x,y+height*9/16,width,height/8);
            g.fillRect(x,y+height*9/16,width,height/8);

        }else
        {   capacitorA.setFromPoint(0,-3*width/16);
            capacitorA.setToPoint(0,3*width/16);
            int temp1=x;
            x=y;
            y=temp1;
            int temp2=width;
            width=height;
            height=temp2;
            Graphics2Dextend g1=new Graphics2Dextend(g);
//            g1.setColor(Color.green);
            g1.drawRect(x,y+height*5/16,width,height/8);
            g1.fillRect(x,y+height*5/16,width,height/8);
            g1.drawRect(x,y+height*9/16,width,height/8);
            g1.fillRect(x,y+height*9/16,width,height/8);
    }


}
}


