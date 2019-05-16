package katech.fragments.depth_3_main.navigation;

/**
 * Created by bjkim on 2017-07-23.
 */
public class NavigationItem {

    private String title;
    private String address;

    public NavigationItem(String title, String address) {
        this.title = title;
        this.address = address;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
