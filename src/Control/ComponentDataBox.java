package Control;

import twaver.Node;
import twaver.TDataBox;

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
        node.setName("开关类");
        //开关元件
        Node node1 = new Node();
        node1.setIcon(null);
        node1.setImage(null);
        node1.setName("断路器类");
        //断路器元件
        Node node2 = new Node();
        node2.setIcon(null);
        node2.setImage(null);
        node2.setName("卷变类");

        Node node3 = new Node();
        node3.setIcon(null);
        node3.setImage(null);
        node3.setName("PT类");

        Node node4 = new Node();
        node4.setIcon(null);
        node4.setImage(null);
        node4.setName("变压器类");

        Node node5 = new Node();
        node5.setIcon(null);
        node5.setImage(null);
        node5.setName("避雷器类");

        Node node6 = new Node();
        node6.setIcon(null);
        node6.setImage(null);
        node6.setName("电源装置类");

        Node node7 = new Node();
        node7.setIcon(null);
        node7.setImage(null);
        node7.setName("其它类");

        Switch switch_1 = new Switch();
        switch_1.setName("普通开关");
        switch_1.setIcon(ComponentDataBox.class.getResource("resource/switch.png").toString());
        switch_1.setParent(node);

        DoubleRollChange switch_2 = new DoubleRollChange();
        switch_2.setName("双卷变");
        switch_2.setIcon(ComponentDataBox.class.getResource("resource/DoubleRollChange.png").toString());
        switch_2.setParent(node2);

        ThreeVolumeChange switch_3 = new ThreeVolumeChange();
        switch_3.setName("三卷变");
        switch_3.setIcon(ComponentDataBox.class.getResource("resource/ThreeVolumeChange.png").toString());
        switch_3.setParent(node2);


        BreakerA switch_4 = new BreakerA();
        switch_4.setName("断路器A");
        switch_4.setIcon(ComponentDataBox.class.getResource("resource/BreakerA.png").toString());
        switch_4.setParent(node1);

        BreakerB switch_5 = new BreakerB();
        switch_5.setName("断路器B");
        switch_5.setIcon(ComponentDataBox.class.getResource("resource/BreakerB.png").toString());
        switch_5.setParent(node1);

        VacuumSwitch switch_6 = new VacuumSwitch();//真空开关
        switch_6.setName("真空开关");
        switch_6.setIcon(ComponentDataBox.class.getResource("resource/VacuumSwitch.png").toString());
        switch_6.setParent(node);

        Junction switch_7 = new Junction();
        switch_7.setName("接点");
        switch_7.setIcon(ComponentDataBox.class.getResource("resource/Junction.png").toString());
        switch_7.setParent(node7);

        VariableField switch_8 = new VariableField();
        switch_8.setName("变电站");
        switch_8.setIcon(ComponentDataBox.class.getResource("resource/VariableField.png").toString());
        switch_8.setParent(node4);

        Fuse switch_9 = new Fuse();
        switch_9.setName("保险丝");
        switch_9.setIcon(ComponentDataBox.class.getResource("resource/Fuse.png").toString());
        switch_9.setParent(node1);

        Alternator switch_10 = new Alternator();
        switch_10.setName("发电机");
        switch_10.setIcon(ComponentDataBox.class.getResource("resource/Alternator.png").toString());
        switch_10.setParent(node6);

        Grounded switch_11=new Grounded();
        switch_11.setName("接地");
        switch_11.setIcon(ComponentDataBox.class.getResource("resource/Grounded.png").toString());
        switch_11.setParent(node7);
        //将开关元件添加到databox中

