# 🛒 e-Compras (Sistema de Loja em Java)

Sistema de console desenvolvido em Java com foco em **Programação Orientada a Objetos (POO)**, simulando o funcionamento de uma loja virtual com cadastro de produtos, clientes e pedidos.

---

## 🚀 Objetivo

Este projeto tem como objetivo praticar e demonstrar conceitos fundamentais de desenvolvimento em Java, incluindo:

- Orientação a Objetos (POO)
- Herança e Polimorfismo
- Encapsulamento
- Camadas de aplicação (Entidade, Negócio, Utilidade)
- Boas práticas de código
- Documentação com Javadoc

---

## 🧠 Funcionalidades

✔ Cadastro de produtos (Livros e Cadernos)  
✔ Listagem de produtos  
✔ Exclusão de produtos com confirmação  
✔ Cadastro de pedidos  
✔ Aplicação de cupons de desconto  
✔ Identificação de cliente por CPF  
✔ Cálculo de total com frete e desconto  
✔ Listagem de pedidos realizados  

---

## 🏗️ Estrutura do Projeto

```
src/
└── diversos/
    └── exoodio/
        ├── basedados/
        │   └── DataBase.java
        │
        ├── entidade/
        │   ├── Produto.java
        │   ├── Livro.java
        │   ├── Caderno.java
        │   ├── Pedido.java
        │   ├── Cliente.java
        │   ├── Cupom.java
        │   └── constantes/
        │       ├── Genero.java
        │       └── Tipo.java
        │
        ├── negocio/
        │   ├── ProdutoNegocio.java
        │   ├── PedidoNegocio.java
        │   └── ClienteNegocio.java
        │
        ├── utilidade/
        │   └── LeitoraDados.java
        │
        └── console/
            └── Start.java
```

---

## 🧩 Conceitos Aplicados

### 🔹 Herança
```java
public abstract class Produto { ... }

public class Livro extends Produto { ... }
public class Caderno extends Produto { ... }
```

### 🔹 Polimorfismo
```java
List<Produto> produtos = new ArrayList<>();
produtos.add(new Livro());
produtos.add(new Caderno());
```

### 🔹 Encapsulamento
```java
private double preco;

public double getPreco() { return preco; }
public void setPreco(double preco) { this.preco = preco; }
```

### 🔹 Uso de Optional
```java
Optional<Produto> produto = produtoNegocio.consultar(codigo);
```

### 🔹 Stream API
```java
bancoDados.getProdutos()
    .stream()
    .filter(p -> p.getCodigo().equalsIgnoreCase(codigo))
    .findFirst();
```

---

## ⚙️ Como Executar

### 🔧 Pré-requisitos

- Java JDK 8 ou superior
- Eclipse ou qualquer IDE Java

---

### ▶️ Executando via terminal

```bash
javac -d bin src/**/*.java
java -cp bin diversos.exoodio.console.Start
```

---

### ▶️ Executando via Eclipse

1. Importe o projeto
2. Vá até:
```
diversos.exoodio.console.Start
```
3. Clique em **Run**

---

## 💡 Exemplo de Uso

```
Bem vindo ao e-Compras

Digite o cpf: 12345678901

Cadastrando pedido...
Digite o código do produto: PR0001
Digite a quantidade: 2

Deseja selecionar mais um produto? n

Caso queira utilizar algum cupom: CUPOM10

Pedido cadastrado com sucesso!
```

---

## 📚 Documentação

O projeto utiliza **Javadoc completo**, documentando:

- Classes
- Métodos
- Regras de negócio

---

## 🧪 Melhorias Futuras

- [ ] Cadastro dinâmico de clientes
- [ ] Persistência em arquivo ou banco de dados
- [ ] Interface gráfica (Swing/JavaFX)
- [ ] API REST com Spring Boot
- [ ] Validações mais robustas

---

## 👨‍💻 Autor

**Guilherme Neto**  
GitHub: https://github.com/guilhermeNetogit  

---

## 📝 Padrão de Commits

Este projeto segue um padrão semântico de commits:

```
feat: nova funcionalidade
fix: correção de bug
refactor: melhoria de código
docs: documentação
```

Exemplo:

```bash
git commit -m "feat: add product creation functionality"
git commit -m "refactor: improve PedidoNegocio logic"
```

---

## ⭐ Considerações Finais

Este projeto foi desenvolvido com foco em aprendizado e boas práticas, sendo uma excelente base para evolução para aplicações maiores.

---

💥 **Projeto ideal para portfólio de desenvolvedor Java iniciante/intermediário**
