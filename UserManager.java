package com.example.messagingprojoop;

import java.io.*;
import java.util.*;

public class UserManager {

    private static final String FILE_PATH = "src/data/users.txt";

    public static boolean registerUser(User user) {

        try {

            File file = new File(FILE_PATH);

            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;

            while ((line = br.readLine()) != null) {

                String[] parts = line.split(",");

                if (parts[0].equalsIgnoreCase(user.getEmail())
                        || parts[1].equalsIgnoreCase(user.getUsername())) {

                    br.close();
                    return false;
                }
            }

            br.close();

            BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));

            String encryptedPassword =
                    EncryptionUtil.encrypt(user.getPassword());

            bw.write(user.getEmail() + ","
                    + user.getUsername() + ","
                    + encryptedPassword);

            bw.newLine();

            bw.close();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static User loginUser(String input,String password){

        try{

            BufferedReader br=
                    new BufferedReader(
                            new FileReader(FILE_PATH)
                    );

            String line;

            while((line=br.readLine())!=null){

                String[] parts=line.split(",");

                String email=parts[0];
                String username=parts[1];

                String encryptedPass=parts[2];

                String decryptedPass=
                        EncryptionUtil.decrypt(
                                encryptedPass
                        );

                if((input.equalsIgnoreCase(email)
                        || input.equalsIgnoreCase(username))
                        && password.equals(decryptedPass)){

                    br.close();

                    return new User(
                            email,
                            username,
                            password
                    );
                }
            }

            br.close();

        }
        catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public static List<String> getAllUsers() {

        List<String> users = new ArrayList<>();

        try {

            BufferedReader br =
                    new BufferedReader(new FileReader(FILE_PATH));

            String line;

            while ((line = br.readLine()) != null) {

                String[] parts = line.split(",");

                users.add(parts[1]);
            }

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }
}
