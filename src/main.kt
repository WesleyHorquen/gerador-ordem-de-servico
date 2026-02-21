fun validaNome(mensagem: String): String {
    while (true) {
        println(mensagem)
        val entrada = readlnOrNull()

        /*
        isNullOrBlank() Retorna true se a string for null OU se contiver apenas espaços em branco
        ! reverte o resultado.
        */
        if (!entrada.isNullOrBlank()) {
            return entrada
        }
        println("> Nome inválido. Por favor, digite novamente.\n")
    }
}

fun menuDeOS() {
    while (true) {
        println("\t> Qual tipo de OS deseja gerar?\n")
        println("1 - Visita Técnica")
        println("2 - Manutenção Corretiva")
        println("3 - Instalação")
        println("4 - Instalação de Repetidores")
        println("5 - Reativação")
        println("6 - Troca de Equipamento")
        println("7 - Retirada de Equipamento")
        println("8 - Avaliação de Realocação")
        println("9 - Realocação de Equipamento")
        println("0 - Sair")

        /*
        ?. executa função à direita se o valor à esquerda NÃO for null
        toIntOrNull() tenta converter para inteiro, se não conseguir retorna null
        ?: Elvis Operator, se o resultado à esquerda for null, usa o valor a direita
        */
        val opcao = readlnOrNull()?.toIntOrNull() ?: -1

        when (opcao) {
            1 -> visitaTecnica()
            2 -> manutencaoCorretiva()
            3 -> instalacao()
            4 -> instalacaoDeRepetidores()
            5 -> reativacao()
            6 -> trocaDeEquipamento()
            7 -> retiradaDeEquipamento()
            8 -> avaliacaoDeRealocacao()
            9 -> realocacaoDeEquipamento()
            0 -> {
                println("> Encerrando...")
                break
            }

            else -> println("> Opção inválida! Digite um número entre 0 e 9.\n")
        }
        println()
    }
}

fun visitaTecnica() {
    println("\n" + "=".repeat(50))
    println("\t>>> Visita Tecnica <<<")
    println("=".repeat(50))
}

fun manutencaoCorretiva() {
    println("\n" + "=".repeat(50))
    println("\t>>> Manutencao Corretiva <<<")
    println("=".repeat(50))
}

fun instalacao() {
    println("\n" + "=".repeat(50))
    println("\t>>> Instalacao <<<")
    println("=".repeat(50))
}

fun instalacaoDeRepetidores() {
    println("\n" + "=".repeat(50))
    println("\t>>> Instalacao de Repetidor <<<")
    println("=".repeat(50))
}

fun reativacao() {
    println("\n" + "=".repeat(50))
    println("\t>>> REATIVAÇÃO <<<")
    println("=".repeat(50))

    // Coleta dos dados e criação do objeto OS
    val os = OrdemServico(
        tipo = "Reativação",
        dataAtendimento = lerEntrada("\nData do Atendimento (DD/MM/AA): "),
        chegada = lerEntrada("Chegada (HH:MM): "),
        saida = lerEntrada("Saída (HH:MM): "),
        clientePresente = lerSimNao("Cliente estava no local"),
        instaladoEm = lerEntrada("\nEquipamento instalado no(a): "),
        modelo = lerEntrada("Modelo do Equipamento: "),
        sinal = lerEntrada("Sinal: "),
        equipamentoBom = lerSimNao("\nEquipamento em bom estado"),
        temRepetidor = lerSimNao("Tem Repetidor de Sinal"),
        temEquipamentosTV = lerSimNao("Tem Equipamentos de TV"),
        observacoes = lerEntrada("\nOBS: ", permitirVazio = true),
        assinaturaTecnico = lerEntrada("\nAssinatura do Técnico: "),
        assinaturaCliente = lerEntrada("Assinatura do Cliente/Responsável: "),
        cpfCliente = lerEntrada("CPF do Cliente/Responsável (opcional): ", permitirVazio = true)
    )

    // Exibe a OS preenchida
    exibirOS(os)
}

fun trocaDeEquipamento() {
    println("\n" + "=".repeat(50))
    println("\t>>> Troca de Equipamento <<<")
    println("=".repeat(50))
}

fun retiradaDeEquipamento() {
    println("\n" + "=".repeat(50))
    println("\t>>> Retirada de Equipamento <<<")
    println("=".repeat(50))
}

