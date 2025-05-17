package model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256Hasher
{
    public static String hash(String input)
    {
        try
        {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes("UTF-8"));

            // Convert the byte array to hex format
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes)
            {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0'); // pad with leading zero if needed
                hexString.append(hex);
            }
            return hexString.toString();

        } catch (Exception e)
        {
            throw new RuntimeException("Error while hashing", e);
        }
    }
}