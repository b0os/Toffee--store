import java.util.*;
import java.util.regex.*;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class AccountManger {

    Scanner in = new Scanner(System.in);

    private User user;


// Getters
    public User getUser() {
        return user;
    }

    // methods

    // check if the email used before
    private boolean emailUsed(String em){

        String filename = "database/registration_info.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if(line.equals(em)) {
                    return true;
                }
            }

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return false;
    }

    // check validation of the email
    private boolean emailValid(String em) {
        String EMAIL_REGEX =
        "^[a-zA-Z0-9_+&*-]+(?:\\." +
        "[a-zA-Z0-9_+&*-]+)*@" +
        "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
        "A-Z]{2,7}$";

        Pattern emailPattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = emailPattern.matcher(user.getEmail());

        return matcher.matches();
    }

    // Minimum length of 8 characters.
    //Contains at least one uppercase letter.
    //Contains at least one lowercase letter.
    //Contains at least one digit.
    //Contains at least one special character (such as !, @, #, $, %, etc.).
    private boolean passValid(String ps){
        String PASS_REGEX =
                "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=-])[A-Za-z\\d@#$%^&+=-]{8,}$";

        Pattern passPattern = Pattern.compile(PASS_REGEX);
        Matcher matcher = passPattern.matcher(ps);

        return matcher.matches();
    }


    // Encrypt password using SHA-256 algorithm
    private String encryptPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }


    // save user info after registration in a file
    private void saveInfo() {
        String filename = "database/registration_info.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {

                // write name to the file
                writer.write(user.getName());
                writer.newLine();

                // write address to the file
                writer.write(user.getAdr());
                writer.newLine();

                // write phone to the file
                writer.write(user.getPhone());
                writer.newLine();

                // write email to the file
                writer.write(user.getEmail());
                writer.newLine();

                // write pass to the file
                String encryptedPassword = encryptPassword(user.getPass());
                writer.write(encryptedPassword);
                writer.newLine();

                writer.newLine();  // Add an extra new line separator between each user's data

            System.out.println("Registration information saved to " + filename);
        } catch (IOException e) {
            System.err.println("Error saving registration information: " + e.getMessage());
        }

    }

    // register new user
    public void registerNewUser() {
        int x = 0, i = 3; String OTP = "";

        String pass_rules =   "Note that the rules of password is\n" +
                "* Minimum length of 8 characters.\n" +
                "* Contains at least one uppercase letter.\n" +
                "* Contains at least one lowercase letter.\n" +
                "* Contains at least one digit.\n" +
                "* Contains at least one special character (such as !, @, #, $, %, etc.).\n";

        user = new User();

        System.out.println("Please Enter your full name:");
        user.setName(in.nextLine());

        do {
            if(x == 0)
                System.out.println("\nPlease Enter your Email:");
            else {
                if(!emailValid(user.getEmail()))
                    System.out.println("\nWrong email format\nPlease Enter valid Email:");
                else if(emailUsed(user.getEmail()))
                    System.out.println("\nEmail used before in the system!!\nPlease Enter another Email:");
            }
            user.setEmail(in.nextLine());
            x++;
        } while(!emailValid(user.getEmail()) || emailUsed(user.getEmail()));

        System.out.println("\nPlease Enter your Address:");
        user.setAddress(in.nextLine());

        System.out.println("\nPlease Enter your Phone:");
        user.setPhone(in.nextLine());

        x = 0;
        do {
            if(x == 0)
                System.out.println("\nPlease Enter your Password:\n" + pass_rules);
            else {
                System.out.println("\n--Weak Password--, please enter strong one following this rules\n" +
                        pass_rules + "Please Enter valid Password:\n");
            }
            user.setPass(in.nextLine());
            x++;
        }while(!passValid(user.getPass()));

        SendOTPEmail otp = new SendOTPEmail();
        otp.sendOtp(user.getEmail());


        do {
            if(i == 0)
                break;
            if(i == 3)
                System.out.println("Enter the otp: ");
            else
                System.out.println("\n-----Wrong otp, you have " + i + " time(s) left------\nEnter the otp: ");
            OTP = in.nextLine();
            i--;
        }while(!otp.getOtp().equals(OTP));

        if (otp.getOtp().equals(OTP))
            saveInfo();
        else
            System.out.println("\n-----Account not registered... wrong otp try again---\n");

    }


    // Login methods
    public boolean loginUser() {
        user = new User();
        String inEmail, inPass, email = "", pass = "";
        String filename = "database/registration_info.txt";

        System.out.println("Please Enter your email");
        inEmail = in.nextLine();

        System.out.println("\nPlease Enter your password");
        inPass = in.nextLine();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.equals(inEmail)) {
                    // Read another line for encrypted password
                    line = reader.readLine();
                    // No decryption needed for hashed passwords
                    pass = line;
                }
            }
            // Hash the entered password using the same algorithm and compare with stored hashed password
            String hashedEnteredPassword = encryptPassword(inPass);
            if (pass.equals(hashedEnteredPassword)) {
                System.out.println("Successful login, welcome to the store\n");
                return true;
            }

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        return false;
    }

    public void logOut(){
        user = null;
    }


}
