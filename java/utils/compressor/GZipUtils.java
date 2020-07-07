package utils.compressor;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author ZhangBaoSheng
 * @version v1.0.0
 */
@Slf4j
public class GZipUtils {

    /**
     * 使用gzip进行压缩
     */
    public static String gzip(String primStr) {
        if (primStr == null || primStr.length() == 0) {
            return primStr;
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        GZIPOutputStream gzip = null;
        try {
            gzip = new GZIPOutputStream(out);
            gzip.write(primStr.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (gzip != null) {
                try {
                    gzip.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return encoder(out.toByteArray());
    }

    private static String encoder(byte[] bytes) {
        if (bytes == null || bytes.length < 1) {
            return "";
        }
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * 使用gzip进行解压缩
     */
    public static String gunzip(String compressedStr) {
        if (compressedStr == null) {
            return null;
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = null;
        GZIPInputStream ginzip = null;
        byte[] compressed;
        String decompressed = null;
        try {
            compressed = new sun.misc.BASE64Decoder().decodeBuffer(compressedStr);
            in = new ByteArrayInputStream(compressed);
            ginzip = new GZIPInputStream(in);

            byte[] buffer = new byte[1024];
            int offset = -1;
            while ((offset = ginzip.read(buffer)) != -1) {
                out.write(buffer, 0, offset);
            }
            decompressed = out.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ginzip != null) {
                try {
                    ginzip.close();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
            try {
                out.close();
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
        return decompressed;
    }

    public static void main(String[] args) throws IOException {
        String strOld = FileUtils.readFileToString(new File("/home/fablen/IdeaProjects/common-utils/compressor-压缩与解压.md"), "utf-8");
        System.out.println(strOld.length());
        String gzip = gzip(strOld);
        System.out.println("gzip " + gzip.length());
        System.out.println("gunzip " + gunzip(gzip).length());

    }
}