package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class EventRepository implements PanacheRepository<EventEntity> {
    public List<EventEntity> findLatest(int limit) {
        return find("order by createdAt desc").page(0, limit).list();
    }
}
