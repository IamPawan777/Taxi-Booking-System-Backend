package com.project.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.project.service.CustomUserDetailsService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

	@Autowired
	JwtUtil jwtUtil;
	
	@Autowired
	CustomUserDetailsService customUserDetailsService;
	
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authHeader = request.getHeader("Authorization");
		String token = null;
		String username = null;
		
		try {
			if(authHeader != null && authHeader.startsWith("Bearer ")) {
				token = authHeader.substring(7);
				username = jwtUtil.extractUsername(token);
			}
		}
		catch (ExpiredJwtException e) {
			sendErrorResponce(response, HttpServletResponse.SC_UNAUTHORIZED, "Token Has Expired...!");
			return;
		}
		catch (JwtException e) {
			sendErrorResponce(response, HttpServletResponse.SC_BAD_REQUEST, "JWT Expired...!");
			return;
		}
		catch (IllegalArgumentException e) {
			sendErrorResponce(response, HttpServletResponse.SC_BAD_REQUEST, "Illegal Argument token...!");
			return;
		}
		catch(Exception e) {
			sendErrorResponce(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unexpected Token occured...!");
			return;
		}
		
		if(username != null && SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails userByUsername = customUserDetailsService.loadUserByUsername(username);
			
			if(jwtUtil.isValidateToken(token, userByUsername)) {
				UsernamePasswordAuthenticationToken authentication =
						new UsernamePasswordAuthenticationToken(userByUsername, null,  userByUsername.getAuthorities());			// roles
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}	
		filterChain.doFilter(request, response);
		
	}
	
	private void sendErrorResponce(HttpServletResponse response, int status, String msg) throws IOException {
		response.setStatus(status);
		response.setContentType("application/json");
		response.getWriter().write("{\"error\":\""+ msg+ "\"}");
		response.getWriter().flush();
	}

}
