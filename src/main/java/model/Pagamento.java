package model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_pagamentos")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_pagamento")
    @Temporal(TemporalType.DATE)
    private Date data;

    @Column(name = "valor")
    private Double valor;

    @Column(name = "usuario_id")
    private Long usuarioId;

    @ManyToOne
    @JoinColumn(name = "conta_id")
    private ContasAReceber contaAReceber;

    // Getters e Setters

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

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public ContasAReceber getContaAReceber() {
        return contaAReceber;
    }

    public void setContaAReceber(ContasAReceber contaAReceber) {
        this.contaAReceber = contaAReceber;
    }
}
