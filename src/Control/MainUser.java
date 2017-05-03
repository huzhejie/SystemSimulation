package Control;

import View.MainUserUI;
import twaver.Link;
import twaver.ResizableNode;
import twaver.TWaverConst;

import java.awt.*;

/**
 * Created by user on 2017/4/2.
 * 用电用户
 */
public class MainUser extends ResizableNode{
    private Point toPoint = new Point(0,0);

    public MainUser() {
        super();
        init();
    }

    public void init() {
        this.putRenderColor(Color.WHITE);
        this.putBorderColor(Color.black);
        this.putBorderInsets(12);
        this.putBorderStroke(TWaverConst.STROKE_SQUARE_THINNEST);
        this.setSize(50,50);
        this.putLabelFont(new Font("黑体",Font.BOLD,20));
    }

    public String getUIClassID() {
        return MainUserUI.class.getName();
    }


    public void setToPoint(int x,int y) {
        int oldx = this.toPoint.x;
        int oldy = this.toPoint.y;
        this.toPoint.x = x;
        this.toPoint.y = y;
        this.firePropertyChange("toPointX",oldx,this.toPoint.x);
        this.firePropertyChange("toPointY",oldy,this.toPoint.y);
        if(this.getToLinks()!=null) {
            java.util.List<Link> toLinks = this.getToLinks();
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
