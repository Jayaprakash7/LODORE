package lodore.com.lodore.Pojo;

/**
 * Created by w7 on 10-Apr-17.
 */

public class BrandInfo {

    private String id;
    private String brand_pic;
    private String brand_head;
    private String description;
    private String id_category;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrandPic() {
        return brand_pic;
    }

    public void setBrandPic(String brandPic) {
        this.brand_pic = brandPic;
    }

    public String getBrandHead() {
        return brand_head;
    }

    public void setBrandHead(String brandHead) {
        this.brand_head = brandHead;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIdCategory() {
        return id_category;
    }

    public void setIdCategory(String idCategory) {
        this.id_category = idCategory;
    }
}
