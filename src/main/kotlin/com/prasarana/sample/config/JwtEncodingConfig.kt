package com.prasarana.sample.config

import com.nimbusds.jose.jwk.source.ImmutableSecret
import com.nimbusds.jose.proc.SecurityContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder
import javax.crypto.spec.SecretKeySpec

@Configuration
class JwtEncodingConfig{
//    private val secretKey = SecretKeySpec(jwtKey.toByteArray(), "HmacSHA256")
    private val secretKey = SecretKeySpec("7c71d30e6b46e60d2e0f81e5e94db5164b1e9605e7b17abfd90353778be2e440".toByteArray(), "HmacSHA256")

    @Bean
    fun jwtDecoder(): JwtDecoder{
        return NimbusJwtDecoder.withSecretKey(secretKey).build()
    }

    @Bean
    fun jwtEncoder(): JwtEncoder{
        val secret = ImmutableSecret<SecurityContext>(secretKey)
        return NimbusJwtEncoder(secret)
    }
}

//@Component
//class JWTTokenUtil {
//    private val expiredDate:Long = 5*60*60
//    private val secret:String = "kerolKacak"
//    fun getUsernameFromToken(token: String): String? {
//        return getClaimFromToken(token, Claims::getSubject)
//    }
//    fun getExpirationDateFromToken(token: String): Date {
//        return getClaimFromToken(token, Claims::getExpiration)
//    }
//    fun <T> getClaimFromToken(token: String, claimsResolver: (Claims)-> T): T {
//        val claims: Claims = getAllClaimsFromToken(token)
//        return claimsResolver.invoke(claims)
//    }
//    //for retrieving any information from token we will need the secret key
//    private fun getAllClaimsFromToken(token: String): Claims {
//        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).body
//    }
//    //Check token expired
//    private fun isTokenExpired(token:String):Boolean{
//        val expiration = getExpirationDateFromToken(token)
//        return expiration.before(Date())
//    }
//    fun generateToken(userDetails: UserDetails): String{
//        val claims = mutableMapOf<String, Any>()
//        return doGenerateToken(claims, userDetails.username)
//    }
//    private fun doGenerateToken(claims: Map<String, Any>, subject: String): String {
//        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(Date(System.currentTimeMillis()))
//            .setExpiration(Date(System.currentTimeMillis() + expiredDate * 1000))
//            .signWith(SignatureAlgorithm.HS512, secret).compact()
//    }
//    //validate token
//    fun validateToken(token: String?, userDetails: UserDetails): Boolean? {
//        val username = getUsernameFromToken(token!!)
//        return username == userDetails.username && !isTokenExpired(token)
//    }
//}

