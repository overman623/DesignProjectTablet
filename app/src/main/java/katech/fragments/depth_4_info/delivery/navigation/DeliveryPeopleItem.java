package katech.fragments.depth_4_info.delivery.navigation;

public class DeliveryPeopleItem {

    private String num;
    private String name;
    private String time;
    private String address;

    public DeliveryPeopleItem(String num, String name, String time, String address) {
        this.num = num;
        this.name = name;
        this.time = time;
        this.address = address;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
