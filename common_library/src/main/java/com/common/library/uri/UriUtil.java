package com.common.library.uri;
import android.text.TextUtils;
import java.util.regex.Pattern;
/**
 * @Description: 判断url地址的合法性
 * @Author: zhouguizhi
 * @CreateDate: 2021/1/6 下午5:17
 * @Version: 1.0
 */
public class UriUtil {
    private UriUtil(){}
    private final static Pattern PATTERN_WEB_URL = Pattern.compile("^([hH][tT]{2}[pP]://|[hH][tT]{2}[pP][sS]://)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\/])+$");

    /**
     * 判断http url是否合法
     * @param url 请求的地址
     * @return
     */
    public static boolean isWebUrl(String url) {
        if (TextUtils.isEmpty(url)) {
            return false;
        } else {
            return PATTERN_WEB_URL.matcher(url).matches();
        }
    }
}
