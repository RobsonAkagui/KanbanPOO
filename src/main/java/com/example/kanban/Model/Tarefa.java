package com.example.kanban.Model;

import com.example.kanban.Enums.TarefaPrioridadeEnumber;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;

public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    @Column(nullable = false)
    private String titulo;

    private String descricao;
    private LocalDate dataCriada = LocalDate.now();
    private String status = "a fazer";
    private TarefaPrioridadeEnumber prioridade;
    private LocalDate dataVencimento ;

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDate getDataCriada() {
        return dataCriada;
    }

    public String getStatus() {
        return status;
    }

    public TarefaPrioridadeEnumber getPrioridade() {
        return prioridade;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setDataCriada(LocalDate dataCriada) {
        this.dataCriada = dataCriada;
    }

    public void setPrioridade(TarefaPrioridadeEnumber prioridade) {
        this.prioridade = prioridade;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }
}
