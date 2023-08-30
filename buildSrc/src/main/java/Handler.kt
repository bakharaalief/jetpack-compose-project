import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.implementation(list: List<String>) = list.forEach {
    add("implementation", it)
}

fun DependencyHandler.implementationPlatform(list: List<String>) = list.forEach {
    add("implementation", platform(it))
}

fun DependencyHandler.testImplementation(list: List<String>) = list.forEach {
    add("testImplementation", it)
}

fun DependencyHandler.androidTestImplementation(list: List<String>) = list.forEach {
    add("androidTestImplementation", it)
}

fun DependencyHandler.androidTestImplementationPlatform(list: List<String>) = list.forEach {
    add("androidTestImplementation", platform(it))
}

fun DependencyHandler.debugImplementatio(list: List<String>) = list.forEach {
    add("debugImplementation", it)
}