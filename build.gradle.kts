plugins {
    kotlin("jvm") version "1.8.10"
    id("io.kotest.multiplatform") version "5.5.5"
    id("com.github.spotbugs") version "5.0.13"
    application
    jacoco
    checkstyle
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
    implementation("ch.qos.logback:logback-classic:1.4.6")

    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
    testImplementation("io.kotest:kotest-property:$kotestVersion")
    testImplementation("io.kotest:kotest-runner-junit5-jvm:$kotestVersion")

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


kotlin {
    jvmToolchain(18)
}

application {
    mainClass.set("MainKt")
}



fun setJacocoClasses(classDirectories: ConfigurableFileCollection) {
    val jacocoExcludes = listOf(
//        "**/com/learn/educative/dataclass/**",
        "**/com/learn/educative/Main*"
    )
    classDirectories.setFrom(
        sourceSets.main.get().output.asFileTree.matching {
            exclude(jacocoExcludes)
    })
}
