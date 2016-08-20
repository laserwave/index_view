package cn.zhikaizhang.indexviewdemo.demo1;

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

public class Demo1 extends Activity {

    private ListView listView;
    private IndexView indexView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo1);

        List<Item> items = getData();
        Collections.sort(items, new PinyinComparator<Item>() {
            @Override
            public int compare(Item s1, Item s2) {
                return compare(s1.getSong(), s2.getSong());
            }
        });

        listView = (ListView)findViewById(R.id.listview);
        listView.setAdapter(new MyAdapter(this, items));

        indexView = (IndexView) findViewById(R.id.indexView);

        Binder binder = new Binder(listView, indexView) {
            @Override
            public String getListItemKey(int position) {
                return ((Item)(listView.getAdapter().getItem(position))).getSong();
            }
        };
        binder.bind();
    }

    private List<Item> getData() {

        List<Item> items = new ArrayList<>();
        items.add(new Item("Amy Diamond", "HeartBeats"));
        items.add(new Item("Avril Lavigne", "How You Remind Me"));
        items.add(new Item("Avril Lavigne", "Innocence"));
        items.add(new Item("Avril Lavigne", "My Happy Ending"));
        items.add(new Item("Avril Lavigne", "Tik Tok"));
        items.add(new Item("Avril Lavigne", "When You're Gone"));
        items.add(new Item("Beyonce", "Halo"));
        items.add(new Item("Beyonce", "If I Were A Boy"));
        items.add(new Item("Lenka", "Blue Skies"));
        items.add(new Item("Blue", "All Rise"));
        items.add(new Item("Nightwish", "She Is My Sin"));
        items.add(new Item("P!nk", "Try"));
        items.add(new Item("Ylvis", "What Does The Fox Say"));
        items.add(new Item("Katy Perry", "Walking On Air"));
        items.add(new Item("Katy Perry", "Firework"));
        items.add(new Item("Katy Perry", "Part Of Me"));
        items.add(new Item("Katy Perry", "Roar"));
        items.add(new Item("Katy Perry", "Teenager Dream"));
        items.add(new Item("Katy Perry", "The One That Got Away"));
        items.add(new Item("Lady Gaga", "Second Time Around"));
        items.add(new Item("Lady Gaga", "Applause"));
        items.add(new Item("Lady Gaga", "Bad Romance"));
        items.add(new Item("Lady Gaga", "Judas"));
        items.add(new Item("Lady Gaga", "Telephone"));
        items.add(new Item("Lady Gaga", "Fashion!"));
        items.add(new Item("Adele", "Hello"));
        items.add(new Item("Adele", "Rolling In The Deep"));
        items.add(new Item("Adele", "Someone Like You"));
        items.add(new Item("Taylor Swift", "You Belong With Me"));
        items.add(new Item("陈奕迅", "十年"));
        items.add(new Item("陈奕迅", "红玫瑰"));
        items.add(new Item("陈奕迅", "浮夸"));
        items.add(new Item("陈奕迅", "好久不见"));
        items.add(new Item("陈奕迅", "不要说话"));
        items.add(new Item("陈奕迅", "单车"));
        items.add(new Item("陈奕迅", "爱情转移"));
        items.add(new Item("陈奕迅", "淘汰"));
        items.add(new Item("陈奕迅", "因为爱情"));
        items.add(new Item("陈奕迅", "你的背包"));
        items.add(new Item("陈奕迅", "阴天快乐"));
        items.add(new Item("范玮琪", "最初的梦想"));
        items.add(new Item("范玮琪", "是非题"));
        items.add(new Item("范玮琪", "那些花儿"));
        items.add(new Item("范玮琪", "到不了"));
        items.add(new Item("范玮琪", "黑白配"));
        items.add(new Item("范玮琪", "动物森林"));
        items.add(new Item("范玮琪", "最亲爱的你"));
        items.add(new Item("范玮琪", "悄悄告诉你"));
        items.add(new Item("_未知歌手", "_未知歌名"));
        return items;
    }

}
