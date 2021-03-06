package Control;

import View.LapPointsUI;
import twaver.ResizableNode;
import twaver.TWaverConst;

import java.awt.*;

/**
 * Created by Administrator on 2017-03-01.
 * 搭接点
 */
public class LapPoints extends ResizableNode {
    private boolean isRotate = false;

    public LapPoints() {
        super.setImage(null);
        init();
    }

    public void init() {
        this.putRenderColor(Color.WHITE);
        this.putBorderColor(Color.black);
        this.putBorderInsets(12);
        this.putBorderStroke(TWaverConst.STROKE_SQUARE_THINNEST);
        this.setSize(30,30);
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
        return LapPointsUI.class.getName();
    }
}
