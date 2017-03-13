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

        DoubleRollChange switch_2 = new DoubleRollChange();
        switch_2.setName("双卷变");
        switch_2.setIcon(ComponentDataBox.class.getResource("resource/shuangjuanbian.png").toString());

        ThreeVolumeChange switch_3 = new ThreeVolumeChange();
        switch_3.setName("三卷变");
        switch_3.setIcon(ComponentDataBox.class.getResource("resource/sanjuanbian.png").toString());

        Breaker switch_4 = new Breaker();
        switch_4.setName("断路器");
        switch_4.setIcon(ComponentDataBox.class.getResource("resource/duanluqi.png").toString());

        VacuumSwitch switch_5 = new VacuumSwitch();//真空开关
        switch_5.setName("真空开关A");
        switch_5.setIcon(ComponentDataBox.class.getResource("resource/VacuumSwitch.png").toString());
        switch_5.setParent(node);

        Junction switch_6 = new Junction();
        switch_6.setName("接点");
        switch_6.setIcon(ComponentDataBox.class.getResource("resource/Junction.png").toString());

        VariableField switch_7 = new VariableField();
        switch_7.setName("变电场");
        switch_7.setIcon(ComponentDataBox.class.getResource("resource/VariableField.png").toString());

        Fuse switch_8 = new Fuse();
        switch_8.setName("保险丝");
        switch_8.setIcon(ComponentDataBox.class.getResource("resource/Fuse.png").toString());

        Alternator switch_9 = new Alternator();
        switch_9.setName("发电机");
        switch_9.setIcon(ComponentDataBox.class.getResource("resource/Alternator.png").toString());

        //将开关元件添加到databox中
        this.addElement(node);
        this.addElement(switch_1);
        this.addElement(switch_2);
        this.addElement(switch_3);
        this.addElement(switch_4);
        this.addElement(switch_5);
        this.addElement(switch_6);
        this.addElement(switch_7);
        this.addElement(switch_8);
    }

}
