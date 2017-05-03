package View;

import Control.Trunk;
import twaver.Node;
import twaver.TWaverConst;
import twaver.network.TNetwork;
import twaver.network.ui.BorderUI;
import twaver.network.ui.DefaultBorderUI;
import twaver.network.ui.EditableBorderUI;
import twaver.network.ui.NodeUI;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.*;

/**
 * Created by user on 2017/4/9.
 */
public class TrunkUI extends NodeUI {
    private Trunk trunk = null;
    protected BorderUI editableBorder = null;
    protected java.util.List<Point> toPointList = new ArrayList<>();
    public TrunkUI(TNetwork network, Node node) {
        super(network, node);
    }
    /**
     * 将元件的边框变成有节点的样式，可以用来改变元件长宽
     * @return
     */
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

    public void paintBody(Graphics2D g){
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setStroke(TWaverConst.DOUBLE_WIDTH_STROKE);
        trunk = (Trunk)this.getElement();

        //get position
        Point location = trunk.getLocation();
        Dimension size = trunk.getSize();
        boolean isRotate = trunk.isRotate();
        int x = location.x;
        int y = location.y;
        int width = size.width;
        int height = size.height;

        g.setColor(trunk.getRenderColor());

        toPointList.clear();

        if(!isRotate){
            Line2D line = new Line2D.Double(x+width/2,y,x+width/2,y+height);
            BasicStroke bs = new BasicStroke(13,BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL);
            g.setStroke(bs);
            g.draw(line);
        }
        else{
            Line2D line = new Line2D.Double(x,y+height/2,x+width,y+height/2);
            BasicStroke bs = new BasicStroke(13,BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL);
            g.setStroke(bs);
            g.draw(line);
        }
        trunk.setToPointList(toPointList);
    }
}
