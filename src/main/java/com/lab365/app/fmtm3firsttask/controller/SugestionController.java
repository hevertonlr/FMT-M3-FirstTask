package com.lab365.app.fmtm3firsttask.controller;

import com.lab365.app.fmtm3firsttask.controller.dto.AnswerRequestDto;
import com.lab365.app.fmtm3firsttask.controller.dto.SugestionRequestDto;
import com.lab365.app.fmtm3firsttask.datasource.entity.Answer;
import com.lab365.app.fmtm3firsttask.datasource.entity.Sugestion;
import com.lab365.app.fmtm3firsttask.service.SugestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="Sugestão")
@AllArgsConstructor
@RestController
@RequestMapping(value = "sugestion",produces = MediaType.APPLICATION_JSON_VALUE)
public class SugestionController{
    protected final SugestionService service;


    @PostMapping("{id}/answer")
    @Operation(summary = "Adicionar Comentário")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) })
    })
    public ResponseEntity<Answer> addAnswer(@PathVariable Long id,@Valid @RequestBody AnswerRequestDto request){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addAnswer(id,request.toEntity()));
    }

    @PostMapping
    @Operation(summary = "Criar")
    public ResponseEntity<Sugestion> create(@Valid @RequestBody SugestionRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request.toEntity()));
    }

    @GetMapping("list")
    @Operation(summary = "Listar")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) })
    })
    public ResponseEntity<List<Sugestion>> list() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("{id}")
    @Operation(summary = "Buscar")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) })
    })
    public ResponseEntity<Sugestion> listById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }



    @Operation(summary = "Deletar")
    @DeleteMapping("delete/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Removed",content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) })
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }



    @PutMapping()
    @Operation(summary = "Atualizar")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) })
    })
    public ResponseEntity<Sugestion> update(@Valid @RequestBody SugestionRequestDto request) {
        return ResponseEntity.ok(service.save(request.toEntity()));
    }
}
