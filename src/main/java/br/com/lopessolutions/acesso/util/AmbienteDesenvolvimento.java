package br.com.lopessolutions.acesso.util;

import br.com.lopessolutions.entidades.principal.TipoAcesso;
import br.com.lopessolutions.entidades.principal.Usuario;
import br.com.lopessolutions.base.criptografia.Criptografia;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class AmbienteDesenvolvimento {

    private static final int TESTE = 0;
    private static final int PRODUCAO = 1;
    private static int ambiente = PRODUCAO; // alterar essa variável para PRODUCAO ou TESTE

    @PersistenceContext(unitName = "LS")
    private EntityManager em;

    public static boolean isProducao() {
        return (ambiente == PRODUCAO);
    }

    public void seedIfFirstAccess() {
        // Tipos de Acesso
        ensureTipoAcesso(1, "Sem Acesso");
        ensureTipoAcesso(2, "Acesso Personalizado");
        ensureTipoAcesso(20, "Acesso Total");

        // Usuário administrador padrão: cria se não existir pelo CPF criptografado
        String encCpfAdmin = Criptografia.getCriptografar("00000000000");
        Long qtdAdmin = em.createQuery("select count(u) from Usuario u where u.cpf = :cpf", Long.class)
                .setParameter("cpf", encCpfAdmin)
                .getSingleResult();
        if (qtdAdmin == 0) {
            TipoAcesso acessoTotal = em.find(TipoAcesso.class, 20);

            Usuario admin = new Usuario();
            admin.setNome("Administrador");
            admin.setCpf("00000000000");
            admin.setSenha("1234");
            admin.setEmail("admin@sistema.local");
            admin.setFornecedorMateria(true);
            admin.setPublicador(true);
            admin.setHabilitado(true);
            admin.setTipoAcesso(acessoTotal);
            em.persist(admin);
        }
    }

    private void ensureTipoAcesso(int codigo, String descricao) {
        TipoAcesso ta = em.find(TipoAcesso.class, codigo);
        if (ta == null) {
            ta = new TipoAcesso();
            ta.setCodigo(codigo);
            ta.setDescricao(descricao);
            em.persist(ta);
        }
    }

    // Nenhuma permissão por formulário é criada aqui; o controle é por TipoAcesso
}