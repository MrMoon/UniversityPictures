package model;

import java.util.HashMap;
import java.util.Set;

import lombok.Data;

@Data
public class Result<T> {

    private int numberOfPictures;
    private T pictures;

    public Result(int numberOfPictures, T pictures) {
        this.numberOfPictures = numberOfPictures;
        this.pictures = pictures;
    }
}
