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

    // Exibe informações do produto
    public void exibirProduto() {
        System.out.println("Código: " + codigo);
        System.out.println("Nome: " + nome);
        System.out.println("Tamanho/Peso: " + tamanhoPeso);
        System.out.println("Cor: " + cor);
        System.out.println("Valor: R$ " + valor);
        System.out.println("Quantidade em Estoque: " + quantidadeEstoque);
    }

    // Método para realizar uma venda
    public void venderProduto(int quantidade, String formaPagamento) {
        if (quantidade > quantidadeEstoque) {
            System.out.println("Estoque insuficiente.");
            return;
        }

        double valorTotal = valor * quantidade;
        double desconto = 0;

        // Verifica a forma de pagamento e aplica desconto se necessário
        if (formaPagamento.equalsIgnoreCase("Pix") || formaPagamento.equalsIgnoreCase("Espécie") ||
            formaPagamento.equalsIgnoreCase("Transferência") || formaPagamento.equalsIgnoreCase("Débito")) {
            desconto = valorTotal * 0.05;  // 5% de desconto
            valorTotal -= desconto;
            System.out.println("Desconto de 5% aplicado. Valor total: R$ " + valorTotal);
        } else if (formaPagamento.equalsIgnoreCase("Crédito")) {
            System.out.println("Parcelado em 3x de R$ " + (valorTotal / 3));
        }

        // Verifica se é pagamento em espécie e calcula troco
        if (formaPagamento.equalsIgnoreCase("Espécie")) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Valor recebido: R$ ");
            double valorRecebido = scanner.nextDouble();

            if (valorRecebido >= valorTotal) {
                double troco = valorRecebido - valorTotal;
                System.out.println("Troco: R$ " + troco);
            } else {
                System.out.println("Valor insuficiente para a compra.");
                return;
            }
        }

        // Atualiza o estoque
        quantidadeEstoque -= quantidade;
        System.out.println("Venda realizada com sucesso. Estoque restante: " + quantidadeEstoque);
    }

    // Método para cadastrar um produto
    public static SistemaControleProduto cadastrarProduto() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o código do produto: ");
        int codigo = scanner.nextInt();

        scanner.nextLine();  // Consumir a nova linha

        System.out.print("Digite o nome do produto: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o tamanho/peso do produto: ");
        double tamanhoPeso = scanner.nextDouble();

        scanner.nextLine();  // Consumir a nova linha

        System.out.print("Digite a cor do produto: ");
        String cor = scanner.nextLine();

        System.out.print("Digite o valor do produto: R$ ");
        double valor = scanner.nextDouble();

        System.out.print("Digite a quantidade em estoque: ");
        int quantidadeEstoque = scanner.nextInt();

        return new SistemaControleProduto(codigo, nome, tamanhoPeso, cor, valor, quantidadeEstoque);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Cadastro do produto
        SistemaControleProduto produto = cadastrarProduto();
        produto.exibirProduto();

        // Realização da venda
        System.out.print("\nDigite a quantidade a ser vendida: ");
        int quantidadeVendida = scanner.nextInt();

        scanner.nextLine();  // Consumir a nova linha

        System.out.print("Digite a forma de pagamento (Pix, Espécie, Transferência, Débito, Crédito): ");
        String formaPagamento = scanner.nextLine();

        produto.venderProduto(quantidadeVendida, formaPagamento);

        scanner.close();
    }
}
