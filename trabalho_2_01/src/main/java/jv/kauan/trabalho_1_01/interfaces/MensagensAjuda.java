package jv.kauan.trabalho_1_01.interfaces;

public class MensagensAjuda {

    public static final String COMO_ABRIR = "📁 Como abrir um arquivo:\n"
        + "◦Para abrir um arquivo salvo anteriormente, siga os passos abaixo:\n\n"
        + "1 - Clique no menu Arquivo, localizado no canto superior esquerdo da tela.\n\n"
        + "2 - Selecione a opção Abrir.\n\n"
        + "3 - Na janela que será exibida, escolha o arquivo desejado no seu computador.\n\n"
        + "4 - Clique em Abrir para carregar o conteúdo no programa.\n\n"
        + "*Certifique-se de que o arquivo esteja no formato compatível com o sistema.";

    public static final String COMO_SALVAR = "💾  Como salvar um tabuleiro:\n"
        + "◦Para salvar o tabuleiro atual, siga os passos:\n\n"
        + "1 - Com o tabuleiro carregado ou editado na tela, vá até o menu Arquivo, no canto superior esquerdo.\n\n"
        + "2 - Clique na opção Salvar.\n\n"
        + "3 - Uma janela será exibida permitindo que você:\n\n"
        + "    Escolha a pasta onde deseja salvar o arquivo.\n\n"
        + "    Digite o nome do arquivo.\n\n"
        + "4 - Após definir o local e o nome, clique em Salvar.\n\n"
        + "* O tabuleiro será gravado no local escolhido com as configurações atuais.";

    public static final String COMO_EDITAR = "✏️ Como editar o tabuleiro:\n"
        + "◦Para editar uma célula do tabuleiro, siga os passos abaixo:\n\n"
        + "1 - Vá até o menu Arquivo, no canto superior esquerdo da tela.\n\n"
        + "2 - Clique na opção Editar.\n\n"
        + "3 - Uma nova janela será aberta exibindo o tabuleiro em formato de botões.\n\n"
        + "4 - Clique no botão correspondente à célula que deseja modificar.\n\n"
        + "5 - Um menu será exibido para que você selecione:\n\n"
        + "    ◦O tipo da célula (Clássica, Forte, Tímida ou Matemática).\n"
        + "    ◦O estado da célula (Viva ou Morta).\n\n"
        + "6 - Após fazer as alterações, clique em Salvar na janela de edição.\n\n"
        + "*A célula será atualizada com as novas configurações.\n\n"
        + "Atenção: o tabuleiro só será alterado se você clicar no botão Salvar.";

    public static final String COMO_AVANCAR = "⏭️ Como avançar o tabuleiro:\n"
        + "O sistema oferece duas formas de avançar as interações do tabuleiro:\n\n"
        + "🔹 Avançar manualmente:\n"
        + "1 - Na tela principal, clique no botão Avançar.\n\n"
        + "2 - O tabuleiro será atualizado para a próxima interação (uma única vez).\n\n"
        + "🔹 Avançar automaticamente:\n"
        + "1 - Clique no botão Avançar automaticamente, também localizado na tela principal.\n\n"
        + "2 - Uma nova janela será exibida.\n\n"
        + "3 - Nessa janela, você deve:\n"
        + "    ◦Informar o número de interações desejado.\n"
        + "    ◦Definir o tempo de delay (em milissegundos) entre cada interação.\n\n"
        + "4 - Após configurar, clique em Iniciar.\n\n"
        + "5 - O tabuleiro começará a avançar automaticamente com base nas configurações fornecidas.\n\n"
        + "⚠️ Observações:\n"
        + "◦Enquanto as interações automáticas estiverem em andamento, o tabuleiro será atualizado a cada intervalo de tempo definido.\n\n"
        + "◦Você pode interromper a execução precionando o botão de pausar.";

    public static final String TIPOS_CELULAS = "◦ Célula clássica: se comporta de maneira idêntica às células do jogo da vida original.\n"
        + "– Toda célula morta com exatamente três vizinhos vivos torna-se viva.\n"
        + "– Toda célula viva com menos de dois vizinhos vivos morre por isolamento.\n"
        + "– Toda célula viva com mais de três vizinhos vivos morre por superpopulação.\n"
        + "– Toda célula viva com dois ou três vizinhos vivos permanece viva.\n"
        + "A vizinhança é definida como as oito células ao redor da célula em questão.\n"
        + "*Quando viva, seu símbolo deve ser o caractere “+”;\n\n"
        + "◦ Célula forte:\n"
        + "– Caso esteja viva, morre apenas se seu número de vizinhos vivos é menor que 2. Caso contrário, continua viva.\n"
        + "– Caso esteja morta, se torna viva se o seu número de vizinhos vivos é maior que 4. Caso contrário, continua morta.\n"
        + "*Quando viva, seu símbolo deve ser o caractere “@”.\n\n"
        + "◦ Célula tímida:\n"
        + "– Caso esteja viva, permanece viva apenas se nenhum de seus vizinhos está vivo. Caso contrário, morre.\n"
        + "– Caso esteja morta, permanece morta enquanto algum de seus vizinhos estiver vivo. Caso contrário, se torna viva.\n"
        + "*Quando viva, seu símbolo deve ser o caractere “&”.\n\n"
        + "◦ Célula matemática:\n"
        + "– Caso esteja viva, se mantém viva apenas se seu número de vizinhos vivos é impar. Caso contrário morre.\n"
        + "– Caso esteja morta, só se torna viva se o seu número de vizinhos vivos é par. Caso contrário permanece morta.\n"
        + "*Quando viva, seu símbolo deve ser o caractere “#”.\n\n"
        + "◦ Célula de borda: sempre está morta, nunca viva.\n"
        + "*Toda célula morta (não importando seu tipo) é representada pelo caractere “.”.";

    public static final String FORMATO_ARQUIVO = "◦O arquivo deve estar escrito desta maneira: \n"
        + "- Tamanho do tabuleiro (x,y).\nex: 2 2 criará um tabuleiro 2 por 2.\n\n"
        + "- Tipos das celulas (+, @, &, #).\nex: + + + + definirá as celulas do tabuleiro 2 por 2 como clássicas.\n\n"
        + "- Estado das celulas (0/1)(0= morta 1= viva).\nex: 1 1 1 1 definirá as celulas do tabuleiro 2 por 2 como vivas.";

    public static final String SOBRE = "Interface para o Jogo da Vida.\nFeito por João Vitor e Kauan.";

}
