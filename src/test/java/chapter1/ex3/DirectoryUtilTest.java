package chapter1.ex3;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.hamcrest.Matcher;
import static org.hamcrest.Matchers.equalTo;
import org.junit.Test;
import static org.junit.Assert.*;

public class DirectoryUtilTest {

    @Test
    public void testGetFiles() {
        File f = getResourceFile("testdir");
        List<Matcher<File>> expects = getExpectFiles(
                "testdir/test.java",
                "testdir/testsubdir1/testsubsubdir1/test.java"
        );
        List<File> results = DirectoryUtil.getFiles(f, "java");
        for (int i = 0; i < expects.size(); i++) {
            assertThat(results.get(i), expects.get(i));
        }
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

    private List<Matcher<File>> getExpectFiles(String... str) {
        return Arrays.stream(str).
                map(s -> equalTo(getResourceFile(s))).
                collect(Collectors.toList());
    }
}
