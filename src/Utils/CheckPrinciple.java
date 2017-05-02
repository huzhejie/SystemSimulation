package Utils;

import View.DrawPanel;
import twaver.Node;

import javax.swing.*;
import javax.xml.bind.Element;
import java.util.ArrayList;

/**
 * Created by user on 2017/5/1.
 */
public class CheckPrinciple {
    public CheckPrinciple(DrawPanel network){
        if(network.getClientProperty("rootList")==null){
            JOptionPane.showMessageDialog(null,"请先进行网络拓扑着色操作");
            return;
        }
        ArrayList<Element> rootList = (ArrayList<Element>)network.getClientProperty("rootList");
        for(Element element:rootList){

        }

    }

//    public void Dijkstra(Node root,Node)
}
