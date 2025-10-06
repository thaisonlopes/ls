# Configuração PostgreSQL 18 para o Sistema LS

## Pré-requisitos
- PostgreSQL 18 instalado e rodando
- Wildfly 31 configurado
- JDK 17

## Passos para Configuração

### 1. Configurar o Banco de Dados PostgreSQL

```sql
-- Conectar como superusuário postgres
psql -U postgres

-- Criar o banco de dados
CREATE DATABASE ls;

-- Criar usuário (se necessário)
CREATE USER postgres WITH PASSWORD '08072014';
GRANT ALL PRIVILEGES ON DATABASE ls TO postgres;

-- Conectar ao banco ls
\c ls
```

### 2. Configurar o DataSource no Wildfly

#### Opção A: Via Console Web do Wildfly
1. Acesse: http://localhost:9990
2. Vá em Configuration → Subsystems → Datasources
3. Clique em "Add" para criar um novo DataSource
4. Configure:
   - **Name**: LSDS
   - **JNDI Name**: java:jboss/datasources/LSDS
   - **Driver**: postgresql
   - **Connection URL**: jdbc:postgresql://localhost:5432/ls
   - **Username**: postgres
   - **Password**: 08072014

#### Opção B: Via CLI do Wildfly
```bash
# Conectar ao Wildfly
./jboss-cli.sh --connect

# Executar o script de configuração
run-batch --file=wildfly-datasource-config.cli
```

### 3. Verificar a Configuração

Após configurar o DataSource, verifique se está funcionando:

```bash
# No console do Wildfly
/subsystem=datasources/data-source=LSDS:test-connection-in-pool
```

### 4. Deploy da Aplicação

1. Compile o projeto:
```bash
mvn clean package
```

2. Faça o deploy no Wildfly:
```bash
cp target/ls-1.0.0-SNAPSHOT.war $WILDFLY_HOME/standalone/deployments/
```

### 5. Verificar a Criação das Tabelas

Após o deploy, as tabelas serão criadas automaticamente. Verifique no PostgreSQL:

```sql
\c ls
\dt
```

Você deve ver as seguintes tabelas:
- intUsuario
- intTipoAcesso
- intTipoPermissao
- intFormulario
- intPermissao
- log
- parametro

### 6. Dados Iniciais

O sistema criará automaticamente:
- Usuário administrador: CPF 00000000000, Senha: admin123
- Tipos de acesso e permissão
- Formulários básicos
- Parâmetros do sistema

## Configurações do Hibernate

O sistema está configurado para:
- **hibernate.hbm2ddl.auto=create-drop**: Cria as tabelas no startup e remove no shutdown
- **hibernate.show_sql=true**: Mostra as queries SQL no console
- **hibernate.format_sql=true**: Formata as queries SQL
- **hibernate.hbm2ddl.import_files**: Executa o script de dados iniciais

## Troubleshooting

### Erro de Conexão
- Verifique se o PostgreSQL está rodando
- Confirme as credenciais no DataSource
- Teste a conexão via CLI do Wildfly

### Tabelas não criadas
- Verifique os logs do Wildfly
- Confirme se o DataSource está configurado corretamente
- Verifique se as entidades JPA estão corretas

### Problemas de Permissão
- Verifique se o usuário postgres tem permissões no banco
- Confirme se o banco 'ls' existe

## Logs Úteis

Para debugar problemas, verifique os logs em:
- Wildfly: `$WILDFLY_HOME/standalone/log/server.log`
- PostgreSQL: `/var/log/postgresql/` (Linux) ou `C:\Program Files\PostgreSQL\18\data\log\` (Windows)
