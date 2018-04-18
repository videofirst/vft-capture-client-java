package co.videofirst.vft.capture.client.junit4;

import static java.lang.Boolean.TRUE;

import co.videofirst.vft.capture.client.annotations.Vft;
import co.videofirst.vft.capture.client.api.CaptureApi;
import co.videofirst.vft.capture.client.configuration.CaptureConfig;
import co.videofirst.vft.capture.client.enums.CaptureState;
import co.videofirst.vft.capture.client.enums.TestStatus;
import co.videofirst.vft.capture.client.exception.VftCaptureException;
import co.videofirst.vft.capture.client.model.capture.CaptureFinishParams;
import co.videofirst.vft.capture.client.model.capture.CaptureStartParams;
import co.videofirst.vft.capture.client.model.capture.CaptureStatus;
import co.videofirst.vft.capture.client.model.config.DisplayConfig;
import co.videofirst.vft.capture.client.util.CaptureUtils;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

/**
 * Junit statement.
 *
 * @author Bob Marks
 */
public class VftRuleStatement extends Statement {

    private final CaptureApi captureApi;
    private final CaptureConfig captureConfig;
    private final VftRule vftRule;
    private final Statement baseStatement;
    private final FrameworkMethod method;
    private final Object target;

    // Other fields
    private Vft vft, vftClass;
    private CaptureStatus captureStatus;

    public VftRuleStatement(CaptureApi captureApi, CaptureConfig captureConfig, VftRule vftRule,
        Statement statement, FrameworkMethod method, Object target) {// Description description) {
        this.captureApi = captureApi;
        this.captureConfig = captureConfig;
        this.vftRule = vftRule;
        this.baseStatement = statement;
        this.method = method;
        this.target = target;

        // set other useful fields
        this.vft = method.getAnnotation(Vft.class);
        this.vftClass = target.getClass().getAnnotation(Vft.class);
    }

    @Override
    public void evaluate() throws Throwable {
        try {
            before();

            baseStatement.evaluate();
        } catch (Error e) {
            fail(e);
            throw e;
        } finally {
            after();
        }
    }

    private void before() {
        vftRule.before(); // Can be overridden for custom setup

        Map<String, String> categories = getCategories();

        Map<String, String> meta = new HashMap<>();
        categories.put("version", "12.5");

        // 1) Read scenario from method name and override if annotation is present
        String scenario = CaptureUtils.getScenarioFromTestMethod(method.getName());
        if (vft != null && vft.scenario() != null && !vft.scenario().trim().isEmpty()) {
            scenario = vft.scenario().trim();
        }

        // 2) Read feature from class name and override if annotation is present
        String feature = CaptureUtils.getFeatureFromTestClass(target.getClass().getSimpleName());
//        if (vftClass != null && vftClass.feature() != null && !vft.feature().trim().isEmpty())  {
//            feature = vft.feature().trim();
//        }

        CaptureStartParams startParams = new CaptureStartParams();

//        for (Field field : vftRule.getClass().getFields()) {
//            Vft vft = field.getAnnotation(Vft.class);
//            if (vft != null) {
//                scenario = vft.scenario();
//            }
//        }

        startParams.setFeature(feature);
        startParams.setScenario(scenario);
        startParams.setCategories(categories);
        startParams.setMeta(meta);
        startParams.setForce(TRUE.toString());
        startParams.setDescription("Awesome description");

        DisplayConfig displayConfig = new DisplayConfig();
        startParams.setDisplay(displayConfig);

        captureStatus = captureApi.start(startParams);
        if (captureStatus.getState() != CaptureState.recording) {
            throw new VftCaptureException("Capture state = [ " + captureStatus.getState()
                + " ], cannot proceed unless it's [ recording ]");
        }
    }

    // Private methods

    private void fail(Error e) {

        if (captureStatus.getState() == CaptureState.recording) {
            CaptureFinishParams finishParams = new CaptureFinishParams();
            finishParams.setTestStatus(TestStatus.fail);
            finishParams.setError(e.getMessage());
            finishParams.setStackTrace(CaptureUtils.getStackTrace(e));
            captureStatus = captureApi.finish(finishParams);
        }

        vftRule.fail(e); // Can be overridden for custom failure handling
    }

    private void after() {

        if (captureStatus.getState() == CaptureState.recording) {
            CaptureFinishParams finishParams = new CaptureFinishParams();
            finishParams.setTestStatus(TestStatus.pass);
            captureApi.finish(finishParams);
        }

        vftRule.after();
    }

    private Map<String, String> getCategories() {
        Map<String, String> categories = new LinkedHashMap<>();
        categories.put("organisation", "Flyte");
        categories.put("product", "App");
        return categories;
    }
}
