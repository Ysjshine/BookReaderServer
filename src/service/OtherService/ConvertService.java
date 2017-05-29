package service.OtherService;

import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.epub.EpubReader;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by dream on 17-5-29.
 */
public class ConvertService {
    @Nullable
    public static String getContents(File file, int chapter) {
        String ans = null;
        try {
            Book book = new EpubReader().readEpub(new FileInputStream(file));
            ans = new String(book.getContents().get(chapter).getData(), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ans;
    }
}
