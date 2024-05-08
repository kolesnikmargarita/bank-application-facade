package by.kolesnik.course.bank.controller.openapi;

import by.kolesnik.course.bank.dto.ErrorResponse;
import by.kolesnik.course.bank.dto.UploadResultDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "Работа с аватарами пользователей", description = "Данный контроллер позволяет добавлять и получать аватары пользователей")
public interface UserImageOpenApi {

    @Operation(
            method = "POST",
            summary = "Загрузка аватарки",
            description = "Загрузить аватар пользователя размером до 10 Мбайт",
            tags = "Работа с пользователями",
            security = @SecurityRequirement(name = "Не требуется"),
            parameters = {
                    @Parameter(
                            in = ParameterIn.PATH,
                            name = "id",
                            description = "Id пользователя, для которого необходимо загрузить аватарку",
                            required = true,
                            example = "1"
                    )
            },
            requestBody = @RequestBody(
                    description = "Загружаемая аватарка",
                    required = true,
                    content = @Content(
                            mediaType = MediaType.MULTIPART_FORM_DATA_VALUE
                    )
            )
    )
    @ApiResponses({
            @ApiResponse(
                    description = "Аватарка загружена",
                    responseCode = "200",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = UploadResultDto.class)),
                            examples = @ExampleObject("""
                                    [
                                        {
                                            "name": "393e92f3-9ec9-4159-a32d-3da60e055c54.jpg",
                                            "url": null
                                        }
                                    ]
                                    """)
                    )
            ),
            @ApiResponse(
                    description = "Размер файла не соответствует требуемому",
                    responseCode = "404",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject("""
                                    [
                                        {
                                            "message": "недопустимый размер файла. Максимальный размер файла 10 Мб"
                                        }
                                    ]
                                    """)
                    )
            ),
            @ApiResponse(
                    description = "Пользователя не существует",
                    responseCode = "404",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject("""
                                    [
                                        {
                                            "message": "Пользователь с id '1' не существует"
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
    UploadResultDto upload(@PathVariable Long id, @RequestParam("file") MultipartFile file);
}
