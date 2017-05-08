package Utils;

import Control.Switch;
import twaver.Element;

/**
 * Created by user on 2017/5/3.
 */
public class Data {
    public Switch aSwitch;
    public Element root;
    public Element to;
    public Double distance;
    public Double power;
    public Double reward = 0.0d;
//    public int totalPower = 0;
    public Data(Double distance,Double power,Switch aSwitch,Element root,Element to){
        this.aSwitch = aSwitch;
        this.root =  root;
        this.distance = distance;
        this.power = power;
        this.to = to;
    }
}
