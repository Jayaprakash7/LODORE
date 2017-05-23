package lodore.com.lodore.Pojo;

import java.util.List;

/**
 * Created by win7 on 09-May-17.
 */

public class ProductDetailsResponse {
    private String status;
    private String message;
    private List<PerfumeList> perfume_list;
    private List<PerfumeDetail> perfume_details;

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

    public List<PerfumeList> getPerfume_list() {
        return perfume_list;
    }

    public void setPerfume_list(List<PerfumeList> perfume_list) {
        this.perfume_list = perfume_list;
    }

    public List<PerfumeDetail> getPerfume_details() {
        return perfume_details;
    }

    public void setPerfume_details(List<PerfumeDetail> perfume_details) {
        this.perfume_details = perfume_details;
    }
}
