package com.common.library.widget;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.common.library.R;
import com.common.library.resource.ResourceUtil;
/**
 * @Description:  上面图片下面是文字 view封装
 * @Author: zhouguizhi
 * @CreateDate: 2020/12/30 下午9:48
 * @Version: 1.0
 */
public class UpImageDownTextView  extends LinearLayout {
    private View rootView;
    private AppCompatImageView ivImage;
    private AppCompatTextView  tvText;
    private ConstraintLayout clRoot;

    private Drawable topDrawable;
    private String downText;
    private int downTextColor;
    private int downTextSize;
    private static final int defaultDownTextColor = ResourceUtil.getColor(R.color.color_333333);
    private static final int defaultBgColor = ResourceUtil.getColor(R.color.white);
    private static final int defaultUpImageToTopMargin = 20;
    private static final int defaultUpTextToImageMargin = 20;
    private static final int defaultUpTextToBottomMargin = 20;
    private static final int defaultDownTextSize = 18;
    private static final int defaultItemWidth = -2;
    private static final int defaultItemHeight = -2;

    private OnRootViewClickListener onRootViewClickListener;
    private int itemWidth;
    private int itemHeight;
    private int bgColor;
    private int upImageToTopMargin;
    private int upTextToImageMargin;
    private int upTextToBottomMargin;


    public UpImageDownTextView(Context context) {
        this(context,null);
    }

    public UpImageDownTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }
    public UpImageDownTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
        tvText.setTextColor(downTextColor);
        tvText.setTextSize(downTextSize);
        tvText.setText(downText);
        clRoot.setBackgroundColor(bgColor);
        setRootLayoutParam();
        if(null!=topDrawable){
            ivImage.setImageDrawable(topDrawable);
        }
        setImageLayoutParam();
        setImageViewLayoutParam();
        setTextViewLayoutParam();
    }
    public void setTextViewLayoutParam(){
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) tvText.getLayoutParams();
        layoutParams.topMargin = upTextToImageMargin;
        layoutParams.bottomMargin = upTextToBottomMargin;
        Log.e("zhouguizhi","upTextToBottomMargin:="+upTextToBottomMargin);
        tvText.setLayoutParams(layoutParams);
    }
    public void setImageViewLayoutParam(){
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) ivImage.getLayoutParams();
        layoutParams.topMargin = upImageToTopMargin;
        ivImage.setLayoutParams(layoutParams);
    }
    public void setImageLayoutParam(){
        if(null==topDrawable){
            return;
        }
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) ivImage.getLayoutParams();
        layoutParams.width = topDrawable.getMinimumWidth();
        layoutParams.height = topDrawable.getMinimumHeight();
        ivImage.setLayoutParams(layoutParams);
    }
    private void setRootLayoutParam() {
        LayoutParams layoutParams = (LayoutParams) clRoot.getLayoutParams();
        layoutParams.width = itemWidth;
        layoutParams.height = itemHeight;
        clRoot.setLayoutParams(layoutParams);
    }
    private void initListener() {
        clRoot.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(null!=onRootViewClickListener){
                    onRootViewClickListener.onRootViewClickListener();
                }
            }
        });
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        if(null==context||attrs==null){
            return;
        }
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.UpImageDownTextView);
        topDrawable = typedArray.getDrawable(R.styleable.UpImageDownTextView_up_top_drawable);
        downText = typedArray.getString(R.styleable.UpImageDownTextView_down_text);
        downTextColor = typedArray.getColor(R.styleable.UpImageDownTextView_down_text_color,defaultDownTextColor);
        downTextSize = typedArray.getInteger(R.styleable.UpImageDownTextView_down_text_size,defaultDownTextSize);
        itemWidth = typedArray.getInteger(R.styleable.UpImageDownTextView_up_item_width,defaultItemWidth);
        itemHeight = typedArray.getInteger(R.styleable.UpImageDownTextView_up_item_height,defaultItemHeight);
        bgColor = typedArray.getColor(R.styleable.UpImageDownTextView_up_bg_color,defaultBgColor);
        upImageToTopMargin = typedArray.getInteger(R.styleable.UpImageDownTextView_up_image_to_top_margin,defaultUpImageToTopMargin);
        upTextToImageMargin = typedArray.getInteger(R.styleable.UpImageDownTextView_up_text_to_image_margin,defaultUpTextToImageMargin);
        upTextToBottomMargin = typedArray.getInteger(R.styleable.UpImageDownTextView_up_text_to_bottom_margin,defaultUpTextToBottomMargin);
        typedArray.recycle();
    }
    private void initView() {
        ivImage = rootView.findViewById(R.id.layout_item_up_image_down_text_iv_image);
        tvText = rootView.findViewById(R.id.layout_item_up_image_down_text_tv_text);
        clRoot = rootView.findViewById(R.id.layout_item_up_image_down_text_cl_root);
    }
    public void initLayout(){
        rootView = LayoutInflater.from(getContext()).inflate(R.layout.layout_item_up_image_down_text,this,true);
    }
    public interface OnRootViewClickListener{
        void onRootViewClickListener();
    }
    public void setOnRootViewClickListener(OnRootViewClickListener onRootViewClickListener) {
        this.onRootViewClickListener = onRootViewClickListener;
    }
}
