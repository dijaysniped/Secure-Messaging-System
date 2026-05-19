package com.example.messagingprojoop;

import java.io.*;
import java.util.*;

public class MessageManager {

    private static final String MESSAGE_DIR = "src/data/messages/";

    public static void sendMessage(Message msg) {

        try {

            File dir = new File(MESSAGE_DIR);

            if (!dir.exists()) {
                dir.mkdirs();
            }

            String filename = generateFileName(
                    msg.getSender(),
                    msg.getReceiver()
            );

            File file = new File(MESSAGE_DIR + filename);

            BufferedWriter bw =
                    new BufferedWriter(new FileWriter(file, true));

            String encrypted =
                    EncryptionUtil.encrypt(
                            msg.getSender() + ":" + msg.getText()
                    );

            bw.write(encrypted);

            bw.newLine();

            bw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<String> loadMessages(
            String user1,
            String user2
    ) {

        List<String> chats = new ArrayList<>();

        try {

            String filename = generateFileName(user1, user2);

            File file = new File(MESSAGE_DIR + filename);

            if (!file.exists()) {
                return chats;
            }

            BufferedReader br =
                    new BufferedReader(new FileReader(file));

            String line;

            while ((line = br.readLine()) != null) {

                String decrypted =
                        EncryptionUtil.decrypt(line);

                chats.add(decrypted);
            }

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return chats;
    }

    private static String generateFileName(
            String a,
            String b
    ) {

        List<String> users = Arrays.asList(a, b);

        Collections.sort(users);

        return users.get(0) + "_" + users.get(1) + ".txt";
    }
}
