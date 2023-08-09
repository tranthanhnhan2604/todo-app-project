package com.nhantran.todoapp.security;

import com.nhantran.todoapp.entity.Users;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Component
@Service
public class JwtService {

//    @Value("${com.nhantran.todoapp.security.secret-key}")
//    private String SECRET_KEY;

    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    @Value("${com.nhantran.todoapp.security.expiration}")
    private Long JWT_EXPIRATION;

    //tao token
    public String generateToken(Users user){
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + JWT_EXPIRATION);
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(currentDate)
                .setExpiration(expireDate)
                .signWith(key,SignatureAlgorithm.HS512)
                .compact();
    }
    //lay username
    public String getEmailFromJWT(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
    //check token hop le
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            throw new AuthenticationCredentialsNotFoundException("JWT was exprired or incorrect",ex.fillInStackTrace());
        }
    }

//    private Claims extractAllClaims(String token){
//        return Jwts.parserBuilder()
//                .setSigningKey(key)
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//    }
//
//    //lay claim
//    public <T> T extractClaim(String token, Function<Claims, T> claimResolver){
//        final Claims claims = extractAllClaims(token);
//        return claimResolver.apply(claims);
//    }
//
//    //lay username
//    public String extractUsername(String token) {
//        return extractClaim(token, Claims::getSubject);
//    }
//
//    //trich xuat ngay het hieu luc
//    private Date extractExpiration(String token) {
//        return extractClaim(token, Claims::getExpiration);
//    }
//
//    //check token con ngay hieu luc
//    private boolean isTokenExpired(String token) {
//        return extractExpiration(token).before(new Date());
//    }
//
//    //check token hop le
//    public boolean isTokenValid(String token, UserDetails userDetails){
//        final String username = extractUsername(token);
//        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//    }

}
