package ToDoList.entities.users;


import ToDoList.entities.to_do_list.Business;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "userinfo")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long uid;

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "active")
    private boolean active;

    @Column(name = "role")
    private String role;

    @OneToMany(mappedBy = "user")
    private List<Business> businessList;

    public User() {}

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public Long getUid() {
        return uid;
    }
    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public List<Business> getBusinessList() {
        return businessList;
    }
    public void setBusinessList(List<Business> businessList) {
        this.businessList = businessList;
    }
}
