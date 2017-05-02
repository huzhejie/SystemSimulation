package Control;


import View.DoubleRollChangeUI;
/**
 * Created by Administrator on 2017-02-16.
 */
public class DoubleRollChange extends RollChange {
    public DoubleRollChange() {
        super();
        init();
    }
    public String getUIClassID() {
        return DoubleRollChangeUI.class.getName();
    }
}



