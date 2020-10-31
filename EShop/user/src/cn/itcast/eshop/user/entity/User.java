package cn.itcast.eshop.user.entity;

public class User {
    /** 用户角色 普通用户 */
    public static final String NORMAL = "NORMAL";
    /** 用户角色 管理员 商家 */
    public static final String ADMIN = "ADMIN";

    private String username;

    private String password;

    private String role = "NORMAL";


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
