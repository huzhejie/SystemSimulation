package Control;

import twaver.Node;
import twaver.RotatableNode;
import twaver.TDataBox;
import twaver.tree.TTree;

/**
 * Created by huzhejie on 2017/2/14.
 */
public class ComponentDataBox extends TDataBox{
    public ComponentDataBox(){
        this.init();
    }
    /**
     * 初始化元件树（数据）
     */
    private void init(){
        Node node = new Node();
        node.setIcon(null);
        node.setImage(null);
        node.setName("开关");
        //开关元件
        Switch switch_1 = new Switch();
        switch_1.setName("开关A");
        switch_1.setIcon(ComponentDataBox.class.getResource("resource/switch.png").toString());
        switch_1.setParent(node);

        //将开关元件添加到databox中
        this.addElement(node);
        this.addElement(switch_1);
    }

}
