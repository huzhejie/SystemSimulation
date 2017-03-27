package Control;

import View.IsolationOfPowerDisUI;
import twaver.Link;
import twaver.ResizableNode;
import twaver.TWaverConst;

import java.awt.*;
import java.util.List;

/**
 * Created by Administrator on 2017-03-02.
 * 配电隔离开关
 */
public class IsolationOfPowerDis extends Switch {
    public IsolationOfPowerDis() {
        super();
        init();
    }

    public String getUIClassID() {
        return IsolationOfPowerDisUI.class.getName();
    }

}

