package View;

import twaver.*;
import twaver.network.*;
import twaver.network.background.ColorBackground;
import twaver.network.ui.Attachment;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;

/**
 * Created by huzhejie on 2017/2/14.
 */
public class DrawPanel extends TNetwork{
    //定义编辑工具栏中需要的用到的功能
    private String ids[] = new String[]{
            "Selection",
            "LazyMove",
            "Magnifier",
            "Pan",
            "$SEPARATOR",
            "ZoomIn",
            "ZoomOut",
            "ZoomReset",
            "ZoomToOverview",
            "ZoomToRectangle",
            "$SEPARATOR",//分隔符
            "CreateLink",
            "CreateOrthogonalLink",
            "CreateText",
            "Undo",
            "Redo",
            "$SEPARATOR",
            "OpenDataFile",
            "SaveDataFile",
            "FullScreen",
            "ExportImage",
            "ExportSVG",
            "Print"
    };


    public  DrawPanel(TDataBox dataBox){
        this.setDataBox(dataBox);
        this.init(dataBox);
    }

    /**
     * 初始化画板
     */
    private void init(final TDataBox dataBox){
        //设置面板长宽
//        final int width = 4000;
//        final int height= 4000;
        //设置背景颜色
        this.setBackground(new ColorBackground(Color.CYAN.darker().darker()));
        //设置工具栏
        this.setToolbar(NetworkToolBarFactory.getToolBar(ids, this));
        //设置Resizable Node可以直接改变大小
        this.setResizableFilter(new ResizableFilter() {
            @Override
            public boolean isResizable(Element element) {
                return true;
            }
        });
        //元素内容有变化时就重画该元素
        this.setElementPropertyChangeRepaintFilter(new ElementPropertyChangeRepaintFilter() {
            @Override
            public boolean isInterested(Element element, PropertyChangeEvent propertyChangeEvent) {
                return true;
            }
        });
        //双击元素标签可以重新编辑该标签
        this.setElementLabelEditableFilter(new EditableFilter() {
            @Override
            public boolean isEditable(Element element) {
                return true;
            }
        });
        this.setDraggingSpeed(DraggingSpeed.HIGH);
        //可以使用ctrl+c以及ctrl+v快捷键(不复制子节点)
//        this.setEnableCopyPasteWithKeyboard(true);

        //添加网格背景
//        CanvasCushion canvasCushion = new CanvasCushion() {
//            int gridSize = 20;
//            Stroke stroke = new BasicStroke(0,BasicStroke.CAP_BUTT,BasicStroke.JOIN_ROUND,10.0f,new float[]{2},0.0f);
//            @Override
//            public void paint(Graphics2D graphics2D) {
//                graphics2D.setColor(Color.WHITE);
//                graphics2D.setStroke(stroke);
//                for(int x = gridSize;x<width;x+=gridSize){
//                    graphics2D.drawLine(x,1,x,height);
//                }
//                for(int y = gridSize;y<height;y+=gridSize){
//                    graphics2D.drawLine(0,y,width,y);
//                }
//            }
//        };
//        this.addCanvasCushion(canvasCushion);

        //添加按delete键就会删除选中的元件的事件
        this.getCanvas().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_DELETE)
                    dataBox.removeSelectedElements();
            }
        });

    }
}
