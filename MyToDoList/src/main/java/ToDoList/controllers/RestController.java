package ToDoList.controllers;

import ToDoList.entities.to_do_list.Business;
import ToDoList.entities.to_do_list.Note;
import ToDoList.entities.users.User;
import ToDoList.repositories.BusinessRepository;
import ToDoList.repositories.NoteRepository;
import ToDoList.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.script.ScriptTemplateConfig;

import java.util.List;
import java.util.Optional;

@org.springframework.web.bind.annotation.RestController

public class RestController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/getUser")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<User> getUser(){
        List<User> list = userRepository.findAll();
        return list;
    }

}
