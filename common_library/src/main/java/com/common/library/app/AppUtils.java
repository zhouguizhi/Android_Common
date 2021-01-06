package com.common.library.app;
import android.app.Application;
import java.lang.reflect.InvocationTargetException;
/**
 * @Description: 在组件化的环境下获取Application
 * @Author: zhouguizhi
 * @CreateDate: 2021/1/6 下午3:21
 * @Version: 1.0
 */
public class AppUtils {
    private static Application sApplication;
    public static Application getApplication() {
        if (sApplication == null) {
            try {
                sApplication = (Application) Class.forName("android.app.ActivityThread")
                        .getMethod("currentApplication")
                        .invoke(null, (Object[]) null);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return sApplication;
    }
}
