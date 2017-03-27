package Control;

import View.VariableFieldUI;
import twaver.ResizableNode;
import twaver.TWaverConst;

import java.awt.*;

/**
 * Created by Administrator on 2017-02-17.
 * 变电站
 */
public class VariableField extends ResizableNode {
    private boolean isRotate = false;

    public VariableField() {
        super.setImage(null);
        init();
    }

    public void init() {
        this.putRenderColor(Color.GREEN);
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
        return VariableFieldUI.class.getName();
    }
}

