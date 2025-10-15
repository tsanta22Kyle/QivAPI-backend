package qivapi.backend.model;

public record SecurityInfo(String id,Boolean ssl , Boolean hsts , Boolean nosniff) {
}
