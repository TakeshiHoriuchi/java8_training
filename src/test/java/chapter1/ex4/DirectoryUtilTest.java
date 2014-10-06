package chapter1.ex4;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class DirectoryUtilTest {

    @Test
    public void testGetFiles() {
        File[] expects = getExpectFiles(
                "testdir",
                "testdir/testsubdir1",
                "testdir/testsubdir1/testsubsubdir1",
                "testdir/testsubdir2",
                "testdir/file1.txt",
                "testdir/file2.txt",
                "testdir/testsubdir1/file.txt",
                "testdir/testsubdir1/testsubsubdir1/file.txt",
                "testdir/testsubdir2/file.txt"
        );
        List<File> shuffled = Arrays.asList(Arrays.copyOf(expects, expects.length));
        Collections.shuffle(shuffled);
        File[] source = shuffled.toArray(new File[0]);
        File[] actual = DirectoryUtil.sort(source);
        assertArrayEquals(actual, expects);
    }

    private File getResourceFile(String str) {
        URI uri = null;
        try {
            uri = DirectoryUtilTest.class.getResource(str).toURI();
        } catch (URISyntaxException e) {
            System.err.println(e);
        }
        return new File(uri);
    }

    private File[] getExpectFiles(String... str) {
        return Arrays.stream(str).map(s -> (getResourceFile(s))).toArray(File[]::new);
    }
}

