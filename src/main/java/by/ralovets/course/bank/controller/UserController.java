package by.ralovets.course.bank.controller;

import by.ralovets.course.bank.dto.CreateUserDto;
import by.ralovets.course.bank.dto.UpdateUserDto;
import by.ralovets.course.bank.dto.UserDto;
import by.ralovets.course.bank.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public UserDto findById(@PathVariable Long id) {
        return userService.findDtoById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto create(@RequestBody CreateUserDto dto) {
        return userService.createUser(dto);
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody UpdateUserDto dto) {
        return userService.updateUser(id, dto);
    }

    @PatchMapping("/{id}")
    public UserDto updateUserParts(@PathVariable Long id, @RequestBody UpdateUserDto dto) {
        return userService.updateUserPartially(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeById(@PathVariable Long id) {
        userService.removeById(id);
    }
}
