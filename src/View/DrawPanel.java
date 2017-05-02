package View;

import Control.*;
import View.Frame.*;
import twaver.*;
import twaver.network.*;
import twaver.network.background.ColorBackground;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by huzhejie on 2017/2/14.
 */
public class DrawPanel extends TNetwork{
    //定义编辑工具栏中需要的用到的功能
    public String ids[] = new String[]{
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
    //数据库连接
    public static Connection connection = null;

    private Color elementColor = null;

    private Color voltageColor_10 = Color.blue;
    private Color voltageColor_35 = Color.yellow;
    private Color voltageColor_110 = Color.red;
    private Color voltageColor_220 = Color.orange;
    private Color voltageColor_500 = Color.green;




    public  DrawPanel(TDataBox dataBox){
        this.setDataBox(dataBox);
        this.init(dataBox);
    }

    /**
     * 初始化画板和数据库连接
     */
    private void init(final TDataBox dataBox){
        //初始化数据库连接
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String url = "jdbc:mysql://localhost:3306/electronicsystem?" +
                    "user=root&password=hzj199429&useUnicode=true&characterEncoding=UTF8&useSSL=false";
            connection = DriverManager.getConnection(url);
//            System.out.println("数据库连接成功");
        }catch (Exception cnfe){
            cnfe.printStackTrace();
        }
        //设置背景颜色
        this.setBackground(new ColorBackground(Color.CYAN.darker().darker()));
        //设置工具栏
        this.setToolbar(NetworkToolBarFactory.getToolBar(ids, this));
        this.getToolbar().setMinimumSize(new Dimension(50,50));
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
                        final JMenuItem property = new JMenuItem("设备资料");
                        final JMenuItem rotate = new JMenuItem("旋转90°");
                        JMenuItem paint = new JMenuItem("重新着色");
                        final JMenuItem addPoint = new JMenuItem("增加节点");
                        final JMenuItem removePoint  = new JMenuItem("删除节点");
                        final JMenuItem open = new JMenuItem("断开开关");
                        final JMenuItem close = new JMenuItem("合上开关");

                        jPopupMenu.add(attribute);
                        jPopupMenu.add(property);
                        jPopupMenu.add(paint);
                        jPopupMenu.add(rotate);
                        jPopupMenu.add(addPoint);
                        jPopupMenu.add(removePoint);
                        jPopupMenu.add(open);
                        jPopupMenu.add(close);

                        rotate.setVisible(false);
                        property.setVisible(false);
                        addPoint.setVisible(false);
                        removePoint.setVisible(false);
                        open.setVisible(false);
                        close.setVisible(false);

                        Iterator iterator = DrawPanel.this.getDataBox().getSelectionModel().selection();
                        while(iterator.hasNext()){
                            final Element element = (Element)iterator.next();
                            if(element.getClientProperty("attribute")==null){
                                Map<String,Object> attributeMap = new HashMap<>();
                                element.putClientProperty("attribute",attributeMap);
                            }

                            paint.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if(elementColor ==null) {
                                        JColorChooser chooser = new JColorChooser();
                                        Color color = chooser.showDialog(DrawPanel.this, "调色板", Color.green);
                                        if (color == null) {
                                            color = Color.green;
                                        }
                                        elementColor = color;
                                    }
                                    element.putRenderColor(elementColor);
                                }
                            });
                            attribute.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    final AttributeFrame attributeFrame = new AttributeFrame(element);
                                    attributeFrame.setVisible(true);
                                }
                            });

                            //如果是母线
                            if(element instanceof Trunk){
                                rotate.setVisible(true);
                                property.setVisible(true);
                                rotate.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        ((Trunk) element).setRotate(!((Trunk) element).isRotate());
                                        ((Trunk) element).setSize(element.getHeight(),element.getWidth());
                                        rotate.setVisible(false);
                                    }
                                });
                                property.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        new GeneratrixFrame(element,connection);
                                        property.setVisible(false);
                                    }
                                });

                            }
                            //如果是开关类型的元件
                            else if(element instanceof Switch) {
                                rotate.setVisible(true);
                                property.setVisible(true);
                                property.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        new SwitchFrame(element,connection);
                                        property.setVisible(false);
                                    }
                                });
                                rotate.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        ((Switch) element).setRotate(!((Switch) element).isRotate());
                                        ((Switch) element).setSize(element.getHeight(),element.getWidth());
                                        rotate.setVisible(false);
                                    }
                                });
                                if(!((Switch)element).isTurnOn()){
                                    close.setVisible(true);
                                    close.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            ((Switch) element).setTurnOn(true);
                                            close.setVisible(false);
                                        }
                                    });
                                }
                                else {
                                    open.setVisible(true);
                                    open.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            ((Switch) element).setTurnOn(false);
                                            open.setVisible(false);
                                        }
                                    });
                                }
                            }
                            //如果是卷变器
                            else if(element instanceof DoubleRollChange){
                                property.setVisible(true);
                                property.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        new RollChangeFrame(element,connection);
                                        property.setVisible(false);
                                    }
                                });
                            }
                            //如果是断路器
                            else if(element instanceof Breaker){
                                property.setVisible(true);
                                property.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        BreakerFrame frame = new BreakerFrame(element,connection);
                                        frame.setContentPane(new BreakerFrame(element,connection).getMainPanel());
                                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                        frame.pack();
                                        frame.setVisible(true);
                                        property.setVisible(false);
                                    }
                                });
                            }
                            //如果是接地开关
                            else if(element instanceof GroundSwitch){
                                rotate.setVisible(true);
                                property.setVisible(true);
                                property.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        new EarthSwitchFrame(element,connection);
                                        property.setVisible(false);
                                    }
                                });
                                rotate.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        ((GroundSwitch) element).setRotate(!((GroundSwitch) element).isRotate());
                                        ((GroundSwitch) element).setSize(element.getHeight(),element.getWidth());
                                        rotate.setVisible(false);
                                    }
                                });
                                if(((GroundSwitch)element).isTurnOn()){
                                    close.setVisible(true);
                                    close.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            ((GroundSwitch) element).setTurnOn(false);
                                            close.setVisible(false);
                                        }
                                    });
                                }
                                else {
                                    open.setVisible(true);
                                    open.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            ((GroundSwitch) element).setTurnOn(true);
                                            open.setVisible(false);
                                        }
                                    });
                                }
                            }
                            //如果是重要用户，箱变，配电站
                            else if(element instanceof BoxChange || element instanceof DistributionStation){
                                property.setVisible(true);
                                property.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        new ControlCenterFrame(element,connection);
                                        property.setVisible(false);
                                    }
                                });
                            }
                            //如果是熔断器，跌落熔断器，保险丝
                            else if(element instanceof Fuse){
                                property.setVisible(true);
                                property.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        new FuseFrame(element,connection);
                                        property.setVisible(false);
                                    }
                                });
                            }
                            //如果是变电所
                            else if(element instanceof VariableField){
                                property.setVisible(true);
                                property.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        new SubstationFrame(element,connection);
                                        property.setVisible(false);
                                    }
                                });
                            }
                            //如果是开闭所，环网柜，分支箱
