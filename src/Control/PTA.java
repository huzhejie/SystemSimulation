package Control;

import View.PTAUI;
import twaver.Link;
import twaver.ResizableNode;
import twaver.TWaverConst;

import java.awt.*;
import java.util.List;

/**
 * Created by Administrator on 2017-03-01.
 */
public class PTA extends PT {
    public PTA() {
        super();
        init();
    }
    public String getUIClassID() {
        return PTAUI.class.getName();
    }
}

