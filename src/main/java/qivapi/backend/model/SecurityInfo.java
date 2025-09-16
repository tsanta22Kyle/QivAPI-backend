package qivapi.backend.model;

public record SecurityInfo(Boolean ssl , Boolean hsts , Boolean nosniff) {
}
