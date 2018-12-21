package soexample.umeng.com.zonghelianxi.fegmengt;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import soexample.umeng.com.zonghelianxi.R;
import soexample.umeng.com.zonghelianxi.ShopCarActivity;
import soexample.umeng.com.zonghelianxi.activity.Erweima;
import soexample.umeng.com.zonghelianxi.adapter.MyPagerAdapter;
import soexample.umeng.com.zonghelianxi.adapter.MyRrcyclerAdapter;
import soexample.umeng.com.zonghelianxi.bean.LeftBean;
import soexample.umeng.com.zonghelianxi.bean.PagerBean;
import soexample.umeng.com.zonghelianxi.bean.RecyclerBean;
import soexample.umeng.com.zonghelianxi.bean.RightBean;
import soexample.umeng.com.zonghelianxi.presenter.Presenter;
import soexample.umeng.com.zonghelianxi.view.IView;

public class ShowFragment extends Fragment implements IView, View.OnClickListener {
    private List<RecyclerBean.DataBean> list;
    private ViewPager pager;
    private RecyclerView recycler;
    //    handler 传送机制
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                int item = pager.getCurrentItem();
                pager.setCurrentItem(item + 1);
                removeMessages(0);
                sendEmptyMessageDelayed(0, 2000);
            }
        }
    };
    private Presenter presenter;
    private MyRrcyclerAdapter adapter;
    private ImageView saomiao;
    private EditText edit_text;
    private ImageView gaode;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.activity_show_fragment, null);
        initView(view);
        adapter.setOnitemCheckedLinter(new MyRrcyclerAdapter.OnitemCheckedLinter() {
            @Override
            public void onChecked() {
                Intent it = new Intent(getContext(), ShopCarActivity.class);
                startActivity(it);

            }
        });
        return view;
    }

    private void initView(View view) {

        pager = (ViewPager) view.findViewById(R.id.pager);
        recycler = (RecyclerView) view.findViewById(R.id.recycler);
//        轮播先给p层传过去URL
        presenter = new Presenter();
        presenter.attch(this);
        presenter.pager("http://www.zhaoapi.cn/ad/getAd");
//      recyclerview适配器
        list = new ArrayList<>();
        adapter = new MyRrcyclerAdapter(getActivity(), list);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        presenter.recyc("http://www.zhaoapi.cn/product/searchProducts?keywords=%E7%AC%94%E8%AE%B0%E6%9C%AC&page=1");
        saomiao = (ImageView) view.findViewById(R.id.saomiao);
        saomiao.setOnClickListener(this);
        edit_text = (EditText) view.findViewById(R.id.edit_text);
        edit_text.setOnClickListener(this);
        gaode = (ImageView) view.findViewById(R.id.gaode);
        gaode.setOnClickListener(this);
    }


    @Override
    public void Success(List<PagerBean.DataBean> list) {
//        轮播适配器
        MyPagerAdapter adapter = new MyPagerAdapter(getActivity(), list);
        pager.setAdapter(adapter);
        handler.sendEmptyMessageDelayed(0, 2000);
    }


    @Override
    public void Failed(Exception e) {

    }

    @Override
    public void Successone(List<RecyclerBean.DataBean> data) {
        if (data != null) {
            list.clear();
            list.addAll(data);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void Failedone(Exception e) {

    }

    @Override
    public void LeftSuccess(List<LeftBean.DataBean> bean) {

    }

    @Override
    public void LeftFailed(Exception e) {

    }

    @Override
    public void RightSuccess(List<RightBean.DataBean> bean) {

    }

    @Override
    public void RightFailed(Exception e) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.delete();
    }

    private void submit() {
        // validate
        String text = edit_text.getText().toString().trim();
        if (TextUtils.isEmpty(text)) {
            Toast.makeText(getContext(), "text不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.saomiao:
                Intent it = new Intent(getContext(), Erweima.class);
                startActivity(it);
                break;
        }
    }
}
