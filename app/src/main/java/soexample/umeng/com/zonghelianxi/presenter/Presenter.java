package soexample.umeng.com.zonghelianxi.presenter;

import android.util.Log;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import soexample.umeng.com.zonghelianxi.bean.LeftBean;
import soexample.umeng.com.zonghelianxi.bean.PagerBean;
import soexample.umeng.com.zonghelianxi.bean.RecyclerBean;
import soexample.umeng.com.zonghelianxi.bean.RightBean;
import soexample.umeng.com.zonghelianxi.inter.ICallBack;
import soexample.umeng.com.zonghelianxi.model.Model;
import soexample.umeng.com.zonghelianxi.view.IView;

/**
 * date:2018/12/20
 * author:冯泽林(asus)
 * function:
 */
public class Presenter {
    private Model model;
    private IView iv;

    public void attch(IView iv) {
        this.iv = iv;
        model = new Model();
    }

    //    轮播
    public void pager(String url) {
        Type type = new TypeToken<PagerBean>() {
        }.getType();
        model.get(url, new ICallBack() {
            @Override
            public void Onsuccess(Object o) {
                PagerBean bean = (PagerBean) o;
                if (bean != null) {
                    List<PagerBean.DataBean> data = bean.getData();
                    Log.i("12432545", "success: " + data.size());
                    iv.Success(data);
                }
            }

            @Override
            public void OnFaidel(Exception e) {
                iv.Failed(e);
            }
        }, type);
    }

    //    商品展示数据
    public void recyc(String url) {
        Type type = new TypeToken<RecyclerBean>() {
        }.getType();
        model.get(url, new ICallBack() {
            @Override
            public void Onsuccess(Object o) {
                RecyclerBean bean = (RecyclerBean) o;
                if (bean != null) {
                    List<RecyclerBean.DataBean> data = bean.getData();
                    iv.Successone(data);
                }
            }

            @Override
            public void OnFaidel(Exception e) {
                iv.Failedone(e);
            }
        }, type);
    }

    //    Left分类数据
    public void getLeft(String url) {
        Type type = new TypeToken<LeftBean>() {
        }.getType();
        model.get(url, new ICallBack() {
            @Override
            public void Onsuccess(Object o) {
                LeftBean bean = (LeftBean) o;
                if (bean != null) {
                    List<LeftBean.DataBean> data = bean.getData();
                    iv.LeftSuccess(data);
                }
            }

            @Override
            public void OnFaidel(Exception e) {
                iv.RightFailed(e);
            }
        }, type);
    }

    //    Right分类数据
    public void getRight(String url) {
        Type type = new TypeToken<RightBean>() {
        }.getType();
        model.get(url, new ICallBack() {
            @Override
            public void Onsuccess(Object o) {
                RightBean bean = (RightBean) o;
                if (bean != null) {
                    List<RightBean.DataBean> data = bean.getData();
                    iv.RightSuccess(data);
                }
            }

            @Override
            public void OnFaidel(Exception e) {
                iv.LeftFailed(e);
            }
        }, type);
    }

    //    解绑
    public void delete() {
        if (iv != null) {
            iv = null;
        }
    }
}
