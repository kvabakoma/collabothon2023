package com.tigerchamp.collabothon2023backend.service.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.tigerchamp.collabothon2023backend.exception.InvalidFileException;
import com.tigerchamp.collabothon2023backend.service.MongoGridFsService;
import com.tigerchamp.collabothon2023backend.utils.Utils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import java.io.*;

import static com.tigerchamp.collabothon2023backend.model.Constants.*;

@Service
public class MongoGridFsServiceImpl implements MongoGridFsService {
    private final GridFsTemplate gridFsTemplate;

    @Autowired
    public MongoGridFsServiceImpl(final GridFsTemplate gridFsTemplate) {
        this.gridFsTemplate = gridFsTemplate;
    }

    @Override
    public ObjectId storeDocumentAsImage(InputStream file, String fileName) {
        DBObject metaData = new BasicDBObject();
        metaData.put(FILE_NAME, fileName);
        return gridFsTemplate.store(file, fileName, APPLICATION_JPG, metaData);
    }

    @Override
    public File getFileById(String fileId) throws IOException {
        GridFSFile gridFsFile = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(fileId)));
        if (gridFsFile != null) {
            File tempFile = Utils.createTempFilePrefixed(fileId);
            try (InputStream inputStream = gridFsTemplate.getResource(gridFsFile.getFilename()).getInputStream();
                 OutputStream outStream = new FileOutputStream(tempFile);) {
                byte[] buffer = new byte[8 * 1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outStream.write(buffer, 0, bytesRead);
                }
                return tempFile;
            } catch (IOException e) {
                throw new InvalidFileException(e.getMessage());
            }
        }
        return null;
    }

    @Override
    public ObjectId storeDocumentAsZip(InputStream file, String fileName) {
        DBObject metaData = new BasicDBObject();
        metaData.put(FILE_NAME, fileName);
        return gridFsTemplate.store(file, fileName, APPLICATION_ZIP, metaData);
    }
}
