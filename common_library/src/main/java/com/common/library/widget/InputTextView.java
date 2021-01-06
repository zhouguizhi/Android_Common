package com.common.library.widget;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.common.library.R;
import com.common.library.resource.ResourceUtil;
/**
 * @Description: 手机号输入 姓名等控件封装
 * @Author: zhouguizhi
 * @CreateDate: 2020/12/30 下午5:00
 * @Version: 1.0
 */
public class InputTextView extends LinearLayout {
    private static final int defaultBottomLineColor = ResourceUtil.getColor(R.color.white);
    private static final int defaultHintTextColor = ResourceUtil.getColor(R.color.white);
    private static final int defaultBgColor = ResourceUtil.getColor(R.color.white);
    private static final int defaultRightTextColor = ResourceUtil.getColor(R.color.color_333333);
    private static final int defaultLeftTextColor = ResourceUtil.getColor(R.color.color_333333);
    private static final int defaultItemHeight = 90;
    private static final int defaultLeftTextToMargin = 20;
    private static final int defaultRightTextToMargin = 20;
    private static final int defaultContentToLeftMargin = 20;
    private static final int defaultContentTextSize = 16;

    private View rootView;
    private AppCompatTextView tvLeftTitle;
    private AppCompatTextView tvRightTitle;
    private AppCompatEditText etContent;
    private View vLine;
    private ConstraintLayout clRoot;

    private String leftText;
    private int leftTextColor;
    private String rightText;
    private int rightTextColor;
    private int bgColor;
    private boolean isShowBottomLine;
    private int bottomLineColor;
    private int itemHeight;
    private String hintText;
    private int hintTextColor;
    private int leftTextToMargin;

    private OnInputRightClickListener onInputRightClickListener;
    private int rightTextToMargin;
    private int contentToLeftMargin;
    private int contentTextSize;


    public InputTextView(Context context) {
        this(context,null);
    }
    public InputTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }
    public InputTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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

    private void initListener() {
        tvRightTitle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(null!=onInputRightClickListener){
                    onInputRightClickListener.onInputRightClickListener();
                }
            }
        });
    }
    private void setAttrsToView() {
        tvLeftTitle.setTextColor(leftTextColor);
        tvLeftTitle.setText(leftText);
        tvRightTitle.setText(rightText);
        tvRightTitle.setTextColor(rightTextColor);
        clRoot.setBackgroundColor(bgColor);
        vLine.setBackgroundColor(bottomLineColor);
        vLine.setVisibility(isShowBottomLine?View.VISIBLE:View.GONE);
        etContent.setHint(hintText);
        etContent.setHintTextColor(hintTextColor);
        etContent.setTextSize(contentTextSize);
        setRootLayoutParam();
        setLeftLayoutParam();
        setRightLayoutParam();
        setContentLayoutParam();
    }
    private void setRootLayoutParam() {
        LayoutParams layoutParams = (LayoutParams) clRoot.getLayoutParams();
        layoutParams.width = LayoutParams.MATCH_PARENT;
        layoutParams.height = itemHeight;
        clRoot.setLayoutParams(layoutParams);
    }
    public void setContentLayoutParam(){
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) etContent.getLayoutParams();
        layoutParams.leftMargin = contentToLeftMargin;
        etContent.setLayoutParams(layoutParams);
    }
    public void setRightLayoutParam(){
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) tvRightTitle.getLayoutParams();
        layoutParams.rightMargin = rightTextToMargin;
        tvRightTitle.setLayoutParams(layoutParams);
    }
    private void setLeftLayoutParam() {
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) tvLeftTitle.getLayoutParams();
        layoutParams.leftMargin = leftTextToMargin;
        tvLeftTitle.setLayoutParams(layoutParams);
    }
    private void initAttrs(Context context, AttributeSet attrs) {
        if(null==context||attrs==null){
            return;
        }
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.InputTextView);
        leftText = typedArray.getString(R.styleable.InputTextView_input_left_text);
        leftTextColor = typedArray.getColor(R.styleable.InputTextView_input_left_text_color,defaultLeftTextColor);
        rightText = typedArray.getString(R.styleable.InputTextView_input_right_text);
        rightTextColor = typedArray.getColor(R.styleable.InputTextView_input_right_text_color,defaultRightTextColor);
        bgColor = typedArray.getColor(R.styleable.InputTextView_input_bg_color,defaultBgColor);
        isShowBottomLine = typedArray.getBoolean(R.styleable.InputTextView_input_isShowBottomLine,true);
        bottomLineColor = typedArray.getColor(R.styleable.InputTextView_input_bottom_line_color,defaultBottomLineColor);
        itemHeight = typedArray.getInteger(R.styleable.InputTextView_input_item_height,defaultItemHeight);
        hintText = typedArray.getString(R.styleable.InputTextView_input_content_hint_text);
        hintTextColor = typedArray.getColor(R.styleable.InputTextView_input_content_hint_text_color,defaultHintTextColor);
        contentTextSize = typedArray.getInteger(R.styleable.InputTextView_input_content_hint_text_size,defaultContentTextSize);
        leftTextToMargin = typedArray.getInteger(R.styleable.InputTextView_input_left_text_to_margin,defaultLeftTextToMargin);
        rightTextToMargin = typedArray.getInteger(R.styleable.InputTextView_input_right_text_to_margin,defaultRightTextToMargin);
        contentToLeftMargin = typedArray.getInteger(R.styleable.InputTextView_input_content_to_left_margin,defaultContentToLeftMargin);


        typedArray.recycle();
    }

    private void initView() {
        tvLeftTitle = rootView.findViewById(R.id.layout_item_input_view_tv_left_title);
        tvRightTitle = rootView.findViewById(R.id.layout_item_input_view_tv_right_title);
        etContent = rootView.findViewById(R.id.layout_item_input_view_et_content);
        vLine = rootView.findViewById(R.id.layout_item_input_view_v_line);
        clRoot = rootView.findViewById(R.id.layout_item_input_view_cl_root);
    }

    private void initLayout() {
        rootView = LayoutInflater.from(getContext()).inflate(R.layout.layout_item_input_view,this,true);
    }
    public interface OnInputRightClickListener{
        void onInputRightClickListener();
    }
    public void setOnInputRightClickListener(OnInputRightClickListener onInputRightClickListener) {
        this.onInputRightClickListener = onInputRightClickListener;
    }
}
