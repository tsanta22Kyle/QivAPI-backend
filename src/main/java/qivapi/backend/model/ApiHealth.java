package qivapi.backend.model;

import java.time.LocalDateTime;

public record ApiHealth(String id, Api api, Boolean isActive, SecurityInfo securityInfo, Boolean isApi , LocalDateTime checkingDatetime) {
}
