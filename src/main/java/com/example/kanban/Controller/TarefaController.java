package com.example.kanban.Controller;

import com.example.kanban.Model.Tarefa;
import com.example.kanban.Serivce.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @GetMapping
    public List<Tarefa> getAllTarefas(){
        return tarefaService.findAll();
    }
    @PostMapping
    public Tarefa postTarefa(@RequestBody Tarefa tarefa){
        return tarefaService.inserirTarefa(tarefa);
    }
    DeleteMapping
    public Tarefa deleteTarefa(@PathVariable Integer id) {
        if (tarefaService.selectId(id) != null) {
            return tarefaService.deleteTarefa(id);
        }
        return tarefaService.selectId(id);
    }
    @PutMapping
    public Tarefa putTarefa(@PathVariable Integer id){
        if (tarefaService.selectId(id) != null) {
            return tarefaService.putTarefa(id,tarefa);
        }
        return tarefaService.selectId(id);
    }
    @PutMapping
    public Tarefa moveTarefa(@PathVariable Integer id){
        if (tarefaService.selectId(id) != null) {
            return tarefaService.moveTarefa(id);
        }
        return tarefaService.selectId(id);
    }
}
