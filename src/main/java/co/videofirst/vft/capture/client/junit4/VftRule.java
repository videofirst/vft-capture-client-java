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
package co.videofirst.vft.capture.client.junit4;

import co.videofirst.vft.capture.client.api.CaptureApi;
import co.videofirst.vft.capture.client.configuration.CaptureConfig;
import co.videofirst.vft.capture.client.configuration.CaptureFactory;
import org.junit.rules.MethodRule;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

/**
 * Junit 4 rule for creating a VFT capture.
 *
 * @author Bob Marks
 */
public class VftRule implements MethodRule {

    private final Object target;
    private final CaptureFactory captureFactory;

    public VftRule(Object target) {
        this.target = target;

        captureFactory = new CaptureFactory();
    }

    @Override
    public Statement apply(Statement base, FrameworkMethod method, Object target) {
    //public Statement apply(Statement base, Description description) {
        CaptureApi captureApi = captureFactory.getCaptureApi();
        CaptureConfig captureConfig = captureFactory.getCaptureConfig();
        return new VftRuleStatement(captureApi, captureConfig, this, base, method, target);
        //return new VftRuleStatement(captureApi, captureConfig, this, base, description);
    }

    public void before() {}

    public void after() {}

    public void fail(Error error) {}

    

}
