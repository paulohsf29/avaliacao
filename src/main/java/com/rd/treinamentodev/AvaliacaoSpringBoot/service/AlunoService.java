package com.rd.treinamentodev.AvaliacaoSpringBoot.service;

import com.rd.treinamentodev.AvaliacaoSpringBoot.model.dto.AlunoDTO;
import com.rd.treinamentodev.AvaliacaoSpringBoot.model.dto.ResultData;
import com.rd.treinamentodev.AvaliacaoSpringBoot.model.entity.AlunoEntity;
import com.rd.treinamentodev.AvaliacaoSpringBoot.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;


    public ResponseEntity gravar(AlunoDTO alunoDTO){
        ResultData resultData = null;
        AlunoEntity entity = new AlunoEntity();
        List<AlunoEntity> listaAlunos = alunoRepository.findAll();
        entity.setNomeAluno(alunoDTO.getNome());
        entity.setCpf(alunoDTO.getCpf());

        //TODO validar se o CPF existe no banco antes de existir, caso exista retornar mensagem de erro
        for (AlunoEntity busca: listaAlunos) {
            if (alunoDTO.getCpf() == busca.getCpf()) {
                resultData = new ResultData(HttpStatus.BAD_REQUEST.value(),"Campo: CPF Já Existe!");
                return ResponseEntity.badRequest().body(resultData);
            }
        }
        entity = alunoRepository.save(entity);
        resultData = new ResultData(HttpStatus.OK.value(), "Aluno Registrado!", entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(resultData);
    }
}
