package com.TDD.demo.controller;

import com.TDD.demo.service.GetPdfFromIntraAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class GetPdfFromIntraAppController {

    @Autowired
    private GetPdfFromIntraAppService getPdfFromIntraAppService;
    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadPdf(@PathVariable Long id) {
        // Call the service to retrieve the PDF file content
        byte[] pdfContent = getPdfFromIntraAppService.getPdfFileFromIntraApp(id);

        if (pdfContent != null) {
            // Create the response headers
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=downloaded.pdf");
            headers.add(HttpHeaders.CONTENT_TYPE, "application/pdf");

            // Return the PDF content as a ResponseEntity
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(pdfContent);
        } else {
            // Handle the case when the PDF content is not found
            // For example, return a 404 Not Found response
            return ResponseEntity.notFound().build();
        }
    }
}