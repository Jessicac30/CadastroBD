import cadastrobd.model.PessoaFisica;
import cadastrobd.model.PessoaFisicaDAO;
import cadastrobd.model.PessoaJuridica;
import cadastrobd.model.PessoaJuridicaDAO;
import java.util.Scanner;
import java.util.List;

public class CadastroBD {

    private static final Scanner scanner = new Scanner(System.in);
    private static final PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
    private static final PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("==================================");
            System.out.println("1 - Incluir Pessoa");
            System.out.println("2 - Alterar Pessoa");
            System.out.println("3 - Excluir Pessoa");
            System.out.println("4 - Buscar pelo Id");
            System.out.println("5 - Exibir Todos");
            System.out.println("0 - Finalizar Programa");
            System.out.println("==================================");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    incluir();
                    break;
                case 2:
                    alterar();
                    break;
                case 3:
                    excluir();
                    break;
                case 4:
                    exibirPorId();
                    break;
                case 5:
                    exibirTodos();
                    break;
                case 0:
                    System.out.println("Finalizando Programa.");
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        } while (opcao != 0);

        scanner.close();
    }

    private static void incluir() {
    System.out.println("F - Pessoa Física | J - Pessoa Jurídica");
    String tipo = scanner.nextLine().trim().toUpperCase();

    if ("F".equals(tipo)) {
        System.out.println("Inserindo Pessoa Física...");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Logradouro: ");
        String logradouro = scanner.nextLine();
        System.out.print("Cidade: ");
        String cidade = scanner.nextLine();
        System.out.print("Estado: ");
        String estado = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        PessoaFisica pf = new PessoaFisica(0, nome, logradouro, cidade, estado, telefone, email, cpf);
        boolean sucesso = pessoaFisicaDAO.incluir(pf);
        if (sucesso) {
            System.out.println("Pessoa Física incluída com sucesso.");
            pf.exibir();
        } else {
            System.out.println("Erro ao incluir Pessoa Física.");
        }
    } else if ("J".equals(tipo)) {
        
        System.out.println("Inserindo Pessoa Jurídica...");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Logradouro: ");
        String logradouro = scanner.nextLine();
        System.out.print("Cidade: ");
        String cidade = scanner.nextLine();
        System.out.print("Estado: ");
        String estado = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("CNPJ: ");
        String cnpj = scanner.nextLine();

        PessoaJuridica pj = new PessoaJuridica(0, nome, logradouro, cidade, estado, telefone, email, cnpj);
        boolean sucesso = pessoaJuridicaDAO.incluir(pj);
        if (sucesso) {
            System.out.println("Pessoa Jurídica incluída com sucesso.");
            pj.exibir();
        } else {
            System.out.println("Erro ao incluir Pessoa Jurídica.");
        }
    } else {
        System.out.println("Opção inválida. Por favor, digite 'F' para Pessoa Física ou 'J' para Pessoa Jurídica.");
    }
}

    private static void alterar() {
    System.out.println("F - Pessoa Física | J - Pessoa Jurídica");
    String tipo = scanner.nextLine().toUpperCase();
    
    System.out.println("Digite o ID da pessoa para alterar:");
    int id = scanner.nextInt();
    scanner.nextLine();

    if ("F".equals(tipo)) {
        PessoaFisica pf = pessoaFisicaDAO.getPessoa(id);
        if (pf != null) {
            System.out.println("Dados atuais:");
            System.out.println(pf);

            System.out.print("Novo Nome (deixe em branco para manter): ");
            String nome = scanner.nextLine();
            if (!nome.isEmpty()) pf.setNome(nome);

            System.out.print("Novo Logradouro (deixe em branco para manter): ");
            String logradouro = scanner.nextLine();
            if (!logradouro.isEmpty()) pf.setLogradouro(logradouro);

            System.out.print("Nova Cidade (deixe em branco para manter): ");
            String cidade = scanner.nextLine();
            if (!cidade.isEmpty()) pf.setCidade(cidade);

            System.out.print("Novo Estado (deixe em branco para manter): ");
            String estado = scanner.nextLine();
            if (!estado.isEmpty()) pf.setEstado(estado);

            System.out.print("Novo Telefone (deixe em branco para manter): ");
            String telefone = scanner.nextLine();
            if (!telefone.isEmpty()) pf.setTelefone(telefone);

            System.out.print("Novo Email (deixe em branco para manter): ");
            String email = scanner.nextLine();
            if (!email.isEmpty()) pf.setEmail(email);

            System.out.print("Novo CPF (deixe em branco para manter): ");
            String cpf = scanner.nextLine();
            if (!cpf.isEmpty()) pf.setCpf(cpf);

            boolean sucesso = pessoaFisicaDAO.alterar(pf);
            if (sucesso) {
                System.out.println("Pessoa Física atualizada com sucesso.");
                pf.exibir();
            } else {
                System.out.println("Erro ao atualizar Pessoa Física.");
            }
        } else {
            System.out.println("Pessoa Física não encontrada.");
        }
    } else if ("J".equals(tipo)) {
        PessoaJuridica pj = pessoaJuridicaDAO.getPessoa(id);
        if (pj != null) {
            System.out.println("Dados atuais:");
            System.out.println(pj);

            System.out.print("Novo Nome (deixe em branco para manter): ");
            String nome = scanner.nextLine();
            if (!nome.isEmpty()) pj.setNome(nome);

            System.out.print("Novo Logradouro (deixe em branco para manter): ");
            String logradouro = scanner.nextLine();
            if (!logradouro.isEmpty()) pj.setLogradouro(logradouro);

            System.out.print("Nova Cidade (deixe em branco para manter): ");
            String cidade = scanner.nextLine();
            if (!cidade.isEmpty()) pj.setCidade(cidade);

            System.out.print("Novo Estado (deixe em branco para manter): ");
            String estado = scanner.nextLine();
            if (!estado.isEmpty()) pj.setEstado(estado);

            System.out.print("Novo Telefone (deixe em branco para manter): ");
            String telefone = scanner.nextLine();
            if (!telefone.isEmpty()) pj.setTelefone(telefone);

            System.out.print("Novo Email (deixe em branco para manter): ");
            String email = scanner.nextLine();
            if (!email.isEmpty()) pj.setEmail(email);

            System.out.print("Novo CNPJ (deixe em branco para manter): ");
            String cnpj = scanner.nextLine();
            if (!cnpj.isEmpty()) pj.setCnpj(cnpj);

            boolean sucesso = pessoaJuridicaDAO.alterar(pj);
            if (sucesso) {
                System.out.println("Pessoa Jurídica atualizada com sucesso.");
                pj.exibir();
            } else {
                System.out.println("Erro ao atualizar Pessoa Jurídica.");
            }
        } else {
            System.out.println("Pessoa Jurídica não encontrada.");
        }
    } else {
        System.out.println("Opção inválida.");
    }
}

    private static void excluir() {
    System.out.println("F - Pessoa Física | J - Pessoa Jurídica");
    String tipo = scanner.nextLine();
    
    System.out.println("Digite o ID da pessoa para excluir:");
    int id = scanner.nextInt();
    scanner.nextLine();

    if (tipo.equalsIgnoreCase("F")) {
        boolean sucesso = pessoaFisicaDAO.excluir(id);
        if (sucesso) {
            System.out.println("Pessoa Física excluída com sucesso.");
        } else {
            System.out.println("Erro ao excluir Pessoa Física.");
        }
    } else if (tipo.equalsIgnoreCase("J")) {
        boolean sucesso = pessoaJuridicaDAO.excluir(id);
        if (sucesso) {
            System.out.println("Pessoa Jurídica excluída com sucesso.");
        } else {
            System.out.println("Erro ao excluir Pessoa Jurídica.");
        }
    } else {
        System.out.println("Opção inválida.");
    }
}

    private static void exibirPorId() {
    System.out.println("F - Pessoa Física | J - Pessoa Jurídica");
    String tipo = scanner.nextLine();
    System.out.println("Digite o ID da pessoa para exibir:");
    int id = scanner.nextInt();
    scanner.nextLine();

    if (tipo.equalsIgnoreCase("F")) {
        PessoaFisica pf = pessoaFisicaDAO.getPessoa(id);
        if (pf != null) {
            System.out.println("Exibindo dados de Pessoa Fisica:");
            pf.exibir();
        } else {
            System.out.println("Pessoa Fisica não encontrada.");
        }
    } else if (tipo.equalsIgnoreCase("J")) {
        PessoaJuridica pj = pessoaJuridicaDAO.getPessoa(id);
        if (pj != null) {
            System.out.println("Exibindo dados de Pessoa Juridica:");
            pj.exibir();
        } else {
            System.out.println("Pessoa Jurídica não encontrada.");
        }
    } else {
        System.out.println("Opção inválida.");
    }
}

    private static void exibirTodos() {
    System.out.println("F - Pessoa Física | J - Pessoa Jurídica");
    String tipo = scanner.nextLine();

    if (tipo.equalsIgnoreCase("F")) {
        List<PessoaFisica> pessoasFisicas = pessoaFisicaDAO.getPessoas();
        System.out.println("Lista de Pessoas Físicas:");
        for (PessoaFisica pf : pessoasFisicas) {
                pf.exibir();
            }
    } else if (tipo.equalsIgnoreCase("J")) {
        List<PessoaJuridica> pessoasJuridicas = pessoaJuridicaDAO.getPessoas();
        System.out.println("Lista de Pessoas Jurídicas:");
        for (PessoaJuridica pj : pessoasJuridicas) {
            pj.exibir();
        }
    } else {
        System.out.println("Opção inválida.");
    }
}

}
