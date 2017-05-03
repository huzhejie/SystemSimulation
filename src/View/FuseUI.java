package View;

import Control.Fuse;
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
 * Created by Administrator on 2017-02-17.
 */
public class FuseUI extends NodeUI {
    private Fuse fuse = null;
    protected BorderUI editableBorder = null;
    public FuseUI(TNetwork network, Node element) {
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
        fuse = (Fuse) this.getElement();

        //get position
        final Point location = fuse.getLocation();
        final Dimension size = fuse.getBounds().getSize();
        boolean isRotate = fuse.isRotate();
        int x = location.x;
        int y = location.y;
        int width = size.width;
        int height = size.height;

        g.setColor(fuse.getRenderColor());
        if(!isRotate){
            fuse.setFromPoint(width/2,0);
            fuse.setToPoint(-width/2,0);
//            g.setColor(Color.green);
            g.drawArc(x, y, width / 2, height * 3 / 4, -10, -200);
            g.drawArc(x + width / 2 - width / 25, y + height / 4, width / 2, height * 3 / 4, -20, 170);

        }else
        {   fuse.setFromPoint(0,height/2);
            fuse.setToPoint(0,-height/2);
            int temp1=x;
            x=y;
            y=temp1;
            int temp2=width;
            width=height;
            height=temp2;
            Graphics2Dextend g1=new Graphics2Dextend(g);
//            g1.setColor(Color.green);
            g1.drawArc(x, y, width / 2, height * 3 / 4, -10, -200);
            g1.drawArc(x + width / 2 - width / 25, y + height / 4, width / 2, height * 3 / 4, -20, 170);

        }
    }





    }
