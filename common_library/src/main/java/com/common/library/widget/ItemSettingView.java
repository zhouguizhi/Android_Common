package com.common.library.widget;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.common.library.R;
import com.common.library.resource.ResourceUtil;
/**
 * @Description: 用于像设置界面那种view的封装
 * @Author: zhouguizhi
 * @CreateDate: 2020/12/30 上午10:43
 * @Version: 1.0
 */
public class ItemSettingView extends LinearLayout {
    private static final int defaultLeftTitleColor = ResourceUtil.getColor(R.color.colorAccent);
    private static final int defaultRightTitleColor = ResourceUtil.getColor(R.color.colorAccent);
    private static final int defaultBgColor = ResourceUtil.getColor(R.color.white);
    private static final int defaultLeftSubTitleColor = ResourceUtil.getColor(R.color.colorAccent);
    private static final int defaultRightSubTitleColor =  ResourceUtil.getColor(R.color.colorAccent);
    private static final int defaultBottomLineColor = ResourceUtil.getColor(R.color.color_333333);
    //左侧标题离父view左侧的距离
    private static final int defaultLeftToLeftMargin = 20;
    private static final int defaultRightTitleToRightMargin = 20;
    private static final int defaultRightSubTitleSize = 16;
    private static final int defaultLeftSubTitleSize = 16;
    private static final int defaultLeftTitleSize = 20;
    private static final int defaultItemViewHeight = 80;
    private static final int defaultRightTitleSize = 16;
    private View rootView;
    private String leftTitle;
    private int leftTitleColor;
    private int leftTitleSize;
    private int bgColor;
    private Drawable rightArrowDrawable;
    private int itemHeight;
    private String rightTitle;
    private int rightTitleColor;
    private int rightTitleSize;
    private String leftSubTitle;
    private int leftSubTitleColor;
    private int leftSubTitleSize;
    private String rightSubTitle;
    private int rightSubTitleColor;
    private int rightSubTitleSize;
    private boolean isShowBottomLine;

    private TextView   tvLeftTitle;
    private TextView   tvLeftSubTitle;
    private ImageView  ivRightArrow;
    private TextView   tvRightTitle;
    private TextView   tvRightSubTitle;
    private ConstraintLayout clRoot;
    private View       vLine;
    private int leftTitleToLeftMargin;
    //右侧的>离右边多少距离
    private int arrowToRightMargin ;
    private OnRootClickListener onRootClickListener;

