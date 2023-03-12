plugins {
    kotlin("jvm") version "1.8.10"
    id("io.kotest.multiplatform") version "5.5.5"
    application
    jacoco
}

val kotestVersion: String by project
val kotlinVersion: String by project

group = "com.learn.educative"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://oss.sonatype.org/content/repositories/snapshots")
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    implementation("io.github.microutils:kotlin-logging-jvm:3.0.5")
    implementation("ch.qos.logback:logback-classic:1.4.5")

    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
    testImplementation("io.kotest:kotest-property:$kotestVersion")
    testImplementation("io.kotest:kotest-runner-junit5-jvm:$kotestVersion")

}

tasks.test.configure {
    useJUnitPlatform()
    excludes.add("com.learn.educative.*")
    finalizedBy(tasks.jacocoTestReport, tasks.jacocoTestCoverageVerification)
}

tasks.jacocoTestReport {
    classDirectories.from(sourceSets.main.get().output.asFileTree.matching {
    })

}

tasks.jacocoTestCoverageVerification {
    violationRules {
        rule {
            limit {
                minimum = BigDecimal("0.80")
            }
        }
        rule {
            isEnabled = false
            element = "CLASS"
            includes = listOf("org.gradle.*", "default.*")
            limit {
                counter = "LINE"
                value = "TOTALCOUNT"
                maximum = BigDecimal("0.3")
            }
        }
    }
}

kotlin {
    jvmToolchain(18)
}

application {
    mainClass.set("MainKt")
}
