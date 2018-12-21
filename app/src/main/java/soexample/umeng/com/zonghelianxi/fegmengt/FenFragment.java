package soexample.umeng.com.zonghelianxi.fegmengt;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import soexample.umeng.com.zonghelianxi.R;
import soexample.umeng.com.zonghelianxi.activity.Erweima;
import soexample.umeng.com.zonghelianxi.adapter.LeftAdapter;
import soexample.umeng.com.zonghelianxi.adapter.RightAdapter;
import soexample.umeng.com.zonghelianxi.bean.LeftBean;
import soexample.umeng.com.zonghelianxi.bean.PagerBean;
import soexample.umeng.com.zonghelianxi.bean.RecyclerBean;
import soexample.umeng.com.zonghelianxi.bean.RightBean;
import soexample.umeng.com.zonghelianxi.presenter.Presenter;
import soexample.umeng.com.zonghelianxi.view.IView;

public class FenFragment extends Fragment implements IView {

    private RecyclerView rec_left;
    private LinearLayout right_ll;
    private List<LeftBean.DataBean> Leftlist;
    private List<RightBean.DataBean> Rightlist;
    private LeftAdapter leftadapter;
    private Presenter presenter;
    private RightAdapter rightadapter;
    String url = "http://www.zhaoapi.cn/product/getProductCatagory?cid=";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.activity_fen_fragment, null);
        initView(view);
        initData();
        initPresent();

        return view;
    }

    private void initPresent() {
        presenter = new Presenter();
        presenter.attch(this);
        presenter.getLeft("http://www.zhaoapi.cn/product/getCatagory");
    }

    private void initData() {
//        实例化
        Leftlist = new ArrayList<>();
        Rightlist = new ArrayList<>();
//        线性布局
        RecyclerView.LayoutManager manager = new GridLayoutManager(getActivity(), 1, LinearLayoutManager.VERTICAL, false);
        rec_left.setLayoutManager(manager);
//        adapter
        leftadapter = new LeftAdapter(getActivity(), Leftlist);
        leftadapter.setOnItemlient(new LeftAdapter.OnItemClient() {
            @Override
            public void OnItemCheck(View itemview, int position) {
                LeftBean.DataBean dataBean = Leftlist.get(position);
                presenter.getRight(url + dataBean.getCid());
                Log.i("Tag", dataBean.getCid() + "");
            }
        });
        rec_left.setAdapter(leftadapter);

    }

    private void initView(View view) {
        rec_left = (RecyclerView) view.findViewById(R.id.rec_left);
        right_ll = (LinearLayout) view.findViewById(R.id.right_ll);
    }

    @Override
    public void Success(List<PagerBean.DataBean> list) {

    }

    @Override
    public void Failed(Exception e) {

    }

    @Override
    public void Successone(List<RecyclerBean.DataBean> data) {

    }

    @Override
    public void Failedone(Exception e) {

    }

    @Override
    public void LeftSuccess(List<LeftBean.DataBean> bean) {
        if (bean != null) {
            Leftlist.clear();
            Leftlist.addAll(bean);
            leftadapter.notifyDataSetChanged();
        }
    }

    @Override
    public void LeftFailed(Exception e) {

    }

    @Override
    public void RightSuccess(List<RightBean.DataBean> bean) {
        if (bean != null) {
            right_ll.removeAllViews();
            for (int i = 0; i < bean.size(); i++) {
                TextView textView = new TextView(getActivity());
                textView.setText(bean.get(i).getName());
                RecyclerView recyclerView = new RecyclerView(getActivity());
                recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 5));


                rightadapter = new RightAdapter(getActivity(), bean.get(i).getList());
                recyclerView.setAdapter(rightadapter);

                Rightlist.clear();
                Rightlist.addAll(bean);
                leftadapter.notifyDataSetChanged();

                right_ll.addView(textView);
                right_ll.addView(recyclerView);

            }
        }
    }

    @Override
    public void RightFailed(Exception e) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.delete();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getRight("http://www.zhaoapi.cn/product/getProductCatagory?cid=1");
    }

}
