package ToDoList.repositories;

import ToDoList.entities.to_do_list.Business;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessRepository extends JpaRepository<Business,Long> {
    Business findByBusinessName(String businessName);
}
