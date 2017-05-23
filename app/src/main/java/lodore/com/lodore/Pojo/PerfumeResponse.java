package lodore.com.lodore.Pojo;


import java.util.List;

public class PerfumeResponse {
    private String status;
    private List<Product> result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Product> getProduct() {
        return result;
    }

    public void setProduct(List<Product> product) {
        this.result = result;
    }
}
