package utils;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.File;

/**
 * Created by dream on 17-5-29.
 */
public class UploadUtils {
    @NotNull
    @Contract("_, _, _ -> !null")
    public static File getRealSrc(@NotNull String prefix, @NotNull String template, @NotNull String id) {
        return new File(prefix, template.replace("*", id));
    }

    public static String[] getDownloadURI(@NotNull String prefix, String URIPattern, @NotNull String[] values) {
        String[] ans = values.clone();
        int length = ans.length;
        for (int i = 0; i < length; i++) {
            ans[i] = prefix + URIPattern + ans[i];
        }
        return ans;
    }
}
