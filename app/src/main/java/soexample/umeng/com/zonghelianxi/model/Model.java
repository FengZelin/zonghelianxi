package soexample.umeng.com.zonghelianxi.model;

import java.lang.reflect.Type;

import soexample.umeng.com.zonghelianxi.inter.ICallBack;
import soexample.umeng.com.zonghelianxi.utils.HttpUtils;

/**
 * date:2018/12/19
 * author:冯泽林(asus)
 * function:
 */
public class Model {
    public void get(String url, ICallBack callBack, Type type){
        HttpUtils.getInstener().get(url,callBack,type);
    }
}
