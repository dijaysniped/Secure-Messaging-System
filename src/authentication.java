public interface authentication {
    String  generateOTP();
    boolean verifyOTP(String inputOTP);
}
