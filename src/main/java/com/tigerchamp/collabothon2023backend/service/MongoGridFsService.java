package com.tigerchamp.collabothon2023backend.service;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Service
public interface MongoGridFsService {
    ObjectId storeDocumentAsImage(InputStream file, String fileName);
    File getFileById(String fileId) throws IOException;
    ObjectId storeDocumentAsZip(InputStream file, String fileName);
}
