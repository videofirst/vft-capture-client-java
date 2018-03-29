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

import co.videofirst.vft.capture.client.annotations.VftBorder;

/**
 * Configuration for a display border.
 *
 * @author Bob Marks
 */
public class DisplayBorderConfig {

    private String display;
    private String color; //
    private String padding; // pixels between capture dimensions
    private String width;

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

    public String getPadding() {
        return padding;
    }

    public void setPadding(String padding) {
        this.padding = padding;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public static DisplayBorderConfig parse(VftBorder border) {
        if (border == null) {
            return null;
        }
        DisplayBorderConfig displayBorderConfig = new DisplayBorderConfig();
        displayBorderConfig.setDisplay(String.valueOf(border.display()));
        displayBorderConfig.setColor(!border.color().equals("") ? border.color() : null);
        displayBorderConfig.setPadding(
            border.padding() != VftBorder.NULL ? String.valueOf(border.padding()) : null);
        displayBorderConfig
            .setWidth(border.width() != VftBorder.NULL ? String.valueOf(border.width()) : null);
        return displayBorderConfig;
    }

}