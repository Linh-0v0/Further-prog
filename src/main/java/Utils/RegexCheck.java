package Utils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexCheck {
    public static boolean csvCheck(String str) {
        //check if it is a csv file
       Pattern pattern = Pattern.compile(".*(.csv)$");
       Matcher matcher = pattern.matcher(str);
       boolean matchFound = matcher.find();
       return matchFound;
    }
}
