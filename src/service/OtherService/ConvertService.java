package service.OtherService;

import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.epub.EpubReader;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

/**
 * Created by dream on 17-5-29.
 */
public class ConvertService {
    @Nullable
    public static String getContents(File file, int chapter) {
        String ans = null;
        try {
            Book book = new EpubReader().readEpub(new FileInputStream(file));
            List<Resource> contents = book.getContents();
            ans = new String(contents.get(chapter).getData(), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ans;
    }

    public static int getChapter(File file) {
        int ans = -1;
        try {
            Book book = new EpubReader().readEpub(new FileInputStream(file));
            List<Resource> contents = book.getContents();
            ans = contents.size();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ans;
    }
}
