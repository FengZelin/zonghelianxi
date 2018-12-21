package soexample.umeng.com.zonghelianxi.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import soexample.umeng.com.zonghelianxi.bean.PagerBean;

/**
 * date:2018/12/20
 * author:冯泽林(asus)
 * function:
 */
public class MyPagerAdapter extends PagerAdapter {
    private Context context;
    private List<PagerBean.DataBean> list;

    public MyPagerAdapter(Context context, List<PagerBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(imageView.getScaleType().FIT_XY);
        PagerBean.DataBean bean = list.get(position % list.size());
        String icon = bean.getIcon();
        Glide.with(context).load(icon).into(imageView);
        container.addView(imageView);
        return imageView;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
