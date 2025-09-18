package com.project.config;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	private final String SECRET_KEY = "asdblkjkljkjk9jsdklskldkljij8j080jkld90v90x90vu000bjbjhbhbhnjbjvgggvgr6utrgyur6u7d9udv90su90vus";		// 3. secret key
	private final long TOKEN_EXPIRY_DURATION = 1000*60*20;
	
	private SecretKey getSecretKey() {			// 1. header
		byte[] decode = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(decode);
		
//		return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
	}
	
	
	// Method to generate token 
		public String generateToken(UserDetails userDetails) {
		    Map<String, Object> claims = new HashMap<>();
//		    return createToken(claims, username);
		    
		    // Add user authorities/roles to claims
		    claims.put("authorities", userDetails.getAuthorities().stream()
		            .map(grantedAuthority -> grantedAuthority.getAuthority())
		            .collect(Collectors.toList()));
		    
		    // Add other user details if needed
		    claims.put("accountStatus", Map.of(
		        "enabled", userDetails.isEnabled(),
		        "accountNonExpired", userDetails.isAccountNonExpired(),
		        "accountNonLocked", userDetails.isAccountNonLocked(),
		        "credentialsNonExpired", userDetails.isCredentialsNonExpired()
		    ));
		    
		    return createToken(claims, userDetails.getUsername());
		}
		public String createToken(Map<String, Object> claims, String username) {		//
			return Jwts.builder()
					.claims(claims)
					.subject(username)
					.issuedAt(new Date(System.currentTimeMillis()))
					.expiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRY_DURATION))
					.signWith(getSecretKey()) 
					.compact();
		}
		
		public Date extractExpiration(String token) {		//
	        return extractAllClaims(token).getExpiration();
	    }    
	    public boolean isTokenExpired(String token) {		//
	    	return extractExpiration(token).before(new Date());
	    }
	    
	    
	    
		public String extractUsername(String token) {	//
			return extractAllClaims(token).getSubject();
		}
	    
	    
		public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {	//
			final Claims claims = extractAllClaims(token);
			return claimResolver.apply(claims);
		}

	    // Extract all claims from token - FIXED METHOD
	    public Claims extractAllClaims(String token) {		//
	    	return Jwts.parser()
	    			.verifyWith(getSecretKey())
	    			.build()
	    			.parseSignedClaims(token)
	    			.getPayload();
	    }
	    

	    // Additional method to validate token
		public boolean isValidateToken(String token, UserDetails userDetails) {		//
			return (extractUsername(token).equals(userDetails.getUsername()) && !isTokenExpired(token));
		}

	
}
