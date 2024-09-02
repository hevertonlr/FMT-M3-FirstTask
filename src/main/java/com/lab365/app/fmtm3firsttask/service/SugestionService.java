package com.lab365.app.fmtm3firsttask.service;

import com.lab365.app.fmtm3firsttask.datasource.entity.Answer;
import com.lab365.app.fmtm3firsttask.datasource.entity.Sugestion;
import com.lab365.app.fmtm3firsttask.datasource.repository.SugestionRepository;
import com.lab365.app.fmtm3firsttask.infra.exception.InvalidException;
import com.lab365.app.fmtm3firsttask.infra.exception.NotFoundException;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import static com.lab365.app.fmtm3firsttask.infra.utils.Util.toJSON;
import static java.lang.String.format;

@Slf4j
@Service
@AllArgsConstructor
public class SugestionService{
    protected final AnswerService answerService;
    protected final SugestionRepository repository;

    protected final String entityName = "Sugestion";

    public List<Sugestion> findAll() {
        List<Sugestion> entities = repository.findAllByOrderByUpdatedAtDesc();
//        List<Sugestion> entities = repository.findAll();
        if (entities.isEmpty())
            throw new NotFoundException(format("Nenhum(a) {0} a listar", entityName));

        log.info("Listando: {} {}(s) encontrados(as)", entities.size(), entityName);
        return entities;
    }

    public Sugestion findById(Long id) throws NotFoundException {
        Sugestion entity = repository.findById(id).orElseThrow(
                () -> {
                    log.warn("Buscando: {} com id ({}) -> NÃO ENCONTRADO!", entityName, id);
                    return new NotFoundException(format("{0} não encontrado(a) com id: {1}", entityName, id));
                });

        log.info("Buscando: {} encontrado(a) -> \n{}\n", entityName, toJSON(entity));
        return entity;
    }

    public Sugestion save(Sugestion source) {
        try {
            boolean isCreation = source.getId() == null;
            Sugestion entity = isCreation ? source : findById(source.getId());
            if (!isCreation) entity.update(source);

            Sugestion finalEntity = repository.save(entity);

            String action = isCreation ? "criado" : "alterado";
            log.info("Salvando: {} {} com sucesso.", entityName, action);
            log.info("Salvando: {} {} -> \n{}\n", entityName, action, toJSON(finalEntity));
            return finalEntity;
        } catch (DataAccessException e) {
            log.error("Salvando: ERRO -> {}", e.getMessage());
            throw new InvalidException(e.getMessage());
        }
    }

    public void delete(Long id) throws NotFoundException {
        repository.findById(id).ifPresentOrElse(repository::delete,
                () -> {
                    log.warn("Deletando: {} com id ({}) -> NÃO ENCONTRADO(A)!", entityName, id);
                    throw new NotFoundException(format("{0} não encontrado(a) com id: {1}", entityName, id));
                });
        log.info("Deletando: {} com id ({}) -> Excluído(a) com sucesso", entityName, id);
    }

    public Answer addAnswer(Long id, Answer entity){
        Sugestion sugestion = repository.findById(id).orElseThrow(
                () -> {
                    log.warn("Buscando: {} com id ({}) -> NÃO ENCONTRADO!", entityName, id);
                    return new NotFoundException(format("{0} não encontrado(a) com id: {1}", entityName, id));
                });

        log.info("Buscando: {} encontrado(a) -> \n{}\n", entityName, toJSON(entity));
        entity.setSugestion(sugestion);
        return answerService.save(entity);
    }
}
