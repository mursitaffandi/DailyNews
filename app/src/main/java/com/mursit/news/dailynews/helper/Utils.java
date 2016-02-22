package com.mursit.news.dailynews.helper;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by ima2 on 2/22/16.
 */
public class Utils {
    public static void CopyStream(InputStream is, OutputStream os) {
        final int buffer_size = 1024;
        try{
            byte[] bytes = new byte[buffer_size];
            for (;;) {
                int cnt = is.read(bytes, 0, buffer_size);
                if (cnt == -1)
                    break;
                os.write(bytes, 0, cnt);
            }
        } catch (Exception e){

        }
    }
}

