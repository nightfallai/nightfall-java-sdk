# Nightfall Java SDK

**Embed Nightfall scanning and detection functionality into Java applications**

<!-- TODO add badges [![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://travis-ci.org/joemccann/dillinger)
-->

##  Features

This SDK provides Java bindings for the Nightfall API. It allows you to add functionality to your applications to
scan plain text and files in order to detect different categories of information. You can leverage any of
the detectors in Nightfall's pre-built library, or you may programmatically define your own custom detectors. 

Additionally, this library provides convenience features such as encapsulating the steps to chunk and upload files.

To obtain an API Key, login to the [Nightfall dashboard](https://app.nightfall.ai/) and click the section
titled "Manage API Keys".

See our [developer documentation](https://docs.nightfall.ai/docs/entities-and-terms-to-know) for more details about
integrating with the Nightfall API.

## Dependencies

The Nightfall Java SDK requires Java 8 or later.

For a full list of external dependencies please consult `pom.xml`.

## Installation

### Maven
Add the following to your project's `pom.xml`:

``` xml
<dependency>
    <groupId>ai.nightfall</groupId>
    <artifactId>scan-api</artifactId>
    <version>1.0.0</version>
</dependency>
```

### Gradle
Add the following to your project's `dependencies`:

```
implementation group: 'ai.nightfall', name: 'scan-api', version: '1.0.0'
```

### Building Locally

Alternatively, if you would like to build the project yourself:
1. Clone this git repository
2. Run `mvn package` from the top-level directory.
3. The `build` directory should contain two artifacts: `api-$VERSION.jar` and `api-$VERSION-shaded.jar`. The former
contains *only* the compiled source files of this project, whereas the latter includes the compiled dependencies. 
4. Take whichever jar you prefer from the `build/` directory and add it to your project's classpath.

## Usage

### Scanning Plain Text

Nightfall provides pre-built detector types, covering data types ranging from PII to PHI to credentials. The following
snippet shows an example of how to scan using pre-built detectors.

####  Sample Code

```java
// By default, the client reads the API key from the environment variable NIGHTFALL_API_KEY
try (NightfallClient c = NightfallClient.Builder.defaultClient()) {

    // Define some detectors to use to scan your data
    Detector creditCard = new Detector("CREDIT_CARD_NUMBER");
    creditCard.setMinConfidence("LIKELY");
    creditCard.setMinNumFindings(1);
    Detector ssn = new Detector("US_SOCIAL_SECURITY_NUMBER");
    ssn.setMinConfidence("POSSIBLE");
    ssn.setMinNumFindings(1);

    // A rule contains a set of detectors to scan with
    DetectionRule rule = new DetectionRule(Arrays.asList(creditCard, ssn), "ANY");

    List<String> payload = Arrays.asList("hello world", "my SSN is 678-99-8212", "4242-4242-4242-4242");
    ScanTextConfig config = ScanTextConfig.fromDetectionRuleUUIDs(Arrays.asList(rule), 20);
    ScanTextRequest req = new ScanTextRequest(payload, config);

    ScanTextResponse response = c.scan(req);
    System.out.println("findings: " + response.getFindings());
}
```

### Scanning Files

Scanning common file types like PDF's or office documents typically requires cumbersome text
extraction methods like OCR.

Rather than implementing this functionality yourself, the Nightfall API allows you to upload the
original files, and then we'll handle the heavy lifting.

The file upload process is implemented as a series of requests to upload the file in chunks. The library
provides a single method that wraps the steps required to upload your file. Please refer to the
[API Reference](https://docs.nightfall.ai/reference) for more details.

The file is uploaded synchronously, but as files can be arbitrarily large, the scan itself is conducted asynchronously.
The results from the scan are delivered by webhook; for more information about setting up a webhook server, refer to
[the docs](https://docs.nightfall.ai/docs/creating-a-webhook-server).

#### Sample Code

```java
// By default, the client reads the API key from the environment variable NIGHTFALL_API_KEY
try (NightfallClient c = NightfallClient.Builder.defaultClient()) {

    // Define some detectors to use to scan your data
    Detector creditCard = new Detector("CREDIT_CARD_NUMBER");
    creditCard.setMinConfidence("LIKELY");
    creditCard.setMinNumFindings(1);
    Detector ssn = new Detector("US_SOCIAL_SECURITY_NUMBER");
    ssn.setMinConfidence("POSSIBLE");
    ssn.setMinNumFindings(1);

    // A rule contains a set of detectors to scan with
    DetectionRule rule = new DetectionRule(Arrays.asList(creditCard, ssn), "ANY");

    // File scans are conducted asynchronously, so provide a webhook route to an HTTPS server to send results to.
    String webhookResponseListenerURL = "https://my-service.com/nightfall/listener";
    ScanPolicy policy = ScanPolicy.fromDetectionRules(Arrays.asList(rule), webhookResponseListenerURL);
    ScanFileRequest req = new ScanFileRequest(policy, "my request metadata");

    // Upload the data to the API, then trigger the async scan
    File file = new File("./super-secret-credit-cards.pdf");
    try (InputStream stream = new FileInputStream(file)) {
        ScanFileResponse response = c.scanFile(req, stream, file.length());
        System.out.println("started scan: " + response.toString());
    }
}
```


## Contributing

Contributions are welcome! Open a pull request to fix a bug, or open an issue to discuss a new feature
or change. Please adhere to the linting criteria defined in `checkstyle.xml`, and be sure to add unit
tests for any new functionality you add.

Refer to `CONTRIBUTING.md` for the full details.

## License

This code is licensed under the terms of the MIT License. See [here](https://opensource.org/licenses/MIT)
for more information.

Java is licensed by Oracle. See [here](https://www.oracle.com/java/technologies/javase/jdk-faqs.html)
for more information.
