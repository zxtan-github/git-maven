package org.ifunq.tanzx;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;

@Mojo(name = "mojo02", defaultPhase=LifecyclePhase.PACKAGE)
public class CustomizeMojo02 extends AbstractMojo {

    public void execute() throws MojoExecutionException {
        System.out.println("CustomizeMojo02 is execute...................................");
    }
}
