package Utils;

import Control.*;
import View.DrawPanel;
import twaver.Element;
import twaver.Link;
import twaver.Node;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by user on 2017/4/6.
 * 拓扑算法(深度优先搜索)
 */
public class Topology {
    private DrawPanel network = null;
    private Color voltageColor = null;
    public Topology(DrawPanel network){
        this.network = network;
        ArrayList<Element> rootList= new ArrayList<>();
        for(Element element:(ArrayList<Element>)network.getDataBox().getAllElements()) {
            if (element instanceof Trunk) {
               rootList.add(element);
            } else if (element instanceof DistributionStation) {
                rootList.add(element);
            }
        }
        if(rootList.size()==0){
            JOptionPane.showMessageDialog(null,"改配网图缺少电源节点");
            return;
        }
        else
            network.putClientProperty("rootList",rootList);
        for(Element element: rootList) {
            int rootVoltage = Integer.parseInt(element.getClientProperty("rootVoltage").toString().replaceAll("kv",""));
            voltageColor = network.getVoltageColor(rootVoltage);
            deepFirstSearch((Node)element,voltageColor,rootVoltage);
        }
    }
    /**
     *深度优先搜索算法
     */
    public void deepFirstSearch(Node root,Color color,int rootVolatage){
        if(rootVolatage==0)
            return;
        if(root instanceof Switch){
            if(((Switch) root).isTurnOn())
                return;
        }
        else if(root instanceof GroundSwitch){
            if(((GroundSwitch)root).isTurnOn())
                return;
        }
        root.putRenderColor(color);
        if(root.getFromLinks()==null)
            return;
        List lineList = root.getFromLinks();
        Iterator it = lineList.iterator();
        while(it.hasNext()){
            Link line = (Link)it.next();
            line.putRenderColor(color);
            Node tempNode = line.getTo();
            if(tempNode instanceof RollChange) {
                rootVolatage = (int)(rootVolatage*((Double)tempNode.getClientProperty("ratio")));
                deepFirstSearch(tempNode,color,rootVolatage);
            }
            else {
                int voltage = Integer.parseInt(tempNode.getClientProperty("rootVoltage").toString().replaceAll("kv", ""));
                if (rootVolatage < voltage) {
                    JOptionPane.showMessageDialog(null, line.getTo().getName() + "节点电压设置错误");
                    return;
                }
                color = network.getVoltageColor(voltage);
                deepFirstSearch(tempNode,color,voltage);
            }
        }
    }
}
