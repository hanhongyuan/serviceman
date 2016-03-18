package com.dstvdm.uploader;

/**
 * Created by paul on 2016/03/18.
 */

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/files")
public class FileUploadController {

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public List<String> provideUploadInfo(Model model) {
        File rootFolder = new File(UploaderApplication.ROOT);
        List<String> fileNames = Arrays.stream(rootFolder.listFiles())
                .map(f -> f.getName())
                .collect(Collectors.toList());

        model.addAttribute("files",
                Arrays.stream(rootFolder.listFiles())
                        .sorted(Comparator.comparingLong(f -> -1 * f.lastModified()))
                        .map(f -> f.getName())
                        .collect(Collectors.toList())
        );

        return fileNames;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/upload")
    public String handleFileUpload(@RequestParam("name") String name,
                                   @RequestParam("file") MultipartFile file) {
        if (name.contains("/")) {
            return "Folder separators not allowed";
        }
        if (name.contains("/")) {
            return "Relative pathnames not allowed";
        }

        if (!file.isEmpty()) {
            try {
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(new File(UploaderApplication.ROOT + "/" + name)));
                FileCopyUtils.copy(file.getInputStream(), stream);
                stream.close();
                return "You successfully uploaded " + name + "!";
            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + name + " because the file was empty";
        }
    }

    @RequestMapping(value = "/downloadFile", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> getFile(@RequestParam("filename") String name) {
        try {
            File fileToDownload = new File(UploaderApplication.ROOT + "/" + name);
            // InputStream inputStream = new FileInputStream(fileToDownload);

            HttpHeaders header = new HttpHeaders();
            header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            header.set("Content-Disposition", "attachment; filename=" + name);
            header.setContentLength(fileToDownload.length());

            InputStreamResource isr = new InputStreamResource(new FileInputStream(fileToDownload));
            return new ResponseEntity<InputStreamResource>(isr, header, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();

        }

        return null;
    }

}