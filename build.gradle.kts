plugins {
    id("com.github.spotbugs") version "5.0.13"
    id ("io.gitlab.arturbosch.detekt") version "1.22.0"
    id("io.kotest.multiplatform") version "5.5.5"
    id("io.micronaut.application") version "3.7.7"
    kotlin("jvm") version "1.8.10"
    kotlin("kapt") version "1.8.10"
    kotlin("plugin.serialization") version "1.8.10"
    application
    jacoco
    checkstyle
}

val kotestVersion: String by project
val kotlinVersion: String by project
val kotestMicronautVersion: String by project

group = "com.learn.educative"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://oss.sonatype.org/content/repositories/snapshots")
}

dependencies {

    implementation("ch.qos.logback:logback-classic")
    implementation("io.github.microutils:kotlin-logging-jvm:3.0.5")
    implementation("io.micronaut:micronaut-http-server-netty")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")

    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
    testImplementation("io.kotest:kotest-property:$kotestVersion")
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-runner-junit5-jvm:$kotestVersion")
    testImplementation("io.micronaut.test:micronaut-test-junit5:$kotestMicronautVersion")
    testImplementation("io.micronaut.test:micronaut-test-kotest:$kotestMicronautVersion")
    testImplementation("io.mockk:mockk:1.13.4")



}

tasks.test.configure {
    useJUnitPlatform()
    finalizedBy(
        tasks.jacocoTestReport,
        tasks.jacocoTestCoverageVerification,
        tasks.check
    )
}

tasks.jacocoTestReport { setJacocoClasses(classDirectories) }

tasks.jacocoTestCoverageVerification {
    setJacocoClasses(classDirectories)
    violationRules {
        rule {
            limit {
                minimum = BigDecimal("0.80")
            }
        }
    }
}

tasks.checkstyleMain.configure {
    reports {
        xml.required.set(false)
        html.required.set(true)
    }
}

tasks.spotbugsMain {
    excludeFilter.set(file("$rootDir/config/spotbugs/exclude.xml") )
    reports.create("html") { required.set(true) }
}

tasks.spotbugsTest {
    enabled = false
}

tasks.detektMain.configure {
    reports {
        xml.required.set(false)
        html.required.set(true)
        txt.required.set(false)
        html.outputLocation.set(file("build/reports/detekt.html"))
    }
}



kotlin {
    jvmToolchain(17)
}

application {
    mainClass.set("MainKt")
}

detekt {
    source = files("src/main/kotlin")
    parallel = true
    config = files("config/detekt/detekt-config.yml")
    buildUponDefaultConfig = true
}



fun setJacocoClasses(classDirectories: ConfigurableFileCollection) {
    val jacocoExcludes = listOf(
        "**/com/learn/educative/dataclass/**",
        "**/com/learn/educative/datastructures/**",
        "**/com/learn/educative/Main*"
    )
    classDirectories.setFrom(
        sourceSets.main.get().output.asFileTree.matching {
            exclude(jacocoExcludes)
    })
}
