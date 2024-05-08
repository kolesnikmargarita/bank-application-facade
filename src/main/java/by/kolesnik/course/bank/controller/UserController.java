package by.kolesnik.course.bank.controller;

import by.kolesnik.course.bank.controller.openapi.UserOpenApi;
import by.kolesnik.course.bank.dto.CreateUserDto;
import by.kolesnik.course.bank.dto.ErrorResponse;
import by.kolesnik.course.bank.dto.UpdateUserDto;
import by.kolesnik.course.bank.dto.UserDto;
import by.kolesnik.course.bank.facade.UserFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController implements UserOpenApi {

    private final UserFacade userFacade;

    public UserController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @Override
    @GetMapping
    public List<UserDto> findAll() {
        return userFacade.findAll();
    }

    @Override
    @GetMapping("/{id}")
    public UserDto findById(@PathVariable Long id) {
        return userFacade.findById(id);
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto create(@RequestBody CreateUserDto dto) {
        return userFacade.createUser(dto);
    }

    @Override
    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody UpdateUserDto dto) {
        return userFacade.updateUser(id, dto);
    }

    @Override
    @PatchMapping("/{id}")
    public UserDto updateUserParts(@PathVariable Long id, @RequestBody UpdateUserDto dto) {
        return userFacade.updateUserPartially(id, dto);
    }

    @Override
    @DeleteMapping("/{id}")
    //@ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeById(@PathVariable Long id) {
        userFacade.removeById(id);
    }
}
