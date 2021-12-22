package com.camel.go4lunch.api;

import org.jetbrains.annotations.Nullable;

public class FirestoreResult {
    Boolean success;
    @Nullable
    Exception error;

    public FirestoreResult(Boolean success) {
        this.success = success;
    }

    public FirestoreResult(@Nullable Exception error) {
        this.success = false;
        this.error = error;
    }

    public Boolean getSuccess() {
        return success;
    }

    @Nullable
    public Exception getException() {
        return error;
    }
}