package View;

import Control.Breaker;
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
 * Created by Administrator on 2017-02-16.
 */
public class BreakerUI extends NodeUI {
    private Breaker breaker = null;
    protected BorderUI editableBorder = null;
    public BreakerUI(TNetwork network, Node element) {
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
        breaker = (Breaker) this.getElement();

        //get position
        final Point location = breaker.getLocation();
        final Dimension size = breaker.getBounds().getSize();
        boolean isRotate = breaker.isRotate();
        final int x = location.x;
        final int y = location.y;
        final int width = size.width;
        final int height = size.height;

        g.setColor(breaker.getBodyColor());
        g.drawRect(x, y + height / 8, width, height / 2);
        g.drawLine(x, y + height * 3 / 8, x - height / 6, y + height * 3 / 8);
        g.drawLine(x - height / 6, y + height * 3 / 8, x, y + height / 20);
        g.drawLine(x - height / 6, y + height * 3 / 8, x, y + 5 * height / 8 + height / 12);
        g.drawLine(x + width, y + height * 3 / 8, x + width + height / 6, y + height * 3 / 8);
        g.drawLine(x + width + height / 6, y + height * 3 / 8, x + width, y + height / 20);
        g.drawLine(x + width + height / 6, y + height * 3 / 8, x + width, y + 5 * height / 8 + height / 12);
        g.drawLine(x - height / 3, y + height * 3 / 8, x - height / 6, y + height / 20);
        g.drawLine(x - height / 3, y + height * 3 / 8, x - height / 6, y + 5 * height / 8 + height / 12);
        g.drawLine(x + width + height / 3, y + height * 3 / 8, x + width + height / 6, y + height / 20);
        g.drawLine(x + width + height / 3, y + height * 3 / 8, x + width + height / 6, y + 5 * height / 8 + height / 12);
        if(isRotate){
            g.rotate(0.5);
        }
    }


}
