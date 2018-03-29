/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017-present, Video First Software
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package co.videofirst.vft.capture.client.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.annotation.Annotation;
import org.junit.internal.runners.statements.InvokeMethod;
import org.junit.runners.model.Statement;

/**
 * Collection of useful static methods.
 *
 * @author Bob Marks
 */
public class CaptureUtils {

    /**
     * Convert Exception stacktrace to a String.
     *
     * @param e
     * @return
     */
    public static String getStackTrace (Error e) {
        if (e == null) {
            return null;
        }
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }

    /**
     * Return a label from a test method name.
     *
     * @param input
     * @return
     */
    public static String getFeatureFromTestClass(String input) {
        if (input == null) {
            return null;
        }
        // 1) Trim dashes and "Test" at end + upper case first letter.
        String feature = input.replaceAll("^_+|(_*[tT]est_*)$", "");
        feature = upperCaseFirstLetter(feature);
        feature = replaceUnderScoresOrCamelToSpaces(feature);
        return feature;
    }

    /**
     * Return a label from a test method.
     *
     * @param input
     * @return
     */
    public static String getScenarioFromTestMethod(String input) {
        if (input == null) {
            return null;
        }
        // 1) Trim dashes and "Test"
        String scenario = input.replaceAll("^(_*(should|test)_*)|_+$", "");
        scenario = upperCaseFirstLetter(scenario);
        scenario = replaceUnderScoresOrCamelToSpaces(scenario);
        return scenario;
    }

    // Private methods

    private static String upperCaseFirstLetter(String input) {
        if (input != null && input.length() > 0) {
            return input.substring(0, 1).toUpperCase() + input.substring(1);
        }
        return input;
    }

    /**
     * This method checks if the inputted String contains underscores - if it does then it converts
     * the underscores to spaces.  Otherwise it will convert camelCase to spaces.
     * @param input
     * @return
     */
    private static String replaceUnderScoresOrCamelToSpaces (String input) {
        if (input.contains("_")) {
            input = input.replaceAll("_+", " ").trim();
            return input;
        }
        else {
            // Ref [ https://stackoverflow.com/a/2560017/1692179 ]
            input = input.replaceAll(
                String.format("%s|%s|%s",
                    "(?<=[A-Z])(?=[A-Z][a-z])",
                    "(?<=[^A-Z])(?=[A-Z])",
                    "(?<=[A-Za-z])(?=[^A-Za-z])"
                ),
                " "
            ).trim();
        }
        return input;
    }

}
