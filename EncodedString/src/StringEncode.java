import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;



public class StringEncode {

	public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
		String PRN=null;
		String filepath=null;
		 if (args.length != 2) {
	            System.out.println(" enter your PRN and JSON file pathn as command line argumnets");
	            return;
	        }
		 
		 PRN=args[0];
		 filepath=args[1];
		 String data=new String(Files.readAllBytes(Paths.get(filepath)));
		 
		  String Value = SearchDestval(data);

          if (Value != null) {
             
              String rstr =randomstringgenerator(8);

             
              String completestring = PRN + Value + rstr;
              String hash =  MD5Hashgenerator(completestring);

     
              System.out.println(hash + ";" + rstr);
          } else {
              System.out.println("key is not found");
          }

      } 

  private static String SearchDestval(String Content) {
      String Key = "\"destination\"";
      int start = Content.indexOf(Key);

      if (start != -1) {
          int colonIndex = Content.indexOf(":", start);
          if (colonIndex != -1) {
              int startvalue = Content.indexOf("\"", colonIndex) + 1;
              int endvalue= Content.indexOf("\"", startvalue);
              return Content.substring(startvalue,endvalue);
          }
      }

      return null;
  }

  private static String randomstringgenerator(int length) {
      String characters = "0123ABCDEFGHIJKLMNOPQRS45UVWXYZabcdefghijklmnopqrstuvwxyz6789T";
      Random random = new Random();
      StringBuilder stringBuilder = new StringBuilder(length);
      for (int i = 0; i < length; i++) {
          stringBuilder.append(characters.charAt(random.nextInt(characters.length())));
      }
      return stringBuilder.toString();
  }

  private static String MD5Hashgenerator(String input) throws NoSuchAlgorithmException {
      MessageDigest md = MessageDigest.getInstance("MD5");
      byte[] digest = md.digest(input.getBytes());
      StringBuilder sb = new StringBuilder();
      for (byte b : digest) {
          sb.append(String.format("%02x", b));
      }
      return sb.toString();
  }
		

	


}
