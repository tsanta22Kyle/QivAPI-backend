package qivapi.backend.model;

public record ApiHealthResponse(String baseUrl,Boolean exist , Boolean active , SecurityInfo secured , Boolean isApi ) {
}
