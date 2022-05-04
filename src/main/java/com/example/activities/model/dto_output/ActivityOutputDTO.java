package com.example.activities.model.dto_output;

import lombok.*;
import org.springframework.lang.NonNull;

import javax.persistence.Column;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActivityOutputDTO {
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
