package com.backend.hormonalcare.iam.infrastructure.hashing.bcrypt;

import com.backend.hormonalcare.iam.application.internal.outboundservices.hashing.HashingService;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface BCryptHashingService extends HashingService, PasswordEncoder {
}
