package lodore.com.lodore.Pojo;

public class PerfumeRequest {
    private String lang;
    private String filter;
    private int _session;
    private int id_subcategory;

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public int get_session() {
        return _session;
    }

    public void set_session(int _session) {
        this._session = _session;
    }

    public int getId_subcategory() {
        return id_subcategory;
    }

    public void setId_subcategory(int id_subcategory) {
        this.id_subcategory = id_subcategory;
    }
}
