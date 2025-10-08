package com.poly.lab5.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ParamService {
    @Autowired
    HttpServletRequest req;

    public String getString(String name, String defaultValue) {
        String value = req.getParameter(name);
        return value != null ? value : defaultValue;
    }
    public int getInt(String name, int defaultValue) {
        String value = req.getParameter(name);
        try {
            return (value != null) ? Integer.parseInt(value) : defaultValue;
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
    public double getDouble(String name, double defaltValue) {
        String value = req.getParameter(name);
        try {
            return (value != null) ? Double.parseDouble(value) : defaltValue;
        } catch (NumberFormatException e) {
            return defaltValue;
        }
    }
    public boolean getBoolean(String name, boolean defaultValue) {
        String value = req.getParameter(name);
        return (value != null) ? Boolean.parseBoolean(value) : defaultValue;
    }
    public Date getDate(String name, String pattern) {
        String value = req.getParameter(name);
        if (value == null){
            return null;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            return sdf.parse(value);
        } catch (ParseException e) {
            throw new RuntimeException("Lỗi định dạng thời gian: " + e.getMessage(), e);
        }
    }
    public File save(MultipartFile file, String path) {
        if (file.isEmpty()) {
            return null;
        }
        try {
            File destinationFile = new File(path, file.getOriginalFilename());
            file.transferTo(destinationFile);
            return destinationFile;
        } catch (IOException e) {
            throw new RuntimeException("Lỗi lưu file: " + e.getMessage(), e);
        }
    }
}
