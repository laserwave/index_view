package cn.zhikaizhang.indexviewdemo.demo2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.zhikaizhang.indexview.Binder;
import cn.zhikaizhang.indexview.IndexView;
import cn.zhikaizhang.indexview.PinyinComparator;
import cn.zhikaizhang.indexviewdemo.R;

public class Demo2 extends Activity {

    private ListView listView;
    private IndexView indexView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo2);

        List<Item> items = getData();
        Collections.sort(items, new PinyinComparator<Item>() {
            @Override
            public int compare(Item s1, Item s2) {
                return compare(s1.getUserName(), s2.getUserName());
            }
        });

        listView = (ListView)findViewById(R.id.listview);
        listView.setAdapter(new MyAdapter(this, items));

        indexView = (IndexView) findViewById(R.id.indexView);

        Binder binder = new Binder(listView, indexView) {
            @Override
            public String getListItemKey(int position) {
                return ((Item)(listView.getAdapter().getItem(position))).getUserName();
            }
        };
        binder.bind();
    }

    private List<Item> getData() {

        List<Item> items = new ArrayList<>();
        items.add(new Item("路平"));
        items.add(new Item("苏唐"));
        items.add(new Item("莫林"));
        items.add(new Item("燕西凡"));
        items.add(new Item("郭有道"));
        items.add(new Item("楚敏"));
        items.add(new Item("子牧"));
        items.add(new Item("秦桑"));
        items.add(new Item("秦琪"));
        items.add(new Item("冷休谈"));
        items.add(new Item("燕秋辞"));
        items.add(new Item("昭音初"));
        items.add(new Item("吕沉风"));
        items.add(new Item("林天表"));
        items.add(new Item("方倚注"));
        items.add(new Item("唐小妹"));
        items.add(new Item("孙迎升"));
        items.add(new Item("孙送招"));
        items.add(new Item("查梦良"));
        items.add(new Item("秦越"));
        items.add(new Item("严歌"));
        items.add(new Item("徐迈"));
        items.add(new Item("詹仁"));
        items.add(new Item("许唯风"));
        items.add(new Item("宋远"));
        items.add(new Item("李遥天"));
        items.add(new Item("陈楚"));
        items.add(new Item("霍英"));
        items.add(new Item("程落烛"));
        items.add(new Item("郭无术"));
        items.add(new Item("阮青竹"));
        items.add(new Item("严鸣"));
        return items;
    }

}
