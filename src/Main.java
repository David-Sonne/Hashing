import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

//import static jdk.vm.ci.aarch64.AArch64.CPUFeature.SHA3;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(hashWithSHA256("abarbeiten"));
        readFromTxt("de-1296-v1.txt");
        //readFromTxt("texts/de-1626-v1.txt");
    }

    public static void readFromTxt(String file) {
        try {
            File myObj = new File(file);
            Scanner myReader = new Scanner(myObj);
            String data;
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
                hashWithSHA256(data);
                if(data.equals("cbfe4a6d948b4c1fc7c05ac4ba1a5b91c145e7bb88ea7d34cf2b10f28\n" +
                        "eafd1318477ff0e5dd679432e3b7b3763c6d3424037221addb68568b9215df83014f\n" +
                        "877")) {
                    System.out.println("This one! It's: " + data);
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static String hashWithSHA256(String input) {
        try {
            // Create a MessageDigest instance for SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Perform the hashing
            byte[] encodedHash = digest.digest(input.getBytes());

            // Convert the byte array into a hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}