package Control;

import View.ColumnIsolationSwitchUI;
import twaver.Link;
import twaver.ResizableNode;
import twaver.TWaverConst;

import java.awt.*;
import java.util.List;

/**
 * Created by Administrator on 2017-03-02.
 * 柱隔离开关
 */
public class ColumnIsolationSwitch extends Switch {

    public ColumnIsolationSwitch() {
        super();
        init();
    }
    public String getUIClassID() {
        return ColumnIsolationSwitchUI.class.getName();
    }

}

