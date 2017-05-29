package service.utils;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.File;

/**
 * Created by dream on 17-5-25.
 */
public class UtilsTopImg {
    @NotNull
    private static final String template = "tops/*.jpg";
    private static final String[] values = {"a", "b", "c"};

    @Contract("_, _ -> !null")
    public static File getRealSrc(@NotNull String prefix, @NotNull String id) {
        return new File(prefix, template.replace("*", id));
    }

    public static String[] getDownloadSrc(String prefix) {
        String[] ans = values.clone();
        int length = ans.length;
        for (int i = 0; i < length; i++) {
            ans[i] = prefix + "/TopImage?id=" + ans[i];
        }
        return ans;
    }
}
