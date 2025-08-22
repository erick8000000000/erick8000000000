package casasBahia;

import excecoes.PrecoInvalidoException;
import excecoes.CodigoDuplicadoException;
import excecoes.ProdutoInvalidoException;
import excecoes.OpcaoInvalidaException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Sistema {
   private ArrayList<Produto> produtos = new ArrayList();

   public Sistema() {
   }

   public void adicionarProduto(Scanner scanner) throws PrecoInvalidoException, CodigoDuplicadoException, ProdutoInvalidoException, OpcaoInvalidaException {
      System.out.println("Digite o nome do produto: ");
      String nome = scanner.nextLine();
      if (nome == null || nome.trim().isEmpty()) {
         throw new ProdutoInvalidoException("Nome do produto inválido!");
      }

      System.out.println("Digite o código: ");
      String codigo = scanner.nextLine();
      // Verifica código duplicado
      for (Produto p : produtos) {
         if (p.getCodigo().equals(codigo)) {
            throw new CodigoDuplicadoException("Código já cadastrado!");
         }
      }

      System.out.println("Digite o preço do produto: ");
      Double preco = scanner.nextDouble();
      scanner.nextLine();
      if (preco <= 0.0) {
         throw new PrecoInvalidoException("O preço precisa ser maior que 0");
      } else {
         System.out.println("Qual o tipo do produto: ");
         System.out.println("1 - Móvel ");
         System.out.println("2 - Eletro ");
         int opcao = scanner.nextInt();
         scanner.nextLine();
         if (opcao == 1) {
            this.adicionarMovel(scanner, nome, codigo, preco);
         } else if (opcao == 2) {
            this.adicionarEletro(scanner, nome, codigo, preco);
         } else {
            throw new OpcaoInvalidaException("Opção inválida! Digite 1 para Móvel ou 2 para Eletro.");
         }
      }
   }

   private void adicionarMovel(Scanner scanner, String nome, String codigo, double preco) {
      System.out.println("Digite o material:");
      String material = scanner.nextLine();
      System.out.println("Digite a categoria:");
      String categoria = scanner.nextLine();
      Movel movel = new Movel(codigo, nome, preco, categoria, material);
      this.produtos.add(movel);
      System.out.println("Produto adicionado");
   }

   private void adicionarEletro(Scanner scanner, String nome, String codigo, double preco) throws OpcaoInvalidaException {
      CategoriaEletro categoriaEletro = null;
      System.out.println("Qual a categoria do eletrodomestico cadastrado?");
      System.out.println("1 - Cozinha");
      System.out.println("2 - Quarto");
      System.out.println("3 - Lavanderia");
      int opcaoCategoria = scanner.nextInt();
      scanner.nextLine();
      if (opcaoCategoria == 1) {
         categoriaEletro = CategoriaEletro.COZINHA;
      } else if (opcaoCategoria == 2) {
         categoriaEletro = CategoriaEletro.QUARTO;
      } else if (opcaoCategoria == 3) {
         categoriaEletro = CategoriaEletro.LAVANDERIA;
      } else {
         throw new OpcaoInvalidaException("Opção inválida! Digite 1, 2 ou 3 para categoria.");
      }

      System.out.println("Digite a voltagem");
      int voltagem = scanner.nextInt();
      scanner.nextLine();
      Eletrodomestico eletro = new Eletrodomestico(codigo, nome, preco, categoriaEletro, voltagem);
      this.produtos.add(eletro);
      System.out.println("Produto adicionado");
   }

   public void listarProdutos() {
      if (this.produtos.size() == 0) {
         System.out.println("Nenhum produto cadastrado!");
      } else {
         Iterator var2 = this.produtos.iterator();

         while(var2.hasNext()) {
            Produto produto = (Produto)var2.next();
            System.out.println(produto);
         }
      }

   }

   public Produto buscarProduto(Scanner scanner) {
      System.out.println("Digite o código procurado:");
      String codigoProcurado = scanner.nextLine();
      Iterator var4 = this.produtos.iterator();

      while(var4.hasNext()) {
         Produto produto = (Produto)var4.next();
         if (produto.getCodigo().equals(codigoProcurado)) {
            System.out.println("Produto encontrado!");
            System.out.println(produto);
            return produto;
         }
      }

      System.out.println("Produto não encontrado!");
      return null;
   }

   public void removerProduto(Scanner scanner) {
      System.out.println("Digite o código do produto: ");
      String codigoProcurado = scanner.nextLine();

      for(int i = 0; i < this.produtos.size(); ++i) {
         if (((Produto)this.produtos.get(i)).getCodigo().equals(codigoProcurado)) {
            this.produtos.remove(i);
            System.out.println("Produto removido");
            return;
         }
      }

      System.out.println("Produto não encontrado!");
   }
}