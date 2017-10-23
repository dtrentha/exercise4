import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.UnsupportedEncodingException;
import java.lang.Math;
import java.util.Objects;

public class exe4 {
    
    public static void main(String[] args){
    
        String seed = "trenthamesworthy";

        findCycle(2,seed);
        
        //System.out.println(seed);

        //String next = sha2(4,"e505");
        //System.out.println(next);

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
			String hexHash = getHex(digest);
			String nhash = hexHash.substring(0, Math.min(hexHash.length(), n));
            return nhash;
        } catch(NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            ex.printStackTrace();
            return ex.getMessage();
        }
    
	}

	public static void findCycle(int n,String seed) {
	
		String tortoise1 = sha2(n,seed);
		String hare = sha2(n,sha2(n,seed));
		String tortoise2 = sha2(n,seed);
		String last1 = null;
		String last2 = null;
		long count = 0;

		while(Objects.equals(tortoise1, hare) != true) {
			tortoise1 = sha2(n,tortoise1);
			hare = sha2(n,sha2(n,hare));
            count++;
			if(count > 1000000000){
				System.out.println("thats a lot");
				return;
			}
			
		}
		
		last1 = tortoise1;

        while(Objects.equals(tortoise1,tortoise2) != true) {
	
			last1 = tortoise1;
			last2 = tortoise2;
			tortoise1 = sha2(n,tortoise1);
			tortoise2 = sha2(n,tortoise2);

		}
		
		System.out.println(tortoise1);
		System.out.println(hare);
		System.out.println(last1);
		System.out.println(last2);
		return;

	}




}
