package Control;

import View.PowerDisCircuitBreakersUI;
import twaver.Link;
import twaver.ResizableNode;
import twaver.TWaverConst;

import java.awt.*;
import java.util.List;

/**
 * Created by Administrator on 2017-03-02.
 * 配电断路器
 */
public class PowerDisCircuitBreakers extends Breaker {
    public PowerDisCircuitBreakers() {
        super();
        init();
    }
    public String getUIClassID() {
        return PowerDisCircuitBreakersUI.class.getName();
    }
}

