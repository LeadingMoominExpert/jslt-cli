# jslt-cli

Build JAR
```bash
gradle clean jar
```
Generate native-image configs
```bash
java -agentlib:native-image-agent=config-output-dir=META-INF/native-image/leading.moomin.expert/jslt-cli -jar build/libs/jslt-cli.jar
```
Build binary
```
native-image --no-fallback --install-exit-handlers -J-Xmx5550m -jar build/libs/jslt-cli.jar --static --libc=musl --gc=G1 -H:+TraceNativeToolUsage
```
