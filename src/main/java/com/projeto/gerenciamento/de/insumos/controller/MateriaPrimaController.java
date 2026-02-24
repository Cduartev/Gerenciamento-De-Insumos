package com.projeto.gerenciamento.de.insumos.controller;

import com.projeto.gerenciamento.de.insumos.dto.materiaprima.AtualizarMateriaPrimaRequest;
import com.projeto.gerenciamento.de.insumos.dto.materiaprima.CriarMateriaPrimaRequest;
import com.projeto.gerenciamento.de.insumos.dto.materiaprima.MateriaPrimaResponse;
import com.projeto.gerenciamento.de.insumos.service.MateriaPrimaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materias-primas")
@RequiredArgsConstructor
public class MateriaPrimaController {

    private final MateriaPrimaService materiaPrimaService;

    @GetMapping
    public ResponseEntity<List<MateriaPrimaResponse>> listarTodas() {
        return ResponseEntity.ok(materiaPrimaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MateriaPrimaResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(materiaPrimaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<MateriaPrimaResponse> criar(@Valid @RequestBody CriarMateriaPrimaRequest request) {
        MateriaPrimaResponse response = materiaPrimaService.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MateriaPrimaResponse> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody AtualizarMateriaPrimaRequest request
    ) {
        return ResponseEntity.ok(materiaPrimaService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        materiaPrimaService.remover(id);
        return ResponseEntity.noContent().build();
    }
}