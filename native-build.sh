#!/bin/bash
native-image \
    --no-fallback \
    --install-exit-handlers \
    -J-Xmx5550m \
    -jar build/libs/jslt-cli.jar \
    --static \
    --libc=musl \
    --gc=G1 \
    -H:+UnlockExperimentalVMOptions \
    -H:+TraceNativeToolUsage \
    -H:Name=jslt \
    -H:Class=leading.moomin.expert.JsltCommand
