package transformer.demo;


import com.alibaba.fastjson.JSONObject;
import transformer.utils.TransformerVoUtils;

import java.util.Date;


public class DemoMain {
    public static void main(String[] args) {
        ValueObj valueObj = new ValueObj();
        Date date = new Date();
        valueObj.setDateA(date);
        valueObj.setDateB(date);
        valueObj.setDateC(date);
        valueObj.setState("1");
        valueObj.setStateB("0");
        TransformerVoUtils.transformerVo(valueObj);
        System.out.println(JSONObject.toJSON(valueObj));
    }
}
