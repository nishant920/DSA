package com.instamart.shopping_delivery.models;

import jakarta.persistence.*;
import jakarta.websocket.OnError;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "warehouses")
public class WareHouse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    String name;
    @OneToOne
    AppUser manager;
    @OneToOne
    @JoinColumn
    Location location;
    @OneToMany
    List<WareHouseItem> wareHouseItems;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
