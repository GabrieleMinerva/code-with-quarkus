package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class EventEntity extends PanacheEntity {
    public String type;
    public String payload;
    public LocalDateTime createdAt;
}
