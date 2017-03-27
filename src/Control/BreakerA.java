package Control;

import View.BreakerAUI;
import twaver.ResizableNode;
import twaver.TWaverConst;
import twaver.Link;
import java.awt.*;
import java.util.List;

/**
 * Created by Administrator on 2017-02-28.
 */
public class BreakerA  extends Breaker {
    public BreakerA() {
        super.setImage(null);
        init();
    }

    public String getUIClassID() {
        return BreakerAUI.class.getName();
    }

}
