package cn.zhikaizhang.indexviewdemo.demo1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cn.zhikaizhang.indexviewdemo.R;

public class MyAdapter extends BaseAdapter {


    private Context context;

    private List<Item> list;

    public MyAdapter(Context context, List<Item> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        Item item = list.get(position);
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, null);
            viewHolder.songTextView = (TextView) convertView.findViewById(R.id.song);
            viewHolder.singerTextView = (TextView) convertView.findViewById(R.id.singer);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.songTextView.setText(item.getSong());
        viewHolder.singerTextView.setText(item.getSinger());
        return convertView;
    }

    class ViewHolder {
        TextView songTextView;
        TextView singerTextView;
    }
}
