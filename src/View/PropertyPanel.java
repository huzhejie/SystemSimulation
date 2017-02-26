package View;

import twaver.ElementAttribute;
import twaver.ElementAttributeVisibleFilter;
import twaver.TDataBox;
import twaver.table.TPropertySheet;

/**
 * Created by huzhejie on 2017/2/14.
 */
public class PropertyPanel extends TPropertySheet {
    public PropertyPanel(TDataBox dataBox){
        this.setDataBox(dataBox);
        this.init();
    }
    /**
     * 初始化属性面板
     */
    private void init(){
        //设置是否可以编辑
        this.setEditable(true);
        //设置属性面板显示哪些属性
        this.setElementAttributeVisibleFilter(new ElementAttributeVisibleFilter() {
            @Override
            public boolean isVisible(ElementAttribute elementAttribute) {
                switch (elementAttribute.getCategoryName()){
                    case "system.alarm":
                        return false;
                    case "system.attachment":
                        return false;
                    case "system.label":
                        return false;
                    case "system.message":
                        return false;
                    case "system.custom":
                        return false;
                    default:
                        return true;
                }
            }
        });
    }
}
