package soexample.umeng.com.zonghelianxi.activity;

import android.app.ActivityManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.RadioGroup;

import java.util.ArrayList;

import soexample.umeng.com.zonghelianxi.R;
import soexample.umeng.com.zonghelianxi.adapter.FragmentAdapter;
import soexample.umeng.com.zonghelianxi.fegmengt.FenFragment;
import soexample.umeng.com.zonghelianxi.fegmengt.ShowFragment;

public class HomeActivity extends FragmentActivity {

    private ViewPager pager;
    private RadioGroup rg_fengment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ActivityManager manager = (ActivityManager)this.getSystemService(ACTIVITY_SERVICE);
//        获取资源ID
        pager = findViewById(R.id.pager);
        rg_fengment = findViewById(R.id.rg_fragment);
//        实例化list
        ArrayList<Fragment> list = new ArrayList<>();
        list.add(new ShowFragment());
        list.add(new FenFragment());

//        Fragment适配器
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(), list);
        pager.setAdapter(adapter);
//        获取按键下标
        rg_fengment.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_show:
                        pager.setCurrentItem(0);
                        break;
                    case R.id.rb_fen:
                        pager.setCurrentItem(1);
                        break;
                }
            }
        });
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                rg_fengment.check(rg_fengment.getChildAt(position).getId());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
