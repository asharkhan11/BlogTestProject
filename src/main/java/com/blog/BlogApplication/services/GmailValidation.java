package com.blog.BlogApplication.services;
import org.springframework.stereotype.Service;
import java.util.regex.*;

@Service
public class GmailValidation {
    private static final String GMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@gmail\\.com$";

    public static boolean isValidGmail(String email) {
        Pattern pattern = Pattern.compile(GMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}