# jslt-cli
## Use it
Download it from releases
```bash
wget https://github.com/LeadingMoominExpert/jslt-cli/releases/download/0.2.2/jslt.tar.gz
tar -xvzf jslt.tar.gz
```
and use it
```bash
Usage: jslt [-hpV] [<jsltTransform>] [<inputFile>]
      [<jsltTransform>]   JSLT transform file
      [<inputFile>]       JSON input to be transformed
  -h, --help              Show this help message and exit.
  -p, --pretty            Pretty print output
  -V, --version           Print version information and exit.
```

## Build it yourself
[Requirements](https://www.graalvm.org/latest/reference-manual/native-image/guides/build-static-executables/)

Build JAR
```bash
./gradlew clean jar
```
Build binary
```
./native-build
```

If you've made changes in the code, before building the binary you need to re-generate the native-image configurations by running the tracing agent
```bash
java -agentlib:native-image-agent=config-merge-dir=src/main/resources/META-INF/native-image/leading.moomin.expert/jslt-cli -jar build/libs/jslt-cli.jar
```