package com.edti.exceltoxml.Services;

import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class PathLocatorService {
    private String path;

    public PathLocatorService() {}

    public String getPath() {
        return new File("").getAbsolutePath();
    }
}
