package View;

import Control.LapPoints;
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
public class LapPointsUI extends NodeUI {
    private LapPoints lapPoints = null;
    protected BorderUI editableBorder = null;
    public LapPointsUI(TNetwork network, Node element) {
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
        lapPoints = (LapPoints) this.getElement();

        //get position
        final Point location = lapPoints.getLocation();
        final Dimension size = lapPoints.getBounds().getSize();
        final int x = location.x;
        final int y = location.y;
        final int width = size.width;
        final int height = size.height;

        g.setPaint(lapPoints.getRenderColor());//设置画笔,设置Paint属性
        g.drawOval(x+width/4, y+height/4, width/2, height/2);
        g.setColor(lapPoints.getRenderColor());
        g.fillOval(x+width/4,y+height/4, width/2, height/2);
        g.drawLine(x,y,x+width,y+height);
        g.drawLine(x,y+height,x+width,y);


}
}


