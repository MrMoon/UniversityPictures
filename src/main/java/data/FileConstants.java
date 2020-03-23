package data;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class FileConstants {
    public static final String PATH = "D:\\MyDocs\\University\\Temp";
    public static final String DATE_FORMAT = "dd-MM-yyyy hh:mm:ss";
    public static final String NAN = "NoSubject";
    public static final HashMap<Integer, String> courses = new HashMap<>();

    public static void init() {
        fillCourses();
        createCourses();
    }

    @NotNull
    public static String getPath(String subjectName) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("D:\\MyDocs\\University\\");
        stringBuilder.append(subjectName);
        stringBuilder.append("\\");
        stringBuilder.append("Pictures\\");
        return stringBuilder.toString();
    }

    public static void fillCourses() {
        courses.put(9, "Digital Logic Design");
        courses.put(11, "Statistics");
        courses.put(12, "Technical Writing");
        courses.put(13, "C#");
    }

    public static void createCourses() {
        courses.forEach((integer, s) -> {
            Path newFolder = Paths.get(getPath(s) + '\\');
            if (!Files.exists(newFolder)) {
                try {
                    Files.createDirectory(newFolder);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
