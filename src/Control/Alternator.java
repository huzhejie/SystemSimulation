package Control;

import View.AlternatorUI;
import twaver.RotatableNode;
import twaver.TWaverConst;

import java.awt.*;

/**
 * Created by Administrator on 2017-02-17.
 */
public class Alternator extends RotatableNode {
    private boolean turnOn = false;

    public Alternator() {
        super();
        init();
    }

    public void init() {
        this.putBorderColor(Color.black);//元件边框的颜色
        this.putBorderInsets(12);
        this.putBorderStroke(TWaverConst.STROKE_SQUARE_THINNEST);
        this.setImage(TWaverConst.BLANK_IMAGE);
//        this.setSize(5,30);
    }

    public boolean isTurnOn() {
        return turnOn;
    }

    public String getUIClassID() {
        return AlternatorUI.class.getName();
    }
}
