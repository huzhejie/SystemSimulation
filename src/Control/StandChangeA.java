package Control;

import View.StandChangeAUI;
import twaver.Link;
import twaver.ResizableNode;
import twaver.TWaverConst;

import java.awt.*;
import java.util.List;

/**
 * Created by Administrator on 2017-03-02.
 */
public class StandChangeA extends RollChange {
    public StandChangeA() {
        super();
        init();
    }
    public String getUIClassID() {
        return StandChangeAUI.class.getName();
    }
}

