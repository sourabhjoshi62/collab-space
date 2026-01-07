package com.example.collab_space.service;

import com.example.collab_space.model.Otp;
import com.example.collab_space.model.User;
import com.example.collab_space.repository.OtpRepository;
import com.example.collab_space.repository.UserRepository;
import com.example.collab_space.requestDto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Random;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    OtpRepository otpRepository;

    @Autowired
    OtpService otpService;

    @Autowired
    MailService mailService;

    public void userRegistration(UserRegistrationDto registrationDto){
        User user1 = userRepository.findByEmail(registrationDto.getEmail());

        if(user1 != null && user1.isActive()){
            throw new RuntimeException("User with this email already exists");
        }
        Otp otp = null;
        if(user1 != null && !user1.isActive()){
            otp = otpService.generateOtp(user1);
            otpRepository.save(otp);
            mailService.registrationOtp(user1.getEmail(), otp.getOtp());
        }else {
            User user = new User();
            user.setActive(false);
            user.setName(registrationDto.getName());
            user.setEmail(registrationDto.getEmail());
            user.setPassword(registrationDto.getPassword());
            userRepository.save(user);
            otp = otpService.generateOtp(user);
            otpRepository.save(otp);
            mailService.registrationOtp(user.getEmail(), otp.getOtp());
        }

    }
}
