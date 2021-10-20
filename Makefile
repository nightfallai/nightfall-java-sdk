
BUILD_DIR=build

VERSION=0.1

NAME=dlp-scan
ARTIFACT=dlp-scan-$(VERSION).jar
SHADED=dlp-scan-$(VERSION)-shaded.jar

all: jar

clean:
	mvn clean

compile:
	mvn compile

jar: clean
	mvn package
	@mv $(BUILD_DIR)/$(SHADED) $(BUILD_DIR)/$(ARTIFACT)

