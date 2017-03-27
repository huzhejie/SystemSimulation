package View;

import twaver.*;
import twaver.network.NetworkToolBarFactory;

import java.awt.*;

/**
 * Created by user on 2017/3/27.
 */
public class LookPanel extends DrawPanel {
    //定义编辑工具栏中需要的用到的功能
    private String ids[] = new String[]{
            "Selection",
            "LazyMove",
            "Magnifier",
            "Pan",
            "$SEPARATOR",
            "ZoomIn",
            "ZoomOut",
            "ZoomReset",
            "ZoomToOverview",
            "ZoomToRectangle",
            "$SEPARATOR",//分隔符
            "Undo",
            "Redo",
            "$SEPARATOR",
    };

    public LookPanel(TDataBox dataBox) {
        super(dataBox);
        this.setToolbar(NetworkToolBarFactory.getToolBar(ids, this));
    }
}

