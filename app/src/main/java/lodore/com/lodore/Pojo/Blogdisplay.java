package lodore.com.lodore.Pojo;

/**
 * Created by w7 on 21-Mar-17.
 */

public class Blogdisplay {
    private String name;
    private String discription;
    private int thumbnail;

    public Blogdisplay() {
    }

    public Blogdisplay(String name,String discription, int thumbnail) {
        this.name = name;
        this.discription = discription;
        this.thumbnail = thumbnail;
    }

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getDiscription() {return discription;}

    public void setDiscription(String discription) {this.discription = discription;}

    public int getThumbnail() {return thumbnail;}

    public void setThumbnail(int thumbnail) {this.thumbnail = thumbnail;}
}
