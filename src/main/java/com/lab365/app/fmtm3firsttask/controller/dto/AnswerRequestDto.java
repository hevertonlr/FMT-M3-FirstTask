package com.lab365.app.fmtm3firsttask.controller.dto;

import com.lab365.app.fmtm3firsttask.datasource.entity.Answer;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Comentário")
public record AnswerRequestDto(@NotBlank
                               @Schema(description = "Texto do comentário", example = "Descreva aqui seu comentário")
                               String text) {
    public Answer toEntity() {
        Answer entity = new Answer();
        entity.setText(text);
        return entity;
    }
}
