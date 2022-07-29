package com.moowork.gradle.node.npm

import com.moowork.gradle.AbstractIntegTest
import org.gradle.testkit.runner.TaskOutcome

class NpxTask_integTest
        extends AbstractIntegTest {

    def 'execute npx command with a package.json file and check inputs up-to-date detection'() {
        given:
        copyResources('fixtures/npx/', '')
        copyResources('fixtures/javascript-project/', '')

        when:
        def result1 = build(":test")

        then:
        result1.task(":nodeSetup").outcome == TaskOutcome.SUCCESS
        result1.task(":npmSetup").outcome == TaskOutcome.SUCCESS
        result1.task(":npmInstall").outcome == TaskOutcome.SUCCESS
        result1.task(":lint").outcome == TaskOutcome.SUCCESS
        result1.task(":test").outcome == TaskOutcome.SUCCESS
        result1.output.contains("3 problems (0 errors, 3 warnings)")
        result1.output.contains("1 passing")

        when:
        def result2 = build(":test")

        then:
        result2.task(":nodeSetup").outcome == TaskOutcome.UP_TO_DATE
        result2.task(":npmSetup").outcome == TaskOutcome.UP_TO_DATE
        result2.task(":npmInstall").outcome == TaskOutcome.SUCCESS
        result2.task(":lint").outcome == TaskOutcome.UP_TO_DATE
        result2.task(":test").outcome == TaskOutcome.UP_TO_DATE

        when:
        def result3 = build(":test", "-DchangeInputs=true")

        then:
        result3.task(":nodeSetup").outcome == TaskOutcome.UP_TO_DATE
        result3.task(":npmSetup").outcome == TaskOutcome.UP_TO_DATE
        result3.task(":npmInstall").outcome == TaskOutcome.UP_TO_DATE
        result3.task(":lint").outcome == TaskOutcome.SUCCESS
        result3.task(":test").outcome == TaskOutcome.SUCCESS

        when:
        def result4 = build(":version")

        then:
        result4.task(":version").outcome == TaskOutcome.SUCCESS
        result4.output.contains("> Task :version${System.lineSeparator()}6.12.0")
    }


    def 'execute npx command using the npm version specified in the package.json file'() {
        given:
        copyResources('fixtures/npx/', '')
        copyResources('fixtures/npm-present/', '')

        when:
        def result = build(":version")

        then:
        result.task(":version").outcome == TaskOutcome.SUCCESS
        result.output.contains("> Task :version${System.lineSeparator()}6.12.0")
    }
}