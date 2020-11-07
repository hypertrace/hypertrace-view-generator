plugins {
  java
  application
  jacoco
  id("org.hypertrace.docker-java-application-plugin")
  id("org.hypertrace.docker-publish-plugin")
  id("org.hypertrace.jacoco-report-plugin")
}

application {
  mainClassName = "org.hypertrace.core.serviceframework.PlatformServiceLauncher"
}

tasks.test {
  useJUnitPlatform()
}

dependencies {
  implementation(project(":hypertrace-view-generator-api"))
  implementation("org.hypertrace.core.viewcreator:view-creator-framework:0.1.17")
  constraints {
    implementation("com.google.guava:guava:30.0-android") {
      because("Information Disclosure (new) [Medium Severity][https://snyk.io/vuln/SNYK-JAVA-COMGOOGLEGUAVA-1015415] in com.google.guava:guava@29.0-android")
    }
  }

  testImplementation("org.junit.jupiter:junit-jupiter:5.6.2")
  testImplementation("org.mockito:mockito-core:3.3.3")
}

description = "view creator for Pinot"
