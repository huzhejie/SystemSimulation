package View;

import Control.Switch;
import twaver.*;
import twaver.network.*;
import twaver.network.background.ColorBackground;
import twaver.network.ui.Attachment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.beans.PropertyChangeEvent;
import java.util.Iterator;

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
            "CreateFlexionLink",
            "CreateShapeLink",
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
    private  static Color tempColor = null;


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
        this.getToolbar().setMinimumSize(new Dimension(30,30));
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
                if(element instanceof Link){
                   ((Link) element).putLinkWidth(1);
                    ((Link) element).putLinkOutlineWidth(0);
                    ((Link) element).putLinkAntialias(true);
//                    if(element instanceof ShapeLink){
//                        if(((ShapeLink)element).getTo()!=null){
//                        }
//                    }
                }
                return true;
            }
        });
        //添加右键菜单
        this.setPopupMenuGenerator(new PopupMenuGenerator() {
            @Override
            public JPopupMenu generate(final TView tView, final MouseEvent mouseEvent) {
                JPopupMenu jPopupMenu = new JPopupMenu();
                if(tView.getDataBox().getSelectionModel().isEmpty()){
                    jPopupMenu.add(new JMenuItem("您未选择任何元件"));
                }
                else{
                    final JMenuItem attribute = new JMenuItem("图形属性");
                    final JMenuItem rotate = new JMenuItem("旋转90°");
                    JMenuItem paint = new JMenuItem("重新着色");
                    final JMenuItem addPoint = new JMenuItem("增加节点");
                    final JMenuItem removePoint  = new JMenuItem("删除节点");
                    final JMenuItem open = new JMenuItem("打开开关");
                    final JMenuItem close = new JMenuItem("合上开关");

                    jPopupMenu.add(attribute);
                    jPopupMenu.add(paint);
                    jPopupMenu.add(rotate);
                    jPopupMenu.add(addPoint);
                    jPopupMenu.add(removePoint);
                    jPopupMenu.add(open);
                    jPopupMenu.add(close);

                    rotate.setVisible(false);
                    addPoint.setVisible(false);
                    removePoint.setVisible(false);
                    open.setVisible(false);
                    close.setVisible(false);

                    Iterator iterator = DrawPanel.this.getDataBox().getSelectionModel().selection();
                    while(iterator.hasNext()){
                        final Element element = (Element)iterator.next();
                        paint.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if(tempColor==null) {
                                    JColorChooser chooser = new JColorChooser();
                                    Color color = chooser.showDialog(DrawPanel.this, "调色板", Color.green);
                                    if (color == null) {
                                        color = Color.green;
                                    }
                                    tempColor = color;
                                }
                                element.putRenderColor(tempColor);
                            }
                        });
                        attribute.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {

                            }
                        });
                        //如果是开关类型的元件
                        if(element instanceof Switch) {
                            rotate.setVisible(true);
                            rotate.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    ((Switch) element).setRotate(!((Switch) element).isRotate());
                                    rotate.setVisible(false);
                                }
                            });
                            if(((Switch)element).isTurnOn()){
                                close.setVisible(true);
                                close.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        ((Switch) element).setTurnOn(false);
                                        close.setVisible(false);
                                    }
                                });
                            }
                            else {
                                open.setVisible(true);
                                open.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        ((Switch) element).setTurnOn(true);
                                        open.setVisible(false);
                                    }
                                });
                            }
                        }
                        //如果是多节点连线的元件
                        else if(element instanceof ShapeLink) {
                            addPoint.setVisible(true);
                            removePoint.setVisible(true);
                            addPoint.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    ((ShapeLink) element).addPoint(mouseEvent.getPoint());
                                    addPoint.setVisible(false);
                                    removePoint.setVisible(false);
                                }
                            });
                            removePoint.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    ((ShapeLink)element).removePoint(((ShapeLink)element).getPoints().size()-1);
                                    addPoint.setVisible(false);
                                    removePoint.setVisible(false);
                                }
                            });
                        }
                        else{
                            System.out.println(DrawPanel.this.getElementLogicalAt(mouseEvent.getPoint()));
                        }
                    }
                    tempColor = null;
                }
                return jPopupMenu;
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
