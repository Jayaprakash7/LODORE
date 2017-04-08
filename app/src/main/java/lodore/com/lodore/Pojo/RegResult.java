package lodore.com.lodore.Pojo;

import java.util.List;

/**
 * Created by w7 on 05-Apr-17.
 */

public class RegResult {

    private String password;
    private String _id;
    private String email;
    private String username;
    private String mobile;
    private String another_mobile;
    private List<Object> documents = null;

    public String getPassword() {
        return password;
    }

    public void setPassword(String id) {
        this.password = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {this.mobile = mobile;}

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
    }

    public String getAnotherMobile() {
        return another_mobile;
    }

    public void setAnotherMobile(String anotherMobile) {
        this.another_mobile = anotherMobile;
    }

    public List<Object> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Object> documents) {
        this.documents = documents;
    }

}

