package org.acme;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class EventService {

    @Inject
    EventRepository repository;

    @Transactional
    public EventEntity create(String type, String payload) {
        EventEntity e = new EventEntity();
        e.type = type;
        e.payload = payload;
        e.createdAt = LocalDateTime.now();
        repository.persist(e);
        return e;
    }

    public List<EventEntity> list(int limit) {
        return repository.findLatest(limit);
    }
}
