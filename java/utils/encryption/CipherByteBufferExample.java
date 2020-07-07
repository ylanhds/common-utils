package utils.encryption;

import org.apache.commons.crypto.cipher.CryptoCipher;
import org.apache.commons.crypto.utils.Utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Properties;

/**
 * Example showing the CryptoCipher API using a ByteBuffer
 */
public class CipherByteBufferExample {

    public static void main(String[] args) throws Exception {
        final SecretKeySpec key = new SecretKeySpec(getUTF8Bytes("66cb9e66a83e4ac69fa3d34eab27c1fc66cb9e66a83e4ac69fa3d34eab27c1fc"), "AES");
        final IvParameterSpec iv = new IvParameterSpec(getUTF8Bytes("1234567890123456"));
        Properties properties = new Properties();
        //Creates a CryptoCipher instance with the transformation and properties.
        final String transform = "AES/CBC/PKCS5Padding";
        final ByteBuffer outBuffer;
        final int bufferSize = 1024;
        final int updateBytes;
        final int finalBytes;
        try (CryptoCipher encipher = Utils.getCipherInstance(transform, properties)) {

            ByteBuffer inBuffer = ByteBuffer.allocateDirect(bufferSize);
            outBuffer = ByteBuffer.allocateDirect(bufferSize);
            inBuffer.put(getUTF8Bytes("hello world!"));

            inBuffer.flip(); // ready for the cipher to read it
            // Show the data is there
            System.out.println("inBuffer=" + asString(inBuffer));

            // Initializes the cipher with ENCRYPT_MODE,key and iv.
            encipher.init(Cipher.ENCRYPT_MODE, key, iv);
            // Continues a multiple-part encryption/decryption operation for byte buffer.
            updateBytes = encipher.update(inBuffer, outBuffer);
            System.out.println(updateBytes);

            // We should call do final at the end of encryption/decryption.
            finalBytes = encipher.doFinal(inBuffer, outBuffer);
            System.out.println(finalBytes);
        }

        outBuffer.flip(); // ready for use as decrypt
        byte[] encoded = new byte[updateBytes + finalBytes];
        outBuffer.duplicate().get(encoded);
        System.out.println(Arrays.toString(encoded));

        // Now reverse the process
        try (CryptoCipher decipher = Utils.getCipherInstance(transform, properties)) {
            decipher.init(Cipher.DECRYPT_MODE, key, iv);
            ByteBuffer decoded = ByteBuffer.allocateDirect(bufferSize);
            decipher.update(outBuffer, decoded);
            decipher.doFinal(outBuffer, decoded);
            decoded.flip(); // ready for use
            System.out.println("decoded=" + asString(decoded));
        }
    }

    /**
     * Converts String to UTF8 bytes
     *
     * @param input the input string
     * @return UTF8 bytes
     */
    private static byte[] getUTF8Bytes(String input) {
        return input.getBytes(StandardCharsets.UTF_8);
    }

    /**
     * Converts ByteBuffer to String
     *
     * @param buffer input byte buffer
     * @return the converted string
     */
    private static String asString(ByteBuffer buffer) {
        final ByteBuffer copy = buffer.duplicate();
        final byte[] bytes = new byte[copy.remaining()];
        copy.get(bytes);
        return new String(bytes, StandardCharsets.UTF_8);
    }

}