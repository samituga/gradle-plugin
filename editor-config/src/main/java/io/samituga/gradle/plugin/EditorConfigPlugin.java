package io.samituga.gradle.plugin;

import io.samituga.gradle.plugin.task.CopyResourceConfig;
import io.samituga.gradle.plugin.task.CopyResourceTask;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.jetbrains.annotations.NotNull;

public class EditorConfigPlugin implements Plugin<Project> {

    private static final String TASK_NAME = "updateEditorConfig";
    private static final String FILE_NAME = ".editorconfig";
    private static final String SOURCE_FILE_PATH = "/editorconfig/" + FILE_NAME;

    @Override
    public void apply(@NotNull Project project) {
        var config = new CopyResourceConfig(SOURCE_FILE_PATH, project.getProjectDir(), FILE_NAME);


        var task = project.getTasks().create(TASK_NAME, CopyResourceTask.class, (copyResourceTask) -> {
            copyResourceTask.setConfig(config);
            copyResourceTask.setGroup("EditorConfig");
            copyResourceTask.setDescription("Copies the .editorconfig file to the project directory");
        });

        project.getTasks().getByName("build").dependsOn(task);
    }
}
