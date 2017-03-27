package Control;

import View.ColumnCircuitBreakerUI;
import twaver.Link;
import twaver.ResizableNode;
import twaver.TWaverConst;

import java.awt.*;
import java.util.List;
/**
 * Created by Administrator on 2017-03-02.
 */
public class ColumnCircuitBreaker extends Breaker {
    public ColumnCircuitBreaker() {
        super();
        init();
    }
    public String getUIClassID() {
        return ColumnCircuitBreakerUI.class.getName();
    }
}

