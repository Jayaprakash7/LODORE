package lodore.com.lodore.Pojo;

import java.util.List;

public class BlogResponse {
    private String status;
    private List<BlogResult> result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<BlogResult> getBlogs() {
        return result;
    }

    public void setBlogs(List<BlogResult> blogs) {
        this.result = blogs;
    }
}
