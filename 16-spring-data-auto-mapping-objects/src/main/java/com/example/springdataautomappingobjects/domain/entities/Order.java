package com.example.springdataautomappingobjects.domain.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {
    private User buyer;
    private Set<Game> orderedGames;

    public Order() {
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyerId) {
        this.buyer = buyerId;
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Set<Game> getGames() {
        return orderedGames;
    }

    public void setGames(Set<Game> games) {
        this.orderedGames = games;
    }
}
