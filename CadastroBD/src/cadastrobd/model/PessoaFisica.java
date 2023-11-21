package cadastrobd.model;

public class PessoaFisica extends Pessoa {
    private String cpf;

    
    public PessoaFisica(String joão, String rua_12_casa_3, String riacho_do_Sul, String pa, String string, String joaoriachocom, String string1) {
        super();
    }

    public PessoaFisica(int id, String nome, String logradouro, String cidade, String estado, String telefone, String email, String cpf) {
        super(id, nome, logradouro, cidade, estado, telefone, email);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }

    @Override
    public String getNome() {
        return super.getNome();
    }

    @Override
    public void setNome(String nome) {
        super.setNome(nome);
    }

    @Override
    public String getLogradouro() {
        return super.getLogradouro();
    }

    @Override
    public void setLogradouro(String logradouro) {
        super.setLogradouro(logradouro);
    }

    @Override
    public String getCidade() {
        return super.getCidade();
    }

    @Override
    public void setCidade(String cidade) {
        super.setCidade(cidade);
    }

    @Override
    public String getEstado() {
        return super.getEstado();
    }

    @Override
    public void setEstado(String estado) {
        super.setEstado(estado);
    }

    @Override
    public String getTelefone() {
        return super.getTelefone();
    }

    @Override
    public void setTelefone(String telefone) {
        super.setTelefone(telefone);
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email);
    }
    
    @Override
    public void exibir() {
        super.exibir();
        System.out.println("CPF: " + cpf);
    }
    
    @Override
    public String toString() {
        return "PessoaFisica{" +
               "id=" + getId() +
               ", nome='" + getNome() + '\'' +
               ", logradouro='" + getLogradouro() + '\'' +
               ", cidade='" + getCidade() + '\'' +
               ", estado='" + getEstado() + '\'' +
               ", telefone='" + getTelefone() + '\'' +
               ", email='" + getEmail() + '\'' +
               ", cpf='" + cpf + '\'' +
               '}';
    }   
}
