package com.example.employeemanagementsystem.Service;

import com.example.employeemanagementsystem.model.Employee;
import com.example.employeemanagementsystem.model.EmployeeDocument;
import com.example.employeemanagementsystem.repo.EmployeeDocumentRepository;
import com.example.employeemanagementsystem.repo.EmployeeRepository;
import com.example.employeemanagementsystem.resonse.ApiResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {

    @Autowired
    EmployeeDocumentRepository employeeDocumentRepo;

    @Autowired
    EmployeeRepository employeeRepository;

    public ApiResponse uploadService(int id, List<MultipartFile> files) {

        Employee employee = employeeRepository.findById(id).orElse(null);

        if (employee == null) {
            return ApiResponse.builder().code("1005").errors(Map.of("error", "employee not found")).build();
        }

        List<EmployeeDocument> employeeDocuments = new ArrayList<>();
        for (MultipartFile file : files) {

            if (file.isEmpty()) {
                return ApiResponse.builder().code("1005").errors(Map.of("error", "upload an file")).build();
            }

            try {
                byte[] bytes = file.getBytes();
                Path path = Paths.get("/home/bassure/file-uploads", id+file.getOriginalFilename());
                Files.write(path, bytes);
                employeeDocuments.add(new EmployeeDocument(0, path.toString(), file.getName(), employee));

            } catch (IOException e) {
                e.printStackTrace();
                return ApiResponse.builder().code("1005").errors(Map.of("error", "error uploading file try again later")).build();
            }
        }

        employeeDocumentRepo.saveAll(employeeDocuments);
        return ApiResponse.builder().code("1000").value("files uploaded successfully").build();

    }

}
