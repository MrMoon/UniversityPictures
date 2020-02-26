package model;

import java.io.File;

import lombok.Data;

@Data
public class Picture {

    private String subjectName;
    private File file;

    public Picture(String subjectName, File file) {
        this.subjectName = subjectName;
        this.file = file;
    }
}
