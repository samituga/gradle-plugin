package io.samituga.gradle.plugin.task;

import static java.util.Objects.requireNonNull;

import org.gradle.api.DefaultTask;
import org.gradle.api.resources.TextResource;
import org.gradle.api.tasks.InputFile;
import org.gradle.api.tasks.OutputDirectory;
import org.gradle.api.tasks.TaskAction;

import java.io.File;

public class CopyResourceTask extends DefaultTask {

    private CopyResourceConfig config;

    @InputFile
    public File getSource() {
        return textResource(config.sourceFilePath()).asFile();
    }

    @OutputDirectory
    public File getDestination() {
        return config.destinationDir();
    }

    public void setConfig(CopyResourceConfig config) {
        this.config = requireNonNull(config, "config");
    }

    @TaskAction
    public void copyResource() {
        var destinationDirectory = getDestination();
        var sourceFile = getSource();

        getProject().copy((it) -> {
            it.from(sourceFile);
            it.into(destinationDirectory);
            it.rename((ignored) -> config.targetFileName());
        });
    }

    private TextResource textResource(String path) {
        return getProject()
                .getResources()
                .getText()
                .fromUri(requireNonNull(CopyResourceTask.class.getResource(path)));
    }
}
