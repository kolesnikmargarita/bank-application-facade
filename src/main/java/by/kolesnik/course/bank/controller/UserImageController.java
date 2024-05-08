package by.kolesnik.course.bank.controller;

import by.kolesnik.course.bank.controller.openapi.UserImageOpenApi;
import by.kolesnik.course.bank.dto.UploadResultDto;
import by.kolesnik.course.bank.facade.UserImageFacade;
import by.kolesnik.course.bank.service.UserImageService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/users/{id}/image")
public class UserImageController implements UserImageOpenApi {

    private final UserImageFacade imageFacade;

    public UserImageController(UserImageFacade imageFacade) {
        this.imageFacade = imageFacade;
    }

    @Override
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public UploadResultDto upload(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        return imageFacade.save(id, file);
    }

    @GetMapping
    public ResponseEntity<Resource> find(@PathVariable Long id) {
        final Resource resource = imageFacade.findByUserId(id);

        final String header = "attachment;filename=\"" + resource.getFilename() + "\"";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, header)
                .body(resource);
    }
}
