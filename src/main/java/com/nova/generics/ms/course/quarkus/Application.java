package com.nova.generics.ms.course.quarkus;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;

/**
 * Entry point for the {@code ms-course-quarkus} instance. Boots the
 * Quarkus runtime and exits when the runtime stops. The class is a
 * pure bootstrapper with no instance state, so the constructor is
 * private and the class is {@code final} + non-instantiable.
 */
@QuarkusMain
public final class Application {

    private Application() {
    }

    /**
     * Boots Quarkus and blocks until the runtime is stopped.
     *
     * @param args command-line arguments forwarded to {@link Quarkus#run}.
     */
    public static void main(final String[] args) {
        Quarkus.run(args);
    }
}
