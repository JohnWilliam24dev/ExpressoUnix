# Documentação Geral – ExpressoUnix

## 🎯 Componente: João

Componente central do sistema, responsável por lidar com a lógica principal de gestão de viagens rodoviárias e interação com o usuário.

---

## 🏷️ Nome do Sistema: ExpressoUnix

## 📝 Descrição Geral

**ExpressoUnix** é um sistema completo de **gerenciamento rodoviário**, projetado para oferecer uma experiência integrada e inteligente tanto para usuários finais (passageiros) quanto para empresas de transporte. Ele atua como um hub digital de transporte terrestre, unificando funcionalidades essenciais para tornar a jornada do passageiro mais fluida, segura e personalizada.

---

## 🎯 Objetivos do Sistema

- Centralizar a gestão de **viagens rodoviárias**;
- Fornecer **controle completo da jornada** ao passageiro;
- Eliminar a necessidade de bilhetes físicos com a **passagem virtual**;
- Oferecer **planejamento de rotas** e **paradas personalizadas**;
- Possibilitar **realocação de viagens** de forma prática;
- Integrar usuários com empresas via uma plataforma intuitiva;
- Ser uma solução escalável que se adapte a diferentes modelos de operação.

---

## 📱 Plataformas Suportadas

- **Mobile (Android / iOS)**: Foco em usabilidade para passageiros e motoristas em campo.
- **Web**: Dashboard gerencial para empresas, atendimento ao cliente e funcionalidades administrativas.

---

## 👥 Públicos-Alvo

### Pessoa Física (PF)

- Passageiros regulares de viagens rodoviárias;
- Viajantes que precisam de flexibilidade e controle na jornada;
- Usuários que desejam evitar filas, papelada ou falta de informações.

### Pessoa Jurídica (PJ)

- Empresas de transporte rodoviário;
- Cooperativas ou consórcios regionais de transporte;
- Empresas que desejam digitalizar e integrar sua operação de transporte.

---

## 💼 Modelo de Negócio

**ExpressoUnix** adota um modelo baseado em **SaaS escalável**, com diferentes níveis de adesão, pensados para atender desde empresas com infraestrutura consolidada até aquelas que desejam implantar uma solução completa do zero.

| Plano | Indicação | Descrição | Implantacão |
| ----- | ---------- | ----------- | ------------- |
| **Básico (SaaS)** | Empresas que **já possuem um sistema próprio** de gerenciamento e **não desejam migrar** ou substituí-lo. | Implantação **apenas do aplicativo mobile**, com integração leve ao sistema atual (via API ou arquivos). | Nuvem |
| **Completo (SaaS)** | Empresas que **não possuem sistema próprio** e desejam **adotar toda a solução ExpressoUnix**. | Implantação completa com painéis administrativos, gestão de rotas, motoristas e interface mobile. | Nuvem |
| **Enterprise (SaaS com migração de dados)** | Empresas que **já possuem sistema** e querem migrar para o ExpressoUnix, **mantendo o banco de dados sob controle próprio**. | Suporte à migração e integração com o banco de dados da empresa. | SaaS + opcional On Premise |

### Comparativo

| Característica | Básico | Completo | Enterprise |
| --------------- | ------- | -------- | ---------- |
| Sistema próprio existente | ✅ | ❌ | ✅ |
| Implantação completa | ❌ | ✅ | ✅ |
| App para passageiros | ✅ | ✅ | ✅ |
| Painel administrativo | ❌ | ✅ | ✅ |
| Integração com legado | Simples | N/A | Avançada |
| Banco de dados próprio | ❌ | ❌ | ✅ |

---

## 🔧 Funcionalidades Principais

### Para Usuário Final (PF)

- Emissão de passagem virtual com QR Code;
- Visualização da rota e pontos de parada;
- Notificações sobre alterações na viagem;
- Solicitação de realocação de viagem em tempo real;
- Histórico de viagens e sugestões inteligentes.

### Para Empresas (PJ)

- Gerenciamento de rotas e horários;
- Cadastro e gestão de veículos e motoristas;
- Monitoramento de ocupação e performance;
- Ferramentas de remarcação e reembolso;
- Integração com meios de pagamento;
- Relatórios de desempenho, faturamento e KPIs.

---

## Diferenciais do Sistema

- **Unificação total da jornada do passageiro**;
- **Flexibilidade modular para empresas de qualquer porte**;
- **Infraestrutura pensada para escalabilidade**;
- **Foco em usabilidade e experiência fluida**;
- **Modelo comercial adaptável (SaaS, On Premise ou híbrido)**.

---

## 🔒 Segurança e Compliance

- Autenticação multifator para empresas;
- Proteção de dados conforme LGPD;
- Criptografia ponta a ponta na passagem virtual;
- Logs auditáveis por parte das empresas.

---

## 📈 Potencial de Expansão

- Integração com terminais rodoviários;
- Módulo de avaliação de viagem por usuários;
- Recompensas e gamificação para clientes fidelizados;
- APIs para integração com ERPs e CRMs de transportadoras.
