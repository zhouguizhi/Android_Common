package com.common;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.common.library.thread.ThreadUtil;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void test(View view) {
        ThreadUtil.transformToMainThread(new Runnable() {
            @Override
            public void run() {

            }
        });
        Log.e("zhouguizhi","线程:="+ ThreadUtil.isRunMainThread());
//        AppUtils.runThirdApp(null,"com.xqxc.customer");

    }
}