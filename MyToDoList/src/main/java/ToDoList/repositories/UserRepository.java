package ToDoList.repositories;

import ToDoList.entities.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//    @Query("select distinct * from users where id=1")
//    User getUser(String userName);

    User findByUserName(String username);
//    User findByUserName(String username,String name);
}
