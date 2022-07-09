package ToDoList.entities.to_do_list;

import ToDoList.entities.users.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Business {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long uid;

    @Column(name = "businessName")
    private String businessName;

    @JsonIgnore
    @OneToMany(mappedBy = "business")
    private List<Note> businessToDoList;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="user_id")
    private User user;

    public Business() {
    }

    public Business(String businessName) {
    }

    public Business(String businessName, String businessToDo) {
        this.businessName = businessName;
    }

    public Long getUid() {
        return uid;
    }
    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getBusinessName() {
        return businessName;
    }
    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public List<Note> getBusinessToDoList() {
        return businessToDoList;
    }
    public void setBusinessToDoList(List<Note> businessToDoList) {
        this.businessToDoList = businessToDoList;
    }
}
