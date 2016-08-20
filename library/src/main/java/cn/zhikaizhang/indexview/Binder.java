package cn.zhikaizhang.indexview;

import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;

public abstract class Binder {

    private ListView listView;
    private IndexView indexView;
    private boolean flag = false;

    public Binder(ListView listView, IndexView indexView){
        this.listView = listView;
        this.indexView = indexView;
    }

    public void  bind(){
        indexView.setOnIndexChangeListener(new IndexView.OnIndexChangeListener() {
            @Override
            public void OnIndexChange(int selectIndex, char index) {
                ListAdapter adapter = listView.getAdapter();
                int pos = -1;
                for (int i = 0; i < adapter.getCount(); i++) {
                    char currentIndex = Util.getIndex(getListItemKey(i));
                    if (currentIndex == index) {
                        pos = i;
                        break;
                    }
                }
                if (pos != -1) {
                    listView.setSelection(pos);
                    flag = true;
                    indexView.setSelectIndex(selectIndex);

                }
            }
        });

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(flag){
                    flag = false;
                    return;
                }
                indexView.setIndex(Util.getIndex(getListItemKey(firstVisibleItem)));
            }

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }
        });

    }

    public abstract String getListItemKey(int position);

}
