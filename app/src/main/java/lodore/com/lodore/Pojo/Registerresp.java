package lodore.com.lodore.Pojo;

import java.util.List;

/**
 * Created by w7 on 05-Apr-17.
 */

public class Registerresp {

    private String status;
    private String message;
    private List<RegResult> result = null;

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

    public List<RegResult> getResult() {
        return result;
    }

    public void setResult(List<RegResult> result) {
        this.result = result;
    }

}



