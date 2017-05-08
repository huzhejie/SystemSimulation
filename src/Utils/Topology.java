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
    private ArrayList<Data> dataList = null;
    private Double distance = 0.00d;
    private Double power = 0.00d;
    private Element root = null;
    public Topology(DrawPanel network){
        this.network = network;
        network.putClientProperty("switchList",new ArrayList<Element>());
        dataList = new ArrayList<Data>();
        network.putClientProperty("dataList",dataList);
        ArrayList<Element> rootList= new ArrayList<>();
        for(Element element:(ArrayList<Element>)network.getDataBox().getAllElements()){
            if (element instanceof Trunk||element instanceof DistributionStation||
                    element instanceof Alternator||element instanceof BoxChange
                    ||element instanceof VariableField) {
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
            root = element;
            int rootVoltage = Integer.parseInt(element.getClientProperty("rootVoltage").toString().replaceAll("kv",""));
            voltageColor = network.getVoltageColor(rootVoltage);
            deepFirstSearch((Node)element,voltageColor,rootVoltage);
        }
    }
    /**
     *深度优先搜索算法
     */
    public void deepFirstSearch(Node node,Color color,int rootVolatage){
        if(rootVolatage==0)
            return;
        if(node instanceof Switch){
            if(!((Switch) node).isTurnOn()) {
                for(int i = 0;i<node.getToLinks().size();i++){
                    Link link = (Link)node.getToLinks().get(i);
                    if(link.getClientProperty("current")!=null)
                        power = power+Double.parseDouble(link.getClientProperty("rootVoltage").toString().replaceAll("kv",""))
                    *Double.parseDouble(link.getClientProperty("current").toString());
                }
                Data data = new Data(distance,power,(Switch)node,this.root,null);
                dataList.add(data);
                return;
            }
        }
        else if(node instanceof GroundSwitch){
            if(!((GroundSwitch)node).isTurnOn())
                return;
        }
        if(node instanceof Junction){}
        else
           node.putRenderColor(color);
        if(node.getFromLinks()==null)
            return;
        List lineList = node.getFromLinks();
        Iterator it = lineList.iterator();
        while(it.hasNext()){
            Link line = (Link)it.next();
            line.putRenderColor(color);
            if(line.getClientProperty("length")!=null){
                distance=distance+Double.parseDouble(line.getClientProperty("length").toString());
            }
            Node tempNode = line.getTo();
            if(tempNode instanceof RollChange
                    ||tempNode instanceof StandChange) {
                rootVolatage = (int)(rootVolatage*((Double)tempNode.getClientProperty("ratio")));
                deepFirstSearch(tempNode,color,rootVolatage);
            }
            else if(tempNode instanceof Junction){
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
