package com.mursit.news.dailynews.helper;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by ima2 on 2/22/16.
 */
public class Utils {
    public static final String LINK = "link";
    public static final String ID =  "0";
    public static final String KATEGORI = "category";
    public static final String DATE = "pubDate";

    public static void CopyStream(InputStream is, OutputStream os) {
        final int buffer_size = 1024;
        try {
            byte[] bytes = new byte[buffer_size];
            for (;;) {
                int count = is.read(bytes, 0, buffer_size);
                if (count == -1)
                    break;
                os.write(bytes, 0, count);
            }
        } catch (Exception ex) {
        }
    }

}

