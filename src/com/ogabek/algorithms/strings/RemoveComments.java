package com.ogabek.algorithms.strings;

import java.util.LinkedList;
import java.util.List;

public class RemoveComments {

    // time: O(n) where n is the sum of all the characters in all the strings
    // space: O(Max(source[i])) if not considering the output, otherwise O(n)
    public static List<String> removeComments(String[] source) {
        List<String> output = new LinkedList<>();

        StringBuilder sb = new StringBuilder();
        boolean blocked = false;

        for (String str : source) {
            int strLen = str.length();

            // iterate over each char of the string
            for (int i = 0; i < strLen; i++) {
                if (!blocked) {
                    // just append if it is the last char
                    if (i == strLen - 1) {
                        sb.append(str.charAt(i));
                    } else {
                        String t = str.substring(i, i + 2);
                        if ("/*".equals(t)) {
                            blocked = true;
                            i++;
                        } else if ("//".equals(t)) {
                            break;
                        } else {
                            sb.append(str.charAt(i));
                        }
                    }
                } else {
                    if (i < strLen - 1) {
                        String t = str.substring(i, i + 2);
                        if ("*/".equals(t)) {
                            blocked = false;
                            i++;
                        }
                    }
                }
            }

            // add the string to the list if not empty
            if (!blocked && sb.length() > 0) {
                output.add(sb.toString());
                sb.setLength(0);
            }
        }

        return output;
    }


}

/*test block*/
// int a = 0; /*/
//     //
// */
// int v = 2; // inline comment


// init output linkedlist
// sb <-string builder
// blocked = false

// for each str in source
//     for each currChar in str
//         if not blocked
//             if last char
//                 sb.append()
//             else
//                 t <- str.substring(i, i + 2)
//                 if t == "/*"
//                     blocked = true
//                     i++
//                 else if t == "//"
//                     break
//                 else
//                     sb.append(currChar)
//                     blocked = false
//         else
//             if i < str.length() - 1
//                 t <- str.substring(i, i + 2)
//                 if t == "*/"
//                     blocked = false
//                     i++

//     if sb.length() > 0
//         output.add(sb.toString())
//         sb = new StringBuilder()

// return output