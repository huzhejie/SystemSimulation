package View;

import Control.StandChangeA;
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
 * Created by Administrator on 2017-03-02.
 */
public class StandChangeAUI extends NodeUI {
    private StandChangeA standChangeA = null;
    protected BorderUI editableBorder = null;
    public StandChangeAUI(TNetwork network, Node element) {
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
        standChangeA = ( StandChangeA) this.getElement();

        //get position
        final Point location = standChangeA.getLocation();
        final Dimension size = standChangeA.getBounds().getSize();
        boolean isRotate = standChangeA.isRotate();
        int x = location.x;
        int y = location.y;
        int width = size.width;
        int height = size.height;


        //画站用变A
        if(!isRotate){
            standChangeA.setFromPoint(width/2,0);
            standChangeA.setToPoint(-width/2,0);
            g.setColor(standChangeA.getRenderColor());
            g.drawOval(x,y+height*3/16,width/2+width/16,height/2+height/16);
            g.setColor(color.white);
            g.drawOval(x+width/2-width/16,y+height*3/16,width/2+width/16,height/2+height/16);

        }else
        {
            standChangeA.setFromPoint(0,height/2);
            standChangeA.setToPoint(0,-height/2);
            int temp1=x;
            x=y;
            y=temp1;
            int temp2=width;
            width=height;
            height=temp2;
            Graphics2Dextend g1=new Graphics2Dextend(g);
            g1.setColor(standChangeA.getRenderColor());
            g1.drawOval(x,y+height*3/16,width/2+width/16,height/2+height/16);
            g1.setColor(color.white);
            g1.drawOval(x+width/2-width/16,y+height*3/16,width/2+width/16,height/2+height/16);
        }
    }


}

