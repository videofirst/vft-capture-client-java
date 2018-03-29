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

import co.videofirst.vft.capture.client.annotations.VftDisplay;

/**
 * Configuration for updating the display.
 *
 * @author Bob Marks
 */
public class DisplayConfig {

    private String screen;
    private String alwaysOnTop;

    private DisplayCaptureConfig capture;
    private DisplayBackgroundConfig background;
    private DisplayBorderConfig border;
    private DisplayTextConfig text;

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public String getAlwaysOnTop() {
        return alwaysOnTop;
    }

    public void setAlwaysOnTop(String alwaysOnTop) {
        this.alwaysOnTop = alwaysOnTop;
    }

    public DisplayCaptureConfig getCapture() {
        return capture;
    }

    public void setCapture(DisplayCaptureConfig capture) {
        this.capture = capture;
    }

    public DisplayBackgroundConfig getBackground() {
        return background;
    }

    public void setBackground(DisplayBackgroundConfig background) {
        this.background = background;
    }

    public DisplayBorderConfig getBorder() {
        return border;
    }

    public void setBorder(DisplayBorderConfig border) {
        this.border = border;
    }

    public DisplayTextConfig getText() {
        return text;
    }

    public void setText(DisplayTextConfig text) {
        this.text = text;
    }

    public static DisplayConfig parse(VftDisplay display) {
        if (display == null) {
            return null;
        }
        DisplayConfig displayConfig = new DisplayConfig();
        displayConfig.setScreen(
            display.screen() != VftDisplay.NULL ? String.valueOf(display.screen()) : null);
        displayConfig.setAlwaysOnTop(String.valueOf(display.alwaysOnTop()));
        displayConfig.setCapture(
            display.capture().length == 1 ? DisplayCaptureConfig.parse(display.capture()[0])
                : null);
        displayConfig.setBackground(display.background().length == 1 ? DisplayBackgroundConfig
            .parse(display.background()[0]) : null);
        displayConfig.setBorder(display.border().length == 1 ? DisplayBorderConfig
            .parse(display.border()[0]) : null);
        displayConfig.setText(display.text().length == 1 ? DisplayTextConfig
            .parse(display.text()[0]) : null);
        return displayConfig;
    }

}
