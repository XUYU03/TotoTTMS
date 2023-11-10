package xupt.se.ttms.model;

public class Usr_Role {
    private Integer usrRoleId;
    private Integer empId;
    private Integer roleId;

    public Usr_Role() {
    }

    public Usr_Role(Integer usrRoleId, Integer empId, Integer roleId) {
        this.usrRoleId = usrRoleId;
        this.empId = empId;
        this.roleId = roleId;
    }

    /**
     * 获取
     * @return usrRoleId
     */
    public Integer getUsrRoleId() {
        return usrRoleId;
    }

    /**
     * 设置
     * @param usrRoleId
     */
    public void setUsrRoleId(Integer usrRoleId) {
        this.usrRoleId = usrRoleId;
    }

    /**
     * 获取
     * @return empId
     */
    public Integer getEmpId() {
        return empId;
    }

    /**
     * 设置
     * @param empId
     */
    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    /**
     * 获取
     * @return roleId
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * 设置
     * @param roleId
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String toString() {
        return "Usr_Role{usrRoleId = " + usrRoleId + ", empId = " + empId + ", roleId = " + roleId + "}";
    }
}

