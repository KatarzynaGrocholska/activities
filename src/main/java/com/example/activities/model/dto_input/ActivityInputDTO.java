package com.example.activities.model.dto_input;

import lombok.*;
import org.springframework.lang.NonNull;

import javax.persistence.Column;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivityInputDTO {
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
