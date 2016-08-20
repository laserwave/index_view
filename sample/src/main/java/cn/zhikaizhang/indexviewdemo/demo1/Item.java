package cn.zhikaizhang.indexviewdemo.demo1;

public class Item {

    private String singer;

    private String song;

    public Item() {
    }

    public Item(String singer, String song) {
        this.singer = singer;
        this.song = song;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }
}
