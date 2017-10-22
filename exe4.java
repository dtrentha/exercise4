import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.UnsupportedEncodingException;

public class exe4 {
    
    public static void main(String[] args){
    
        String seed = "trenthamesworthy";

        String result = sha2(4,seed);
        
        System.out.println(seed);
        System.out.println(result);

        String next = sha2(4,"e505");
        System.out.println(next);

    }
    private static String getHex(byte[] hash) {
        StringBuilder stringb = new StringBuilder();
        for(int i = 0; i < hash.length; i++) {
            stringb.append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));
        }
        return stringb.toString();
    }
    public static String sha2(int n, String seed) {
        String result = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = seed.getBytes("UTF-8");
            //System.out.println(getHex(hash));
            md.update(hash);
            byte[] digest = md.digest();
            return getHex(digest);
        } catch(NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            ex.printStackTrace();
            return ex.getMessage();
        }
    
	}





    





}
