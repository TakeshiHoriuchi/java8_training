package chapter1.ex2;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class DirectoryUtil {

    public static List<File> subdirectories(File targetDir) {
        if (targetDir == null) throw new NullPointerException();
        
        List<File> results = new LinkedList<>();
        List<File> unsearched = new LinkedList<>();
        unsearched.add(targetDir);

        while (!unsearched.isEmpty()) {
            File dir = unsearched.remove(0);
            List<File> sf = Arrays.
                    stream(dir.listFiles((file) -> file.isDirectory())).
                    collect(Collectors.toList());
            results.addAll(sf);
            unsearched.addAll(sf);
        }

        return results;
    }
    
    public static List<File> subdirectoriesByMethodReference(File targetDir) {
        if (targetDir == null) throw new NullPointerException();
        
        List<File> results = new LinkedList<>();
        List<File> unsearched = new LinkedList<>();
        unsearched.add(targetDir);

        while (!unsearched.isEmpty()) {
            File dir = unsearched.remove(0);
            List<File> sf = Arrays.
                    stream(dir.listFiles(DirectoryUtil::accept)).
                    collect(Collectors.toList());
            results.addAll(sf);
            unsearched.addAll(sf);
        }

        return results;
    }
    
    private static boolean accept(File file) {
        return file.isDirectory();
    }
}
