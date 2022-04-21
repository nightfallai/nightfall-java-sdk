
BUILD_DIR=build

VERSION=1.2.1

NAME=scan-api
ARTIFACT=$(NAME)-$(VERSION).jar
SHADED=$(NAME)-$(VERSION)-shaded.jar

all: jar

clean:
	mvn clean

compile:
	mvn compile

jar: clean
	mvn package
	@mv $(BUILD_DIR)/$(SHADED) $(BUILD_DIR)/$(ARTIFACT)

