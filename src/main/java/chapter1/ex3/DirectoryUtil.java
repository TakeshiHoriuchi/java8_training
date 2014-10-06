package chapter1.ex3;

/*
* extがラムダ式内でキャプチャされている
*/

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.io.FilenameUtils;

public class DirectoryUtil {

    public static List<File> getFiles(File targetDir, String ext) {
        if (targetDir == null || ext == null) throw new NullPointerException();

        List<File> results = new LinkedList<>();
        List<File> unsearchedDir = new LinkedList<>();
        unsearchedDir.add(targetDir);

        while (!unsearchedDir.isEmpty()) {
            File dir = unsearchedDir.remove(0);
            List<File> listDir = Arrays.
                    stream(dir.listFiles((file) -> file.isDirectory())).
                    collect(Collectors.toList());
            unsearchedDir.addAll(listDir);
            
            listDir = Arrays.
                    stream(dir.list((d, name) -> FilenameUtils.getExtension(name).equals(ext))).
                    map((name) -> new File(dir, name)).
                    collect(Collectors.toList());
            results.addAll(listDir);
        }

        return results;
    }
}
