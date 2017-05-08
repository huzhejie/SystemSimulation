package Utils;

import View.DrawPanel;
import twaver.Element;
import twaver.Link;
import twaver.Node;
import twaver.base.A.A.E;


import javax.swing.*;
import java.lang.annotation.ElementType;
import java.util.*;

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
                     Double distance = tempDataList.get(i).distance+tempDataList.get(j).distance;
                     Double power = tempDataList.get(j).power;
                     Data data = new Data(distance,power,null,tempDataList.get(i).root,tempDataList.get(j).root);
                     dataList.add(data);
                }

            }
        }

        DataPower powerComparator = new DataPower();
        Collections.sort(dataList,powerComparator);

        minPower = dataList.get(0).power;
        maxPower = dataList.get(dataList.size()-1).power;

        DataLength lengthComparator = new DataLength();
        Collections.sort(dataList,lengthComparator);

        minLength = dataList.get(0).distance;
        maxLength = dataList.get(dataList.size()-1).distance;

        ArrayList<Element> rootList = (ArrayList<Element>)network.getClientProperty("rootList");

        //存储排列的数据
        List<DataSet> set = new ArrayList<>();

        Map<Integer,Element> map = new HashMap<>();
        int count = 0;
        for(Element element:rootList){
            element.putClientProperty("number",count);
            map.put(count,element);
            int totalPower = 0;
            int voltage = Integer.parseInt(element.getClientProperty("rootVoltage").toString().replaceAll("kv",""));
            for(int i = 0;i<((Node)element).getFromLinks().size();i++){
                Link link = (Link)((Node)element).getFromLinks().get(i);
                if(link.getClientProperty("current")!=null)
                    totalPower+=voltage*Double.parseDouble(link.getClientProperty("current").toString());
            }
            element.putClientProperty("totalPower",totalPower);
            count++;
        }

        for(Data data:dataList){
            data.reward = 10*(maxPower-data.power)/(maxPower-minPower)+
                    10*(maxLength-data.distance)/(maxLength-minLength);
        }
        Graph g;
        ArrayList<Vertex> list = new ArrayList<>();
        ArrayList<Vertex> sublist;
        //获取前j个电源设备的设备名称
        ArrayList<String> nameList = new ArrayList<>();
        for(int i = 0;i<rootList.size();i++) {
            //初始化数据
            g = new Graph(rootList.size());
            for(Data data:dataList) {
                g.addEdge((int) data.root.getClientProperty("number"), (int) data.to.getClientProperty("number"), data.reward,data.power);
            }
            //使用dijkstra算法并将结果存储到list中
            dijkstra(g.vertices.get(i));
            for(Vertex v:g.vertices.values()){
                if(v.minDistance != Integer.MAX_VALUE) {
                    list.add(v);
                }
            }
            if(list.size()==0) {
                set.add(new DataSet(rootList.get(i),new ArrayList<String>()));
                continue;
            }
            //进行升序排列，从小到大
            Collections.sort(list, new Comparator<Vertex>() {
                @Override
                public int compare(Vertex o1, Vertex o2) {
                    if(o1.minDistance-o2.minDistance>0)
                        return 1;
                    else if(o1.minDistance-o2.minDistance==0)
                        return 0;
                    else
                        return -1;
                }
            });
            //对专供功率进行检验
            double powerSum = 0.0d;
            int j = 0;
            while(powerSum<(double)rootList.get(i).getClientProperty("totalPower")&&j<list.size()){
                powerSum+=list.get(j).power;
                j++;
            }
            if(j==list.size()){
                set.add(new DataSet(rootList.get(i),new ArrayList<String>()));
                continue;
            }
            //获取满足功率条件的前j个电源
            sublist = (ArrayList<Vertex>)list.subList(0,j-1);

            for(Vertex vertex:sublist){
                nameList.add(map.get(vertex.id).getName());
            }

            DataSet data = new DataSet(rootList.get(i),nameList);
            set.add(data);

            list.clear();
            nameList.clear();
        }

    }
    private static void dijkstra(Vertex source){
        source.minDistance = 0.0d;
        source.power = 0.0d;
        PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>();
        queue.add(source);
        while(!queue.isEmpty()){
            Vertex v = queue.poll();
            v.visited = true;
            for(Edge edge:v.nighbors){
                if(edge.target.visited)
                    continue;
                double newDist = v.minDistance +edge.weight;
                /*
                make a comparision with new distance
                 */
                if(edge.target.minDistance>newDist){
                    /*
                    change the value of minDistance of the target vertex of the directed edge
                     */
                    queue.remove(edge.target);
                    edge.target.minDistance = newDist;
                    edge.target.power = edge.power;
                    queue.add(edge.target);
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
class Edge{
    /*
    the target vertex of the edge
     */
    public final Vertex target;
    /*
    the weight of the edge
     */
    public final double weight;

    public final double power;

    public Edge(Vertex target,double weight,double power){
        this.target = target;
        this.weight = weight;
        this.power = power;
    }
}
class Graph{
    /*
    the set of the vertices of the graph
     */
    Map<Integer,Vertex> vertices;
    public Graph(int vertexNumber){
        vertices = new LinkedHashMap<>();
        for(int i = 0;i<vertexNumber;i++){
            vertices.put(i,new Vertex(i));
        }
    }

    /**
     * add an edge into the graph
     * @param source the start vertex of the edge
     * @param target the target vertex of the edge
     * @param weight the weight of the edge
     */
    public void addEdge(int source,int target,double weight,double power){
        Vertex v = vertices.get(source);
        Edge edge = new Edge(vertices.get(target),weight,power);
        v.nighbors.add(edge);
    }
}
class Vertex implements Comparable<Vertex>{
    /*
    the id of vertex
     */
    int id;
    /*
    minimal distance from the source to this vertex
     */
    double minDistance = Integer.MAX_VALUE;
    /*
    stores edges whose start vertex's id equals to this vertex's id
     */
    ArrayList<Edge> nighbors;
    /*
    if it is visited
     */
    boolean visited;

    double power;

    public Vertex(int id){
        this.id = id;
        this.nighbors = new ArrayList<Edge>();
        this.visited = false;
    }
    public int compareTo(Vertex o) {
        return Double.compare(minDistance,o.minDistance);
    }
}