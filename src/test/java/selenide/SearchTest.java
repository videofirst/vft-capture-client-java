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
package selenide;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

import co.videofirst.vft.capture.client.annotations.Sid;
import co.videofirst.vft.capture.client.annotations.Vft;
import co.videofirst.vft.capture.client.annotations.VftBackground;
import co.videofirst.vft.capture.client.annotations.VftBorder;
import co.videofirst.vft.capture.client.annotations.VftCapture;
import co.videofirst.vft.capture.client.annotations.VftDisplay;
import co.videofirst.vft.capture.client.annotations.VftText;
import co.videofirst.vft.capture.client.annotations.VftType;
import co.videofirst.vft.capture.client.junit4.VftRule;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;

/**
 * Search test.
 *
 * @author Bob Marks
 */
@VftType(
    feature = "Searching stuff",
    categories = {"organisation=IBM", "product=Search"},
    meta = {"version=1.2"}
)
@VftDisplay(
    alwaysOnTop = true,
    screen = 1,
    capture = @VftCapture(x = "20", y = "40", width = "200", height = "300"),
    background = @VftBackground(color = "#aabbcc", x = "10", y = "30", width = "300", height = "400"),
    border = @VftBorder(color = "#0099ff", width = 3, padding = 2),
    text = @VftText()
)
public class SearchTest {

    @Rule
    public VftRule rule = new VftRule(this);

    @Test
    @Vft(scenario = "Search by city", meta = {"author=Dave"})
    @VftDisplay(
        alwaysOnTop = true,
        screen = 1,
        capture = @VftCapture(x = "20", y = "40", width = "200", height = "300"),
        background = @VftBackground(color = "#aabbcc", x = "10", y = "30", width = "300", height = "400"),
        border = @VftBorder(color = "#0099ff", width = 3, padding = 2),
        text = @VftText()
    )
    @Sid(1943)
    public void shouldSearchByCity() {
        open("http://www.google.com");
        $(By.name("q")).shouldBe(visible).setValue("kiev");
        $(By.name("btnK")).click();

        $("h2").shouldBe(visible).shouldHave(text("Search Results"));
        $(By.xpath("//h3[contains(string(), 'Wikipedia')]")).should(exist);
    }

    //public

}
