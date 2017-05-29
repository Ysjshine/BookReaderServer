package service.utils;

import bean.BookBean;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Created by dream on 17-5-25.
 */
public class UtilsBook {
    @NotNull
    private static final String[] templates = {"books/File_*.epub", "images/Cover_*.jpg"};

    public static String[] fillFileSrc(int id) {
        String tempID = String.valueOf(id);
        return new String[]{templates[0].replace("*", tempID), templates[1].replace("*", tempID)};
    }

    public static void changeDownloadUri(List<BookBean> books, String prefix) {
        if (books != null) {
            for (BookBean b : books) {
                b.fileSource = prefix + "/File?id=" + b.BookID;
                b.imgSource = prefix + "/Image?id=" + b.BookID;
            }
        }
    }
}
