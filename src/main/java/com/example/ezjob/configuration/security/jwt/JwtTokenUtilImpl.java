package com.example.ezjob.configuration.security.jwt;

import com.example.ezjob.common.ApplicationConstants.JwtClaims;
import com.example.ezjob.common.ApplicationConstants.Web.Security;
import com.example.ezjob.configuration.security.SecretProvider;
import com.example.ezjob.exception.JwtAuthenticationException;
import com.example.ezjob.exception.JwtTokenBlackListedException;
import com.example.ezjob.exception.JwtTokenIllegalArgumentException;
import com.example.ezjob.persistense.entity.RoleName;
import com.example.ezjob.service.BlackListingService;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Util class for creation, validation, etc. JWT token
 */
@Component
@RequiredArgsConstructor
public class JwtTokenUtilImpl implements JwtTokenUtil {
  private final SecretProvider secretProvider;
  private final UserDetailsService userDetailsService;
  private final BlackListingService blackListingService;

  @Value("${spring.security.expirationTime}")
  private long validityInMilliseconds;

  @Override
  @Nonnull
  public String getUsername(@Nonnull final String token) {
    return Jwts.parser().setSigningKey(secretProvider.getEncodedSecret()).parseClaimsJws(token)
            .getBody().getSubject();
  }

  @Override
  @Nonnull
  public Date getExpirationDate(@Nonnull final String token) {
    return Jwts.parser().setSigningKey(secretProvider.getEncodedSecret()).parseClaimsJws(token)
            .getBody().getExpiration();
  }

  @Override
  @Nonnull
  public Authentication getAuthentication(@Nonnull final String token) {
    final var userDetails = this.userDetailsService.loadUserByUsername(getUsername(token));
    return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
  }

  /**
   * Checks if token expired
   *
   * @param token a JWT token to check
   * @return {@code true} if token outdated {@code false} otherwise
   */
  @Nonnull
  private Boolean isTokenExpired(@Nonnull final String token) {
    final var expiration = getExpirationDate(token);
    final var timeZoneId = Jwts.parser().setSigningKey(secretProvider.getEncodedSecret()).parseClaimsJws(token)
            .getBody().get(JwtClaims.TIME_ZONE_ID).toString();
    return expiration.before(getCurrentZonedDateTime(timeZoneId));
  }

  @Override
  @Nonnull
  public String createToken(@Nonnull final String username, @Nonnull final Set<RoleName> roles) {
    final var claims = Jwts.claims();
    claims.put(JwtClaims.ROLES, getRoleNames(roles));

    claims.put(JwtClaims.TIME_ZONE_ID,
            Security.SERVER_TIMEZONE_ID);

    var now = getCurrentZonedDateTime(
            claims.get(JwtClaims.TIME_ZONE_ID).toString());
    final var expirationDate = new Date(now.getTime() + validityInMilliseconds);

    return Jwts.builder()
            .setClaims(claims)
            .setSubject(username)
            .setIssuedAt(now)
            .setExpiration(expirationDate)
            .signWith(SignatureAlgorithm.HS256, secretProvider.getEncodedSecret())
            .compact();
  }

  @Override
  @Nonnull
  public Boolean isTokenValid(@Nonnull final String token) {
    final var blackListedToken = blackListingService.getBlackListedJwt(token);
    if (blackListedToken != null) {
      throw new JwtTokenBlackListedException(
              "The JWT token is blacklisted");
    }
    try {
      Jwts.parser().setSigningKey(secretProvider.getEncodedSecret()).parseClaimsJws(token);
      return !isTokenExpired(token);
    } catch (JwtAuthenticationException e) {
      throw new JwtAuthenticationException(
              "The provided JWT token is expired or invalid and cannot be used for authentication", e);
    } catch (JwtException e) {
      throw new JwtAuthenticationException(
              "The provided JWT token is expired or invalid and cannot be used for authentication", e);
    } catch (IllegalArgumentException e) {
      throw new JwtTokenIllegalArgumentException(
              "An error occurred while parsing the provided JWT token. ", e);
    }
  }

  @Override
  @Nullable
  public String parseJwt(@Nonnull final HttpServletRequest request) {
    final var headerAuth = request.getHeader(Security.TOKEN_HEADER_NAME);

    if (StringUtils.hasText(headerAuth) && headerAuth.startsWith(Security.TOKEN_PREFIX)) {
      return headerAuth.substring(7);
    }
    return null;
  }

  /**
   * Retrieve the current date and time in the specific time zone.
   *
   * @param timeZoneId the id of the time zone to use
   * @return the current date and time in the specific time zone
   */
  @Nonnull
  private Date getCurrentZonedDateTime(@Nonnull final String timeZoneId) {
    return Date.from(ZonedDateTime.of(LocalDateTime.now(),
            ZoneId.of(timeZoneId)).toInstant());
  }

  /**
   * Converts a set of roles to a set of strings.
   *
   * @param roles the set of roles that need to be converted
   * @return set of string representation of roles
   */
  @Nonnull
  private Set<String> getRoleNames(@Nonnull final Set<RoleName> roles) {
    final var roleNames = new HashSet<String>();

    roles.forEach(role -> roleNames.add(role.toString()));
    return roleNames;
  }
}
