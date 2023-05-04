package org.greenSoftware.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.greenSoftware.constants.Constants;
import org.greenSoftware.dto.UserDTO;
import org.greenSoftware.dto.UserResponseDTO;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

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
    
    public static String createJWT(UserDTO user,Algorithm algorithm){
        //Algorithm algorithm=Algorithm.HMAC256("daSecret");
        return JWT.create()
            .withIssuer("daSecret")
            .withSubject("daSecret details")
            .withClaim("userName", user.getName())
            .withClaim("email", user.getEmail())
            .withClaim("level", user.getLevel())
            .withIssuedAt(new Date())
            .withExpiresAt(new Date(System.currentTimeMillis()+5000L))
            .withJWTId(UUID.randomUUID().toString())
            .withNotBefore(new Date(System.currentTimeMillis()+100L))
            .sign(algorithm);
    }
    
    public static UserResponseDTO verifyJWT(String jwt,Algorithm algorithm){
        JWTVerifier jwtVerifier=JWT.require(algorithm)
        .withIssuer("daSecret").build();
        
        try{
            DecodedJWT decodedJwt=jwtVerifier.verify(jwt);

            return new UserResponseDTO(
                new UserDTO(
                    decodedJwt.getClaim("userName").asString(),
                    decodedJwt.getClaim("email").asString(),
                    null,
                    decodedJwt.getClaim("level").asInt()
                ),
                true,
                jwt
            );
        }catch(JWTVerificationException e){
            e.printStackTrace();
        }
        
        return new UserResponseDTO(new UserDTO(
            "","","",0
        ),false,null);
    }
}