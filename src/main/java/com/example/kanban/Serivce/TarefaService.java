package com.example.kanban.Serivce;

import com.example.kanban.Enums.TarefaPrioridadeEnumber;
import com.example.kanban.Model.Tarefa;
import com.example.kanban.Repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    public Tarefa inserirTarefa(Tarefa tarefa) {
        LocalDate now = LocalDate.now();
        if (now.isBefore(tarefa.getDataVencimento())) {
            return tarefaRepository.save(tarefa);
        }
        throw new RuntimeException("Tarefa não criada");
    }

    public List<Tarefa> findAll() {

        List<Tarefa> todasTarefa = tarefaRepository.findAll();
        System.out.println("********* TABELA DE TAREFAS ***************");
        System.out.println("|ID  |Data Criacao     |Data Termino     |Prioridade |Status       |");

        LocalDate now = LocalDate.now();


        exibirTarefaPorStatus(todasTarefa, " A fazer", now);
        exibirTarefaPorStatus(todasTarefa, "Em progresso", now);
        exibirTarefaPorStatus(todasTarefa, " Concluido", now);


        return todasTarefa;
    }

    private void exibirTarefaPorStatus(List<Tarefa> tarefas, String status, LocalDate now) {
        System.out.println("Status: " + status.toLowerCase());

        for (TarefaPrioridadeEnumber prioridade : TarefaPrioridadeEnumber.values()) {
            tarefas.stream()
                    .filter(tarefa -> tarefa.getStatus().equalsIgnoreCase(status) && tarefa.getPrioridade() == prioridade)
                    .forEach(tarefa -> exibirTarefa(tarefa, now));
        }
    }

    private void exibirTarefa(Tarefa tarefa, LocalDate now) {
        String prioridade = tarefa.getPrioridade().toString();
        if (tarefa.getDataVencimento().isBefore(now) && !tarefa.getStatus().equalsIgnoreCase("Concluido")) {
            System.out.printf("\033[31m|  %-4d | %-15s | %-15s | %-10s | %-12s |\033[0m%n",
                    tarefa.getId(),
                    tarefa.getDataCriada(),
                    tarefa.getDataVencimento(),
                    prioridade,
                    tarefa.getStatus());
        } else {
            System.out.printf("|  %-4d | %-15s | %-15s | %-10s | %-12s |%n",
                    tarefa.getId(),
                    tarefa.getDataCriada(),
                    tarefa.getDataVencimento(),
                    prioridade,
                    tarefa.getStatus());
        }
    }

    public Tarefa selectId(int id) {
        Optional<Tarefa> tarefa = tarefaRepository.findById(id);
        if (tarefa.isEmpty()) {
            throw new RuntimeException("Tarefa nao encontrada");
        }
        return tarefa.get();
    }

    public Tarefa deleteTarefa(int id) {
        Tarefa tarefa = selectId(id);
        if (tarefa != null) {
            tarefaRepository.deleteById(id);
            return tarefa;
        }
        throw new RuntimeException("Tarefa  nao encontrada");
    }

    public Tarefa putTarefa(Integer id, Tarefa tarefaUpdate) {
        Tarefa tarefa = selectId(id);
        if (tarefa != null) {
            LocalDate now = LocalDate.now();
            if (tarefaUpdate.getTitulo() != null) {
                tarefa.setTitulo(tarefaUpdate.getTitulo());
            }

            if (tarefaUpdate.getDescricao() != null) {
                tarefa.setDescricao(tarefaUpdate.getDescricao());
            }

            if (tarefaUpdate.getPrioridade() != null) {
                tarefa.setPrioridade(tarefaUpdate.getPrioridade());
            }

            if (tarefaUpdate.getStatus() != null) {
                tarefa.setStatus(tarefaUpdate.getStatus());
            }

            if (tarefaUpdate.getDataCriada() != null && tarefaUpdate.getDataCriada().isBefore(now)) {
                tarefa.setDataCriada(tarefaUpdate.getDataCriada());
            }

            if (tarefaUpdate.getDataVencimento() != null && tarefaUpdate.getDataVencimento().isBefore(now)) {
                tarefa.setDataVencimento(tarefaUpdate.getDataVencimento());

            tarefaRepository.save(tarefa);
            return tarefa;
            }

            throw new RuntimeException("Tarefa nao encontrada");
        }

        public Tarefa moveTarefa(Integer id){
            Tarefa tarefa = selectId(id);
            if(tarefa != null){
                switch (tarefa.getStatus()) {
                    case " A fazer":
                        tarefa.setStatus("Em progresso");
                        break;
                    case " Em progresso":
                        tarefa.setStatus("Concluido");
                        break;
                    default:
                        throw new RuntimeException("Tarefa concluid");
                }
                return tarefaRepository.save(tarefa);
            }
            throw new RuntimeException("Tarefa nao encontrada");
        }
    }
}

