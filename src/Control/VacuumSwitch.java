package Control;

import View.VacuumSwitchUI;
import twaver.ResizableNode;
import twaver.RotatableNode;
import twaver.TWaverConst;

import java.awt.*;

/**
 * Created by Administrator on 2017-02-17.
 */
public class VacuumSwitch extends Switch {
    public VacuumSwitch() {
    }
    public String getUIClassID() {
        return VacuumSwitchUI.class.getName();
    }
}


