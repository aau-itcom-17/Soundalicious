import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * This class has a method that copies files from one folder to older.
 * Used to copy sound files in user's personal files.
 */

public class CopyFolder {

    public static void copyFolder(File sourceFolder, File destFolder) throws IOException {
        //Check if sourceFolder is a directory or file
        //If sourceFolder is file; then copy the file directly to new location
        if (sourceFolder.isDirectory()) {
            //Check if if the destinationFolder is already present; If not then create it
            if (!destFolder.exists()) {
                destFolder.mkdir();
                System.out.println("Directory created :: " + destFolder);
            }

            //Get all files from source directory
            String files[] = sourceFolder.list();

            for (String file : files) {
                File srcFile = new File(sourceFolder, file);
                File destFile = new File(destFolder, file);

                copyFolder(srcFile, destFile);
            }
        } else {
            //Copy the file content from one place to another
            Files.copy(sourceFolder.toPath(), destFolder.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File copied :: " + destFolder);
        }
    }
}

