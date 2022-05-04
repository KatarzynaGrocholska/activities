package com.example.activities.model.entity;

import lombok.*;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="activities")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NonNull
    private String activity;
    @NonNull
    private String type;
    private int participants;
    private double price;
    private String link;
    @NonNull
    @Column(unique=true)
    private String key;
    private double accessibility;
}
