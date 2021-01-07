package com.common;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.common.library.phone.PhoneUtil;
import com.common.library.resource.ResourceUtil;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void test(View view) {
        
        Log.e("zhouguizhi","res:="+ PhoneUtil.toConverStarInCenter("15257171435"));
    }
}