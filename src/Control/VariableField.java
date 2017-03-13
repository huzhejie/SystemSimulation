package Control;

import View.VariableFieldUI;
import twaver.RotatableNode;
import twaver.TWaverConst;

import java.awt.*;

/**
 * Created by Administrator on 2017-02-17.
 */
public class VariableField extends RotatableNode {
    private boolean turnOn = false;

    public VariableField() {
        super();
        init();
    }

    public void init() {
        this.putBorderColor(Color.black);//元件边框的颜色
        this.putBorderInsets(12);
        this.putBorderStroke(TWaverConst.STROKE_SQUARE_THINNEST);
//        this.setSize(5,30);
    }

    public boolean isTurnOn() {
        return turnOn;
    }

    public String getUIClassID() {
        return VariableFieldUI.class.getName();
    }
}