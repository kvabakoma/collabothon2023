package com.tigerchamp.collabothon2023backend.init;

import com.mongodb.client.gridfs.model.GridFSFile;
import com.tigerchamp.collabothon2023backend.model.entity.User;
import com.tigerchamp.collabothon2023backend.service.BankService;
import com.tigerchamp.collabothon2023backend.service.MongoGridFsService;
import com.tigerchamp.collabothon2023backend.service.RoleService;
import com.tigerchamp.collabothon2023backend.service.UserService;
import io.jsonwebtoken.lang.Strings;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.*;

@Profile("!test")
@Component
public class DataInit implements CommandLineRunner {
    private final UserService userService;
    private final RoleService roleService;
    private final BankService bankService;
    private final MongoGridFsService mongoGridFsService;

    @Autowired
    public DataInit(UserService userService, RoleService roleService, BankService bankService, MongoGridFsService mongoGridFsService) {
        this.userService = userService;
        this.roleService = roleService;
        this.bankService = bankService;
        this.mongoGridFsService = mongoGridFsService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.roleService.rolesInit();
        this.userService.usersInit();
        this.bankService.bankInit();
        getGridFsFile();
    }

    private void testGetById() {
        User user = userService.findByUsername("pesho");
        if (user != null) {
            user = userService.findById("65337b2384ec7c3ace5df11f");
        }
        System.out.println(user.getUsername());
    }

    private void testGridFs() throws IOException {
        String femalePhoto = "users/christopher-campbell-rDEOVtE7vOs-unsplash.jpg";
        InputStream inputStream = getFileAsIOStream(femalePhoto);
        String originalFileName = femalePhoto.substring(femalePhoto.indexOf('/') + 1);
        ObjectId id = mongoGridFsService.storeDocumentAsImage(inputStream, originalFileName);
        System.out.println(id);
    }

    private void getGridFsFile() {
        String fileId = "6534340784f3085de3d89f5b";
        File targetFile = new File("/files/" + fileId + ".jpg");
        try (InputStream inputStream = new FileInputStream(mongoGridFsService.getFileById(fileId));
             OutputStream outStream = new FileOutputStream(targetFile);) {
            byte[] buffer = new byte[8 * 1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private InputStream getFileAsIOStream(final String fileName)
    {
        InputStream ioStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream(fileName);

        if (ioStream == null) {
            throw new IllegalArgumentException(fileName + " is not found");
        }
        return ioStream;
    }
}
