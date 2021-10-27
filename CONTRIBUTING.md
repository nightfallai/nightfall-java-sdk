# Contributing

Thank you for considering contributing to the Nightfall Java SDK.

There are many ways to contribute, such as writing code samples, improving the documentation,
submitting bug reports and feature requests, or writing code to improve the library itself.

Please, don't use the issue tracker for personal support questions. Feel free to reach out to `support@nightfall.ai`
to address those issues.

# Responsibilities
* Ensure cross-platform compatibility for every change that's accepted. Windows, Mac, Debian & Ubuntu Linux, etc.
* Ensure backwards compatibility with Java 8
* Create issues for any major changes and enhancements that you wish to make. Discuss things transparently
and get community feedback.
* Avoid introducing new external dependencies whenever possible. When absolutely required, validate the software
licenses used by these dependencies (e.g. avoid unintentional copyleft requirements).

# How to report a bug

## Security Disclosures 
If you find a security vulnerability, do NOT open an issue. Email `security@nightfall.ai` instead.

In order to determine whether you are dealing with a security issue, ask yourself these two questions:
* Can I access something that's not mine, or something I shouldn't have access to?
* Can I disable something for other people?

If the answer to either of those two questions are "yes", then you're probably dealing with a security issue.
Note that even if you answer "no" to both questions, you may still be dealing with a security issue, so if you're
unsure, just email us at `security@nightfall.ai`.

## Creating an Issue
When filing an issue, make sure to answer these five questions:
1. What version of Java are you using (go version)?
2. What operating system and processor architecture are you using?
3. How did you discover the issue? Is the issue reproducible?
4. What did you expect to see?
5. What did you see instead?


### Suggesting a New Feature

If you find yourself wishing for a feature that doesn't exist in this SDK, you are probably not alone.
There are bound to be others out there with similar needs. Open an issue on our issues list on GitHub which
describes the feature you would like to see, why you need it, and how it should work.

# Code Review
### Explain how a contribution gets accepted after it’s been submitted.

The core team looks at open pull requests on a regular basis. In order for your pull request to be merged, it
must meet the following requirements:
* It must pass the checkstyle linter; this should be run automatically when you run `mvn package`.
* It must add unit tests to cover any new functionality.
* It must get approval from one of the code owners.

If a pull request remains idle for more than two weeks, we may close it.
