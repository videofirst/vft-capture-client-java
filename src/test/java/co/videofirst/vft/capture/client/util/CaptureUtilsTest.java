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

import static co.videofirst.vft.capture.client.util.CaptureUtils.getFeatureFromTestClass;
import static co.videofirst.vft.capture.client.util.CaptureUtils.getScenarioFromTestMethod;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

/**
 * Unit test to test the methods of CaptureUtils.
 *
 * @author Bob Marks
 */
public class CaptureUtilsTest {

    @Test
    public void shouldConvertExceptionToString() {

        try {
            throw new RuntimeException("this is an exception");
        } catch (RuntimeException re) {
            String message = CaptureUtils.getStackTrace(new Error(re));
            System.out.println(message);
            assertThat(message)
                .startsWith("java.lang.Error: java.lang.RuntimeException: this is an exception");
        }
    }

    @Test
    public void shouldGetFeatureFromTestClass() {
        assertThat(getFeatureFromTestClass("Search")).isEqualTo("Search");
        assertThat(getFeatureFromTestClass("SearchTest")).isEqualTo("Search");
        assertThat(getFeatureFromTestClass("TestSearch")).isEqualTo("Test Search");
        assertThat(getFeatureFromTestClass("Test___Search__")).isEqualTo("Test Search");
        assertThat(getFeatureFromTestClass("AdvancedSearchTest")).isEqualTo("Advanced Search");
        assertThat(getFeatureFromTestClass("Advanced_search_Belfast_Test_"))
            .isEqualTo("Advanced search");
        assertThat(getFeatureFromTestClass("__Advanced_search___Test__"))
            .isEqualTo("Advanced search");
        assertThat(getFeatureFromTestClass("")).isEqualTo("");
        assertThat(getFeatureFromTestClass("l")).isEqualTo("L");
        assertThat(getFeatureFromTestClass("lowercase")).isEqualTo("Lowercase");
        assertThat(getFeatureFromTestClass("Class")).isEqualTo("Class");
        assertThat(getFeatureFromTestClass("MyClass")).isEqualTo("My Class");
        assertThat(getFeatureFromTestClass("HTML")).isEqualTo("HTML");
        assertThat(getFeatureFromTestClass("PDFLoader")).isEqualTo("PDF Loader");
        assertThat(getFeatureFromTestClass("AString")).isEqualTo("A String");
        assertThat(getFeatureFromTestClass("SimpleXMLParser")).isEqualTo("Simple XML Parser");
        assertThat(getFeatureFromTestClass("GL11Version")).isEqualTo("GL 11 Version");
        assertThat(getFeatureFromTestClass("99Bottles")).isEqualTo("99 Bottles");
        assertThat(getFeatureFromTestClass("May5")).isEqualTo("May 5");
        assertThat(getFeatureFromTestClass("BFG9000")).isEqualTo("BFG 9000");
    }

    @Test
    public void shouldGetScenarioFromTestMethod() {
        assertThat(getScenarioFromTestMethod("testMy_name_is___Bob")).isEqualTo("My name is Bob");
        assertThat(getScenarioFromTestMethod("shouldSearch_by_city_")).isEqualTo("Search by city");
        assertThat(getScenarioFromTestMethod("shouldSearch_byCity_")).isEqualTo("Search byCity");
        assertThat(getScenarioFromTestMethod("shouldSearchByCity")).isEqualTo("Search By City");
        assertThat(getScenarioFromTestMethod("shouldsearchbycity")).isEqualTo("Searchbycity");
        assertThat(getScenarioFromTestMethod("")).isEqualTo("");
        assertThat(getScenarioFromTestMethod("l")).isEqualTo("L");
        assertThat(getScenarioFromTestMethod("lowercase")).isEqualTo("Lowercase");
        assertThat(getScenarioFromTestMethod("Class")).isEqualTo("Class");
        assertThat(getScenarioFromTestMethod("MyClass")).isEqualTo("My Class");
        assertThat(getScenarioFromTestMethod("HTML")).isEqualTo("HTML");
        assertThat(getScenarioFromTestMethod("PDFLoader")).isEqualTo("PDF Loader");
        assertThat(getScenarioFromTestMethod("AString")).isEqualTo("A String");
        assertThat(getScenarioFromTestMethod("SimpleXMLParser")).isEqualTo("Simple XML Parser");
        assertThat(getScenarioFromTestMethod("GL11Version")).isEqualTo("GL 11 Version");
        assertThat(getScenarioFromTestMethod("99Bottles")).isEqualTo("99 Bottles");
        assertThat(getScenarioFromTestMethod("May5")).isEqualTo("May 5");
        assertThat(getScenarioFromTestMethod("BFG9000")).isEqualTo("BFG 9000");
    }

}
