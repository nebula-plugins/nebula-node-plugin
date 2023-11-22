package com.moowork.gradle.node.yarn

import com.moowork.gradle.AbstractIntegTest
import org.gradle.testkit.runner.TaskOutcome

class YarnRuleIntegrationSpec extends AbstractIntegTest {
    def 'execute yarn_install rule'() {
        given:
        writeBuild('''
            plugins {
                id 'nebula.node'
            }

            node {
                yarnVersion = "1.15.2"
                download = true
                workDir = file('build/node')
                yarnWorkDir = file('build/yarn')
            }
        ''')
        writeEmptyPackageJson()

        when:
        def result = buildTask('yarn_install')

        then:
        result.outcome == TaskOutcome.SUCCESS
    }

    def 'can execute an yarn module using yarn_run_'() {
        given:
        writeBuild('''
            plugins {
                id 'nebula.node'
            }

            node {
                yarnVersion = "1.15.2"
                download = true
            }
        ''')

        copyResources('fixtures/yarn/package.json', 'package.json')
        writeFile("yarn.lock", "")

        when:
        def result = buildTask('yarn_run_parent')

        then:
        result.outcome == TaskOutcome.SUCCESS
        fileExists('child1.txt')
        fileExists('child2.txt')
        fileExists('parent1.txt')
        fileExists('parent2.txt')
    }

}
