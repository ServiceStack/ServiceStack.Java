import io.gitlab.arturbosch.detekt.Detekt
import org.jetbrains.changelog.markdownToHTML
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.io.FileInputStream
import java.util.*

fun properties(key: String) = project.findProperty(key).toString()

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.5.10"
    // gradle-intellij-plugin - read more: https://github.com/JetBrains/gradle-intellij-plugin
    id("org.jetbrains.intellij") version "1.0"
    // gradle-changelog-plugin - read more: https://github.com/JetBrains/gradle-changelog-plugin
    id("org.jetbrains.changelog") version "1.1.2"
    // detekt linter - read more: https://detekt.github.io/detekt/gradle.html
    id("io.gitlab.arturbosch.detekt") version "1.17.1"
    // ktlint linter - read more: https://github.com/JLLeitschuh/ktlint-gradle
    id("org.jlleitschuh.gradle.ktlint") version "10.0.0"
}
version = properties("pluginVersion")
// Configure project's dependencies
repositories {
    mavenCentral()
}
dependencies {
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.17.1")
}

intellij {
    version.set("2019.2")
    pluginName.set("ServiceStackIDEA")
    plugins.set(listOf("maven","java"))
}

var pubNightly: String? = System.getenv("SERVICESTACKIDEA_PUBLISH_NIGHTLY")
var buildNum: String? = System.getenv("BUILD_NUMBER")


if(pubNightly != null && buildNum != null) {
    // Append build number to version for a new nightly build version to be published.
    version = "${version}.$buildNum"
}

var jbToken: String? = ""

if (properties("jetbrains.plugins.user").isEmpty()) {
    val props = Properties()
    val fis = FileInputStream("local.properties")
    props.load(fis)
}
jbToken = properties("jetbrains.plugins.token")

tasks {

    runPluginVerifier {
        ideVersions.set(listOf(
                "WS-2019.2",
                "WS-2020.2.4",
                "IIU-2020.1.1",
                "IIU-2021.1"
        ))
    }

    publishPlugin {
        token.set(jbToken)
        channels.set(listOf("beta"))
    }

    patchPluginXml {
        version.set(properties("pluginVersion"))
        sinceBuild.set(properties("pluginSinceBuild"))
        untilBuild.set(properties("pluginUntilBuild"))
    }
}
