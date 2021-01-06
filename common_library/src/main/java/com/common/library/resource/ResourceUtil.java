package com.common.library.resource;
import androidx.annotation.ColorRes;
import com.common.library.app.AppUtils;
/**
 * @Description: ResourceUtil 获取res资源
 * @Author: zhouguizhi
 * @CreateDate: 2021/1/6 下午5:33
 * @Version: 1.0
 */
public class ResourceUtil {
    private ResourceUtil(){}

    /**
     * @param strId strings.xml资源id
     * @return id对应的字符串
     */
    public static String getString( int strId) {
        return AppUtils.getApplication().getResources().getString(strId);
    }
    public static int getColor(@ColorRes int colorId) {
        return AppUtils.getApplication().getResources().getColor(colorId);
    }
}
