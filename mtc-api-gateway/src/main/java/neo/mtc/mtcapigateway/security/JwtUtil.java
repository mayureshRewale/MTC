package neo.mtc.mtcapigateway.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {

//    @Value("${jwt.secret}")
    private String secret = "3F4528482B4D6251655468576D597133743677397A24432646294A404E635266";

    private Key key;

    @PostConstruct
    public void init(){
//        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(secret));
    }

    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    private boolean isTokenExpired(String token) {
        return this.getAllClaimsFromToken(token).getExpiration().before(new Date());
    }

    public boolean isInvalid(String token) {
        return this.isTokenExpired(token);
    }

}