//        LapPoints switch_12=new  LapPoints();
//        switch_12.setName("搭接点");
//        switch_12.setIcon(ComponentDataBox.class.getResource("resource/LapPoints.png").toString());

        GroundSwitch switch_13 = new  GroundSwitch();
        switch_13.setName("接地开关");
        switch_13.setIcon(ComponentDataBox.class.getResource("resource/GroundSwitch.png").toString());
        switch_13.setParent(node);

        LightningRod switch_14 = new  LightningRod();
                switch_14.setName("避雷器B");
        switch_14.setIcon(ComponentDataBox.class.getResource("resource/LightningRod.png").toString());
        switch_14.setParent(node5);

        LightningArrester switch_15 = new   LightningArrester();
        switch_15.setName("避雷器A");
        switch_15.setIcon(ComponentDataBox.class.getResource("resource/LightningArresterA.png").toString());
        switch_15.setParent(node5);

        PT switch_16=new  PT();
        switch_16.setName("PTB");
        switch_16.setIcon(ComponentDataBox.class.getResource("resource/PT.png").toString());
        switch_16.setParent(node3);


        PTA switch_17=new  PTA();
        switch_17.setName("PTA");
        switch_17.setIcon(ComponentDataBox.class.getResource("resource/PTA.png").toString());
        switch_17.setParent(node3);

        StandChange switch_18 =new StandChange();
        switch_18.setName("站用变B");
        switch_18.setIcon(ComponentDataBox.class.getResource("resource/StandChange.png").toString());
        switch_18.setParent(node4);

        StandChangeA switch_19 =new StandChangeA();
        switch_19.setName("站用变A");
        switch_19.setIcon(ComponentDataBox.class.getResource("resource/PTA.png").toString());
        switch_19.setParent(node4);

        DistributionStation  switch_20 =new DistributionStation();
        switch_20.setName("配电站");
        switch_20.setIcon(ComponentDataBox.class.getResource("resource/DistributionStation.png").toString());
        switch_20.setParent(node6);

        BoxChange switch_21 =new BoxChange();
        switch_21.setName("箱变");
        switch_21.setIcon(ComponentDataBox.class.getResource("resource/BoxChange.png").toString());
        switch_21.setParent(node4);

        Fuses  switch_22 =new Fuses();
        switch_22.setName("熔断器");
        switch_22.setIcon(ComponentDataBox.class.getResource("resource/Fuses.png").toString());
        switch_22.setParent(node1);

        ColumnCircuitBreaker switch_23 =new ColumnCircuitBreaker();
        switch_23.setName("柱断路器");
        switch_23.setIcon(ComponentDataBox.class.getResource("resource/ColumnCircuitBreaker.png").toString());
        switch_23.setParent(node1);

        PowerDisCircuitBreakers switch_24 =new PowerDisCircuitBreakers();
        switch_24.setName("配电断路器");
        switch_24.setIcon(ComponentDataBox.class.getResource("resource/PowerDisCircuitBreakers.png").toString());
        switch_24.setParent(node1);

        ColumnIsolationSwitch switch_25 =new ColumnIsolationSwitch();
        switch_25.setName("柱隔离开关");
        switch_25.setIcon(ComponentDataBox.class.getResource("resource/ColumnIsolationSwitch.png").toString());
        switch_25.setParent(node);

        IsolationOfPowerDis switch_26 =new IsolationOfPowerDis();
        switch_26.setName("配电隔离开关");
        switch_26.setIcon(ComponentDataBox.class.getResource("resource/IsolationOfPowerDis.png").toString());
        switch_26.setParent(node);

//        CapacitorA switch_27 =new CapacitorA();
//        switch_27.setName("电容器");
//        switch_27.setIcon(ComponentDataBox.class.getResource("resource/CapacitorA.png").toString());

        Trunk switch_27 = new Trunk();
        switch_27.setName("母线");
        switch_27.setIcon(ComponentDataBox.class.getResource("resource/Trunk.png").toString());
        switch_27.setParent(node6);


        this.addElement(node);
        this.addElement(node1);
        this.addElement(node2);
        this.addElement(node3);
        this.addElement(node4);
        this.addElement(node5);
        this.addElement(node6);
        this.addElement(node7);
        this.addElement(switch_1);
        this.addElement(switch_2);
        this.addElement(switch_3);
        this.addElement(switch_4);
        this.addElement(switch_5);
        this.addElement(switch_6);
        this.addElement(switch_7);
        this.addElement(switch_8);
        this.addElement(switch_9);
        this.addElement(switch_10);
        this.addElement(switch_11);
//        this.addElement(switch_12);
        this.addElement(switch_13);
        this.addElement(switch_14);
        this.addElement(switch_15);
        this.addElement(switch_16);
        this.addElement(switch_17);
        this.addElement(switch_18);
        this.addElement(switch_19);
        this.addElement(switch_20);
        this.addElement(switch_21);
        this.addElement(switch_22);
        this.addElement(switch_23);
        this.addElement(switch_24);
        this.addElement(switch_25);
        this.addElement(switch_26);
        this.addElement(switch_27);
//        this.addElement(switch_28);



    }

}
