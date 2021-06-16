package RamadhanKalih.jwork;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;

import javax.crypto.Cipher;

public class Handshake
{
    private KeyPair keyPair;  
    
    public Handshake() {
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(2048);
            keyPair = generator.generateKeyPair();
        } catch (Exception e) {
            System.err.println(e);            
            return;
        }
    }

    public String encryptMessage(String message) {
        byte[] msg = message.getBytes(StandardCharsets.UTF_8);
        return encryptMessage(msg);
    }

    public String encryptMessage(byte[] message) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPublic());
            byte[] cipherMsg = cipher.doFinal(message);
            return Base64.getEncoder().encodeToString(cipherMsg);
        } catch (Exception e) {
            System.err.println(e);
            return new String();
        }
    }

    public String decryptMessage(String message) {
        byte[] msg = message.getBytes(StandardCharsets.UTF_8);
        return decryptMessage(msg);
    }

    public String decryptMessage(byte[] message) {
        try {
            byte[] plain = Base64.getDecoder().decode(message);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());
            return new String(cipher.doFinal(plain));
        } catch (Exception e) {
            System.err.println(e);            
            return new String();
        }
    }
}
