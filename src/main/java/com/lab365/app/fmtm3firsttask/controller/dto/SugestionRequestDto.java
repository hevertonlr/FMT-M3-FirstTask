package com.lab365.app.fmtm3firsttask.controller.dto;
import com.lab365.app.fmtm3firsttask.datasource.entity.Sugestion;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Sugestão")
public record SugestionRequestDto (

        @NotBlank
        @Schema(description = "Título da sugestão", example = "Sugestão de melhoria/modificação")
        String title,

        @Schema(description = "Descrição da sugestão", example = "Como sugestão gostaria de informar que...")
        String description

){
    public Sugestion toEntity() {
        Sugestion entity = new Sugestion();
        entity.setTitle(title);
        entity.setDescription(description);
        return entity;
    }
}

