package soexample.umeng.com.zonghelianxi.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import soexample.umeng.com.zonghelianxi.R;
import soexample.umeng.com.zonghelianxi.bean.LeftBean;

/**
 * date:2018/12/20
 * author:冯泽林(asus)
 * function:
 */
public class LeftAdapter extends RecyclerView.Adapter<LeftAdapter.ViewHolde> {
    private Context context;
    private List<LeftBean.DataBean> list;

    public LeftAdapter(Context context, List<LeftBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolde onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(context,R.layout.left_item,null);
        ViewHolde holde = new ViewHolde(view);
        return holde;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolde holder, final int position) {
        holder.tx_left.setText(list.get(position).getName());
        holder.tx_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onItemlient!=null){
                    onItemlient.OnItemCheck(view,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolde extends RecyclerView.ViewHolder {
        private TextView tx_left;
        public ViewHolde(View itemView) {
            super(itemView);
            tx_left=itemView.findViewById(R.id.tx_left);
        }
    }
    public interface OnItemClient{
        void OnItemCheck(View itemview,int position);
    }
    private OnItemClient onItemlient;

    public void setOnItemlient(OnItemClient onItemlient) {
        this.onItemlient = onItemlient;
    }
}
