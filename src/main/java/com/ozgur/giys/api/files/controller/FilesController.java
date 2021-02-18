package com.ozgur.giys.api.files.controller;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import com.ozgur.giys.api.files.message.ResponseMessage;
import com.ozgur.giys.api.files.model.FileInfo;
import com.ozgur.giys.api.files.service.FileStorageServiceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

@RestController
@RequestMapping(path = "/api/files")
public class FilesController {

    @Autowired
    FileStorageServiceImp fileStorageService;

    @PostMapping(path = "/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestPart("file") MultipartFile file) {

        String message = "";
        try {
            this.fileStorageService.save(file);
            message = "Uploaded file successfully " + file.getOriginalFilename();
            return ResponseEntity.ok(ResponseMessage.builder().message(message).build());
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("")
    public ResponseEntity<List<FileInfo>> getAllFiles() {

        List<FileInfo> files = this.fileStorageService.loadAll().map(path -> {
            String fileName = path.getFileName().toString();
            String url = MvcUriComponentsBuilder.fromMethodName(FilesController.class, "getFile", fileName).build()
                    .toString();
            Long size = (long) 0;
            String fullPath = this.fileStorageService.getRoot().toString() + "/" + path.toString();
            try {
                File file = new File(fullPath);
                size = file.length();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new FileInfo(fileName, url, size);
        }).collect(Collectors.toList());

        return ResponseEntity.ok(files);

    }

    @GetMapping("{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {

        Resource file = this.fileStorageService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);

    }

}
