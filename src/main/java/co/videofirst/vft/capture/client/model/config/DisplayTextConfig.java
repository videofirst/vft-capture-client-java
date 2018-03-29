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
package co.videofirst.vft.capture.client.model.config;

import co.videofirst.vft.capture.client.annotations.VftText;

/**
 * Configuration for a displaying text.
 *
 * @author Bob Marks
 */
public class DisplayTextConfig {

    private String display;
    private String color;
    private String fontSize;
    private String x;
    private String y;

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFontSize() {
        return fontSize;
    }

    public void setFontSize(String fontSize) {
        this.fontSize = fontSize;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public static DisplayTextConfig parse(VftText text) {
        if (text == null) {
            return null;
        }
        DisplayTextConfig displayTextConfig = new DisplayTextConfig();
        displayTextConfig.setDisplay(String.valueOf(text.display()));
        displayTextConfig.setColor(!text.color().equals("") ? text.color() : null);
        displayTextConfig.setFontSize(
            text.fontSize() != VftText.NULL ? String.valueOf(text.fontSize()) : null);
        displayTextConfig.setX(!text.x().equals("") ? text.x() : null);
        displayTextConfig.setY(!text.y().equals("") ? text.y() : null);
        return displayTextConfig;
    }


}
