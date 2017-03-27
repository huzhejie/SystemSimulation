package Control;

import View.LightningRodUI;
import twaver.Link;
import twaver.ResizableNode;
import twaver.TWaverConst;

import java.awt.*;
import java.util.List;

/**
 * Created by Administrator on 2017-03-01.
 */
public class LightningRod extends LightningArrester {
    public LightningRod() {
        super();
        init();
    }

    public String getUIClassID() {
        return LightningRodUI.class.getName();
    }
}
