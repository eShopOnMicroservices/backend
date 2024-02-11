plugins {
    base
    kotlin("jvm") version "1.9.22"
}

group = "com.eshoponmicroservices"
version = "0.1.0"

repositories {
    mavenCentral()
}


tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
    jvmToolchain(8)
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    subprojects.forEach { it.repositories { mavenCentral() } }
}
