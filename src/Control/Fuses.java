package Control;

import View.FusesUI;
import twaver.Link;
import twaver.ResizableNode;
import twaver.TWaverConst;

import java.awt.*;
import java.util.List;

/**
 * Created by Administrator on 2017-03-02.
 *熔断器
 */
public class Fuses extends Fuse {
    public Fuses() {
        super();
        init();
    }
    public String getUIClassID() {
        return FusesUI.class.getName();
    }
}

