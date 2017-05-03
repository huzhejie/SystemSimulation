package View;

import Control.Alternator;
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
public class AlternatorUI extends NodeUI {
    private Alternator alternator = null;
    protected BorderUI editableBorder = null;
    public AlternatorUI(TNetwork network, Node element) {
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
        alternator = (Alternator) this.getElement();

        //get position
        final Point location = alternator.getLocation();
        final Dimension size = alternator.getBounds().getSize();
        boolean isRotate = alternator.isRotate();
        int x = location.x;
        int y = location.y;
        int width = size.width;
        int height = size.height;

        g.setColor(alternator.getRenderColor());
        //设置背景色
        if(!isRotate){
            alternator.setFromPoint(0,-height/2);
            alternator.setToPoint(0,-height/2);
//            g.setColor(Color.green);
            Font ValueFont = new Font("Times NewRoman", width / 2, height / 4);
            //画发电机
            g.setFont(ValueFont);
            g.drawString("G", x + width / 2 - width / 12, y + height / 3);
            g.drawOval(x, y, width, height);
            g.drawArc(x + width / 8, y + height * 5 / 12, width * 3 / 8, height * 3 / 8, 0, -200);
            g.drawArc(x + width / 2, y + height * 5 / 12, width * 3 / 8, height * 3 / 8, -20, 200);

        }else
        {   alternator.setFromPoint(0,-height/2);
            alternator.setToPoint(0,-height/2);
            int temp1=x;
            x=y;
            y=temp1;
            int temp2=width;
            width=height;
            height=temp2;
            Graphics2Dextend g1=new Graphics2Dextend(g);
//            g1.setColor(Color.green);
            Font ValueFont = new Font("Times NewRoman", width / 2, height / 4);
            //画发电机
            g1.setFont(ValueFont);
            g1.drawString("G", x + width / 2 - width / 12, y + height / 3);
            g1.drawOval(x, y, width, height);
            g1.drawArc(x + width / 8, y + height * 5 / 12, width * 3 / 8, height * 3 / 8, 0, -200);
            g1.drawArc(x + width / 2, y + height * 5 / 12, width * 3 / 8, height * 3 / 8, -20, 200);

        }



    }
}

