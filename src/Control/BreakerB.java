package Control;

import View.BreakerBUI;
import twaver.Link;
import twaver.ResizableNode;
import twaver.TWaverConst;

import java.awt.*;
import java.util.List;

/**
 * Created by Administrator on 2017-02-16.
 */
public class BreakerB extends Breaker {
    public BreakerB() {
        super();
        init();
    }
    public String getUIClassID() {
        return BreakerBUI.class.getName();
    }

}
