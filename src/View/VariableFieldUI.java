package View;

import Control.VariableField;
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
 * Created by Administrator on 2017-02-17.
 */
public class VariableFieldUI extends NodeUI {
    private VariableField variableField = null;
    protected BorderUI editableBorder = null;
    public VariableFieldUI(TNetwork network, Node element) {
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
            if(variableField !=null){
                variableField.setRotate(!variableField.isRotate());
            }
        }
    }
    public void paintBody(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setStroke(TWaverConst.DOUBLE_WIDTH_STROKE);
        variableField = (VariableField) this.getElement();

        //get position
        final Point location = variableField.getLocation();
        final Dimension size = variableField.getBounds().getSize();
        boolean isRotate = variableField.isRotate();
        int x = location.x;
        int y = location.y;
        int width = size.width;
        int height = size.height;

        Font ValueFont = new Font("黑体", 12, height / 2);

        g.setColor(Color.white);
        g.setFont(ValueFont);
        g.drawString("变电站", x - width / 8, y + height / 2);


    }
}
