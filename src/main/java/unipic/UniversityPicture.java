package unipic;

import java.io.IOException;
import java.text.ParseException;

import data.FileConstants;
import data.PicturesManipulation;

public class UniversityPicture {
    public static void main(String[] args) throws IOException, ParseException {
        System.out.println("Hello World!");
        PicturesManipulation manipulation = new PicturesManipulation(FileConstants.PATH);
        System.out.println("Number of Images Read is " + manipulation.manipulatePictures());
    }
}
