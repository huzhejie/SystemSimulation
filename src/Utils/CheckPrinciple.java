package Utils;

import View.DrawPanel;
import twaver.Element;


import javax.swing.*;
import java.lang.annotation.ElementType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by user on 2017/5/1.
 */
public class CheckPrinciple {
    private Double maxPower;
    private Double minPower;
    private Double maxLength;
    private Double minLength;
    public CheckPrinciple(DrawPanel network){
        if(network.getClientProperty("dataList")==null){
            JOptionPane.showMessageDialog(null,"请先进行网络拓扑着色操作");
            return;
        }
        ArrayList<Data> tempDataList = (ArrayList<Data>)network.getClientProperty("dataList");
        ArrayList<Data> dataList = new ArrayList<>();
        /**
         * 合并连接同一个开关的俩个电源节点的数据
         */
        for(int i = 0;i<tempDataList.size();i++){
            String name_1 = tempDataList.get(i).aSwitch.getName();
            for(int j = i+1;j<tempDataList.size();j++){
                if(tempDataList.get(j).aSwitch.getName().equals(name_1)){

                }

            }
        }

        DataPower powerComparator = new DataPower();
        Collections.sort(dataList,powerComparator);

        maxPower = dataList.get(0).power;
        minPower = dataList.get(dataList.size()-1).power;

        DataLength lengthComparator = new DataLength();
        Collections.sort(dataList,lengthComparator);

        maxLength = dataList.get(0).distance;
        minLength = dataList.get(dataList.size()-1).distance;

        ArrayList<Element> rootList = (ArrayList<Element>)network.getClientProperty("rootList");

        for(Data data:dataList){
            data.reward = 10*(maxPower-data.power)/(maxPower-minPower)+
                             10*(maxLength-data.distance)/(maxLength-minLength);
            for(Element element:rootList){
                if(data.root.getName().equals(element.getName())){

                }
            }
        }




    }

}
class DataPower implements Comparator<Data> {
    @Override
    public int compare(Data o1, Data o2) {
        if(o1.power>o2.power)
            return 1;
        else if(o1.power<o2.power)
            return -1;
        else
            return 0;
    }
}
class DataLength implements Comparator<Data>{

    @Override
    public int compare(Data o1, Data o2) {
        if(o1.distance>o2.distance)
            return 1;
        else if(o1.distance<o2.distance)
            return -1;
        else
            return 0;
    }
}