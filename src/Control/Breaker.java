package Control;

import View.BreakerUI;
import twaver.ResizableNode;
import twaver.RotatableNode;
import twaver.TWaverConst;

import java.awt.*;

/**
 * Created by Administrator on 2017-02-16.
 */
public class Breaker extends ResizableNode {
    private boolean isRotate = false;

    public Breaker() {
        super.setImage(null);
        init();
    }

    public void init() {
        this.putBodyColor(Color.GREEN);
        this.putBorderColor(Color.black);
        this.putBorderInsets(12);
        this.putBorderStroke(TWaverConst.STROKE_SQUARE_THINNEST);
        this.setSize(40,30);
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

    public String getUIClassID() {
        return BreakerUI.class.getName();
    }
}
