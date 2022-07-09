package ToDoList.controllers;

import ToDoList.entities.to_do_list.Business;
import ToDoList.entities.to_do_list.Note;
import ToDoList.entities.users.User;
import ToDoList.repositories.BusinessRepository;
import ToDoList.repositories.NoteRepository;
import ToDoList.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
public class MainController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BusinessRepository businessRepository;
    @Autowired
    private NoteRepository noteRepository;

    private Authentication auth;

    @GetMapping("/")
    public String getHomePage(Model model) {
        auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUserName(auth.getName());
        List<Business> list = new LinkedList<>();

        list = user.getBusinessList();

        model.addAttribute("user",user);
        model.addAttribute("businessList",list);
        return "homepage";
    }

    @GetMapping("/businessAdd")
    public String businessAddGet(){
        return "business_add";
    }

    @PostMapping("/businessAdd")
    public String businessAddPost(@RequestParam(name = "businessName", required = false) String businessName, Model model){
        auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUserName(auth.getName());
        Business business = new Business();


        if (Objects.equals(businessName, "")){
            String businessNameIsEmpty = "Empty business name";
            model.addAttribute("message",businessNameIsEmpty);
            return "business_add";
        }

        business.setBusinessName(businessName);
        business.setUser(user);
        businessRepository.save(business);
        user.getBusinessList().add(business);
        userRepository.save(user);

        return "redirect:/";
    }

    @GetMapping("/noteAdd")
    public String noteAddGet(@RequestParam(name = "businessId") Long id, Model model){
        Optional<Business> businessOptional = businessRepository.findById(id);
        Business business = businessOptional.get();
        model.addAttribute("business", business);
        businessRepository.save(business);
        return "note_add";
    }

    @PostMapping("/noteAdd")
    public String noteAddPost(@RequestParam(name = "businessId") Long businessId,
                              @RequestParam(name = "note", required = false, defaultValue = "ToDo") String newNote,
                              Model model){
        auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUserName(auth.getName());
        Optional<Business> businessOptional = businessRepository.findById(businessId);
        Business business = businessOptional.get();
        Note note = new Note();


        note.setNote(newNote);
        note.setBusiness(business);
        noteRepository.save(note);
        business.getBusinessToDoList().add(note);
        model.addAttribute("business",business);
        model.addAttribute("noteRepository",noteRepository);
        businessRepository.save(business);
        userRepository.save(user);

        return "note_add";
    }

    @GetMapping("/deleteNote")
    public String deleteNote(@RequestParam(name = "noteToDelete") Long noteToDeleteId,
                             @RequestParam(name = "businessIdToDeleteNote")Long businessId,
                             Model model){
        Optional<Note> noteOptional = noteRepository.findById(noteToDeleteId);
        Note note = noteOptional.get();
        Optional<Business> businessOptional = businessRepository.findById(businessId);
        Business business = businessOptional.get();
        List<Note> list = business.getBusinessToDoList();
        list.remove(note);
        noteRepository.delete(note);
        business.setBusinessToDoList(list);
        businessRepository.save(business);

        model.addAttribute("business",business);
        model.addAttribute("noteRepository",noteRepository);
        return "note_add";
    }

}
