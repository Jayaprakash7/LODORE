package lodore.com.lodore.Pojo;

import java.util.List;

/**
 * Created by w7 on 10-Apr-17.
 */

public class BrandDetailsResponse {

    private String status;
    private String message;
    private List<BrandInfo> brandinfo = null;
    private List<BrandProducts> brandProducts = null;

    public String getStatus() {return status;}

    public void setStatus(String status) {this.status = status;}

    public String getMessage() {return message;}

    public void setMessage(String message) {this.message = message;}

    public List<BrandInfo> getBrandinfo() {return brandinfo;}

    public void setBrandinfo(List<BrandInfo> brandinfo) {this.brandinfo = brandinfo;}

    public List<BrandProducts> getbrandProducts() {return brandProducts;}

    public void setbrandProducts(List<BrandProducts> brandProducts) {this.brandProducts = brandProducts;}
}
