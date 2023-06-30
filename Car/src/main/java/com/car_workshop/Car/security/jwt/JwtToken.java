package com.car_workshop.Car.security.jwt;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.car_workshop.Car.dto.v1.security.TokenDto;
import com.car_workshop.Car.exceptions.InvalidJwtException;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class JwtToken {

	@Value("${security.jwt.token.secret-key:secret}")
	private String secretKey = "secret";
	
	@Value("${security.jwt.token.expire-lenght:3600000}")
	private long validit = 3600000;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	Algorithm algorithm = null;
	
	@PostConstruct
	protected void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
		algorithm = Algorithm.HMAC256(secretKey.getBytes());
	}
	
	public TokenDto createAccessToken(String userName, List<String> roles) {
		Date now = new Date();
		System.out.println(now);
		Date valid = new Date(now.getTime() + validit);
		System.out.println(valid);           
		var accessToken = getAccessToken(userName, roles, now, valid);
		var refreshToken = getRefreshToken(userName, roles, now);
		return new TokenDto(userName, true, now, valid, accessToken, refreshToken);
	}
	
	public TokenDto refreshToken(String refreshToken) {
		if (refreshToken.contains("Bearer ")) refreshToken =
				refreshToken.substring("Bearer ".length());
		
		JWTVerifier verifier = JWT.require(algorithm).build();
		DecodedJWT decodedJWT = verifier.verify(refreshToken);
		String username = decodedJWT.getSubject();
		List<String> roles = decodedJWT.getClaim("roles").asList(String.class);
		return createAccessToken(username, roles);
	}

	private String getRefreshToken(String userName, List<String> roles, Date now) {
		Date validRefreshToken = new Date(now.getTime() + (validit * 3));
		return JWT.create().withClaim("roles", roles)
				.withIssuedAt(now)
				.withExpiresAt(validRefreshToken)
				.withSubject(userName)
				.sign(algorithm)
				.strip();
	}

	private String getAccessToken(String userName, List<String> roles, Date now, Date valid) {
		String url = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
		return JWT.create().withClaim("roles", roles)
				.withIssuedAt(now)
				.withExpiresAt(valid)
				.withSubject(userName)
				.withIssuer(url)
				.sign(algorithm)
				.strip();
	}
	
	public Authentication getAuthentication(String token) {
		DecodedJWT decodedJWT = decodedToken(token);
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(decodedJWT.getSubject());
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}

	private DecodedJWT decodedToken(String token) {
		Algorithm alg = Algorithm.HMAC256(secretKey.getBytes());
		JWTVerifier verifier = JWT.require(alg).build();
		DecodedJWT decodedJWT = verifier.verify(token);
		return decodedJWT;
	}
	
	public String resolveToken(HttpServletRequest req) {
		String bearerToken = req.getHeader("Authorization");
		
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring("Bearer ".length());
		}
		return null;
	}
	
	public Boolean validateToken(String token) {
		DecodedJWT decodedJWT = decodedToken(token);
		try {
			Date dt = new Date();
			Date t = new Date(dt.getTime() - validit);
			if (decodedJWT.getExpiresAt().before(t)) {
				return false;
			}
			return true;
		} 
		catch (Exception e) {
			throw new InvalidJwtException("Token inválido");
		}
	}
}




