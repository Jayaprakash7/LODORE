package lodore.com.lodore.Pojo;

import java.util.List;

public class BlogResponse {
    private String status;
    private String message;
    private List<BlogResult> blogs;

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

    public List<BlogResult> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<BlogResult> blogs) {
        this.blogs = blogs;
    }
}
