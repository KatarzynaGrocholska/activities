package com.example.activities.mapper;

import com.example.activities.model.dto_input.ActivityInputDTO;
import com.example.activities.model.dto_output.ActivityOutputDTO;
import com.example.activities.model.entity.Activity;
import com.example.activities.model.mapper.ActivityMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ActivityMapperTest {

    ActivityMapper mapper = ActivityMapper.INSTANCE;

    @Test
    void activityInputDTOToActivity() {
        //given
        ActivityInputDTO expected = ActivityInputDTO.builder()
                .activity("Build a house")
                .accessibility(0.2)
                .key("56788655443")
                .link("link.pl")
                .price(3.5)
                .participants(2)
                .type("charity")
                .build();

        //when
        Activity actual =
                mapper.activityInputDtoToActivity(expected);
        //then
        Assertions.assertAll(
                () -> assertThat(actual).isNotNull(),
                () -> assertThat(actual.getActivity()).isEqualTo(expected.getActivity()),
                () -> assertThat(actual.getAccessibility()).isEqualTo(expected.getAccessibility()),
                () -> assertThat(actual.getKey()).isEqualTo(expected.getKey()),
                () -> assertThat(actual.getLink()).isEqualTo(expected.getLink()),
                () -> assertThat(actual.getPrice()).isEqualTo(expected.getPrice()),
                () -> assertThat(actual.getParticipants()).isEqualTo(expected.getParticipants()),
                () -> assertThat(actual.getType()).isEqualTo(expected.getType())
        );
    }

    @Test
    void activityToActivityOutputDTO() {
        //given
        Activity expected = Activity.builder()
                .activity("Build a house")
                .accessibility(0.2)
                .key("56788655443")
                .link("link.pl")
                .price(3.5)
                .participants(2)
                .type("charity")
                .build();
        //when
        ActivityOutputDTO actual =
                mapper.activityToActivityOutputDTO(expected);
        //then
        Assertions.assertAll(
                () -> assertThat(actual).isNotNull(),
                () -> assertThat(actual.getActivity()).isEqualTo(expected.getActivity()),
                () -> assertThat(actual.getAccessibility()).isEqualTo(expected.getAccessibility()),
                () -> assertThat(actual.getKey()).isEqualTo(expected.getKey()),
                () -> assertThat(actual.getLink()).isEqualTo(expected.getLink()),
                () -> assertThat(actual.getPrice()).isEqualTo(expected.getPrice()),
                () -> assertThat(actual.getParticipants()).isEqualTo(expected.getParticipants()),
                () -> assertThat(actual.getType()).isEqualTo(expected.getType())
        );
    }
}
