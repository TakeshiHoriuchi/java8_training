package chapter1.ex2;

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
    public void testSubdirectories() throws URISyntaxException {
        File f = getResourceFile("testdir");
        List<Matcher<File>> expects = getExpectFiles(
                "testdir/testsubdir1",
                "testdir/testsubdir2",
                "testdir/testsubdir1/testsubsubdir1"
        );
        List<File> results = DirectoryUtil.subdirectories(f);
        // 本来 containsInAnyOrder を使うべきだが containsInAnyOrder の可変長引数でオーバーロードしているメソッドの方が実行されるので上手くいかない
        for (int i=0; i < expects.size(); i++)
            assertThat(results.get(i), expects.get(i));
    }
    
    @Test
    public void testSubdirectoriesByMethodReference() throws URISyntaxException {
        File f = getResourceFile("testdir");
        List<Matcher<File>> expects = getExpectFiles(
                "testdir/testsubdir1",
                "testdir/testsubdir2",
                "testdir/testsubdir1/testsubsubdir1"
        );
        List<File> results = DirectoryUtil.subdirectoriesByMethodReference(f);
        for (int i=0; i < expects.size(); i++)
            assertThat(results.get(i), expects.get(i));
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
