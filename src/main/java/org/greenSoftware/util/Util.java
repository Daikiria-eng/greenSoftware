package org.greenSoftware.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.greenSoftware.constants.Constants;

public class Util {
    public static String hashIt(String toHash) throws NoSuchAlgorithmException{
        MessageDigest digest=MessageDigest.getInstance("SHA-256");
        byte[] encodedHash=digest.digest(toHash.getBytes(StandardCharsets.UTF_8));

        StringBuilder hexString=new StringBuilder(2*encodedHash.length);
        for(int i=0;i<encodedHash.length;i++){
            String hex=Integer.toHexString(0xff & encodedHash[i]);
            if(hex.length()==1) hexString.append('0');
            hexString.append(hex);
        }
        
        return hexString.toString();
    }
    
    public static boolean validateEmail(String email){
        Pattern pattern=Pattern.compile(Constants.PATTERN_TO_VALIDATE_EMAIL);
        Matcher matcher=pattern.matcher(email);

        return matcher.find();
    }
    
    public static String escapeSpecialChars(String string){
        Pattern pattern=Pattern.compile(Constants.PATTERN_TO_ESCAPE);
        Matcher matcher=pattern.matcher(string);

        return (matcher.find())?
            matcher.replaceAll(""):string;
    }
}