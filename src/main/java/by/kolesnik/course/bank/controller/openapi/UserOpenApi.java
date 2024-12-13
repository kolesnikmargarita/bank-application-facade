package by.kolesnik.course.bank.controller.openapi;

import by.kolesnik.course.bank.dto.CreateUserDto;
import by.kolesnik.course.bank.dto.ErrorResponse;
import by.kolesnik.course.bank.dto.UpdateUserDto;
import by.kolesnik.course.bank.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Работа с пользователями", description = "Данный контроллер позволяет производить SRUD-операции над пользователями")
public interface UserOpenApi {

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
    public List<UserDto> findAll();

    @Operation(
            method = "GET",
            summary = "Получить пользователя по id",
            description = "Выводит информации о пользователе с соответствующим id",
            tags = "Работа с пользователями",
            security = @SecurityRequirement(name = "Не требуется")
    )
    @ApiResponses({
            @ApiResponse(
                    description = "Данные о пользователе успешно получены",
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
                    description = "пользователь не найден",
                    responseCode = "404",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject("""
                                    [
                                        {
                                             "message": "Пользователь с id '7' не найден"
                                         }
                                    ]
                                    """)
                    )
            )
    }
    )
    public UserDto findById(@PathVariable Long id);

    @Operation(
            method = "POST",
            summary = "Добавить пользователя",
            description = "Сохранение нового пользователя в базу данных",
            tags = "Работа с пользователями",
            security = @SecurityRequirement(name = "Не требуется")
    )
    @ApiResponses({
            @ApiResponse(
                    description = "Пользователь успешно сохранен",
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
                    description = "не верный синтаксис json",
                    responseCode = "400",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject("""
                                    [
                                        {
                                            "timestamp": "2024-05-08T18:26:41.151+00:00",
                                            "status": 400,
                                            "error": "Bad Request",
                                            "path": "/users"
                                          }
                                    ]
                                    """)
                    )
            ),
            @ApiResponse(
                    description = "не верно введены данные сохраняемого пользователя",
                    responseCode = "500",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject("""
                                    [
                                        {
                                             "timestamp": "2024-05-08T18:27:34.412+00:00",
                                             "status": 500,
                                             "error": "Internal Server Error",
                                             "path": "/users"
                                           }
                                    ]
                                    """)
                    )
                    )
    }
    )
    public UserDto create(@RequestBody CreateUserDto dto);

    @Operation(
            method = "PUT",
            summary = "Изменить информацию о пользователе",
            description = "Изменить всю информацию о пользователе, подлежащую изменению",
            tags = "Работа с пользователями",
            security = @SecurityRequirement(name = "Не требуется")
    )
    @ApiResponses({
            @ApiResponse(
                    description = "Данные успешно изменены",
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
                    description = "не верный синтаксис json",
                    responseCode = "400",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject("""
                                    [
                                        {
                                            "timestamp": "2024-05-08T18:26:41.151+00:00",
                                            "status": 400,
                                            "error": "Bad Request",
                                            "path": "/users/1"
                                          }
                                    ]
                                    """)
                    )
            ),
            @ApiResponse(
                    description = "не верно введены новые данные пользователя",
                    responseCode = "500",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject("""
                                    [
                                        {
                                             "timestamp": "2024-05-08T18:27:34.412+00:00",
                                             "status": 500,
                                             "error": "Internal Server Error",
                                             "path": "/users/1"
                                           }
                                    ]
                                    """)
                    )
            )
    }
    )
    public UserDto updateUser(@PathVariable Long id, @RequestBody UpdateUserDto dto);

    @Operation(
            method = "PATCH",
            summary = "частично изменить информацию о пользователе",
            description = "Изменить необходимую информацию о пользователе, подлежащую изменению",
            tags = "Работа с пользователями",
            security = @SecurityRequirement(name = "Не требуется")
    )
    @ApiResponses({
            @ApiResponse(
                    description = "Данные успешно изменены",
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
                    description = "не верный синтаксис json",
                    responseCode = "400",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject("""
                                    [
                                        {
                                            "timestamp": "2024-05-08T18:26:41.151+00:00",
                                            "status": 400,
                                            "error": "Bad Request",
                                            "path": "/users/1"
                                          }
                                    ]
                                    """)
                    )
            ),
            @ApiResponse(
                    description = "не верно введены новые данные пользователя",
                    responseCode = "500",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject("""
                                    [
                                        {
                                             "timestamp": "2024-05-08T18:27:34.412+00:00",
                                             "status": 500,
                                             "error": "Internal Server Error",
                                             "path": "/users/1"
                                           }
                                    ]
                                    """)
                    )
            )
    }
    )
    public UserDto updateUserParts(@PathVariable Long id, @RequestBody UpdateUserDto dto);

    @Operation(
            method = "DELETE",
            summary = "Удалить пользователя",
            description = "Удалить пользователя по id",
            tags = "Работа с пользователями",
            security = @SecurityRequirement(name = "Не требуется")
    )
    @ApiResponses({
            @ApiResponse(
                    description = "Пользователь успешно удален",
                    responseCode = "200",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = UserDto.class)),
                            examples = @ExampleObject()
                    )
            )
    }
    )
    public void removeById(@PathVariable Long id);
}
