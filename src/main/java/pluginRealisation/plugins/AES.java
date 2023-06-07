package pluginRealisation.plugins;

import pluginRealisation.Plugin;
import pluginRealisation.PluginType;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class AES implements Plugin {
    private static final String PRIVATE_KEY_STRING = "0123456789abcdef";
    private static final String PUBLIC_KEY_STRING = "0123456789abcdef";
    private static SecretKey privateKey;
    private static SecretKey publicKey;

    @Override
    public PluginType getType() {
        return PluginType.AES;
    }

    @Override
    public void initialiseKeyFromStringRSA() {
        privateKey = new SecretKeySpec(PRIVATE_KEY_STRING.getBytes(), "AES");
        publicKey = new SecretKeySpec(PUBLIC_KEY_STRING.getBytes(), "AES");
    }

    @Override
    public String encrypt(String info) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return encode(cipher.doFinal(info.getBytes()));
        } catch (NoSuchPaddingException | IllegalBlockSizeException | NoSuchAlgorithmException | BadPaddingException |
                 InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String decrypt(String data) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return new String(cipher.doFinal(decode(data)), StandardCharsets.UTF_8);
        } catch (NoSuchPaddingException | IllegalBlockSizeException | NoSuchAlgorithmException | BadPaddingException |
                 InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    private static String encode(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }

    private static byte[] decode(String data) {
        return Base64.getDecoder().decode(data);
    }
}
