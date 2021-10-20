
BUILD_DIR=build

VERSION=0.1

ARTIFACT=api-$(VERSION).jar
SHADED=api-$(VERSION)-shaded.jar

all: jar

clean:
	mvn clean

compile:
	mvn compile

jar: clean
	mvn package
	@mv $(BUILD_DIR)/$(SHADED) $(BUILD_DIR)/$(ARTIFACT)

