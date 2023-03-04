plugins {
    id("java")
    id("org.jetbrains.intellij") version "1.13.1"
    kotlin("jvm") version "1.8.0"
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
    version.set("221.6008.13.2211.9619390") //
    type.set("AI") // Target IDE Platform

    plugins.set(listOf("android"))
}

dependencies {
}

tasks {
    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "11"
        targetCompatibility = "11"
    }

    patchPluginXml {
        sinceBuild.set("221")
        untilBuild.set("221.*")
    }
}

// Below for IDEA Dev
// https://youtrack.jetbrains.com/issue/IDEA-296777
// Workaround for https://youtrack.jetbrains.com/issue/IDEA-285839/Classpath-clash-when-using-coroutines-in-an-unbundled-IntelliJ-plugin
configurations.all {
    exclude ("org.jetbrains.kotlinx", "kotlinx-coroutines-core")
    exclude ("org.jetbrains.kotlinx", "kotlinx-coroutines-core-jvm")
    exclude ("org.jetbrains.kotlinx", "kotlinx-coroutines-jdk8")
}