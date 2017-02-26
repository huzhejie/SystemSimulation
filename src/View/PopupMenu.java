package View;

import javax.swing.*;

/**
 * Created by huzhejie on 2017/2/16.
 */
public class PopupMenu extends JPopupMenu {
    //右键菜单
    private JMenuItem menuItem_1 = new JMenuItem();
    private JMenuItem menuItem_2 = new JMenuItem();
    public PopupMenu(){
        menuItem_1.setLabel("旋转90°");
        this.add(menuItem_1);
        this.addSeparator();
        this.add(menuItem_2);
    }
}
