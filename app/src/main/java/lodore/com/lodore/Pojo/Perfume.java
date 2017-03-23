package lodore.com.lodore.Pojo;

/**
 * Created by w7 on 21-Mar-17.
 */

public class Perfume {

    private String name;
    private int flavour;
    private int thumbnail;

    public Perfume() {
    }

    public Perfume(String name, int flavour, int thumbnail) {
        this.name = name;
        this.flavour = flavour;
        this.thumbnail = thumbnail;
    }

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public int getFlavour() {return flavour;}

    public void setFlavour(int flavour) {this.flavour = flavour;}

    public int getThumbnail() {return thumbnail;}

    public void setThumbnail(int thumbnail) {this.thumbnail = thumbnail;}
}
