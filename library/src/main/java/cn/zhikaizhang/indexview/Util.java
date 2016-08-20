package cn.zhikaizhang.indexview;

import android.content.Context;
import android.graphics.Paint;
import android.util.TypedValue;

import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;

public class Util {

    /**
     * convert dp to px
     */
    public static int dp2px(Context context, float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }


    /**
     * get the baseline to draw text between top and bottom
     */
    public static float getBaseline(float top, float bottom, Paint paint){
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        return (top + bottom - fontMetrics.bottom - fontMetrics.top) / 2;
    }

    /**
     * convert chinese characters to pinyin for sorting
     */
    public static final String getStringForSort(String src){
        try{
            String s = PinyinHelper.convertToPinyinString(src, "", PinyinFormat.WITHOUT_TONE).toUpperCase();
            return s;
        }catch(Exception e){
            return src;
        }
    }

    /**
     * get the index of a string
     */
    public static final char getIndex(String src){
        String res = src;
        try{
            res = PinyinHelper.convertToPinyinString(src, "", PinyinFormat.WITHOUT_TONE).toUpperCase();
        }catch(Exception e){
        }
        if(res.charAt(0) >= 'A' && res.charAt(0) <= 'Z'){
            return res.charAt(0);
        }else{
            return '#';
        }
    }
}
