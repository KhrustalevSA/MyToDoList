package ToDoList.entities.to_do_list;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @Column(name = "note")
    private String note;

    @ManyToOne
    @JoinColumn(name="business_id")
    private Business business;

    public Note() {
    }

    public Note(String note) {
        this.note = note;
    }

    public Note(String note, Business business) {
        this.note = note;
        this.business = business;
    }

    public Long getUid() {
        return uid;
    }
    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }

    public Business getBusiness() {
        return business;
    }
    public void setBusiness(Business business) {
        this.business = business;
    }
}
