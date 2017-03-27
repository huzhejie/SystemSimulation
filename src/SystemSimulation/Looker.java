package SystemSimulation;

import Control.DrawPanelDataBox;
import View.LookPanel;
import twaver.Element;
import twaver.TDataBox;
import twaver.TWaverUtil;
import twaver.network.action.CopyAction;
import twaver.network.background.ColorBackground;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by user on 2017/3/27.
 */
public class Looker{

    public static void main(String []args){
        final DrawPanelDataBox box = new DrawPanelDataBox();
        final LookPanel network = new LookPanel(box);

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

        JFrame jFrame = new JFrame();
        jFrame.setLayout(new BorderLayout());
        jFrame.setTitle("查看器");
        jFrame.add(network,BorderLayout.CENTER);
        jFrame.setSize(1000,800);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setJMenuBar(new LookerMenuBar(network));
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
