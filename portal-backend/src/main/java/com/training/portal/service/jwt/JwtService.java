package com.training.portal.service.jwt;


import java.security.Key;
import java.util.Map;

public interface JwtService {

    String generateToken(String subjectEmail, Map<String, Object> claims);

}
