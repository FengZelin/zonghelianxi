package soexample.umeng.com.zonghelianxi.activity;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import soexample.umeng.com.zonghelianxi.R;

/**
 * date:2018/12/20
 * author:冯泽林(asus)
 * function:
 */
public class MySubAddView extends LinearLayout implements View.OnClickListener {
    private TextView sub_view,num_view,add_view;
    private int number=0;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
        num_view.setText(number+"");
    }

    public MySubAddView(Context context) {
        this(context,null);
    }

    public MySubAddView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View view=View.inflate(context,R.layout.sub_add_view,this);
        sub_view=view.findViewById(R.id.sub_view);
        num_view=view.findViewById(R.id.num_view);
        add_view=view.findViewById(R.id.add_view);

        sub_view.setOnClickListener(this);
        add_view.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sub_view:
                if(number>1){
                    --number;
                    num_view.setText(number+"");
                    if(mOnNumberChecked!=null){
                        mOnNumberChecked.onchecked(number);
                    }
                }else{
                    Toast.makeText(getContext(),"兄弟，不能再少了！，再少就赔本了",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.add_view:
                ++number;
                num_view.setText(number+"");
                if(mOnNumberChecked!=null){
                    mOnNumberChecked.onchecked(number);
                }
                break;
        }
    }

    public interface OnNumberChecked{
        void onchecked(int num);
    };
    private OnNumberChecked mOnNumberChecked;

    public void setOnNumberChecked(OnNumberChecked onNumberChecked) {
        mOnNumberChecked = onNumberChecked;
    }
}
