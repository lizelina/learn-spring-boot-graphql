package com.learn.graphql.domain.bank.input;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UpdateTaskNameInput {
    @NotNull
    Long id;
    @NotBlank
    String title;

}
