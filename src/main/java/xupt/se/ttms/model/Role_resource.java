package xupt.se.ttms.model;

public class Role_resource {
    private Integer role_res_id;

    private Integer role_id;

    private Integer res_id;

    @Override
    public String  toString() {
        return "Role_resource{" +
                "role_res_id=" + role_res_id +
                ", role_id=" + role_id +
                ", res_id=" + res_id +
                '}';
    }

    public Integer getRole_res_id() {
        return role_res_id;
    }

    public void setRole_res_id(Integer role_res_id) {
        this.role_res_id = role_res_id;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public Integer getRes_id() {
        return res_id;
    }

    public void setRes_id(Integer res_id) {
        this.res_id = res_id;
    }

    public Role_resource() {
    }

    public Role_resource(Integer role_res_id, Integer role_id, Integer res_id) {
        this.role_res_id = role_res_id;
        this.role_id = role_id;
        this.res_id = res_id;
    }
}
