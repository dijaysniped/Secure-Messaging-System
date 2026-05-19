package com.example.messagingprojoop;

import java.security.SecureRandom;

public class OTPgen implements authentication {
 
    private String currentOTP;                  
    private long generatedAt;                 
    private static final long EXPIRE = 120000; // 2 min otp expiry
    private final SecureRandom secureRandom = new SecureRandom(); //creates random numbers unlike math.random()
 
    @Override
    public String generateOTP() {
        currentOTP= String.valueOf(100000+ secureRandom.nextInt(900000));
        generatedAt= System.currentTimeMillis();
        return currentOTP;
    }
 
    @Override
    public boolean verifyOTP(String inputOTP) {
        if (currentOTP == null) {
            return false;
        }

        boolean expired= (System.currentTimeMillis() - generatedAt) >EXPIRE;
        if (expired) {
            currentOTP = null; 
            return false;
        }
 
        boolean matched= currentOTP.equals(inputOTP.trim());
        if (matched){
            currentOTP= null; 
        }
        return matched;
    }
  
    //showing a countdown timer in the GUI
    public long secondsRemaining() {
        if (currentOTP == null){
             return 0;
        }

        long elapsed= System.currentTimeMillis() - generatedAt;
        long remaining= (EXPIRE - elapsed) / 1000;
        return Math.max(remaining, 0);
    }
 
    
    public boolean hasActiveOTP() {
        if (currentOTP == null){
             return false;
        }
        return (System.currentTimeMillis() -generatedAt) <= EXPIRE;
    }


}