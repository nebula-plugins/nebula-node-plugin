package com.moowork.gradle.node.yarn

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskAction
import org.gradle.process.ExecResult

import javax.inject.Inject

class YarnTask
    extends DefaultTask
{
    protected YarnExecRunner runner

    private Iterable<?> args = []

    private ExecResult result

    private String[] yarnCommand

    @Inject
    public YarnTask() {
        this(false)
    }

    public YarnTask(boolean afterEvaluateConfiguration)
    {
        this.runner = new YarnExecRunner( this.project )
        dependsOn( YarnSetupTask.NAME )

        def initWorkDir = {
            if (!this.runner.workingDir) {
                def workingDir = this.project.node.nodeModulesDir
                setWorkingDir(workingDir)
            }

            if (!this.runner.workingDir.exists()) {
                this.runner.workingDir.mkdirs();
            }
        }

        //This class is used from different contexts.
        //1) it is a parent for a usually created task - in that case we need to initialize
        //   working directory after evaluation so we can capture customizations
        //2) task created by a rule. In that case we are already after evaluation and we don't need to
        //   wait with configuration. Gradle 7+ actually fails if you invoke after evaluate
        //   if a project is past that phase
        if (afterEvaluateConfiguration) {
            this.project.afterEvaluate {
                initWorkDir()
            }
        } else {
            initWorkDir()
        }
    }

    void setArgs( final Iterable<?> value )
    {
        this.args = value
    }

    void setYarnCommand( String[] cmd )
    {
        this.yarnCommand = cmd
    }

    @Internal
    Iterable<?> getArgs()
    {
        return this.args
    }

    void setEnvironment( final Map<String, ?> value )
    {
        this.runner.environment << value
    }

    void setWorkingDir( final Object value )
    {
        this.runner.workingDir = value
    }

    void setIgnoreExitValue( final boolean value )
    {
        this.runner.ignoreExitValue = value
    }

    void setExecOverrides( final Closure closure )
    {
        this.runner.execOverrides = closure
    }

    @Internal
    ExecResult getResult()
    {
        return this.result
    }

    @TaskAction
    void exec()
    {
        if ( this.yarnCommand != null )
        {
            this.runner.arguments.addAll( this.yarnCommand )
        }

        this.runner.arguments.addAll( this.args )
        this.result = this.runner.execute()
    }
}
