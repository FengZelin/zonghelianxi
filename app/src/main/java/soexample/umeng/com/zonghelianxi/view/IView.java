package soexample.umeng.com.zonghelianxi.view;

import android.support.v7.widget.RecyclerView;

import java.util.List;

import soexample.umeng.com.zonghelianxi.bean.LeftBean;
import soexample.umeng.com.zonghelianxi.bean.PagerBean;
import soexample.umeng.com.zonghelianxi.bean.RecyclerBean;
import soexample.umeng.com.zonghelianxi.bean.RightBean;

/**
 * date:2018/12/20
 * author:冯泽林(asus)
 * function:
 */
public interface IView {
    void Success(List<PagerBean.DataBean> list);

    void Failed(Exception e);

    void Successone(List<RecyclerBean.DataBean> data);

    void Failedone(Exception e);

    void LeftSuccess(List<LeftBean.DataBean> bean);

    void LeftFailed(Exception e);

    void RightSuccess(List<RightBean.DataBean> bean);

    void RightFailed(Exception e);
}
