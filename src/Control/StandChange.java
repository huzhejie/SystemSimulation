package Control;

import View.StandChangeUI;
import twaver.Link;
import twaver.ResizableNode;
import twaver.TWaverConst;

import java.awt.*;
import java.util.List;

/**
 * Created by Administrator on 2017-03-02.
 */
public class StandChange extends RollChange {
    public StandChange() {
        super();
        init();
    }
    public String getUIClassID() {
        return StandChangeUI.class.getName();
    }
}


