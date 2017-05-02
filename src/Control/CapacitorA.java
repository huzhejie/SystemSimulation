package Control;

import View.CapacitorAUI;
import twaver.Link;
import twaver.ResizableNode;
import twaver.TWaverConst;

import java.awt.*;
import java.util.List;

/**
 * Created by Administrator on 2017-03-02.
 */
public class CapacitorA extends ResizableNode {
    private boolean isRotate = false;
    private Point fromPoint = new Point(0,0);
    private Point toPoint = new Point(0,0);
    public CapacitorA() {
        super.setImage(null);
        init();
    }

    public void init() {
        this.putRenderColor(Color.WHITE);
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
        return CapacitorAUI.class.getName();
    }
    public void setFromPoint(int x,int y) {
        int oldx = this.fromPoint.x;
        int oldy = this.fromPoint.y;
        this.fromPoint.x = x;
        this.fromPoint.y = y;
        this.firePropertyChange("fromPointX",oldx,this.fromPoint.x);
        this.firePropertyChange("fromPointY",oldy,this.fromPoint.y);
        if(this.getFromLinks()!=null) {
            List<Link> fromLinks = this.getFromLinks();
            for (Link link : fromLinks) {
                link.putLinkFromXOffset(this.fromPoint.x);
                link.putLinkFromYOffset(this.fromPoint.y);
                if(link.getClientProperty("fromPointX")==null){
                    link.putClientProperty("fromPointX",this.fromPoint.x);
                    link.putClientProperty("fromPointY",this.fromPoint.y);
                }
                else {
                    int oldX = (int) link.getClientProperty("fromPointX");
                    int oldY = (int) link.getClientProperty("fromPointY");
                    link.firePropertyChange("fromPointX", oldX, this.fromPoint.x);
                    link.firePropertyChange("fromPointY", oldY, this.fromPoint.y);
                }
            }
        }
    }

    public void setToPoint(int x,int y) {
        int oldx = this.toPoint.x;
        int oldy = this.toPoint.y;
        this.toPoint.x = x;
        this.toPoint.y = y;
        this.firePropertyChange("toPointX",oldx,this.toPoint.x);
        this.firePropertyChange("toPointY",oldy,this.toPoint.y);
        if(this.getToLinks()!=null) {
            List<Link> toLinks = this.getToLinks();
            for (Link link : toLinks) {
                link.putLinkToXOffset(this.toPoint.x);
                link.putLinkToYOffset(this.toPoint.y);
                if(link.getClientProperty("toPointX")==null){
                    link.putClientProperty("toPointX",this.toPoint.x);
                    link.putClientProperty("toPointY",this.toPoint.y);
                }
                else {
                    int oldX = (int) link.getClientProperty("toPointX");
                    int oldY = (int) link.getClientProperty("toPointY");
                    link.firePropertyChange("toPointX", oldX, this.toPoint.x);
                    link.firePropertyChange("toPointY", oldY, this.toPoint.y);
                }
            }
        }
    }
}


