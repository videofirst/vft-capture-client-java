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

import co.videofirst.vft.capture.client.annotations.VftBackground;

/**
 * Configuration for display background.
 *
 * @author Bob Marks
 */
public class DisplayBackgroundConfig extends AbstractDisplayRectangleConfig {

    private String display;
    private String color;

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

    public static DisplayBackgroundConfig parse(VftBackground background) {
        if (background == null) {
            return null;
        }
        DisplayBackgroundConfig displayBackgroundConfig = new DisplayBackgroundConfig();
        displayBackgroundConfig.setX(!background.x().equals("") ? background.x() : null);
        displayBackgroundConfig.setY(!background.y().equals("") ? background.y() : null);
        displayBackgroundConfig
            .setWidth(!background.width().equals("") ? background.width() : null);
        displayBackgroundConfig
            .setHeight(!background.height().equals("") ? background.height() : null);
        displayBackgroundConfig.setDisplay(String.valueOf(background.display()));
        displayBackgroundConfig
            .setColor(!background.color().equals("") ? background.color() : null);

        return displayBackgroundConfig;
    }

}