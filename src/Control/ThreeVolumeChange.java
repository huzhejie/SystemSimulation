package Control;

import View.ThreeVolumeChangeUI;
import twaver.Link;
import twaver.ResizableNode;
import twaver.TWaverConst;

import java.awt.*;
import java.util.List;

/**
 * Created by Administrator on 2017-02-16.
 */
public class ThreeVolumeChange extends DoubleRollChange {
    public ThreeVolumeChange() {
        super();
        init();
    }
    public String getUIClassID() {
        return ThreeVolumeChangeUI.class.getName();
    }
}
