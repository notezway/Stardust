package umiker9.stardust2d.systems.io;

import umiker9.stardust2d.systems.error.Error;
import umiker9.stardust2d.systems.error.ErrorBuilder;
import umiker9.stardust2d.systems.error.ErrorStack;

import java.io.*;

public class FileIO {

    public static byte[] getFileAsBytes(String path) {
        return getFileAsBytes(new File(path));
    }

    public static byte[] getFileAsBytes(File file) {
        byte[] bytes = new byte[(int) file.length()];
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            bis.read(bytes);
            bis.close();
        } catch (IOException e) {
            ErrorStack.addError(new ErrorBuilder().setLevel(Error.ERROR).setException(e).setErrorSource(file)
                    .setMessage("File could not be read [" + file.getPath() + "]").finish());
            return new byte[0];
        }
        return bytes;
    }

    public static FileInputStream getFileAsInputStream(String path) {
        try {
            return new FileInputStream(path);
        } catch (FileNotFoundException e) {
            ErrorStack.addError(new ErrorBuilder().setMessage("File [" + path + "] could not be found")
                    .setLevel(Error.ERROR).setException(e).setErrorSource(path).finish());
        }

        return null;
    }

    public static FileInputStream getFileAsInputStream(File file) {
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            ErrorStack.addError(new ErrorBuilder().setMessage("File [" + file.getPath() + "] could not be found")
                    .setLevel(Error.ERROR).setException(e).setErrorSource(file).finish());
        }

        return null;
    }

    public static Resource getResource(String path) {
        return new Resource(path, getFileAsBytes(path));
    }

    public static Resource getResource(File file) {
        return new Resource(file.getPath(), getFileAsBytes(file));
    }

    public static File[] findFiles(String directory, final String suffix) {
        File folder = new File(directory);

        if(!folder.exists()) {
            ErrorStack.addError(new ErrorBuilder().setLevel(Error.ERROR).setErrorSource(folder)
                    .setMessage("Folder [" + directory + "] does not exist").finish());
            return new File[0];
        }

        if(!folder.isDirectory()) {
            ErrorStack.addError(new ErrorBuilder().setLevel(Error.ERROR).setErrorSource(folder)
                    .setMessage("[" + directory + "] is not a folder").finish());
            return new File[0];
        }

        return folder.listFiles((dir, name) -> {
            return name.toLowerCase().endsWith(suffix.toLowerCase());
        });
    }
}
