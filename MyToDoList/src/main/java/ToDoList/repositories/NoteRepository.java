package ToDoList.repositories;

import ToDoList.entities.to_do_list.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}
