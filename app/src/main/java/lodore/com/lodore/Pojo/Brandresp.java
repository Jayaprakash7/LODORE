package lodore.com.lodore.Pojo;

import java.util.List;

import javax.xml.transform.Result;

/**
 * Created by w7 on 07-Apr-17.
 */

public class Brandresp {

        private String status;
        private String message;
        private List<BrandResult> result = null;

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

        public List<BrandResult> getBrandResult() {
            return result;
        }

        public void setBrandResult(List<BrandResult> result) {
            this.result = result;
        }

}
