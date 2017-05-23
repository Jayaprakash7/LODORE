package lodore.com.lodore.Pojo;

import java.util.List;

import javax.xml.transform.Result;

/**
 * Created by w7 on 07-Apr-17.
 */

public class Brandresp {

        private String status;
        private List<BrandResult> result = null;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<BrandResult> getProduct_result() {
            return result;
        }

        public void setProduct_result(List<BrandResult> result) {
            this.result = result;
        }

}
