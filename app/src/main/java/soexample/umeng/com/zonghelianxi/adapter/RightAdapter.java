package soexample.umeng.com.zonghelianxi.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.List;

import soexample.umeng.com.zonghelianxi.R;
import soexample.umeng.com.zonghelianxi.bean.LeftBean;
import soexample.umeng.com.zonghelianxi.bean.RecyclerBean;
import soexample.umeng.com.zonghelianxi.bean.RightBean;

/**
 * date:2018/12/20
 * author:冯泽林(asus)
 * function:
 */
public class RightAdapter extends RecyclerView.Adapter<RightAdapter.ViewHodel> {
    private Context context;
    private List<RightBean.DataBean.ListBean> list;

    public RightAdapter(Context context, List<RightBean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public ViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.right_item,null);
        ViewHodel hodel = new ViewHodel(view);
        return hodel;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodel holder, int position) {


        holder.tv_rgiht.setText(list.get(position).getName());
        Glide.with(context).load(list.get(position).getIcon()).into(holder.iv_rgiht);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHodel extends RecyclerView.ViewHolder {
        private ImageView iv_rgiht;
        private TextView tv_rgiht;
        public ViewHodel(View itemView) {
            super(itemView);
            iv_rgiht=itemView.findViewById(R.id.iv_right);
            tv_rgiht=itemView.findViewById(R.id.tv_right);
        }
    }
}
