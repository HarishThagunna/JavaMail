import java.io.*;
import java.util.Properties;
import java.util.Scanner;
import javax.mail.*;


public class ReceiveMail {
    public static void receiveMessage(String username, String password){
        try{
            Properties properties = new Properties();
            properties.setProperty("mail.store.protocol","imaps");
            Session emailSession = Session.getDefaultInstance(properties);
            Store emailStore = emailSession.getStore("imaps");
            emailStore.connect("imap.gmail.com",username, password);
            Folder emailFolder = emailStore.getFolder("Inbox");
            emailFolder.open(Folder.READ_ONLY);
            Message messages[] =emailFolder.getMessages();
            for(int i= messages.length-5;i<messages.length;i++){
                System.out.println("Email Number: "+(i+1));
                System.out.println("Subject: "+messages[i].getSubject());
                System.out.println("From: "+messages[i].getFrom()[0]);
                System.out.println("Sent Date: "+messages[i].getSentDate());
                System.out.println("Reply To: "+messages[i].getReplyTo()[0]);
                System.out.println("Reply To: "+messages[i].getReceivedDate());
                System.out.println("----XXXX----XXXX---------");
            }
            emailFolder.close(false);
            emailStore.close();

        }catch (NoSuchProviderException nspe){
            nspe.printStackTrace();
        }catch (MessagingException me){
            me.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter username: ");
        String username = sc.next();
        System.out.println("Enter password: ");
        String password = sc.next();
        receiveMessage(username,password);
    }
}
