package by.kolesnik.course.bank.controller;

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

@Tag(name = "Работа с пользователями", description = "Данный контроллер позволяет производить SRUD-операции над пользователями")
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserFacade userFacade;

    public UserController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @GetMapping
    @Operation(
            method = "GET",
            summary = "Получить список всех пользователей",
            description = "Выводит список всех пользователей, находящихся в базе",
            tags = "Работа с пользователями",
            security = @SecurityRequirement(name = "Не требуется")
    )
    @ApiResponses({
            @ApiResponse(
                    description = "Список пользователей успешно получен",
                    responseCode = "200",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = UserDto.class)),
                            examples = @ExampleObject("""
                                    [
                                        {
                                            "id": 1,
                                            "firstName": "John",
                                            "lastName": "Doe",
                                            "email": "johndoe@example.com",
                                            "phoneNumber": "8-800-555-35-35"
                                        }
                                    ]
                                    """)
                    )
            ),
            @ApiResponse(
                    description = "Внутренняя ошибка сервера",
                    responseCode = "500",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject("""
                                    [
                                        {
                                            "message": "Неизвестная ошибка сервера"
                                        }
                                    ]
                                    """)
                    )
            )
    }
    )
    public List<UserDto> findAll() {
        return userFacade.findAll();
    }

    @GetMapping("/{id}")
    public UserDto findById(@PathVariable Long id) {
        return userFacade.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto create(@RequestBody CreateUserDto dto) {
        return userFacade.createUser(dto);
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody UpdateUserDto dto) {
        return userFacade.updateUser(id, dto);
    }

    @PatchMapping("/{id}")
    public UserDto updateUserParts(@PathVariable Long id, @RequestBody UpdateUserDto dto) {
        return userFacade.updateUserPartially(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeById(@PathVariable Long id) {
        userFacade.removeById(id);
    }
}
