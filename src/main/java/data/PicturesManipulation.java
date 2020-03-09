package data;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.text.ParseException;

/**
 * Main Class For moving the picture
 */
public class PicturesManipulation {

    private String path;
    private ReadFolder readFolder = new ReadFolder();

    public PicturesManipulation(@NotNull String path) {
        this.path = path;
    }

    /**
     * Move Pictures to it's file
     */
    public int manipulatePictures() throws IOException, ParseException {
        return readFolder.manipulate(path).size();
    }
}
