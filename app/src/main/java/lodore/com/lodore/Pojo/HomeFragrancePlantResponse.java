package lodore.com.lodore.Pojo;

import java.util.List;

/**
 * Created by win7 on 11-Apr-17.
 */

public class HomeFragrancePlantResponse {
    private String status;
    private String message;
    private List<HomeFragrancePlantResult> result;

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

    public List<HomeFragrancePlantResult> getResult() {
        return result;
    }

    public void setResult(List<HomeFragrancePlantResult> result) {
        this.result = result;
    }
}
