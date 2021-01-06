package com.common.library.widget;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.common.library.R;
import com.common.library.resource.ResourceUtil;
/**
 * @Description: 标题栏封装
 * @Author: zhouguizhi
 * @CreateDate: 2020/12/30 下午3:19
 * @Version: 1.0
 */
public class TitleView extends LinearLayout {
    private static final int defaultCenterTextColor = ResourceUtil.getColor(R.color.color_333333);
    private static final int defaultRightTextColor = ResourceUtil.getColor(R.color.color_333333);
    private static final int defaultBgColor =  ResourceUtil.getColor(R.color.white);
    private static final int defaultBottomLineColor = ResourceUtil.getColor(R.color.color_333333);
    private static final int defaultItemHeight = 90;

    private View             rootView;
    private View             vLine;
    private ConstraintLayout clRoot;
    private RelativeLayout   rlLeft;
    private RelativeLayout   rlRight;
    private TextView         tvTitle;
    private ImageView        ivLeft;
    private TextView         tvRightTitle;

    private OnLeftTitleClickListener  onLeftTitleClickListener;
    private OnRightTitleClickListener onRightTitleClickListener;

    private String centerText;
    private int titleCenterTextColor;
    private String rightText;
    private int rightTextColor;
    private int bgColor;
    private boolean isShowBottomLine;
    private int bottomLineColor;
    private int itemHeight;

    public TitleView(Context context) {
        this(context,null);
    }
    public TitleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TitleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }
    public void init(Context context, @Nullable AttributeSet attrs){
        initLayout();
        initView();
        initAttrs(context,attrs);
        setAttrsToView();
        initListener();
    }
    private void setAttrsToView() {
        tvTitle.setTextColor(titleCenterTextColor);
        tvTitle.setText(centerText);
        tvRightTitle.setText(rightText);
        clRoot.setBackgroundColor(bgColor);
        vLine.setBackgroundColor(bottomLineColor);
        vLine.setVisibility(isShowBottomLine?View.VISIBLE:View.GONE);
        setRootLayoutParam();
    }

    private void setRootLayoutParam() {
        LayoutParams layoutParams = (LayoutParams) clRoot.getLayoutParams();
        layoutParams.width = LayoutParams.MATCH_PARENT;
        layoutParams.height = itemHeight;
        clRoot.setLayoutParams(layoutParams);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        if(null==context||attrs==null){
            return;
        }
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleView);
        centerText = typedArray.getString(R.styleable.TitleView_title_center_text);
        titleCenterTextColor = typedArray.getColor(R.styleable.TitleView_title_center_text_color,defaultCenterTextColor);
        rightText = typedArray.getString(R.styleable.TitleView_title_right_text);
        rightTextColor = typedArray.getColor(R.styleable.TitleView_title_right_text_color,defaultRightTextColor);
        bgColor = typedArray.getColor(R.styleable.TitleView_title_bg_color,defaultBgColor);
        isShowBottomLine = typedArray.getBoolean(R.styleable.TitleView_title_isShowBottomLine,true);
        bottomLineColor = typedArray.getColor(R.styleable.TitleView_title_bottom_line_color,defaultBottomLineColor);
        itemHeight = typedArray.getInteger(R.styleable.TitleView_title_item_height,defaultItemHeight);
        typedArray.recycle();
    }

    private void initListener() {
        rlLeft.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(null!=onLeftTitleClickListener){
                    onLeftTitleClickListener.onLeftTitleClickListener();
                }
            }
        });
        rlRight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(null!=onRightTitleClickListener){
                    onRightTitleClickListener.onRightTitleClickListener();
                }
            }
        });
    }
    private void initView() {
        if(rootView==null){
            return;
        }
       clRoot = rootView.findViewById(R.id.layout_item_title_view_cl_root);
       rlLeft =  rootView.findViewById(R.id.layout_item_title_view_rl_left);
       tvTitle = rootView.findViewById(R.id.layout_item_title_view_tv_title);
       vLine = rootView.findViewById(R.id.layout_item_title_view_v_line);
       ivLeft = rootView.findViewById(R.id.layout_item_title_view_iv_left);
       tvRightTitle = rootView.findViewById(R.id.layout_item_title_view_tv_right_text);
       rlRight = rootView.findViewById(R.id.layout_item_title_view_rl_right);
    }
    public void initLayout(){
        rootView = LayoutInflater.from(getContext()).inflate(R.layout.layout_item_title_view,this,true);
    }
    public interface OnLeftTitleClickListener{
        void onLeftTitleClickListener();
    }
    public void setOnLeftTitleClickListener(OnLeftTitleClickListener onLeftTitleClickListener) {
        this.onLeftTitleClickListener = onLeftTitleClickListener;
    }
    public interface OnRightTitleClickListener{
        void onRightTitleClickListener();
    }
    public void setOnRightTitleClickListener(OnRightTitleClickListener onRightTitleClickListener) {
        this.onRightTitleClickListener = onRightTitleClickListener;
    }
}
