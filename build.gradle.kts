tasks.create("build") {
    group = "build"
    dependsOn(gradle.includedBuild("specification").task(":build"),
            gradle.includedBuild("tck").task(":build"))
}

tasks.create("packageTck") {
    group = "build"
    dependsOn(gradle.includedBuild("specification").task(":build"),
            gradle.includedBuild("tck").task(":packageTck"))
}

tasks.create("clean") {
    group = "build"
    dependsOn(gradle.includedBuild("specification").task(":clean"),
            gradle.includedBuild("tck").task(":clean"))
}
