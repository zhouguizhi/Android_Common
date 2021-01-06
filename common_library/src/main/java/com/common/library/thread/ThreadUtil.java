package com.common.library.thread;
import android.os.Handler;
import android.os.Looper;
/**
 * @Description: 操作线程相关
 * @Author: zhouguizhi
 * @CreateDate: 2021/1/6 下午4:51
 * @Version: 1.0
 */
public class ThreadUtil {
    private ThreadUtil(){}

    /**
     * 判断是否运行在主线程中
     * @return true 是在主线程  false反之
     */
    public static boolean isRunMainThread(){
        return Looper.getMainLooper() == Looper.myLooper();
    }

    /**
     * 切换到主线程
     * @param runnable 执行的task
     */
    public static void transformToMainThread(Runnable runnable){
        if(null==runnable){
            return;
        }
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(runnable);
    }
}
