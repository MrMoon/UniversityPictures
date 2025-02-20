package data;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import model.Picture;
import model.Result;

import static data.FileConstants.DATE_FORMAT;
import static data.FileConstants.NAN;

/**
 * Main Class to Read the Folder and manipulate it
 */
class ReadFolder {
    private SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

    /**
     * Read the Main folder that has the lecture pictures manipulate them and put them in a hash map
     *
     * @param path path of the folder to get the pictures from
     * @return HashMap ( key = Subject Name , value = a set of pictures )
     * @throws IOException in case there is no file
     */
    @NotNull
    private Result<Set<Picture>> readFolder(String path) throws IOException, ParseException {
        FileConstants.init();
        HashSet<Picture> pictures = new HashSet<>();
        File folder = new File(path);
        System.out.println("Main Folder Name " + folder.getName());
        if (!folder.exists()) {
            System.out.println("No Such Folder Exists");
            return new Result<>(0, null);
        }
        for (File file : Objects.requireNonNull(folder.listFiles())) {
            BasicFileAttributes fileAttributes = Files.readAttributes(Paths.get(file.getPath()), BasicFileAttributes.class);
            String subject = getSubject(dateFormat.parse(dateFormat.format(fileAttributes.lastModifiedTime().toMillis())).toInstant().atZone(ZoneId.systemDefault()).getHour());
            Picture picture = new Picture(subject , file);
            String src = folder.getAbsolutePath() + '\\' + file.getName() , dest = FileConstants.getPath(picture.getSubjectName()) + '\\' + file.getName();
            pictures.add(picture);
            try {
                Files.move(Paths.get(src), Paths.get(dest), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new Result<>(pictures.size(), pictures);
    }

    /**
     * Getting subject name by the date of the picture
     * @param hour the hour that the picture was taken on
     * @return Subject Name
     */
    @NotNull
    @Contract (pure = true)
    private String getSubject(int hour) {
        Optional<String> course = Optional.ofNullable(FileConstants.courses.get(hour));
        return course.orElse(NAN);
    }

    public Set<Picture> manipulate(String path) throws IOException, ParseException {
        return readFolder(path).getPictures();
    }
}