    public ItemSettingView(Context context) {
        this(context,null);
    }
    public ItemSettingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }
    public ItemSettingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        if(null==context||attrs==null){
            return;
        }
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ItemSettingView);
        leftTitle = typedArray.getString(R.styleable.ItemSettingView_item_setting_leftTitle);
        leftTitleColor = typedArray.getColor(R.styleable.ItemSettingView_item_setting_leftTitleColor, defaultLeftTitleColor);
        leftTitleSize = typedArray.getInteger(R.styleable.ItemSettingView_item_setting_leftTitleSize, defaultLeftTitleSize);


        leftSubTitle = typedArray.getString(R.styleable.ItemSettingView_item_setting_leftSubTitle);
        leftSubTitleColor = typedArray.getColor(R.styleable.ItemSettingView_item_setting_leftSubTitleColor, defaultLeftSubTitleColor);
        leftSubTitleSize = typedArray.getInteger(R.styleable.ItemSettingView_item_setting_leftSubTitleSize, defaultLeftSubTitleSize);


        rightTitle = typedArray.getString(R.styleable.ItemSettingView_item_setting_rightTitle);
        rightTitleColor = typedArray.getColor(R.styleable.ItemSettingView_item_setting_rightTitleColor, defaultRightTitleColor);
        rightTitleSize = typedArray.getInteger(R.styleable.ItemSettingView_item_setting_rightTitleSize, defaultRightTitleSize);


        rightSubTitle = typedArray.getString(R.styleable.ItemSettingView_item_setting_rightSubTitle);
        rightSubTitleColor = typedArray.getColor(R.styleable.ItemSettingView_item_setting_rightSubTitleColor, defaultRightSubTitleColor);
        rightSubTitleSize = typedArray.getInteger(R.styleable.ItemSettingView_item_setting_rightSubTitleSize, defaultRightSubTitleSize);


        bgColor = typedArray.getColor(R.styleable.ItemSettingView_item_setting_bg_color,defaultBgColor);
        rightArrowDrawable = typedArray.getDrawable(R.styleable.ItemSettingView_item_setting_rightArrowDrawable);
        itemHeight = typedArray.getInteger(R.styleable.ItemSettingView_item_setting_height,defaultItemViewHeight);
        isShowBottomLine = typedArray.getBoolean(R.styleable.ItemSettingView_item_setting_isShowBottomLine,false);
        rightSubTitleColor = typedArray.getColor(R.styleable.ItemSettingView_item_setting_bottom_line_color, defaultBottomLineColor);
        leftTitleToLeftMargin = typedArray.getInteger(R.styleable.ItemSettingView_item_setting_leftTitleToLeftMargin, defaultLeftToLeftMargin);
        arrowToRightMargin = typedArray.getInteger(R.styleable.ItemSettingView_item_setting_rightTitleToRightMargin, defaultRightTitleToRightMargin);
        typedArray.recycle();
    }
    public void init(Context context,AttributeSet attrs){
        initLayout();
        initView();
        initAttrs(context,attrs);
        setAttrsToView();
        initListener();
    }
    public void initListener(){
        clRoot.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(null!=onRootClickListener){
                    onRootClickListener.onRootClickListener();
                }
            }
        });
    }
    private void initView() {
        if(null==rootView){
            return;
        }
        tvLeftTitle = rootView.findViewById(R.id.layout_item_setting_view_tv_left_title);
        tvLeftSubTitle = rootView.findViewById(R.id.layout_item_setting_view_tv_left_sub_title);
        ivRightArrow = rootView.findViewById(R.id.layout_item_setting_view_iv_right_arrow);
        tvRightTitle = rootView.findViewById(R.id.layout_item_setting_view_tv_right_title);
        tvRightSubTitle = rootView.findViewById(R.id.layout_item_setting_view_tv_right_sub_title);
        clRoot = rootView.findViewById(R.id.layout_item_setting_view_cl_root);
        vLine = rootView.findViewById(R.id.layout_item_setting_view_v_line);
    }
    private void setAttrsToView() {
        clRoot.setBackgroundColor(bgColor);
        tvLeftTitle.setText(leftTitle);
        tvLeftSubTitle.setText(leftSubTitle);
        tvRightTitle.setText(rightTitle);
        tvRightSubTitle.setText(rightSubTitle);
        tvLeftTitle.setTextColor(leftTitleColor);
        tvLeftSubTitle.setTextColor(leftSubTitleColor);
        tvRightTitle.setTextColor(rightTitleColor);
        tvRightSubTitle.setTextColor(rightSubTitleColor);
        LayoutParams layoutParams = (LayoutParams) clRoot.getLayoutParams();
        layoutParams.width = LayoutParams.MATCH_PARENT;
        layoutParams.height = itemHeight;
        clRoot.setLayoutParams(layoutParams);
        vLine.setVisibility(isShowBottomLine?View.VISIBLE:View.GONE);
        vLine.setBackgroundColor(defaultBottomLineColor);
        setBottomLineLayoutParam();
        setLeftTitleLayoutParam();
        tvLeftTitle.getLayoutParams();
    }
    public void initLayout(){
        rootView = LayoutInflater.from(getContext()).inflate(R.layout.layout_item_setting_view,this,true);
    }
    public void setBottomLineLayoutParam(){
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) vLine.getLayoutParams();
        layoutParams.width = LayoutParams.MATCH_PARENT;
        layoutParams.height = 1;
        vLine.setLayoutParams(layoutParams);
    }
    public void setLeftTitleLayoutParam(){
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) tvLeftTitle.getLayoutParams();
        layoutParams.leftMargin = leftTitleToLeftMargin;
        tvLeftTitle.setLayoutParams(layoutParams);
    }
    public interface OnRootClickListener{
        void onRootClickListener();
    }
    public void setOnRootClickListener(OnRootClickListener onRootClickListener) {
        this.onRootClickListener = onRootClickListener;
    }

    public void setLeftTitle(String str){
        tvLeftTitle.setText(str);
    }
    public void setLeftSubTitle(String str){
        tvLeftSubTitle.setText(str);
    }
    public void setRightTitle(String str){
        tvRightTitle.setText(str);
    }
    public void setRightSubTitle(String str){
        tvRightSubTitle.setText(str);
    }
    public void setLeftTitleColor(@Nullable int color){
        tvLeftTitle.setTextColor(color);
    }
    public void setLeftSubTitleColor(@Nullable int color){
        tvLeftSubTitle.setTextColor(color);
    }

    public void setRightTitleColor(@Nullable int color){
        tvRightTitle.setTextColor(color);
    }
    public void setRightSubTitleColor(@Nullable int color){
        tvRightSubTitle.setTextColor(color);
    }
}
