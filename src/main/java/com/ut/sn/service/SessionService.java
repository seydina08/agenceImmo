package com.ut.sn.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ut.sn.models.Agence;
import com.ut.sn.repo.AgenceRepo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;

@Service
public class SessionService {

    private static final String HEADER_STRING = "Authorization";

    @Autowired
    AgenceRepo agenceRepo;

    public String getJWTToken(Agence agence) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(agence.getLogin())
                .claim("agenceid", agence.getId())
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                //.setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(io.jsonwebtoken.SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }

    public String getUsername(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        String user = null;
        if (token != null) {
            // parse the token.

            try {
                user = Jwts.parser()
                        .setSigningKey("mySecretKey")
                        .parseClaimsJws(token.replace("Bearer ", ""))
                        .getBody()
                        .getSubject();


            } catch (Exception e) {

                throw e;

            }
        }
        return user;

    }

    private boolean checkJWTToken(HttpServletRequest request) {
        String authenticationHeader = request.getHeader(HEADER_STRING);
        if (authenticationHeader == null || !authenticationHeader.startsWith("Bearer "))
            return false;
        return true;
    }

    private void setUpSpringAuthentication(Claims claims) {
        @SuppressWarnings("unchecked")
        List<String> authorities = (List) claims.get("authorities");

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(claims.getSubject(), null,
                authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
        SecurityContextHolder.getContext().setAuthentication(auth);

    }

    public Claims doFilterInternal(HttpServletRequest request) {
        Claims claims = null;
        try {
            if (checkJWTToken(request)) {
                claims = validateToken(request);
                if (claims.get("authorities") != null) {
                    setUpSpringAuthentication(claims);
                } else {
                    SecurityContextHolder.clearContext();
                }
            } else {
                SecurityContextHolder.clearContext();
            }

        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e) {
            //throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());

        }
        return claims;
    }

    public void verifyClaims(Claims claims) {

        if (claims == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Vous n'êtes pas autorisé à effectuer cette action.");
        } else {
            Optional<Agence> optionalAgence = agenceRepo.findById(Long.parseLong(claims.get("agenceid").toString()));
            if (! optionalAgence.isPresent())
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Vous n'êtes pas autorisé à effectuer cette action.");
        }

    }

    private Claims validateToken(HttpServletRequest request) {
        String jwtToken = request.getHeader(HEADER_STRING).replace("Bearer ", "");
        return Jwts.parser().setSigningKey("mySecretKey".getBytes()).parseClaimsJws(jwtToken).getBody();
    }

    public Agence getAgence(Claims claims) {
        if (claims == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Vous n'êtes pas autorisé à effectuer cette action.");
        } else {
        	 Optional<Agence> optionalAgence = agenceRepo.findById(Long.parseLong(claims.get("agenceid").toString()));
            if (!optionalAgence.isPresent())
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Vous n'êtes pas autorisé à effectuer cette action.");
            else return optionalAgence.get();
        }
    }


}