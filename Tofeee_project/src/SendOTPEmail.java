import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;
import javax.activation.*;

public class SendOTPEmail {

    // Generate OTP
    private String otp = generateOTP();

    public String getOtp(){
        return otp;
    }

    // Method to generate OTP
    public static String generateOTP() {
        Random random = new Random();
        int otpLength = 6;
        StringBuilder otp = new StringBuilder(otpLength);
        for (int i = 0; i < otpLength; i++) {
            otp.append(random.nextInt(10));
        }
        return otp.toString();
    }
    public void sendOtp(String email) {

        // Sender's email address and password
        // ****replace it with your credentials****
        String from = "your-email@example.com";
        String password = "your-password";

        // Email host and port configuration
        String host = "smtp.gmail.com";
        String port = "587";

        // Email subject and body
        String subject = "Your One Time Password (OTP)";
        String body = "Your OTP is: " + otp;

        // Email properties configuration
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.debug", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // Email session creation
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(from, password);
            }
        });

        try {
            // Create email message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject(subject);
            message.setText(body);

            // Send email message
            Transport.send(message);

            System.out.println("\n------OTP sent successfully to " + email + " ---------\n");

        } catch (MessagingException e) {
                throw new RuntimeException(e);
        }
    }


}
