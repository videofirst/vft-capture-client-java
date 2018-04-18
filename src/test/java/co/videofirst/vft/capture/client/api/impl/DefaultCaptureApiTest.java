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
package co.videofirst.vft.capture.client.api.impl;

import co.videofirst.vft.capture.client.annotations.Vft;
import co.videofirst.vft.capture.client.enums.LogTier;
import co.videofirst.vft.capture.client.enums.TestStatus;
import co.videofirst.vft.capture.client.model.TestLog;
import co.videofirst.vft.capture.client.model.capture.Capture;
import co.videofirst.vft.capture.client.model.capture.CaptureFinishParams;
import co.videofirst.vft.capture.client.model.capture.CaptureStartParams;
import co.videofirst.vft.capture.client.model.capture.CaptureStatus;
import co.videofirst.vft.capture.client.model.capture.CaptureSummary;
import co.videofirst.vft.capture.client.model.info.Info;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;

/**
 * Unit test to test the methods of DefaultCaptureApi.
 *
 * Really this should be an integration test e.g. load VFT-Capture as an in memory app.
 *
 * @author Bob Marks
 */
public class DefaultCaptureApiTest {

    @Vft
    @Test
    public void testSelect() {
        System.out.println("test select");
    }

    public static void main(String[] args) throws InterruptedException {
        String url = "http://localhost:1357/api";
        String user = "test";
        String pass = "password";
        DefaultCaptureApi api = new DefaultCaptureApi(url, user, pass);

        // Info

        Info info = api.info();
        System.out.println(info);

        // Cancel

        api.cancel();

        // Start

        CaptureStartParams captureStartParams = new CaptureStartParams();
        captureStartParams.setCategories(asMap("organisation", "Google", "product", "Search"));
        captureStartParams.setFeature("Integration");
        captureStartParams.setScenario("Main Method");
        captureStartParams.setRecord("false");
        CaptureStatus captureStatus = api.start(captureStartParams);

        // Record

        captureStatus = api.record();
        Thread.sleep(1000);

        // Stop

        captureStatus = api.stop();

        // Finish

        TestLog testLog = new TestLog();
        testLog.setCat("browser");
        testLog.setLog("starting browser up");
        testLog.setTier(LogTier.L1);
        testLog.setTs(LocalDateTime.of(2001, 01, 02, 03, 04, 05));

        CaptureFinishParams captureFinishParams = new CaptureFinishParams();
        captureFinishParams.setTestStatus(TestStatus.fail);
        captureFinishParams.setDescription("awesome description");
        captureFinishParams.setError("awesome error");
        captureFinishParams.setMeta(asMap("a", "1", "b", "2"));
        captureFinishParams.setLogs(Arrays.asList(testLog));

        captureStatus = api.finish(captureFinishParams);

        // List

        List<CaptureSummary> list = api.list();
        for (CaptureSummary summary : list) {
            System.out.println(summary.getId());
        }

        // Select

        Capture capture = api.select(list.get(0).getId());
        System.out.println(capture.getId());

    }

    private static Map<String, String> asMap(String... keyValues) {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < keyValues.length; i += 2) {
            map.put(keyValues[i], keyValues[i + 1]);
        }
        return map;
    }

}