//                        else if(element instanceof kaibisuo){
//                            property.setVisible(true);
//                            property.addActionListener(new ActionListener() {
//                                @Override
//                                public void actionPerformed(ActionEvent e) {
//                                    new SwitchStationFrame();
//                                    property.setVisible(false);
//                                }
//                            });
//                        }

                            //如果是多节点连线的元件
                            else if(element instanceof ShapeLink) {
                                //如果是馈线
                                if (((ShapeLink) element).getFrom() instanceof Trunk){
                                    property.setVisible(true);
                                property.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        new FeederFrame(element, connection);
                                        property.setVisible(false);
                                    }
                                });
                            }
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
                        }
                        elementColor = null;
                    }
                    return jPopupMenu;
                }
            });
            //双击元素标签可以重新编辑该标签
//        this.setElementLabelEditableFilter(new EditableFilter() {
//            @Override
//            public boolean isEditable(Element element) {
//                return true;
//            }
//        });
            //加快拖动速度
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


    public Color getVoltageColor(int voltage){
        switch (voltage){
            case 10:
                return voltageColor_10;
            case 35:
                return voltageColor_35;
            case 110:
                return voltageColor_110;
            case 220:
                return voltageColor_220;
            case 500:
                return voltageColor_500;
            default:
                return Color.white;
        }
    }
    public Color getVoltageColor_10() {
        return voltageColor_10;
    }

    public void setVoltageColor_10(Color voltageColor_10) {
        this.voltageColor_10 = voltageColor_10;
    }

    public Color getVoltageColor_35() {
        return voltageColor_35;
    }

    public void setVoltageColor_35(Color voltageColor_35) {
        this.voltageColor_35 = voltageColor_35;
    }

    public Color getVoltageColor_110() {
        return voltageColor_110;
    }

    public void setVoltageColor_110(Color voltageColor_110) {
        this.voltageColor_110 = voltageColor_110;
    }

    public Color getVoltageColor_220() {
        return voltageColor_220;
    }

    public void setVoltageColor_220(Color voltageColor_220) {
        this.voltageColor_220 = voltageColor_220;
    }

    public Color getVoltageColor_500() {
        return voltageColor_500;
    }

    public void setVoltageColor_500(Color voltageColor_500) {
        this.voltageColor_500 = voltageColor_500;
    }

}
