package java_base.objects;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static final String TEXT = "aaababaabaaaabaabaabaabaaababaabaaababaabaaaabaabaabaabbabaabaaababaababaabaabaabaaabbaab";
    public static final String PATTERN = "aab";

    static Pattern pattern = Pattern.compile(PATTERN);
    static Matcher matcher = pattern.matcher(TEXT);

    public static void main(String[] args) {
        int count = 0;

        while (matcher.find()) {
            count++;
        }

        System.out.println("Строка " + PATTERN + " встретилась в тексте " + count + " раз");
    }
}