# Nightfall Java SDK

**Embed Nightfall scanning and detection functionality into Java applications**

TODO

<!-- TODO add badges [![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://travis-ci.org/joemccann/dillinger)
-->

##  Features

TODO

See our [developer documentation](https://docs.nightfall.ai/docs/entities-and-terms-to-know) for more details about
integrating with the Nightfall API.

TODO: maybe add a section about signing up for an API key? or maybe we just link to that specifically in the docs.

## Dependencies

The Nightfall Java SDK requires Java 8 or later.

The SDK also requires the following libraries:

| group                         | artifact           | version    |
| ----------------------------- | ------------------ | ---------- |
| com.fasterxml.jackson.core	| jackson-databind	 |  2.13.0    |
| com.fasterxml.jackson.core	| jackson-annotation |  2.13.0    |
| com.squareup.okhttp3			| okhttp             |  4.9.2     |

For a full list of dependencies please consult the `pom.xml` file

## Installation

### Maven
Add the following to your project's `pom.xml`:

``` xml
<dependency>
    <groupId>ai.nightfall.api</groupId>
    <artifactId>scan</artifactId>
    <version>1.0</version>
</dependency>
```

### Gradle
Add the following to your project's `dependencies`:

```
implementation group: 'ai.nightfall.api', name: 'scan', version: '1.0'
```

### Building Locally

Alternatively, if you would like to build the project yourself:
1. Clone this git repository
2. Run `mvn package` from the top-level directory.
3. The `build` directory should contain two artifacts: `api-$VERSION.jar` and `api-$VERSION-shaded.jar`. The former
contains *only* the compiled source files of this project, whereas the latter includes the compiled dependencies. 
4. Take whichever jar you prefer from the `build/` directory and add it to your project's classpath.
> Steps for building the project

## Usage

### Scanning Plaintext

Nightfall provides pre-built detector types, covering data types ranging from PII to PHI to credentials. The following
snippet shows an example of how to scan using pre-built detectors.

####  Sample Code

```java
// By default, the client reads the API key from the environment variable NIGHTFALL_API_KEY
try (NightfallClient c = NightfallClient.Builder.defaultClient()) {

    // Define some detectors to use to scan your data
    Detector creditCard = new Detector("CREDIT_CARD_NUMBER");
    Detector ssn = new Detector("US_SOCIAL_SECURITY_NUMBER");

    // A rule contains a set of detectors to scan with
    DetectionRule rule = new DetectionRule();
    rule.setDetectors(Arrays.asList(creditCard, ssn));
    rule.setLogicalOp("ANY");

    List<String> payload = Arrays.asList("hello world", "my SSN is 678-99-8212", "4242-4242-4242-4242");
    ScanTextConfig config = ScanTextConfig.fromDetectionRuleUUIDs(Arrays.asList(rule), 20);
    ScanTextRequest req = new ScanTextRequest(payload, config);

    ScanTextResponse response = c.scan(req);
    System.out.println("findings: " + response.getFindings());
}
```

### Scanning Files

In order to scan common file types like PDF's or office documents, traditional methods require cumbersome text
extraction methods like OCR. Rather than implementing this yourself, the Nightfall API allows you to upload the
original files, and we'll handle the heavy lifting.

As files can be arbitrarily large, these scans are conducted asynchronously, and the scan results are delivered
by webhook. For more information about setting up your webhook server, refer to
[the docs](https://docs.nightfall.ai/docs/creating-a-webhook-server).

####  Sample Code

```java
// By default, the client reads the API key from the environment variable NIGHTFALL_API_KEY
try (NightfallClient c = NightfallClient.Builder.defaultClient()) {

    // Define some detectors to use to scan your data
    Detector creditCard = new Detector("CREDIT_CARD_NUMBER");
    Detector ssn = new Detector("US_SOCIAL_SECURITY_NUMBER");

    // A rule contains a set of detectors to scan with
    DetectionRule rule = new DetectionRule();
    rule.setDetectors(Arrays.asList(creditCard, ssn));
    rule.setLogicalOp("ANY");

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

## License

This code is licensed under the terms of the MIT License. See [here](https://opensource.org/licenses/MIT)
for more information.

Java is licensed by Oracle. See [here](https://www.oracle.com/java/technologies/javase/jdk-faqs.html)
for more information.
