package soexample.umeng.com.zonghelianxi.adapter;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import soexample.umeng.com.zonghelianxi.R;
import soexample.umeng.com.zonghelianxi.bean.RecyclerBean;
import soexample.umeng.com.zonghelianxi.utils.Okhttp2utlis;

/**
 * date:2018/12/20
 * author:冯泽林(asus)
 * function:
 */
public class MyRrcyclerAdapter extends RecyclerView.Adapter<MyRrcyclerAdapter.ViewHolder> {
    private Context context;
    private List<RecyclerBean.DataBean> list;

    public MyRrcyclerAdapter(Context context, List<RecyclerBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        holder.text_view.setText(list.get(position).getTitle());
        holder.text_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnitemCheckedLinter.onChecked();
            }
        });
        String images = list.get(position).getImages();
        String[] split = images.split("\\|");
        Glide.with(context).load(Okhttp2utlis.okhtt(split[0])).into(holder.image_View);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView image_View;
        private TextView text_view;

        public ViewHolder(View itemView) {
            super(itemView);
            image_View = itemView.findViewById(R.id.iv_view);
            text_view = itemView.findViewById(R.id.text_view);
        }
    }
    public interface OnitemCheckedLinter{
        void onChecked();
    }
    private OnitemCheckedLinter mOnitemCheckedLinter;

    public void setOnitemCheckedLinter(OnitemCheckedLinter onitemCheckedLinter) {
        mOnitemCheckedLinter = onitemCheckedLinter;
    }
}
