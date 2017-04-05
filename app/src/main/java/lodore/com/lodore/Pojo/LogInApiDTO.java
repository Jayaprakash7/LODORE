package lodore.com.lodore.Pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by win7 on 05-Apr-17.
 */

public class LogInApiDTO {
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("another_mobile")
    @Expose
    private String another_mobile;

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getMobile() {
        return mobile;
    }

    public String getAnother_mobile() {
        return another_mobile;
    }
}
