package View;

import Control.ColumnIsolationSwitch;
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
public class ColumnIsolationSwitchUI extends NodeUI {
    private ColumnIsolationSwitch columnIsolationSwitch = null;
    protected BorderUI editableBorder = null;
    public ColumnIsolationSwitchUI(TNetwork network, Node element) {
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
        columnIsolationSwitch = (ColumnIsolationSwitch) this.getElement();

        //get position
        final Point location = columnIsolationSwitch.getLocation();
        final Dimension size = columnIsolationSwitch.getBounds().getSize();
        boolean isRotate = columnIsolationSwitch.isRotate();
        boolean isTurnOn = columnIsolationSwitch.isTurnOn();
        int x = location.x;
        int y = location.y;
        int width = size.width;
        int height = size.height;

        g.setColor(columnIsolationSwitch.getRenderColor());

        //画柱断路开关
        if(!isRotate){
            columnIsolationSwitch.setFromPoint(width / 2, 0);
            columnIsolationSwitch.setToPoint(-width / 2, 0);
            if(!isTurnOn) {
                g.drawLine(x, y + height / 2, x + width / 4, y + height / 2);
                g.drawLine(x + width / 4, y + height / 2, x + width * 3 / 4, y + height / 6);
                g.drawLine(x + width * 3 / 4, y + height / 3, x + width * 3 / 4, y + height * 2 / 3);
                g.drawLine(x + width * 3 / 4, y + height / 2, x + width, y + height / 2);
            }
            else{
                g.drawLine(x, y + height / 2, x + width / 4, y + height / 2);
                g.drawLine(x + width / 4, y + height / 2, x + width * 3 / 4, y + height / 2);
                g.drawLine(x + width * 3 / 4, y + height / 3, x + width * 3 / 4, y + height * 2 / 3);
                g.drawLine(x + width * 3 / 4, y + height / 2, x + width, y + height / 2);
            }

        }else
        {
            columnIsolationSwitch.setFromPoint(0,-height/2);
            columnIsolationSwitch.setToPoint(0,height/2);
            if(!isTurnOn) {
                int temp1 = x;
                x = y;
                y = temp1;
                int temp2 = width;
                width = height;
                height = temp2;
                Graphics2Dextend g1 = new Graphics2Dextend(g);
//            g1.setColor(Color.green);
                g1.drawLine(x, y + height / 2, x + width / 4, y + height / 2);
                g1.drawLine(x + width / 4, y + height / 2, x + width * 3 / 4, y + height / 6);
                g1.drawLine(x + width * 3 / 4, y + height / 3, x + width * 3 / 4, y + height * 2 / 3);
                g1.drawLine(x + width * 3 / 4, y + height / 2, x + width, y + height / 2);
            }
            else{
                int temp1 = x;
                x = y;
                y = temp1;
                int temp2 = width;
                width = height;
                height = temp2;
                Graphics2Dextend g1 = new Graphics2Dextend(g);
//            g1.setColor(Color.green);
                g1.drawLine(x, y + height / 2, x + width / 4, y + height / 2);
                g1.drawLine(x + width / 4, y + height / 2, x + width * 3 / 4, y + height / 2);
                g1.drawLine(x + width * 3 / 4, y + height / 3, x + width * 3 / 4, y + height * 2 / 3);
                g1.drawLine(x + width * 3 / 4, y + height / 2, x + width, y + height / 2);
            }
        }
    }


}


