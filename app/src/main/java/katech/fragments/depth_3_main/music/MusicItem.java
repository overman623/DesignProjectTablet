package katech.fragments.depth_3_main.music;

/**
 * Created by bjkim on 2017-07-22.
 */
public class MusicItem {

    private int num;
    private String title;
    private String singer;

    public MusicItem(int num, String title, String singer) {
        this.num = num;
        this.title = title;
        this.singer = singer;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }
}
