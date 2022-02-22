package ai.nightfall.examples;


import ai.nightfall.scan.NightfallClient;
import ai.nightfall.scan.model.Confidence;
import ai.nightfall.scan.model.DetectionRule;
import ai.nightfall.scan.model.Detector;
import ai.nightfall.scan.model.LogicalOp;
import ai.nightfall.scan.model.ScanTextConfig;
import ai.nightfall.scan.model.ScanTextRequest;
import ai.nightfall.scan.model.ScanTextResponse;

import java.util.Arrays;
import java.util.List;


/**
 * TextScannerExample provides a simple example of a program that sends text to Nightfall's detection API.
 * NIGHTFALL_API_KEY must be set as an environment variable.
 *
 */
public class TextScannerExample {
    public static final String usage = "Usage: scanner <string> ...";

    /**
     * Submit the provided args for scanning and print the result.
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            throw new RuntimeException(usage);
        }
        try (NightfallClient client = NightfallClient.Builder.defaultClient()) {

            Detector creditCard = new Detector("CREDIT_CARD_NUMBER");
            creditCard.setMinConfidence(Confidence.LIKELY);
            creditCard.setMinNumFindings(1);
            Detector ssn = new Detector("US_SOCIAL_SECURITY_NUMBER");
            ssn.setMinConfidence(Confidence.POSSIBLE);
            ssn.setMinNumFindings(1);

            // A rule contains a set of detectors to scan with
            DetectionRule rule = new DetectionRule(Arrays.asList(creditCard, ssn), LogicalOp.ANY);

            List<String> payload = Arrays.asList(args).subList(1, args.length);
            // Define some detectors to use to scan your data
            ScanTextConfig config = ScanTextConfig.fromDetectionRules(Arrays.asList(rule), 20);
            ScanTextRequest req = new ScanTextRequest(payload, config);

            ScanTextResponse response = client.scanText(req);
            System.out.println("findings: " + response.getFindings());

        }
    }
}
