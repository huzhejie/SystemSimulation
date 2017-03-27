package Control;

import View.VacuumSwitchUI;
import twaver.Link;
import twaver.ResizableNode;
import twaver.TWaverConst;

import java.awt.*;
import java.util.List;
/**
 * Created by Administrator on 2017-02-17.
 */
public class VacuumSwitch extends Switch {
    public VacuumSwitch() {
        super();
        init();
    }
    public String getUIClassID() {
        return VacuumSwitchUI.class.getName();
    }
}


