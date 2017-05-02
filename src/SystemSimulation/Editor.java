package SystemSimulation;

import Control.*;
import View.DrawPanel;
import View.PropertyPanel;
import twaver.*;
import twaver.network.action.CopyAction;
import twaver.network.background.ColorBackground;
import twaver.tree.ElementNode;
import twaver.tree.TTree;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.List;


/**
 * Created by huzhejie on 2017/2/2.
 */
public class Editor {
    //存储元件名称
    public static String temp = "";
    //存储图元类别的名称
    public static ComponentIndexSet indexSet = new ComponentIndexSet();

    public static void main(final String[] args ){

        final DrawPanelDataBox box = new DrawPanelDataBox();
        final ComponentDataBox componentBox = new ComponentDataBox();
        final DrawPanel network = new DrawPanel(box);
        //初始化属性面板
        PropertyPanel sheet = new PropertyPanel(box);

        //添加元件的鼠标左键响应事件
        network.getCanvas().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Element element;
//                System.out.println(network.getDataBox().getLastSelectedElement().getID());
                if(e.getButton()==MouseEvent.BUTTON1){
//                    System.out.println(temp);
                    switch(temp){
                        case "":
                            break;
                        default:
                            element = componentBox.getElementByName(temp).copy();
                            int id;
//                            if(element instanceof Trunk) {
//                                ((ArrayList<Element>)network.getClientProperty("rootList")).add(element);
//                            }
//                            else if(element instanceof DistributionStation){
//                                ((ArrayList<Element>)network.getClientProperty("rootList")).add(element);
//                            }
                            if(element!=null&&!indexSet.getSet().equals(element.getName())) {
                                double zoom = network.getZoomer().getZoom();
                                element.setLocation(e.getX()/zoom,e.getY()/zoom);
                                box.addElement(element);
                                temp = "";
                            }
                            Class clazz = null;
                            switch (element.getName()){
                                case "母线":
                                    clazz = Trunk.class;
                                    break;
                                case "普通开关":
                                    clazz = Switch.class;
                                    break;
                                case "双卷变":
                                    clazz = DoubleRollChange.class;
                                    break;
                                case "三卷变":
                                    clazz = ThreeVolumeChange.class;
                                    break;
                                case "断路器A":
                                    clazz = BreakerA.class;
                                    break;
                                case "断路器B":
                                    clazz = BreakerB.class;
                                    break;
                                case "真空开关":
                                    clazz = VacuumSwitch.class;
                                    break;
                                case "接电":
                                    clazz = Junction.class;
                                    break;
                                case "变电站":
                                    clazz = VariableField.class;
                                    break;
                                case "保险丝":
                                    clazz = Fuse.class;
                                    break;
                                case "发电机":
                                    clazz = Alternator.class;
                                    break;
                                case "接地":
                                    clazz = Grounded.class;
                                    break;
                                case "搭接点":
                                    clazz = LapPoints.class;
                                    break;
                                case "接地开关":
                                    clazz = GroundSwitch.class;
                                    break;
                                case "避雷器A":
                                    clazz = LightningArrester.class;
                                    break;
                                case "避雷器B":
                                    clazz = LightningRod.class;
                                    break;
                                case "PTA":
                                    clazz = PTA.class;
                                    break;
                                case "PTB":
                                    clazz = PT.class;
                                    break;
                                case "站用变A":
                                    clazz = StandChangeA.class;
                                    break;
                                case "站用变B":
                                    clazz = StandChange.class;
                                    break;
                                case "配电站":
                                    clazz = DistributionStation.class;
                                    break;
                                case "箱变":
                                    clazz = BoxChange.class;
                                    break;
                                case "熔断器":
                                    clazz = Fuses.class;
                                    break;
                                case "柱断路器":
                                    clazz = ColumnCircuitBreaker.class;
                                    break;
                                case "配电断路器":
                                    clazz = PowerDisCircuitBreakers.class;
                                    break;
                                case "柱隔离开关":
                                    clazz = ColumnIsolationSwitch.class;
                                    break;
                                case "配电隔离开关":
                                    clazz = IsolationOfPowerDis.class;
                                    break;
                                case "电容器":
                                    clazz = CapacitorA.class;
                                    break;
                            }
                            if(network.getDataBox().getElementsByType(clazz)==null)
                                id = 1;
                            else
                                id = network.getDataBox().getElementsByType(clazz).size();
                            element.setName(element.getName()+id);
                            element.putClientProperty("numberID",id);
//                            System.out.println(element.getID());
                            break;
                    }
                }
            }
        });
        //添加ctrl+c以及ctrl+v的复制粘贴功能
        network.getCanvas().addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_C) {
                    //拷贝网元
                    new CopyAction(network).actionPerformed(null);
                    //或使用 "box.copySelection();"
                }else if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_V) {
                    //粘贴拷贝的网元
                    pasteElements(null,box);
                }
            }
        });

        //初始化元件树（视图），并将数据注入
        TTree tree = new TTree(componentBox);
        tree.addTreeNodeClickedActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MouseActionEvent event = (MouseActionEvent)e;
                if(event.getSource() instanceof ElementNode){
                    temp = ((ElementNode) event.getSource()).getElement().getName();
                }
            }
        });

        //frame布局
        JScrollPane jScrollPane = new JScrollPane(sheet);
        JScrollPane jScrollPane1 = new JScrollPane(tree);

        JSplitPane pane1 = new JSplitPane();
        JSplitPane pane2 = new JSplitPane();
        pane1.setOrientation(JSplitPane.VERTICAL_SPLIT);
        pane2.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        pane1.setBottomComponent(jScrollPane);
        pane1.setTopComponent(jScrollPane1);
        pane1.setDividerLocation(500);
        pane2.setLeftComponent(pane1);
        pane2.setRightComponent(network);
        pane2.setDividerLocation(300);


        JFrame jFrame = new JFrame();
        jFrame.setTitle("编辑器");
        jFrame.getContentPane().add(pane2);
        jFrame.setSize(1000,800);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setJMenuBar(new MenuBar(network));
        TWaverUtil.centerWindow(jFrame);
        jFrame.setVisible(true);
    }

    /**
     * 将复制的元素粘贴到network中
     * @param parentElement
     * @param box
     * @return
     */
    public static java.util.List pasteElements(Element parentElement, TDataBox box){
        java.util.List pastes = new ArrayList();
        try{
            java.util.List list = TWaverUtil.getCopyElements();
            if(list != null && list.size() > 0){
                int offset = TWaverUtil.getPasteOffset() + 50;
                TWaverUtil.setPasteOffset(offset);
                for(int i=0; i<list.size(); i++){
                    Element element = (Element)list.get(i);
                    if(element.getUIClassID().equals("LinkUI"))
                        continue;
                    Element newElement = element.copy();
                    //设置显示的名称
                    newElement.setName(element.getName()+"_copy");
                    //设置网元的位置
                    if (element.getLocation() != null) {
                        int x = element.getLocation().x + offset;
                        int y = element.getLocation().y + offset;
                        newElement.setLocation(x, y);
                    }
                    //设置父亲
                    if(parentElement != null && newElement.getParent() == null){
                        newElement.setParent(parentElement);
                    }
                    //将网元添加到DataBox中
                    if(!box.contains(newElement)){
                        box.addElement(newElement);
                    }
                    pastes.add(newElement);
                }
                box.getSelectionModel().setSelection(pastes);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return pastes;
    }
}
