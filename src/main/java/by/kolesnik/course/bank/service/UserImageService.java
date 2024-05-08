package by.kolesnik.course.bank.service;

import by.kolesnik.course.bank.dto.UploadResultDto;
import by.kolesnik.course.bank.exception.CantLoadFileException;
import by.kolesnik.course.bank.exception.CantReadFileException;
import by.kolesnik.course.bank.util.FileUtil;
import by.kolesnik.course.bank.entity.User;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ServerErrorException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class UserImageService {

    private final Path path = Paths.get("file-storage");
    private final UserService userService;

    public UserImageService(UserService userService) {
        this.userService = userService;

        try {
            if (!Files.exists(path)) {
                Files.createDirectory(path);
            }
        } catch (IOException exception) {
            throw new ServerErrorException("Не удалось создать хранилище", exception);
        }
    }

    // 1. сохранить файл
    // 2. получить его имя
    // найти пользователя по id
    // сохранить имя картинки у пользователя
    // вернуть URL
    public UploadResultDto save(Long id, MultipartFile file) {
        final String filename = saveFile(file);

        updateUserImageName(id, filename);

        return new UploadResultDto(filename, null);
    }

    public Resource findByUserId(Long id) throws MalformedURLException {
        final String filename = findImageNameByUserId(id);
        final Path file = path.resolve(filename);

        Resource resource = new UrlResource(file.toUri());
        if (resource.exists() || resource.isReadable()) {
            return resource;
        } else {
            throw new CantReadFileException("Не удалось прочитать файл.");
        }
    }

    public String saveFile(MultipartFile file) {
        final String extension = FileUtil.getFileExtension(file.getOriginalFilename());
        final String filename = UUID.randomUUID() + extension;

        try {
            Files.copy(file.getInputStream(), path.resolve(filename));
        } catch (IOException exception) {
            throw new CantLoadFileException("Ошибка загрузки файла:" + exception.getMessage());
        }

        return filename;
    }

    public void updateUserImageName(Long id, String filename) {
        final User user = userService.findUserById(id);

        user.setImageName(filename);

        userService.save(user);
    }

    public String findImageNameByUserId(Long id) {
        return userService.findUserById(id).getImageName();
    }
}
