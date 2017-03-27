package View;

import Control.ColumnCircuitBreaker;
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
public class ColumnCircuitBreakerUI extends NodeUI {
    private ColumnCircuitBreaker columnCircuitBreaker = null;
    protected BorderUI editableBorder = null;
    public ColumnCircuitBreakerUI(TNetwork network, Node element) {
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
            if(columnCircuitBreaker !=null){
                columnCircuitBreaker.setRotate(!columnCircuitBreaker.isRotate());
            }
        }
    }
    public void paintBody(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setStroke(TWaverConst.DOUBLE_WIDTH_STROKE);
        columnCircuitBreaker = (ColumnCircuitBreaker) this.getElement();

        //get position
        final Point location = columnCircuitBreaker.getLocation();
        final Dimension size = columnCircuitBreaker.getBounds().getSize();
        boolean isRotate = columnCircuitBreaker.isRotate();
        int x = location.x;
        int y = location.y;
        int width = size.width;
        int height = size.height;

        g.setColor(columnCircuitBreaker.getRenderColor());
        //画柱断路器
        if(!isRotate){
            columnCircuitBreaker.setFromPoint(width/2,0);
            columnCircuitBreaker.setToPoint(-width/2,0);
//            g.setColor(Color.green);
            g.drawRect(x+width/8,y+height*3/8,width*3/4,height/4);
            g.drawLine(x,y+height/2,x+width/8,y+height/2);
            g.drawLine(x+width,y+height/2,x+width*7/8,y+height/2);

        }else
        {
            columnCircuitBreaker.setFromPoint(0,height/2);
            columnCircuitBreaker.setToPoint(0,-height/2);
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


