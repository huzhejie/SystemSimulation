package View;

import Control.VacuumSwitch;
import twaver.Node;
import twaver.TWaverConst;
import twaver.network.TNetwork;
import twaver.network.ui.BorderUI;
import twaver.network.ui.DefaultBorderUI;
import twaver.network.ui.EditableBorderUI;
import twaver.network.ui.NodeUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

/**
 * Created by Administrator on 2017-02-17.
 */
public class VacuumSwitchUI extends NodeUI {
    private VacuumSwitch vacuumSwitch = null;
    protected BorderUI editableBorder = null;

    public VacuumSwitchUI(TNetwork network, Node element) {
        super(network, element);
    }
//    /**
//     * 根据鼠标的动作改变的开关的开闭以及是否旋转
//     * @param gesture
//     * @param e
//     */
//    public void performAction(int gesture, MouseEvent e){
//        if(gesture==TWaverConst.MOUSE_LEFT_DOUBLE_CLICKED){
//            if(vacuumSwitch !=null) {
//                vacuumSwitch.setTurnOn(!vacuumSwitch.isTurnOn());
//            }
//        }
//    }
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
    public void paintBody(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setStroke(TWaverConst.DOUBLE_WIDTH_STROKE);
        vacuumSwitch = (VacuumSwitch) this.getElement();

        //get position
        Point location = vacuumSwitch.getLocation();
        Dimension size = vacuumSwitch.getBounds().getSize();
        boolean trunOn = vacuumSwitch.isTurnOn();
        boolean isRotate = vacuumSwitch.isRotate();
        int x = location.x;
        int y = location.y;
        int width = size.width;
        int height = size.height;

        g.setColor(vacuumSwitch.getRenderColor());
        if(!isRotate) {
            vacuumSwitch.setToPoint(-width/2,-height/8);
            vacuumSwitch.setFromPoint(width/2,-height/8);
            if(!trunOn) {
                //画中间部分
                g.drawLine(x + width / 3, y + height * 3 / 8, x + width * 21 / 36, y + height / 20);
//                g.drawLine(x + width * 2 / 3, y + height / 8 + height / 12, x + width * 2 / 3, y + Math.max(height,width) / 4);

                //画左半边
                g.drawLine(x + width / 6, y + height * 3 / 8, x + width / 3, y + height * 3 / 8);
                g.drawLine(x + width / 6, y + height * 3 / 8, x + width / 3, y + height / 20);
                g.drawLine(x + width / 6, y + height * 3 / 8, x + width / 3, y + 5 * height / 8 + height / 12);
                g.drawLine(x, y + height * 3 / 8, x + width / 6, y + height / 20);
                g.drawLine(x, y + height * 3 / 8, x + width / 6, y + 5 * height / 8 + height / 12);

                //画右半边
                g.drawLine(x + width * 2 / 3, y + height * 3 / 8, x + width * 5 / 6, y + height * 3 / 8);
                g.drawLine(x + width * 5 / 6, y + height * 3 / 8, x + width * 2 / 3, y + height / 20);
                g.drawLine(x + width * 5 / 6, y + height * 3 / 8, x + width * 2 / 3, y + 5 * height / 8 + height / 12);
                g.drawLine(x + width, y + height * 3 / 8, x + width * 5 / 6, y + height / 20);
                g.drawLine(x + width, y + height * 3 / 8, x + width * 5 / 6, y + 5 * height / 8 + height / 12);
            }
            else{
                //画中间部分
                g.drawLine(x+width/3, y + height * 3 / 8, x + width*2/3, y + height * 3 / 8);
//                g.drawLine(x + width*2/3, y + height / 8 + height / 12, x + width*2/3, y + Math.max(height,width) / 4);

                //画左半边
                g.drawLine(x+width/6, y + height * 3 / 8, x+width/3, y + height * 3 / 8);
                g.drawLine(x+width/6, y + height * 3 / 8, x+width/3, y + height / 20);
                g.drawLine(x+width/6, y + height * 3 / 8, x+width/3, y + 5 * height / 8 + height / 12);
                g.drawLine(x, y + height * 3 / 8, x+width/6, y + height / 20);
                g.drawLine(x, y + height * 3 / 8, x+width/6, y + 5 * height / 8 + height / 12);

                //画右半边
                g.drawLine(x + width*2/3, y + height * 3 / 8, x + width * 5/ 6, y + height * 3 / 8);
                g.drawLine(x + width * 5/ 6, y + height * 3 / 8, x + width*2/3, y + height / 20);
                g.drawLine(x + width * 5/ 6, y + height * 3 / 8, x + width*2/3, y + 5 * height / 8 + height / 12);
                g.drawLine(x + width , y + height * 3 / 8, x + width * 5/ 6, y + height / 20);
                g.drawLine(x + width , y + height * 3 / 8, x + width * 5/ 6, y + 5 * height / 8 + height / 12);

            }
        }
        else{
            vacuumSwitch.setToPoint(0,-height/2);
            vacuumSwitch.setFromPoint(0,height/3);
            if(!trunOn){
                //画中间部分
                g.drawLine(x+width/2, y + height/ 4, x + width*7/8, y + height/2);
//                g.drawLine(x + width*2/3, y + height / 8 + height / 12, x + width*2/3, y + Math.max(height,width) / 4);

                //画上半边
                g.drawLine(x+width/2, y, x+width*3/4, y + height / 6);
                g.drawLine(x+width/2, y, x+width/4, y + height / 6);
                g.drawLine(x+width/2, y + height/6, x+width/2, y +height /4);
                g.drawLine(x+width/2, y + height/6, x+width*3/4, y + height / 3);
                g.drawLine(x+width/2, y + height/6, x+width/4, y +height /3);

                //画下半边
                g.drawLine(x + width/2, y + height * 2/3, x + width /2, y + height * 7 / 12);
                g.drawLine(x + width/2, y + height * 5 / 6, x + width*3/4, y + height *2/3);
                g.drawLine(x + width/2, y + height * 5 / 6, x + width/4, y + height *2/3);
                g.drawLine(x + width/2 , y + height * 2 / 3, x + width * 3/ 4, y + height / 2);
                g.drawLine(x + width/2 , y + height * 2 / 3, x + width /4, y +height/2);


            }
            else{
                //画中间部分
                g.drawLine(x+width/2, y + height/ 4, x + width/2, y + 2*height/3);
//                g.drawLine(x + width*2/3, y + height / 8 + height / 12, x + width*2/3, y + Math.max(height,width) / 4);

                //画上半边
                g.drawLine(x+width/2, y, x+width*3/4, y + height / 6);
                g.drawLine(x+width/2, y, x+width/4, y + height / 6);
                g.drawLine(x+width/2, y + height/6, x+width/2, y +height /4);
                g.drawLine(x+width/2, y + height/6, x+width*3/4, y + height / 3);
                g.drawLine(x+width/2, y + height/6, x+width/4, y +height /3);

                //画下半边
                g.drawLine(x + width/2, y + height * 2/3, x + width /2, y + height * 7 / 12);
                g.drawLine(x + width/2, y + height * 5 / 6, x + width*3/4, y + height *2/3);
                g.drawLine(x + width/2, y + height * 5 / 6, x + width/4, y + height *2/3);
                g.drawLine(x + width/2 , y + height * 2 / 3, x + width * 3/ 4, y + height / 2);
                g.drawLine(x + width/2 , y + height * 2 / 3, x + width /4, y +height/2);


            }
        }


    }
}