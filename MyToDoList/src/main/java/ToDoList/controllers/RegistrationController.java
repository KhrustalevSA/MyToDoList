package ToDoList.controllers;

import ToDoList.entities.users.User;
import ToDoList.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Objects;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepository repository;



    @GetMapping("/registration")
    public String getRegistration(Model model){
        model.addAttribute("User",new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String postRegistration(User user, Model model){
        User username = repository.findByUserName(user.getUserName());

        if(username != null){
            String userExist = "user already exist";
            model.addAttribute("userExist", userExist);
            model.addAttribute("User",new User());
            return "registration";
        }

        user.setActive(true);

        if (Objects.equals(user.getUserName(), "admin")){
            user.setRole("ADMIN");
        } else {
            user.setRole("USER");
        }

        repository.save(user);
        return "redirect:/login";
    }
}
