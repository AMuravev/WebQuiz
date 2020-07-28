package engine.controller;

import engine.annotation.RequestDto;
import engine.entiry.User;
import engine.model.UserDtoRequestModel;
import engine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/register")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public void register(@RequestDto(UserDtoRequestModel.class) User user) {
        userService.save(user);
    }
}
