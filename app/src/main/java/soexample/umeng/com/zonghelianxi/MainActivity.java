package soexample.umeng.com.zonghelianxi;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.TextView;

import soexample.umeng.com.zonghelianxi.activity.HomeActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView text_view;
    private Button but_tiao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        text_view = (TextView) findViewById(R.id.text_view);
        but_tiao = (Button) findViewById(R.id.but_tiao);
//        点击事件
        but_tiao.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_tiao:
//                属性动画
                AnimatorSet set = new AnimatorSet();
                set.playTogether(
//                        X轴转
                        ObjectAnimator.ofFloat(text_view, "rotationX", 0, 360),
                        ObjectAnimator.ofFloat(text_view, "rotationY", 0, 0),
                        ObjectAnimator.ofFloat(text_view,"rotation",0,0),
//                        变大
                        ObjectAnimator.ofFloat(text_view,"scaleX",0,3f),
                        ObjectAnimator.ofFloat(text_view,"scaleY",0,3f),
//                        透明
                        ObjectAnimator.ofFloat(text_view,"alpha",1,0.5f)
                );
//                时间
                set.setDuration(2000).start();

                set.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
//                        动画完毕后 进行跳转
                        Intent it = new Intent(MainActivity.this, HomeActivity.class);
                        startActivity(it);
                        finish();
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });
                break;
        }
    }
}
