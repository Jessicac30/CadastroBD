package cadastrobd.test;

import cadastrobd.model.PessoaFisica;
import cadastrobd.model.PessoaFisicaDAO;
import cadastrobd.model.PessoaJuridica;
import cadastrobd.model.PessoaJuridicaDAO;
import java.util.List;

public class CadastroBDTeste {

    public static void main(String[] args) {
        PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();

        PessoaFisica pessoaExistente = pessoaFisicaDAO.getPessoaPorCpf("11111111111");
        if (pessoaExistente != null) {
            pessoaFisicaDAO.excluir(pessoaExistente.getId());
        }

        PessoaFisica novaPessoa = new PessoaFisica(0, "João", "Rua 12, casa 3, Quintanda", "Riacho do Sul", "PA", "1111-1111", "joao@riacho.com", "11111111111");
        boolean sucessoInclusao = pessoaFisicaDAO.incluir(novaPessoa);


            novaPessoa.setNome("Pedro");
            novaPessoa.setLogradouro("Rua nova, 456");
            novaPessoa.setCidade("Nova Cidade");
            novaPessoa.setEstado("SP");
            novaPessoa.setTelefone("8765-4321");
            novaPessoa.setEmail("pedro@email.com");
            boolean sucessoAlteracao = pessoaFisicaDAO.alterar(novaPessoa);

            
            List<PessoaFisica> pessoasFisicas = pessoaFisicaDAO.getPessoas();
            System.out.println("Listando todas as pessoas físicas:");
            for (PessoaFisica pf : pessoasFisicas) {
                pf.exibir();
            }

            boolean sucessoExclusao = pessoaFisicaDAO.excluir(novaPessoa.getId());
            
            PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();

 
        PessoaJuridica novaPessoaJuridica = new PessoaJuridica(0, "JJC", "Rua 11, Centro", "Riacho do Norte", "PA", "1212-1212", "jjc@riacho.com", "11111111111111");
        boolean sucessoInclusaoPJ = pessoaJuridicaDAO.incluir(novaPessoaJuridica);
  
            novaPessoaJuridica.setNome("CCJ");
            novaPessoaJuridica.setLogradouro("Rua nova, 12");
            novaPessoaJuridica.setCidade("Cidade Nova");
            novaPessoaJuridica.setEstado("SP");
            novaPessoaJuridica.setTelefone("8765-1123");
            novaPessoaJuridica.setEmail("CCJ@email.com");
            boolean sucessoAlteracaoPJ = pessoaJuridicaDAO.alterar(novaPessoaJuridica);

            List<PessoaJuridica> todasPessoasJuridicas = pessoaJuridicaDAO.getPessoas();
            System.out.println("Listando todas as pessoas jurídicas:");
            for (PessoaJuridica pj : todasPessoasJuridicas) {
                pj.exibir();
            }

            boolean sucessoExclusaoPJ = pessoaJuridicaDAO.excluir(novaPessoaJuridica.getId());
    }
}

