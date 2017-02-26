package Control;

import View.SwitchUI;
import twaver.ResizableNode;
import twaver.RotatableNode;
import twaver.TWaverConst;

import java.awt.*;

/**
 * Created by huzhejie on 2017/2/2.
 */
public class Switch extends ResizableNode {
    private boolean turnOn = false;
    private boolean isRotate = false;
    public Switch(){
//        super();
        super.setImage(null);
        init();
    }
    public void init(){
        this.putBodyColor(Color.GREEN);
        this.putBorderColor(Color.black);
        this.putBorderInsets(12);
        this.putBorderStroke(TWaverConst.STROKE_SQUARE_THINNEST);
        this.setSize(30,30);
    }
    public boolean isTurnOn(){
        return turnOn;
    }
    public String getUIClassID() {
        return SwitchUI.class.getName();
    }
    public void setTurnOn(boolean turnOn) {
        if(this.turnOn != turnOn){
            boolean oldValue = this.turnOn;
            this.turnOn = turnOn;
            this.firePropertyChange("turnOn", oldValue, this.turnOn);
        }
    }
    public boolean isRotate(){
        return isRotate;
    }
    public void setRotate(boolean isRotate){
        if(this.isRotate!=isRotate){
            boolean oldValue = this.isRotate;
            this.isRotate = isRotate;
            this.firePropertyChange("isRotate",oldValue,this.isRotate);
        }
    }
}
