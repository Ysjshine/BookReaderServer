package service.utils;

import org.jetbrains.annotations.NotNull;

/**
 * Created by dream on 17-5-29.
 */
public class UtilsNewsImg {
    @NotNull
    private static final String topTemplate = "news/*.jpg";

    public static String fillTopTemplate(@NotNull String id) {
        return topTemplate.replace("*", id);
    }

    public static String[] changeDownloadSrc(String[] tops, String prefix) {
        if (tops != null) {
            String[] ans = tops.clone();
            int length = ans.length;
            for (int i = 0; i < length; i++) {
                ans[i] = prefix + "/NewsImage?id=" + ans[i];
            }
            return ans;
        }
        return null;
    }
}
