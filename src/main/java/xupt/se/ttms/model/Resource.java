package xupt.se.ttms.model;

public class Resource {
    private Integer res_id;

    private String res_parent;

    private String res_name;

    private String res_URL;

    @Override
    public String toString() {
        return "Resource{" +
                "res_id=" + res_id +
                ", res_parent='" + res_parent + '\'' +
                ", res_name='" + res_name + '\'' +
                ", res_URL='" + res_URL + '\'' +
                '}';
    }

    public void setRes_id(Integer res_id) {
        this.res_id = res_id;
    }

    public void setRes_parent(String res_parent) {
        this.res_parent = res_parent;
    }

    public void setRes_name(String res_name) {
        this.res_name = res_name;
    }

    public void setRes_URL(String res_URL) {
        this.res_URL = res_URL;
    }

    public Integer getRes_id() {
        return res_id;
    }

    public String getRes_parent() {
        return res_parent;
    }

    public String getRes_name() {
        return res_name;
    }

    public String getRes_URL() {
        return res_URL;
    }

    public Resource() {
    }

    public Resource(Integer res_id, String res_parent, String res_name, String res_URL) {
        this.res_id = res_id;
        this.res_parent = res_parent;
        this.res_name = res_name;
        this.res_URL = res_URL;
    }
}
