package Control;

import View.TrunkUI;
import twaver.Link;
import twaver.ResizableNode;
import twaver.TWaverConst;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2017/4/9.
 */
public class Trunk extends ResizableNode {
    private boolean isRotate = false;
    private List<Point> toPointList = new ArrayList<>();
    public Trunk(){
        super();
        init();
    }
    public void init(){
        this.putRenderColor(Color.WHITE);
        this.putBorderColor(Color.black);
        this.putBorderInsets(12);
        this.putBorderStroke(TWaverConst.STROKE_SQUARE_THINNEST);
        this.setSize(10,50);
    }

    public boolean isRotate() {
        return isRotate;
    }

    public String getUIClassID() {
        return TrunkUI.class.getName();
    }

    public void setRotate(boolean rotate) {
        isRotate = rotate;
    }
    public void setToPointList(List<Point> toPointList) {
        Dimension size = this.getSize();
        int width = size.width;
        int height = size.height;
        this.toPointList = toPointList;
        if(this.getAllLinks()!=null&&this.getAllLinks().size()!=0){
            if(!this.isRotate)
                for(int i = 0;i<this.getAllLinks().size();i++){
                    Point point = new Point(0,height*(i+1)/(1+this.getAllLinks().size())-height/2);
                    this.toPointList.add(point);
                }
            else
                for(int i = 0;i<this.getAllLinks().size();i++){
                    Point point = new Point(width*(i+1)/(1+this.getAllLinks().size())-width/2,0);
                    this.toPointList.add(point);
                }
            List<Link> toLinks = this.getAllLinks();
            for(int i = 0;i<this.getAllLinks().size();i++){
                toLinks.get(i).putLinkFromXOffset(this.toPointList.get(i).x);
                toLinks.get(i).putLinkFromYOffset(this.toPointList.get(i).y);
                if(toLinks.get(i).getClientProperty("fromPointX")==null||
                        toLinks.get(i).getClientProperty("fromPointY")==null){
                    toLinks.get(i).putClientProperty("fromPointX",this.toPointList.get(i).x);
                    toLinks.get(i).putClientProperty("fromPointY",this.toPointList.get(i).y);
                }
                else {
                    int oldX = (int) toLinks.get(i).getClientProperty("fromPointX");
                    int oldY = (int) toLinks.get(i).getClientProperty("fromPointY");
                    toLinks.get(i).firePropertyChange("fromPointX", oldX, this.toPointList.get(i).x);
                    toLinks.get(i).firePropertyChange("fromPointY", oldY, this.toPointList.get(i).y);
                }
            }
        }
    }

}
