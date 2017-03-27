package View;

import Control.IsolationOfPowerDis;
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
public class IsolationOfPowerDisUI extends NodeUI {
    private IsolationOfPowerDis isolationOfPowerDis = null;
    protected BorderUI editableBorder = null;
    public IsolationOfPowerDisUI(TNetwork network, Node element) {
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
            if(isolationOfPowerDis !=null){
                isolationOfPowerDis.setRotate(!isolationOfPowerDis.isRotate());
            }
        }
    }
    public void paintBody(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setStroke(TWaverConst.DOUBLE_WIDTH_STROKE);
        isolationOfPowerDis = (IsolationOfPowerDis) this.getElement();

        //get position
        final Point location = isolationOfPowerDis.getLocation();
        final Dimension size = isolationOfPowerDis.getBounds().getSize();
        boolean isRotate = isolationOfPowerDis.isRotate();
        int x = location.x;
        int y = location.y;
        int width = size.width;
        int height = size.height;

        g.setColor(isolationOfPowerDis.getRenderColor());
        //画p配电隔离开
        if(!isRotate){
            isolationOfPowerDis.setFromPoint(width/2,0);
            isolationOfPowerDis.setToPoint(-width/2,0);
//            g.setColor(Color.green);
            g.drawLine(x,y+height/2,x+width/4,y+height/2);
            g.drawLine(x+width/4,y+height/2,x+width*3/4,y+height/6);
            g.drawLine(x+width*3/4,y+height/2,x+width,y+height/2);

        }else
        {
            isolationOfPowerDis.setFromPoint(0,height/2);
            isolationOfPowerDis.setToPoint(0,-height/2);
            int temp1=x;
            x=y;
            y=temp1;
            int temp2=width;
            width=height;
            height=temp2;
            Graphics2Dextend g1=new Graphics2Dextend(g);
//            g1.setColor(Color.green);
            g1.drawLine(x,y+height/2,x+width/4,y+height/2);
            g1.drawLine(x+width/4,y+height/2,x+width*3/4,y+height/6);
            g1.drawLine(x+width*3/4,y+height/2,x+width,y+height/2);
        }
    }


}


