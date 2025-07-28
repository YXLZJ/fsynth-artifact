package erepair.program.test;

import erepair.program.Main;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.TestPlan;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;
import org.junit.platform.reporting.legacy.xml.LegacyXmlReportGeneratingListener;

import java.io.PrintWriter;
import java.nio.file.Paths;

import static org.junit.platform.engine.discovery.DiscoverySelectors.selectPackage;


/**
 **/
public class TestExecutor {
    private SummaryGeneratingListener summaryGeneratingListener = new SummaryGeneratingListener();
    private LegacyXmlReportGeneratingListener xmlListener = new LegacyXmlReportGeneratingListener(Main.REPORTS_FOLDER, new PrintWriter(System.out));

    public static void runTests() {
        TestExecutor runner = new TestExecutor();
        runner.runAllTests();

        TestExecutionSummary summary = runner.summaryGeneratingListener.getSummary();
        summary.printFailuresTo(new PrintWriter(System.out));
        summary.printTo(new PrintWriter(System.out));
    }

    public void runAllTests() {
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                .selectors(selectPackage("erepair.program.test.unittest"))
//                .filters(includeClassNamePatterns(".*Test"))
                .build();
        Launcher launcher = LauncherFactory.create();
        TestPlan testPlan = launcher.discover(request);
        launcher.registerTestExecutionListeners(summaryGeneratingListener);
        launcher.registerTestExecutionListeners(xmlListener);
        launcher.execute(request);
    }
}
