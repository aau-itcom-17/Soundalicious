import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class StartUpSavingFolders {

    public static void startUpSavingFolders () throws Exception {

        // Creating the game folder which contains a sound folder and a database of the questions.
        String homePath = System.getProperty("user.home");
        File appFolder = new File(homePath + File.separator + "p1");
        File soundFolder = new File(homePath + File.separator + "p1" + File.separator + "Sounds");
        if (appFolder.exists() && appFolder.isDirectory()) {
            System.out.println("app folder: " + appFolder);
            //Questions questions = new Questions(); Here we need to import the xml page.
        } else {
            if (appFolder.mkdir()) {
                System.out.println("P1 folder was created....");
            } else {

            }
        }
        if (soundFolder.exists() && soundFolder.isDirectory()) {
            System.out.println("sound folder in appfolder" + soundFolder);

        } else {
            if (soundFolder.mkdir()) {
                System.out.println("Sound folder was created....");
            } else {

            }
        }

        // copy the sounds folder to the computer

        String k = "";
        String k1 = "";
        for (int i  = 10; i > 0; i--) {

            try {

                File soundPackageFile = new File(homePath + File.separator + "downloads" + File.separator + "P1-master" + k + File.separator + "Sounds");
                File soundPackageFileWithSlash = new File(homePath + File.separator + "downloads" + File.separator + "P1-master" + k1 + File.separator + "Sounds");
                File soundPackageFileWithSlashIdea = new File(homePath + File.separator + "IdeaProjects" + File.separator + "P1-master" + k1 + File.separator + "Sounds");

                if (!soundPackageFile.exists()){
                    soundPackageFile = soundPackageFileWithSlash;
                }
                if (!soundPackageFileWithSlash.exists()){
                    soundPackageFile = soundPackageFileWithSlashIdea;
                }

                System.out.println("File path to sound folder in downloads: " + soundPackageFile);

                // Change files to byte to check if they are the same.
                long f1 = FileUtils.sizeOfDirectory(soundPackageFile);
                long f2 = FileUtils.sizeOfDirectory(soundFolder);

                System.out.println("Size of sound folder from github: "+f1);
                System.out.println("Size of the user folder: "+f2);

                if (f1 != f2) {
                    // copies the folder on startup
                    CopyFolder.copyFolder(soundPackageFile, soundFolder);
                    System.out.println("Folder from github has been copied to this computer");
                }
                if (soundPackageFile.exists()){
                    break;
                }

            } catch (RuntimeException | NoSuchFileException e){

            }

            k = " " + i + "";
            k1 = "-" + i + "";
        }

    }
}







