package io.samituga.gradle.plugin.task;

import static java.util.Objects.requireNonNull;

import java.io.File;

public record CopyResourceConfig(String sourceFilePath, File destinationDir, String targetFileName) {

    public CopyResourceConfig(String sourceFilePath, File destinationDir, String targetFileName) {
        this.sourceFilePath = requireNonNull(sourceFilePath, "sourceFilePath");
        this.destinationDir = requireNonNull(destinationDir, "destinationDir");
        this.targetFileName = requireNonNull(targetFileName, "targetFileName");
    }
}
