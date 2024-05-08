package by.kolesnik.course.bank.facade;

import by.kolesnik.course.bank.dto.UploadResultDto;
import by.kolesnik.course.bank.exception.IncorrectURLException;
import by.kolesnik.course.bank.service.UserImageService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.Path;

@Component
public class UserImageFacade {

    private final UserImageService userImageService;

    public UserImageFacade(UserImageService userImageService) {
        this.userImageService = userImageService;
    }

    public UploadResultDto save(Long id, MultipartFile file) {
        userImageService.deleteFile(id);

        final String filename = userImageService.saveFile(file);

        userImageService.updateUserImageName(id, filename);

        return new UploadResultDto(filename, null);
    }

    public Resource findByUserId(Long id) {

        return userImageService.findByUserId(id);
    }
}
