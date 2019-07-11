package org.ifunq.tanzx;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.util.List;

@Mojo(name = "mojo01", defaultPhase=LifecyclePhase.PACKAGE)
public class CustomizeMojo01 extends AbstractMojo {

    @Parameter
    private String msg;

    @Parameter
    private List<String> options;

    @Parameter(property = "basedir")
    private String baseDir;

    @Parameter(property = "project.name")
    private String projectName;

    public void execute() throws MojoExecutionException {
        System.out.println("CustomizeMojo01 is execute...................................");
        System.out.println("msg->" + msg);
        System.out.println("options->" + options);
        System.out.println("baseDir->" + baseDir);
        System.out.println("projectName->" + projectName);
    }
}
