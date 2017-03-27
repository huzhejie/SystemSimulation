package View;


import java.awt.*;

/**
 * Created by Administrator on 2017-03-01.
 */
public class Graphics2Dextend {
    /*
    * 这个类是主要是提供一个对于图像翻转的方法
    * */

   private Graphics2D g ;

    public Graphics2Dextend(Graphics2D g) {
        this.g = g;
    }

    public  void  drawLine(int x1,int y1,int x2,int y2){
        g.drawLine(y1,x1,y2,x2);
    }

    public void drawRect(int x, int y, int width, int height){
        g.drawRect(y,x,height,width);
    }

    public void setColor(Color c){
        g.setColor(c);
    }

    public  void drawPolygon(int xPoints[], int yPoints[], int nPoints){
        g.drawPolygon(yPoints,xPoints,nPoints);
    }

    public  void fillPolygon(int xPoints[], int yPoints[], int nPoints){
        g.fillPolygon(yPoints,xPoints,nPoints);
    }

    public  void drawOval(int x, int y, int width, int height){
        g.drawOval(y,x,height,width);
    }
    public  void fillRect(int x, int y, int width, int height)
    {
        g.fillRect(y,x,height,width);
    }
    public void drawArc(int x, int y, int width, int height,int startAngle, int arcAngle){
       g.drawArc(y,x,height,width,arcAngle,startAngle);
   };
    public void setFont(Font font){g.setFont(font);}
    public void drawString(String str, int x, int y){g.drawString(str,y, x);}


}

