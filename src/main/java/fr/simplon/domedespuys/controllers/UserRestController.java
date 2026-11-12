package fr.simplon.domedespuys.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import fr.simplon.domedespuys.entities.User;
import fr.simplon.domedespuys.services.UserServiceImpl;
import fr.simplon.domedespuys.views.UserView;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserRestController {

    private UserServiceImpl userService;


//    @GetMapping
//    @JsonView(UserView.UserListView.class)
//    public List<User> getUsers() {
//        return userRepository.findAll();
//    }

    @GetMapping("/{id}")
    @JsonView(UserView.UserDetailView.class)
    public User getUser(@PathVariable String id) {
        return userService.getById(id);
    }
}
