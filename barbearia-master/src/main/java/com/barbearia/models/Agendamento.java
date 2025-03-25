package com.barbearia.models;

import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "agendamentos")
public class Agendamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date data;

    private LocalTime hora;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "barbeiro_id", nullable = false)
    private Barbeiro barbeiro;

    @ManyToOne
    @JoinColumn(name = "servico_id", nullable = false)
    private Servico servico;

    @OneToOne(mappedBy = "agendamento", cascade = CascadeType.ALL, orphanRemoval = true)
    private Pagamento pagamento;


    public Agendamento() {
    }

    public Agendamento(Long id, Date data, LocalTime hora, Usuario usuario, Barbeiro barbeiro, Servico servico, Pagamento pagamento) {
        this.id = id;
        this.data = data;
        this.hora = hora;
        this.usuario = usuario;
        this.barbeiro = barbeiro;
        this.servico = servico;
        this.pagamento = pagamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Barbeiro getBarbeiro() {
        return barbeiro;
    }

    public void setBarbeiro(Barbeiro barbeiro) {
        this.barbeiro = barbeiro;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }
    public Pagamento getPagamento() {
        return pagamento;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Agendamento that = (Agendamento) o;
        return Objects.equals(id, that.id) && Objects.equals(data, that.data) && Objects.equals(hora, that.hora) && Objects.equals(usuario, that.usuario) && Objects.equals(barbeiro, that.barbeiro) && Objects.equals(servico, that.servico) && Objects.equals(pagamento, that.pagamento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, data, hora, usuario, barbeiro, servico, pagamento);
    }
}
