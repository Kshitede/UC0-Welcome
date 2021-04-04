import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.regex.Pattern;

@FunctionalInterface
interface IUserValidationFunction {
    boolean validate(String strName);
    static void printValidationResult(String strName, String strError){
        ArrayList<String> listErrors = new ArrayList<String>();
        System.out.println("Validation Failed: " + strError);
   }
}

public class UserRegistrationApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        boolean validInput = true;
        Predicate<String> isThreeLetters = str -> str.length() >= 3;
        Predicate<String> isFirstLetterCaps = str -> Character.toUpperCase(str.charAt(0)) == str.charAt(0);
        validInput = true;
        do {
            System.out.println("First Name: ");
            String strFirstName = sc.next();
            validInput = true;
            if (!isThreeLetters.test(strFirstName)) {
                validInput = false;
                IUserValidationFunction.printValidationResult(strFirstName, "First Name lesser than 3 letters.");
            }
            if (!isFirstLetterCaps.test(strFirstName)) {
                validInput = false;
                IUserValidationFunction.printValidationResult(strFirstName, "First Name doesnt start with a capital letter.");
            }
        }while(!validInput);

        do {
        System.out.println("Last Name: ");
        String strLastName = sc.next();
        validInput = true;

        if(!isThreeLetters.test(strLastName)){
            validInput = false;
            IUserValidationFunction.printValidationResult(strLastName, "Last Name lesser than 3 letters.");
        }
        if(!isFirstLetterCaps.test(strLastName)){
            validInput = false;
            IUserValidationFunction.printValidationResult(strLastName, "Last Name doesnt start with a capital letter.");
        }
        }while(!validInput);

        do {
        System.out.println("Email: ");
        String strEmail = sc.next();
        validInput = true;
        Predicate<String> isEmailValid = str ->isEmailValid(str);
        if(!isEmailValid.test(strEmail)){
            validInput = false;
            IUserValidationFunction.printValidationResult(strEmail, "Please provide a valid email eg abc.xyz@bl.co.in");
        }
        }while(!validInput);

        do {
        System.out.println("Mobile: ");
        String strMobile = sc.next();
        validInput = true;
        Predicate<String> isPhoneValid = str ->isPhoneValid(str);
        if(!isPhoneValid.test(strMobile)){
            validInput = false;
            IUserValidationFunction.printValidationResult(strMobile, "Please provide a valid phone eg 91 7829827580");
        }
        }while(!validInput);

        do {
        System.out.println("Password: ");
        String strPassword = sc.next();
        validInput = true;
        Predicate<String> isPasswordValid = str ->isPasswordValid(str);
        if(!isPasswordValid.test(strPassword)){
            validInput = false;
            IUserValidationFunction.printValidationResult(strPassword, "Please provide a valid password: \n Minimum 8 Characters \n At least one upper case \n At least one number \n Has exactly one special character");
        }
        }while(!validInput);

    }

    public static boolean isEmailValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    private static boolean isPhoneValid(String strPhone) {
        String regex = "\\d{2}\\s\\d{10}"; // XXX-XXX-XXXX
        if (strPhone == null)
            return false;
        return strPhone.matches(regex);
    }

    private static boolean isPasswordValid(String strPassword) {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.+[@#$%^&+=])(?=\\S+$).{8,}$"; // XXX-XXX-XXXX
        if (strPassword == null)
            return false;
        return strPassword.matches(regex);
    }
}
