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
        set.add("开关");
        set.add("卷变");
        set.add("断路器");
        set.add("PT");
        set.add("站用变");
    }

    public Set<String> getSet() {
        return set;
    }

    public void setSet(Set<String> set) {
        this.set = set;
    }
}
