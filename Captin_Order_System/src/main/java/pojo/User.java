package pojo;

public class User {
    private String name;
    private String pwd;
    private int pid;
    private int Role;

    public int getRole() {
        return Role;
    }

    public void setRole(int role) {
        Role = role;
    }



    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }







    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public User() {

    }
}
