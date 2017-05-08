package Utils;

import twaver.Element;

import java.util.ArrayList;


/**
 * Created by huzhejie on 2017/5/4.
 * 该类用来存储最短路径求得的结果数据
 */
public class DataSet {
    private ArrayList<String> list;
    private Element data;
    public DataSet(Element data,ArrayList<String> list){
        this.data =  data;
        this.list = list;
    }

    public ArrayList<String> getList() {
        return list;
    }

    public Element getData() {
        return data;
    }
}