fun avaliacaoDeRealocacao() {
    println("\n" + "=".repeat(50))
    println("\t>>> Avaliacao para Realocacao <<<")
    println("=".repeat(50))
}

fun realocacaoDeEquipamento() {
    println("\n" + "=".repeat(50))
    println("\t>>> Realocacao de Equipamento <<<")
    println("=".repeat(50))
}

// Classe para armazenar os dados da OS
data class OrdemServico(
    val tipo: String,
    val dataAtendimento: String,
    val chegada: String,
    val saida: String,
    val clientePresente: String,
    val instaladoEm: String,
    val modelo: String,
    val sinal: String,
    val equipamentoBom: String,
    val temRepetidor: String,
    val temEquipamentosTV: String,
    val observacoes: String,
    val assinaturaTecnico: String,
    val assinaturaCliente: String,
    val cpfCliente: String = ""
)

// Função auxiliar para ler entradas com validação
fun lerEntrada(mensagem: String, permitirVazio: Boolean = false): String {
    while (true) {
        print(mensagem)
        val entrada = readlnOrNull()?.trim()

        when {
            entrada == null -> println("Erro de leitura. Tente novamente.")
            entrada.isBlank() && !permitirVazio -> println("Campo obrigatório! Por favor, preencha.")
            else -> {
                // Se a mensagem for de data, aplica a formatação
                if (mensagem.contains("Data", ignoreCase = true)) {
                    return formatarData(entrada)
                }
                return entrada
            }
        }
    }
}

// Função auxiliar para ler respostas SIM/NÃO
fun lerSimNao(pergunta: String): String {
    while (true) {
        print("$pergunta (SIM/NÃO): ")
        val resposta = readlnOrNull()?.trim()?.uppercase()

        when (resposta) {
            "SIM", "S" -> return "SIM"
            "NÃO", "NAO", "N" -> return "NÃO"
            else -> println("Resposta inválida! Digite SIM ou NÃO.")
        }
    }
}

fun formatarData(data: String): String {
    // Se a data já estiver no formato correto (com barras), retorna ela mesma
    if (data.contains("/")) {
        return data
    }

    // Remove espaços em branco
    val dataLimpa = data.trim()

    // Pega os 2 primeiros caracteres (dia), os 2 do meio (mês) e os 2 últimos (ano)
    val dia = dataLimpa.substring(0, 2)
    val mes = dataLimpa.substring(2, 4)
    val ano = dataLimpa.substring(4, 6)

    // Retorna no formato dia/mês/ano
    return "$dia/$mes/$ano"
}

fun exibirOS(os: OrdemServico) {
    println("\n" + "=".repeat(50))
    println("\tORDEM DE SERVIÇO - ${os.tipo.uppercase()}")
    println("=".repeat(50))

    println("\nTipo: ${os.tipo}")
    println("Data do Atendimento: ${os.dataAtendimento}")
    println("Chegada: ${os.chegada}")
    println("Saída: ${os.saida}")
    println("Cliente estava no local: ${os.clientePresente}")

    println("\nEquipamento instalado no(a): ${os.instaladoEm}")
    println("Modelo do Equipamento: ${os.modelo}")
    println("Sinal: ${os.sinal}")

    println("\nItens Avaliados")
    println("Equipamento em bom estado: ${os.equipamentoBom}")
    println("Tem Repetidor de Sinal: ${os.temRepetidor}")
    println("Tem Equipamentos de TV: ${os.temEquipamentosTV}")

    println("\nOBS: ${os.observacoes}")

    println("\nAssinatura do Técnico: ${os.assinaturaTecnico}")

    val clienteCompleto = if (os.cpfCliente.isNotBlank()) {
        "${os.assinaturaCliente}   CPF: ${os.cpfCliente}"
    } else {
        os.assinaturaCliente
    }
    println("Assinatura do Cliente/Responsável: $clienteCompleto")

    println("\n" + "=".repeat(50))
    println("✅ OS gerada com sucesso!")
    println("=".repeat(50))
}


fun main() {
    println("\t\n >>> Olá, Bem vindo ao gerador de OS <<<\n")
    menuDeOS()
    //val nomeDoCliente = validaNome("> Digite o nome do cliente: ")
}