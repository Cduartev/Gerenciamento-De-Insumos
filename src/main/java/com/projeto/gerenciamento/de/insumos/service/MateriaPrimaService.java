package com.projeto.gerenciamento.de.insumos.service;

import com.projeto.gerenciamento.de.insumos.domain.entity.MateriaPrima;
import com.projeto.gerenciamento.de.insumos.domain.repository.MateriaPrimaRepository;
import com.projeto.gerenciamento.de.insumos.dto.materiaprima.AtualizarMateriaPrimaRequest;
import com.projeto.gerenciamento.de.insumos.dto.materiaprima.CriarMateriaPrimaRequest;
import com.projeto.gerenciamento.de.insumos.dto.materiaprima.MateriaPrimaResponse;
import com.projeto.gerenciamento.de.insumos.exception.RecursoNaoEncontradoException;
import com.projeto.gerenciamento.de.insumos.exception.RegraDeNegocioException;
import com.projeto.gerenciamento.de.insumos.mapper.MateriaPrimaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MateriaPrimaService {

    private final MateriaPrimaRepository materiaPrimaRepository;
    private final MateriaPrimaMapper materiaPrimaMapper;

    @Transactional(readOnly = true)
    public List<MateriaPrimaResponse> listarTodas() {
        return materiaPrimaRepository.findAll()
                .stream()
                .map(materiaPrimaMapper::paraResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public MateriaPrimaResponse buscarPorId(Long id) {
        MateriaPrima materiaPrima = buscarEntidadePorId(id);
        return materiaPrimaMapper.paraResponse(materiaPrima);
    }

    @Transactional
    public MateriaPrimaResponse criar(CriarMateriaPrimaRequest request) {
        validarCodigoDuplicadoNaCriacao(request.codigo());

        MateriaPrima materiaPrima = materiaPrimaMapper.paraEntidade(request);
        MateriaPrima salva = materiaPrimaRepository.save(materiaPrima);

        return materiaPrimaMapper.paraResponse(salva);
    }

    @Transactional
    public MateriaPrimaResponse atualizar(Long id, AtualizarMateriaPrimaRequest request) {
        MateriaPrima materiaPrima = buscarEntidadePorId(id);

        validarCodigoDuplicadoNaAtualizacao(request.codigo(), id);

        materiaPrima.setCodigo(request.codigo());
        materiaPrima.setNome(request.nome());
        materiaPrima.setQuantidadeEstoque(request.quantidadeEstoque());
        materiaPrima.setUnidadeMedida(request.unidadeMedida());

        MateriaPrima atualizada = materiaPrimaRepository.save(materiaPrima);
        return materiaPrimaMapper.paraResponse(atualizada);
    }

    @Transactional
    public void remover(Long id) {
        MateriaPrima materiaPrima = buscarEntidadePorId(id);
        materiaPrimaRepository.delete(materiaPrima);
    }

    private MateriaPrima buscarEntidadePorId(Long id) {
        return materiaPrimaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Materia-prima nao encontrada para o id: " + id));
    }

    private void validarCodigoDuplicadoNaCriacao(String codigo) {
        if (materiaPrimaRepository.existsByCodigo(codigo)) {
            throw new RegraDeNegocioException("Ja existe materia-prima com o codigo informado.");
        }
    }

    private void validarCodigoDuplicadoNaAtualizacao(String codigo, Long id) {
        if (materiaPrimaRepository.existsAllByCodigoAndIdNot(codigo, id)) {
            throw new RegraDeNegocioException("Ja existe outra materia-prima com o codigo informado.");
        }
    }
}
