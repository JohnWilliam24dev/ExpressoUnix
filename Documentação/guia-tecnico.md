
# Guia Técnico do ExpressoUnix

## Sumário

- [Camada de Entidades](#camada-de-entidades)  
- [Camada de Repositório](#camada-de-repositório)  
- [Camada de Aplicação](#camada-de-aplicação)  
- [Camada de Fachada](#camada-de-fachada)  
- [Camada de Controller](#camada-de-controller)  

## Camada de Entidades

Nesta camada definimos as classes que representam os dados da nossa aplicação, geralmente mapeando tabelas do banco. No ExpressoUnix temos, por exemplo, a classe `Funcionario` (no pacote `Entities`), que possui atributos como `id`, `nome`, `dataNascimento` e `cargo`. Essas entidades são como **modelos de pessoas** numa fábrica: contêm somente dados (com seus getters/setters) e não fazem nada além de armazenar essas informações. Elas são usadas pelas camadas superiores (como Repositório e Application) para criar objetos e transportar dados, mas em si não têm lógica de negócio. 

A camada de Entidades não comunica diretamente com o cliente ou com o banco; ela **é** o dado. As camadas mais altas (Application/Service) recebem objetos `Funcionario`, usam seus valores e depois passam para o Repositório gravar no banco. Como já foi escrito antes, aqui só colocamos atributos e anotações JPA (`@Entity`, `@Id`, etc.) – pense na entidade como a ficha de cadastro num formulário, que vai do cliente até o banco de dados.

## Camada de Repositório

A **Camada de Repositório** é como o **almoxarifado** da nossa fábrica de software: sua responsabilidade é *armazenar e recuperar dados* do banco. É nessa camada que fazemos o acesso à persistência. No Spring, usamos a anotação `@Repository` para indicar isso. A camada de Repositório conhece diretamente o banco de dados (via JPA), mas não sabe nada sobre regras de negócio.

A relação dessa camada é simples: ela só recebe solicitações da camada de Aplicação (Service). Ou seja, **Application** chama os métodos do repositório para salvar, buscar ou apagar objetos. Em analogia, imagine que a Aplicação é a linha de produção que precisa de peças (dados); ela pede ao Repositório (estoquista) e ele entrega ou armazena essas peças.

### Exemplo
```java
public void createFuncionario(Funcionario funcionario){
    funcionarioJPA.save(funcionario);
}

public Funcionario getFuncionarioById(long id){
    return funcionarioJPA.findById(id).get();
}

public List<Funcionario> getAllFuncionario(){
    return funcionarioJPA.findAll();
}

public void updateFuncionario(Funcionario funcionario){
    funcionarioJPA.save(funcionario);
}

public void deleteFuncionario(long id){
    funcionarioJPA.deleteById(id);
}
```

## Camada de Aplicação

A **Camada de Aplicação** (ou Service) é como o **gerente de chão de fábrica**. Ela recebe as solicitações do controlador (via fachada) e sabe *o que* deve ser feito – qual é a lógica de negócio. Em projetos Spring, damos a essa camada a anotação `@Service`. 

No nosso exemplo, a camada de Aplicação ainda é simples, porque não há muita regra complexa: ela delega diretamente para o Repositório. Mas ela **faz a mediação** entre a fachada/controlador e o repositório.

### Exemplo
```java
public void createFuncionario(Funcionario funcionario){
    funcionarioRepository.createFuncionario(funcionario);
}

public Funcionario getFuncionarioById(long id) {
    return funcionarioRepository.getFuncionarioById(id);
}

public List<Funcionario> getAllFuncionario() {
    return funcionarioRepository.getAllFuncionario();
}

public void updateFuncionario(Funcionario funcionario) {
    funcionarioRepository.updateFuncionario(funcionario);
}

public void deleteFuncionario(long id) {
    funcionarioRepository.deleteFuncionario(id);
}
```

## Camada de Fachada

A **Camada de Fachada** é uma camada extra que age como uma espécie de **porta de entrada ou atendente geral** para o serviço. No ExpressoUnix usamos um componente anotado com `@Component` chamado `FuncionarioFacade`. Sua função é receber chamadas dos controladores (HTTP) e repassá-las à camada de Aplicação.

Na prática, no nosso código a fachada tem métodos quase idênticos aos da camada de Aplicação. Ela simplifica o controlador, evitando que o controller precise saber detalhes de vários serviços.

### Exemplo
```java
public void createFuncionario(Funcionario funcionario) {
    funcionarioApplication.createFuncionario(funcionario);
}

public Funcionario getFuncionarioById(long id) {
    return funcionarioApplication.getFuncionarioById(id);
}

public List<Funcionario> getAllFuncionario() {
    return funcionarioApplication.getAllFuncionario();
}

public void updateFuncionario(Funcionario funcionario) {
    funcionarioApplication.updateFuncionario(funcionario);
}

public void deleteFuncionario(long id) {
    funcionarioApplication.deleteFuncionario(id);
}
```

## Camada de Controller

A **Camada de Controller** é o ponto de contato com o mundo exterior (nosso “balcão de atendimento” da fábrica). Em aplicações Spring Boot, usamos `@RestController` para definir esse componente. O controller é responsável por receber requisições HTTP (GET, POST, PUT, DELETE, etc.), extrair parâmetros e chamar as camadas internas para processar essas requisições.

### Exemplo
```java
@PostMapping
public void createFuncionario(@RequestBody Funcionario funcionario) {
    funcionarioFacade.createFuncionario(funcionario);
}

@GetMapping("/{id}")
public Funcionario getFuncionarioById(@PathVariable long id) {
    return funcionarioFacade.getFuncionarioById(id);
}

@GetMapping
public List<Funcionario> getAllFuncionarios() {
    return funcionarioFacade.getAllFuncionario();
}

@PutMapping("/{id}")
public void updateFuncionario(@RequestBody Funcionario funcionario) {
    funcionarioFacade.updateFuncionario(funcionario);
}

@DeleteMapping("/{id}")
public void deleteFuncionario(@PathVariable long id) {
    funcionarioFacade.deleteFuncionario(id);
}
```

O controller não faz cálculos nem acessa o banco diretamente; ele apenas converte a requisição HTTP em uma chamada Java e repassa para a fachada. Assim, respeita-se a divisão de responsabilidades da arquitetura N-layer.
