package cn.zhikaizhang.indexview;

import java.util.Comparator;

public abstract class PinyinComparator<T> implements Comparator<T> {

    public abstract int compare(T s1, T s2);

    public int compare(String s1, String s2) {

        char i1 = Util.getIndex(s1);
        char i2 = Util.getIndex(s2);
        if(i1 == '#' && i2 == '#'){
            return Util.getStringForSort(s1).compareTo(Util.getStringForSort(s2));
        }else if(i1 == '#'){
            return 1;
        }else if(i2 == '#'){
            return -1;
        }else{
            return Util.getStringForSort(s1).compareTo(Util.getStringForSort(s2));
        }
    }
}
