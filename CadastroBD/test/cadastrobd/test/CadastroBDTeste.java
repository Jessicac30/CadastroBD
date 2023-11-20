package cadastrobd.test;

import cadastrobd.model.PessoaFisica;
import cadastrobd.model.PessoaFisicaDAO;
import cadastrobd.model.PessoaJuridica;
import cadastrobd.model.PessoaJuridicaDAO;
import java.util.List;

public class CadastroBDTeste {

    public static void main(String[] args) {
        PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
        PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();

       
        PessoaFisica pf = new PessoaFisica(0, "João", "Rua 12, casa 3, Quintanda", "Riacho do Sul", "PA", "1111-1111", "joao@riacho.com", "11111111111");
        pessoaFisicaDAO.incluir(pf);

       
        pf.setNome("Pedro");
        pessoaFisicaDAO.alterar(pf);

       
        List<PessoaFisica> listaPF = pessoaFisicaDAO.getPessoas();
        listaPF.forEach(PessoaFisica::exibir);

       
        pessoaFisicaDAO.excluir(pf.getId());

        
        PessoaJuridica pj = new PessoaJuridica(0, "JJC", "Rua 11, Centro", "Riacho do Norte", "PA", "1212-1212", "jjc@riacho.com", "11111111111111");
        pessoaJuridicaDAO.incluir(pj);

        
        pj.setNome("CCJ");
        pessoaJuridicaDAO.alterar(pj);

        
        List<PessoaJuridica> listaPJ = pessoaJuridicaDAO.getPessoas();
        listaPJ.forEach(PessoaJuridica::exibir);

        
        pessoaJuridicaDAO.excluir(pj.getId());
    }
    
}

