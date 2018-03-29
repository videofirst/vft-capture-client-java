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
package co.videofirst.vft.capture.client.model.info;

import co.videofirst.vft.capture.client.model.UploadStatus;
import co.videofirst.vft.capture.client.model.VftDefaults;
import co.videofirst.vft.capture.client.model.capture.CaptureStatus;
import java.util.List;

/**
 * Top-level information object.
 *
 * @author Bob Marks
 */
public class Info {

    private ConfigInfo info;
    private VftDefaults defaults;
    private CaptureStatus captureStatus;
    private List<UploadStatus> uploads;

    public ConfigInfo getInfo() {
        return info;
    }

    public void setInfo(ConfigInfo info) {
        this.info = info;
    }

    public VftDefaults getDefaults() {
        return defaults;
    }

    public void setDefaults(VftDefaults defaults) {
        this.defaults = defaults;
    }

    public CaptureStatus getCaptureStatus() {
        return captureStatus;
    }

    public void setCaptureStatus(CaptureStatus captureStatus) {
        this.captureStatus = captureStatus;
    }

    public List<UploadStatus> getUploads() {
        return uploads;
    }

    public void setUploads(List<UploadStatus> uploads) {
        this.uploads = uploads;
    }

}
