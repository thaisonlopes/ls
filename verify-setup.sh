#!/bin/bash

echo "=== VERIFICAÇÃO DA CONFIGURAÇÃO POSTGRESQL 18 + WILDFLY 31 ==="
echo ""

# Verificar se o PostgreSQL está rodando
echo "1. Verificando PostgreSQL..."
if pg_isready -h localhost -p 5432; then
    echo "✅ PostgreSQL está rodando"
else
    echo "❌ PostgreSQL não está rodando. Inicie o serviço."
    exit 1
fi

# Verificar se o banco 'ls' existe
echo ""
echo "2. Verificando banco de dados 'ls'..."
if psql -h localhost -U postgres -d ls -c "SELECT 1;" > /dev/null 2>&1; then
    echo "✅ Banco 'ls' existe e está acessível"
else
    echo "❌ Banco 'ls' não existe ou não está acessível"
    echo "Execute: createdb -U postgres ls"
    exit 1
fi

# Verificar se o Wildfly está rodando
echo ""
echo "3. Verificando Wildfly..."
if curl -s http://localhost:9990 > /dev/null 2>&1; then
    echo "✅ Wildfly está rodando"
else
    echo "❌ Wildfly não está rodando. Inicie o servidor."
    exit 1
fi

# Verificar se o DataSource está configurado
echo ""
echo "4. Verificando DataSource LSDS..."
if curl -s "http://localhost:9990/management" > /dev/null 2>&1; then
    echo "✅ Console de administração do Wildfly acessível"
    echo "   Acesse: http://localhost:9990"
    echo "   Vá em: Configuration → Subsystems → Datasources"
    echo "   Verifique se o DataSource 'LSDS' está configurado"
else
    echo "⚠️  Console de administração não acessível"
fi

echo ""
echo "=== PRÓXIMOS PASSOS ==="
echo "1. Configure o DataSource LSDS no Wildfly"
echo "2. Faça o deploy da aplicação"
echo "3. Verifique os logs do Wildfly para confirmar a criação das tabelas"
echo "4. Acesse a aplicação em: http://localhost:8080/ls"
echo ""
echo "Usuário padrão: CPF 00000000000, Senha: admin123"
