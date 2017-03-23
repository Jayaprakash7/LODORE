package lodore.com.lodore.Pojo;

/**
 * Created by w7 on 21-Mar-17.
 */

public class Brandsdispay {

    private String name;
    private int thumbnail;

    public Brandsdispay() {
    }

    public Brandsdispay(String name, int thumbnail) {
        this.name = name;
        this.thumbnail = thumbnail;
    }

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public int getThumbnail() {return thumbnail;}

    public void setThumbnail(int thumbnail) {this.thumbnail = thumbnail;}
}
