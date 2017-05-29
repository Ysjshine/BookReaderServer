package service.utils;

import bean.BookBean;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.List;

/**
 * Created by dream on 17-5-25.
 */
public class UtilsBook {
    @NotNull
    private static final String[] templates = {"books/File_*.epub", "images/Cover_*.jpg"};

    @Contract("_, _, _ -> !null")
    public static File getRealSrc(@NotNull String prefix, int id, int type) {
        String tempID = String.valueOf(id);
        return new File(prefix, templates[type].replace("*", tempID));
    }

    public static String[] getRealSrc(int id) {
        String tempID = String.valueOf(id);
        return new String[]{templates[0].replace("*", tempID), templates[1].replace("*", tempID)};
    }

    public static void getDownloadSrc(@NotNull String prefix, List<BookBean> books) {
        if (books != null) {
            for (BookBean b : books) {
                b.fileSource = prefix + "/File?id=" + b.BookID;
                b.imgSource = prefix + "/Image?id=" + b.BookID;
            }
        }
    }
}
