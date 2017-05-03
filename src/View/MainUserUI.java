package View;

import Control.MainUser;
import twaver.Link;
import twaver.Node;
import twaver.TWaverConst;
import twaver.network.TNetwork;
import twaver.network.ui.BorderUI;
import twaver.network.ui.DefaultBorderUI;
import twaver.network.ui.EditableBorderUI;
import twaver.network.ui.NodeUI;

import java.awt.*;

/**
 * Created by user on 2017/4/2.
 */
public class MainUserUI extends NodeUI {
    private MainUser mainUser = null;
    protected BorderUI editableBorder = null;
    public MainUserUI(TNetwork network, Node element) {
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
        mainUser = (MainUser) this.getElement();


        //get position
        final Point location = mainUser.getLocation();
        final Dimension size = mainUser.getBounds().getSize();
        int x = location.x;
        int y = location.y;
        int width = size.width;
        int height = size.height;
        if(mainUser.getToLinks()!=null) {
            if (((Link) mainUser.getToLinks().get(0)).getFrom().getLocation().getY() < y)
                mainUser.setToPoint(x + width / 2, y);
            else
                mainUser.setToPoint(x + width / 2, y + height);
        }

        Font ValueFont = new Font("黑体", Font.BOLD, 20);

        g.setColor(Color.white);
        g.setFont(ValueFont);
        g.drawString("用电用户", x, y + height / 2);
    }
}
