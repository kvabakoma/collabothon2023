package com.tigerchamp.collabothon2023backend.utils;

import com.tigerchamp.collabothon2023backend.exception.InvalidFileException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Utils {
    public static File createTempFilePrefixed(String filePrefix) {
        try {
            return File.createTempFile(filePrefix, ".jpg");
        } catch (IOException e) {
            throw new InvalidFileException("Error during creation of temp file " + e.getMessage());
        }
    }

    public static boolean deleteTempFile(File file) {
        String fileName = file.getName();
        try {
            boolean isDeleted = Files.deleteIfExists(Path.of(file.getPath()));
            if (isDeleted) {
                return true;
            }
        } catch (IOException e) {
            throw new InvalidFileException("Error during deleting file '" + fileName + "'" + e);
        }
        return false;
    }
}
