import java.util.Scanner;

public class SistemaControleProduto {

    // Atributos do produto
    private int codigo;
    private String nome;
    private double tamanhoPeso;
    private String cor;
    private double valor;
    private int quantidadeEstoque;

    // Construtor para inicializar o produto
    public SistemaControleProduto(int codigo, String nome, double tamanhoPeso, String cor, double valor, int quantidadeEstoque) {
        this.codigo = codigo;
        this.nome = nome;
        this.tamanhoPeso = tamanhoPeso;
        this.cor = cor;
        this.valor = valor;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    // Método para exibir informações do produto
    public void exibirInformacoesProduto() {
        System.out.println("Código: " + codigo);
        System.out.println("Nome: " + nome);
        System.out.println("Tamanho/Peso: " + tamanhoPeso);
        System.out.println("Cor: " + cor);
        System.out.println("Valor: R$ " + valor);
        System.out.println("Quantidade em Estoque: " + quantidadeEstoque);
    }

    // Método para realizar venda do produto
    public void venderProduto(int quantidade, String formaPagamento) {
        if (quantidade > quantidadeEstoque) {
            System.out.println("Quantidade em estoque insuficiente.");
            return;
        }

        double valorTotal = valor * quantidade;
        double desconto = 0;

        // Aplicar descontos conforme a forma de pagamento
        if (formaPagamento.equalsIgnoreCase("Pix") || formaPagamento.equalsIgnoreCase("Espécie") || 
            formaPagamento.equalsIgnoreCase("Transferência") || formaPagamento.equalsIgnoreCase("Débito")) {
            desconto = valorTotal * 0.05;  // 5% de desconto
            valorTotal -= desconto;
            System.out.println("Forma de pagamento: " + formaPagamento);
            System.out.println("Desconto de 5% aplicado. Valor com desconto: R$ " + valorTotal);
        } else if (formaPagamento.equalsIgnoreCase("Crédito")) {
            System.out.println("Forma de pagamento: Crédito");
            System.out.println("Parcelado em 3x de R$ " + (valorTotal / 3));
        }

        // Para pagamentos em espécie, verificar troco
        if (formaPagamento.equalsIgnoreCase("Espécie")) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Valor recebido: R$ ");
            double valorRece
