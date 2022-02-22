package ai.nightfall.examples;

import ai.nightfall.scan.NightfallClient;
import ai.nightfall.scan.model.Confidence;
import ai.nightfall.scan.model.DetectionRule;
import ai.nightfall.scan.model.Detector;
import ai.nightfall.scan.model.LogicalOp;
import ai.nightfall.scan.model.ScanFileRequest;
import ai.nightfall.scan.model.ScanFileResponse;
import ai.nightfall.scan.model.ScanPolicy;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/**
 * FileScannerExample provides a simple example of a program that sends text to Nightfall's file detection API.
 * NIGHTFALL_API_KEY must be set as an environment variable, and webhookURL must be a running webhook endpoint
 * that is ready to receive a response.
 *
 * For an example webhook server, see https://docs.nightfall.ai/docs/creating-a-webhook-server
 *
 */
public class FileScannerExample {
    public static final String usage = "Usage: scanner <webhookURL> <filename>";

    /**
     * Submit the provided files for scanning and print the result.
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            throw new RuntimeException(usage);
        }
        // File scans are conducted asynchronously, so provide a webhook route to an HTTPS server to send results to.
        String webhookResponseListenerURL = args[0];
        String file = args[1];
        try (NightfallClient client = NightfallClient.Builder.defaultClient()) {
            // Define some detectors to use to scan your data
            Detector creditCard = new Detector("CREDIT_CARD_NUMBER");
            creditCard.setMinConfidence(Confidence.LIKELY);
            creditCard.setMinNumFindings(1);
            Detector ssn = new Detector("US_SOCIAL_SECURITY_NUMBER");
            ssn.setMinConfidence(Confidence.POSSIBLE);
            ssn.setMinNumFindings(1);

            // A rule contains a set of detectors to scan with
            DetectionRule rule = new DetectionRule(Arrays.asList(creditCard, ssn), LogicalOp.ANY);

            ScanPolicy policy = ScanPolicy.fromDetectionRules(Arrays.asList(rule), webhookResponseListenerURL);
            ScanFileRequest req = new ScanFileRequest(policy, "my request metadata");

            // Upload the data to the API, then trigger the async scan
            File file1 = new File(file);
            try (InputStream stream = new FileInputStream(file1)) {
                ScanFileResponse response = client.scanFile(req, stream, file1.length());
                System.out.println("started scan: " + response.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
