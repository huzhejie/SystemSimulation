package View;

import Control.DoubleRollChange;
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
 * Created by Administrator on 2017-02-16.
 */
public class DoubleRollChangeUI extends NodeUI {
    private DoubleRollChange doubleRollChange = null;
    protected BorderUI editableBorder = null;
    public DoubleRollChangeUI(TNetwork network, Node element) {
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
        doubleRollChange = (DoubleRollChange) this.getElement();

        //get position
        final Point location = doubleRollChange.getLocation();
        final Dimension size = doubleRollChange.getBounds().getSize();
        boolean isRotate = doubleRollChange.isRotate();
        int x = location.x;
        int y = location.y;
        int width = size.width;
        int height = size.height;
        if (!isRotate) {
            doubleRollChange.setToPoint(-width / 4, -height/2);
            doubleRollChange.setFromPoint(-width / 4, height*3/8);
            g.setColor(new Color(255, 0, 0));//双卷变
            g.drawOval(x, y, width / 2, height / 2);
            g.drawLine(x + width / 4, y + height / 4, x + width / 4, y + height / 8);
            g.drawLine(x + width / 4, y + height / 4, x + width / 8, y + height / 4 + height / 8);
            g.drawLine(x + width / 4, y + height / 4, x + width / 2 - width / 8, y + height / 4 + height / 8);
            //箭头
            g.drawLine(x - width / 8, y + height / 2 - height / 8, x + width / 2 + width / 8, y);
            g.drawLine(x + width / 2, y, x + width / 2 + width / 8, y);
            g.drawLine(x + width / 2 + width / 12, y + height / 10, x + width / 2 + width / 8, y);
            g.drawLine(x + width / 2, y, x + width / 2 + width / 12, y + height / 10);
            //蓝圆
            g.setColor(new Color(0, 2, 255));
            g.drawOval(x, y + height / 2 - height / 8, width / 2, height / 2);
            g.drawLine(x + width / 8, y + height / 2, x + width / 2 - width / 8, y + height / 2 - height / 8 + height / 4);
            g.drawLine(x + width / 8, y + height * 3 / 4, x + width / 2 - width / 8, y + height / 2 - height / 8 + height / 4);
            g.drawLine(x + width / 8, y + height / 2, x + width / 8, y + height * 3 / 4);
        }else
        {
            doubleRollChange.setToPoint(-width / 2, -height/4);
            doubleRollChange.setFromPoint(width*3 / 8, -height/4);
            int temp1 = x;
            x = y;
            y = temp1;
            int temp2 = width;
            width = height;
            height = temp2;
            Graphics2Dextend g1 = new Graphics2Dextend(g);
            g1.setColor(new Color(255, 0, 0));//双卷变
            g1.drawOval(x, y, width / 2, height / 2);
            g1.drawLine(x + width / 4, y + height / 4, x + width / 4, y + height / 8);
            g1.drawLine(x + width / 4, y + height / 4, x + width / 8, y + height / 4 + height / 8);
            g1.drawLine(x + width / 4, y + height / 4, x + width / 2 - width / 8, y + height / 4 + height / 8);
            //箭头
            g1.drawLine(x - width / 8, y + height / 2 - height / 8, x + width / 2 + width / 8, y);
            g1.drawLine(x + width / 2, y, x + width / 2 + width / 8, y);
            g1.drawLine(x + width / 2 + width / 12, y + height / 10, x + width / 2 + width / 8, y);
            g1.drawLine(x + width / 2, y, x + width / 2 + width / 12, y + height / 10);
            //蓝圆
            g1.setColor(new Color(0, 2, 255));
            g1.drawOval(x, y + height / 2 - height / 8, width / 2, height / 2);
            g1.drawLine(x + width / 8, y + height / 2, x + width / 2 - width / 8, y + height / 2 - height / 8 + height / 4);
            g1.drawLine(x + width / 8, y + height * 3 / 4, x + width / 2 - width / 8, y + height / 2 - height / 8 + height / 4);
            g1.drawLine(x + width / 8, y + height / 2, x + width / 8, y + height * 3 / 4);

        }
    }
}