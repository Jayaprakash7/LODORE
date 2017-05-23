package lodore.com.lodore.Pojo;

import java.util.List;

/**
 * Created by w7 on 10-Apr-17.
 */

public class BrandDetailsResponse {

    private String status;
    private String message;
    private List<BrandInfo> brand_detail = null;
    private List<BrandProducts> brandlist = null;

    public String getStatus() {return status;}

    public void setStatus(String status) {this.status = status;}

    public String getMessage() {return message;}

    public void setMessage(String message) {this.message = message;}

    public List<BrandInfo> getBrand_detail() {return brand_detail;}

    public void setBrand_detail(List<BrandInfo> brand_detail) {this.brand_detail = brand_detail;}

    public List<BrandProducts> getbrandProducts() {return brandlist;}

    public void setbrandProducts(List<BrandProducts> brandProducts) {this.brandlist = brandlist;}
}
