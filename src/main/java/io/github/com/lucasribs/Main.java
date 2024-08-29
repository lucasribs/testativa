package io.github.com.lucasribs;

import DAO.ContasAReceberDAO;
import DAO.PagamentoDAO;
import model.ContasAReceber;
import model.Pagamento;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // Inicializar os DAOs
        ContasAReceberDAO contaDAO = new ContasAReceberDAO();
        PagamentoDAO pagamentoDAO = new PagamentoDAO();

        // 1. Criar uma nova conta a receber
        ContasAReceber novaConta = new ContasAReceber();
        novaConta.setDescricao("Conta de Energia Elétrica");
        novaConta.setValorTotal(500.00);

        // 2. Criar dois pagamentos associados à nova conta
        Pagamento pagamento1 = new Pagamento();
        pagamento1.setData(new Date());
        pagamento1.setValor(250.00);
        pagamento1.setUsuarioId(1L);
        //pagamento1.setContaAReceber(novaConta);
        //pagamentoDAO.salvarPagamento(pagamento1);

        Pagamento pagamento2 = new Pagamento();
        pagamento2.setData(new Date());
        pagamento2.setValor(250.00);
        pagamento2.setUsuarioId(2L);
        //pagamento2.setContaAReceber(novaConta);
        //pagamentoDAO.salvarPagamento(pagamento2);

        // Associar os pagamentos à conta
        novaConta.setPagamentos(Arrays.asList(pagamento1, pagamento2));

        // 3. Salvar a nova conta a receber (o que também salvará os pagamentos devido ao CascadeType.ALL)
        contaDAO.salvarConta(novaConta);

        // 4. Recuperar e exibir todas as contas a receber do banco de dados
        List<ContasAReceber> todasContas = contaDAO.buscarTodasContas();
        System.out.println("Contas a Receber no Banco de Dados:");
        for (ContasAReceber conta : todasContas) {
            System.out.println("ID: " + conta.getId() + ", Descrição: " + conta.getDescricao() + ", Valor Total: " + conta.getValorTotal());

            // Exibir os pagamentos associados à conta
            List<Pagamento> pagamentos = conta.getPagamentos();
            for (Pagamento pag : pagamentos) {
                System.out.println("  Pagamento ID: " + pag.getId() + ", Valor: " + pag.getValor() + ", Data: " + pag.getData());
            }
        }

        // Fechar os DAOs
        //contaDAO.excluirConta(novaConta.getId());
        pagamentoDAO.fechar();
        contaDAO.fechar();
    }
}