package com.ozgur.giys.api.files.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FileInfo {

    private String name;
    private String url;
    private Long size;    
}
