package View;

import Control.PowerDisCircuitBreakers;
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
public class PowerDisCircuitBreakersUI extends NodeUI {
    private PowerDisCircuitBreakers powerDisCircuitBreakers = null;
    protected BorderUI editableBorder = null;
    public PowerDisCircuitBreakersUI(TNetwork network, Node element) {
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
            if(powerDisCircuitBreakers !=null){
                powerDisCircuitBreakers.setRotate(!powerDisCircuitBreakers.isRotate());
            }
        }
    }
    public void paintBody(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setStroke(TWaverConst.DOUBLE_WIDTH_STROKE);
        powerDisCircuitBreakers = (PowerDisCircuitBreakers) this.getElement();

        //get position
        final Point location = powerDisCircuitBreakers.getLocation();
        final Dimension size = powerDisCircuitBreakers.getBounds().getSize();
        boolean isRotate = powerDisCircuitBreakers.isRotate();
        int x = location.x;
        int y = location.y;
        int width = size.width;
        int height = size.height;

        g.setColor(powerDisCircuitBreakers.getRenderColor());

        //画配电断路器
        if(!isRotate){
            powerDisCircuitBreakers.setFromPoint(width/2,0);
            powerDisCircuitBreakers.setToPoint(-width/2,0);
//            g.setColor(Color.green);
            g.drawRect(x+width/8,y+height*3/8,width*3/4,height/4);
            g.drawLine(x,y+height/2,x+width/8,y+height/2);
            g.drawLine(x+width,y+height/2,x+width*7/8,y+height/2);

        }else
        {
            powerDisCircuitBreakers.setFromPoint(0,height/2);
            powerDisCircuitBreakers.setToPoint(0,-height/2);
            int temp1=x;
            x=y;
            y=temp1;
            int temp2=width;
            width=height;
            height=temp2;
            Graphics2Dextend g1=new Graphics2Dextend(g);
//            g1.setColor(Color.green);
            g1.drawRect(x+width/8,y+height*3/8,width*3/4,height/4);
            g1.drawLine(x,y+height/2,x+width/8,y+height/2);
            g1.drawLine(x+width,y+height/2,x+width*7/8,y+height/2);
        }
    }


}


