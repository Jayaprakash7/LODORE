package lodore.com.lodore.Pojo;

import java.util.List;

/**
 * Created by win7 on 10-May-17.
 */

public class CartResponse {
    private String status;
    private String message;
    private List<CartResult> result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<CartResult> getResult() {
        return result;
    }

    public void setResult(List<CartResult> result) {
        this.result = result;
    }
}
