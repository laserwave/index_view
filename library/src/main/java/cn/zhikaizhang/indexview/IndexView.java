package cn.zhikaizhang.indexview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.zhikaizhang.library.R;

public class IndexView extends TextView {

    private static final String INDEXES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ#";

    private int selectIndex = 0;

    private float heightOccupy;

    private int indexTextColor;

    private int selectIndexTextColor;

    private int selectIndexBgColor;

    private float indexTextSize;

    private float indexTextSizeScale;

    private float singleIndexHeight;

    private Drawable tipBg;

    private int tipTextColor;

    private Paint indexTextPaint = new Paint();

    private Paint selectIndexTextPaint = new Paint();

    private Paint selectIndexBgPaint = new Paint();

    private int width;

    private int height;

    private final int DEFAULT_WIDTH = Util.dp2px(getContext(), 25);

    private static final int TIP_SHOW_TIME = 1000;

    private TextView tip;

    private WindowManager windowManager;

    private boolean tipVisible = false;

    private OnIndexChangeListener onIndexChangeListener;

    private Runnable hideTipRunnable;

    public IndexView(Context context, AttributeSet attrs, int defStyle) {

        super(context, attrs, defStyle);
        /**
         * read attributes from xml
         */
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.IndexView, defStyle, 0);
        heightOccupy = typedArray.getFloat(R.styleable.IndexView_heightOccupy, 1.0f);
        indexTextColor = typedArray.getColor(R.styleable.IndexView_indexTextColor, Color.rgb(32, 32, 32));
        selectIndexTextColor = typedArray.getColor(R.styleable.IndexView_selectIndexTextColor, Color.WHITE);
        selectIndexBgColor = typedArray.getColor(R.styleable.IndexView_selectIndexBgColor, Color.rgb(124, 180, 246));
        indexTextSizeScale = typedArray.getFloat(R.styleable.IndexView_indexTextSizeScale, 0.65f);
        tipBg = typedArray.getDrawable(R.styleable.IndexView_tipBg);
        tipTextColor = typedArray.getColor(R.styleable.IndexView_tipTextColor, Color.WHITE);
        typedArray.recycle();
        /**
         * initial the TextView to show the tip
         */
        tip = new TextView(context);
        tip.setLayoutParams(new ViewGroup.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        tip.setTextSize(Util.dp2px(context, 16));
        tip.setTextColor(tipTextColor);
        tip.setBackground(tipBg);
        tip.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.width = Util.dp2px(context, 80);
        params.height = Util.dp2px(context, 80);
        params.type = WindowManager.LayoutParams.TYPE_APPLICATION;
        params.flags =  WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
        params.format = PixelFormat.TRANSLUCENT;
        tip.setVisibility(INVISIBLE);
        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.addView(tip, params);
        hideTipRunnable = new Runnable() {
            @Override
            public void run() {
                if (tipVisible) {
                    tipVisible = false;
                    tip.setVisibility(INVISIBLE);
                }
            }
        };
        /**
         *  initial paints
         */
        indexTextPaint.setAntiAlias(true);
        indexTextPaint.setStyle(Paint.Style.STROKE);
        indexTextPaint.setColor(indexTextColor);

        selectIndexTextPaint.setAntiAlias(true);
        selectIndexTextPaint.setStyle(Paint.Style.STROKE);
        selectIndexTextPaint.setColor(selectIndexTextColor);
        selectIndexTextPaint.setFakeBoldText(true);

        selectIndexBgPaint.setAntiAlias(true);
        selectIndexBgPaint.setStyle(Paint.Style.FILL);
        selectIndexBgPaint.setColor(selectIndexBgColor);
    }

    public IndexView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IndexView(Context context) {
        this(context, null);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        if(widthMode != MeasureSpec.EXACTLY){
            width = Math.min(DEFAULT_WIDTH, width);
        }
        singleIndexHeight = height * heightOccupy / INDEXES.length();
        indexTextSize = Math.min(singleIndexHeight, width) * indexTextSizeScale;
        indexTextPaint.setTextSize(indexTextSize);
        selectIndexTextPaint.setTextSize(indexTextSize);
        setMeasuredDimension(MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        float singleIndexWidth = getMeasuredWidth();
        for (int i = 0; i < INDEXES.length(); i++) {

            float x = (singleIndexWidth - indexTextPaint.measureText(INDEXES, i, i + 1)) / 2;
            float baseline = (1 - heightOccupy) / 2.0f * height + i * singleIndexHeight + Util.getBaseline(0, singleIndexHeight, indexTextPaint);

            if(i == selectIndex){
                float left = 0.1f * singleIndexWidth;
                float top = (1 - heightOccupy) / 2.0f * height + i * singleIndexHeight + 0.05f * singleIndexHeight;
                float right = 0.9f * singleIndexWidth;
                float bottom = (1 - heightOccupy) / 2.0f * height + i * singleIndexHeight + 0.95f * singleIndexHeight;
                canvas.drawRoundRect(new RectF(left, top, right, bottom), 5, 5, selectIndexBgPaint);
                canvas.drawText(String.valueOf(INDEXES.charAt(i)), x, baseline, selectIndexTextPaint);
            }else{
                canvas.drawText(String.valueOf(INDEXES.charAt(i)), x, baseline, indexTextPaint);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (tipVisible) {
                    this.removeCallbacks(hideTipRunnable);
                }
                break;
            case MotionEvent.ACTION_UP:
                this.postDelayed(hideTipRunnable, TIP_SHOW_TIME);
                break;
        }
        int newSelectIndex = getSelectByY(y);
        if (selectIndex != newSelectIndex && onIndexChangeListener != null) {
            onIndexChangeListener.OnIndexChange(newSelectIndex, INDEXES.charAt(newSelectIndex));
            tip.setText(String.valueOf(INDEXES.charAt(newSelectIndex)));
            if (!tipVisible) {
                tipVisible = true;
                tip.setVisibility(VISIBLE);
            }
        }

        return true;
    }

    private int getSelectByY(float y) {
        int select = (int) ((y - (1 - heightOccupy) / 2.0f * height) / singleIndexHeight);
        if(select < 0)
            return 0;
        if(select > INDEXES.length() - 1){
            return INDEXES.length() - 1;
        }
        return select;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (tipVisible) {
            tipVisible = false;
            windowManager.removeViewImmediate(tip);
        }
    }

    protected interface OnIndexChangeListener {
        void OnIndexChange(int select, char index);
    }

    protected void setOnIndexChangeListener(OnIndexChangeListener onIndexChangeListener) {
        this.onIndexChangeListener = onIndexChangeListener;
    }

    protected void setIndex(char index){
        if(index >= 'A' && index <= 'Z'){
            selectIndex = index - 'A';
        }else if(index == '#'){
            selectIndex = index - 'Z' + 1;
        }
        invalidate();
    }

    protected void setSelectIndex(int selectIndex){
        this.selectIndex = selectIndex;
        invalidate();
    }

}