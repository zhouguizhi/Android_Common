package com.common.library.app;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * @Description:
 * @Author: zhouguizhi
 * @CreateDate: 2021/1/6 下午3:21
 * @Version: 1.0
 */
public class AppUtils {
    private static Application sApplication;

    /**
     * 在组件化的环境下获取Application,
     * @return 返回 Application
     */
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
    /**
     * 获取本地apk的名称
     * @return String
     */
    public static String getAppName(Context context) {
        if(context==null){
            if(getApplication()!=null){
                context = getApplication().getApplicationContext();
            }else{
                return "unKnown";
            }
        }
        try {
            PackageManager e = context.getPackageManager();
            PackageInfo packageInfo = e.getPackageInfo(context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (PackageManager.NameNotFoundException var4) {
            var4.printStackTrace();
            return "unKnown";
        }
    }
    /**
     * @param context 上下文
     * @return 返回build.gradle中对应的versionName 值
     */
    public static String getVersionName(Context context) {
        String verName = "";
        try {
            verName = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return verName;
    }

    /**
     * @param context 上下文
     * @return 返回build.gradle中对应的versionCode 值
     */
    public static int getVersionCode(Context context) {
        int versionCode = 0;
        try {
            versionCode = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }
    /**
     * 获取应用程序的包名
     * @param context 上下文
     * @return app的包名
     */
    public static  String getPackageName(Context context) {
        if(context==null){
            throw new NullPointerException("context must be past");
        }
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            return packageInfo.packageName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 是否是系统应用
     * @param context 上下文
     * @param packageName 包名
     * @return true表示是系统app false表示不是系统级的app
     */
    public static boolean isSystemApp(Context context, String packageName) {
        checkContext(context);
        if(TextUtils.isEmpty(packageName)){
            throw new NullPointerException("packageName must be past");
        }
        boolean isSystemApp = false;
        PackageManager pm = context.getPackageManager();
        try {
            ApplicationInfo applicationInfo = pm.getApplicationInfo(packageName, 0);
            if (applicationInfo != null && (applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) > 0) {
                isSystemApp = true;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            isSystemApp = false;
        }
        return isSystemApp;
    }
    /**
     * 获取app  在AndroidManifest.xml文件中声明的权限
     * @param  context 上下文
     * @return app所申请的所有权限
     */
    public static List<String> getPermissions(Context context){
        checkContext(context);
        List<String> permissions=new ArrayList<>();
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_PERMISSIONS);
            if(null!=packageInfo.requestedPermissions){
                permissions.addAll(Arrays.asList(packageInfo.requestedPermissions));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return permissions;
    }
    /**
     * 获取app 图标
     * @param context 上下文
     * @param packageName 包名
     * @return app的图标
     */
    public static Drawable getAppIcon(Context context, String packageName) {
        checkContext(context);
        PackageManager pm = context.getPackageManager();
        Drawable appIcon = null;
        try {
            ApplicationInfo applicationInfo = pm.getApplicationInfo(packageName, 0);
            appIcon = applicationInfo.loadIcon(pm);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return appIcon;
    }
    public static void checkContext(Context context){
        if(context==null){
            throw new NullPointerException("context must be past");
        }
    }
    /**
     * 启动第三方应用
     * @param context 上下文
     * @param packageName 第三方包名
     */
    public static void runThirdApp(Context context, String packageName) {
        checkContext(context);
        context.startActivity(new Intent(context.getPackageManager().getLaunchIntentForPackage(packageName)));
    }
}
