package Control;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by huzhejie on 2017/2/15.
 */
public class ComponentIndexSet {
    private Set<String> set = new HashSet<>();
    public ComponentIndexSet(){
        //添加元件目录
        set.add("开关类");
//        set.add("卷变类");
        set.add("断路器类");
        set.add("PT类");
        set.add("站用变类");
        set.add("避雷器类");
        set.add("电源装置类");
        set.add("其他类");

    }

    public Set<String> getSet() {
        return set;
    }

    public void setSet(Set<String> set) {
        this.set = set;
    }
}
