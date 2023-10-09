package com.example.employeemanagementsystem.controller;

import com.example.employeemanagementsystem.Service.FileUploadService;
import com.example.employeemanagementsystem.resonse.ApiResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/employees")
public class FileUploadController {

    @Autowired
    FileUploadService fileUploadService;

    @PostMapping("/{id}/documents")
    public ApiResponse uploadFiles(@PathVariable("id") int id, @RequestParam("aadhar") MultipartFile aadhar , @RequestParam("pan") MultipartFile pan) {
        return fileUploadService.uploadService(id, List.of(aadhar , pan));
    }

}
