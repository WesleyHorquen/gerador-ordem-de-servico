package src.main.kotlin

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

// Classe para armazenar os dados iniciais
data class DadosIniciais(
    val dataAtendimento: String,
    val chegada: String,
    val saida: String,
    val clientePresente: String
)

// Modifique a função dadosIniciais para RETORNAR os dados
fun dadosIniciais(): DadosIniciais {
    val dataAtendimento = lerEntrada("\nData do Atendimento (DD/MM/AA): ")
    val chegada = lerEntrada("Chegada (HH:MM): ")
    val saida = lerEntrada("Saída (HH:MM): ")
    val clientePresente = lerSimNao("Cliente estava no local")

    return DadosIniciais(dataAtendimento, chegada, saida, clientePresente)
}

data class ObsAssinaturas(
    val observacoes: String,
    val assinaturaTecnico: String,
    val assinaturaCliente: String,
    val cpfCliente: String
)

fun obsAssinaturas(clientePresente: String): ObsAssinaturas {
    // Observações e assinaturas (sempre preenchidos)
    println("\n📋 OBSERVAÇÕES:")
    val observacoes = lerEntrada("OBS: ", permitirVazio = true)

    println("\n✍️ ASSINATURAS:")
    val assinaturaTecnico = lerEntrada("Assinatura do Técnico: ")
    val assinaturaCliente = lerEntrada("Assinatura do Cliente/Responsável: ",
        permitirVazio = clientePresente != "SIM") // Opcional se cliente não estava presente
    val cpfCliente = lerEntrada("CPF do Cliente/Responsável (opcional): ", permitirVazio = true)

    return ObsAssinaturas(observacoes, assinaturaTecnico, assinaturaCliente, cpfCliente)
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

    val dados = dadosIniciais()

    var porta = ""
    var metragemUtilizada = ""
    var modelo = ""

    var sugeridoInstalacao = ""
    var instaladoEm = ""

    if(dados.clientePresente == "SIM"){
        porta = lerEntrada("Porta: ")
        metragemUtilizada = lerEntrada("Metragem utilizada: ")
        modelo = lerEntrada("Modelo: ")

        sugeridoInstalacao = lerEntrada("Foi sugerido instalação no(a): ")
        instaladoEm = lerEntrada("Equipamento instalado no(a): ")
    }else{
        println("\n⚠️ Cliente não estava presente")
    }

    val obsAssinaturasData = obsAssinaturas(dados.clientePresente)

    // Criar objeto OS com os dados coletados
    val osInstalacaoRepetidor = OrdemServico(
        tipo = "Instalação de Repetidores",
        dataAtendimento = dados.dataAtendimento,
        chegada = dados.chegada,
        saida = dados.saida,
        clientePresente = dados.clientePresente,
        instaladoEm = instaladoEm,
        modelo = modelo,
        sinal = "",
        equipamentoBom = "",
        temRepetidor = "",
        temEquipamentosTV = "",
        porta = porta,
        metragemUtilizada = metragemUtilizada,
        sugeridoInstalacao = sugeridoInstalacao,
        observacoes = obsAssinaturasData.observacoes,
        assinaturaTecnico = obsAssinaturasData.assinaturaTecnico,
        assinaturaCliente = obsAssinaturasData.assinaturaCliente,
        cpfCliente = obsAssinaturasData.cpfCliente
    )
    exibirOS(osInstalacaoRepetidor)
}

fun reativacao() {
    println("\n" + "=".repeat(50))
    println("\t>>> REATIVAÇÃO <<<")
    println("=".repeat(50))

    val dados = dadosIniciais()

    // Variáveis que podem ser vazias se cliente não estava presente
    var instaladoEm = ""
    var modelo = ""
    var sinal = ""
    var equipamentoBom = ""
    var temRepetidor = ""
    var temEquipamentosTV = ""

    // Só pergunta sobre equipamento se cliente estava presente
    if (dados.clientePresente == "SIM") {
        instaladoEm = lerEntrada("Equipamento instalado no(a): ")
        modelo = lerEntrada("Modelo do Equipamento: ")
        sinal = lerEntrada("Sinal: ")

        println("\n🔍 ITENS AVALIADOS:")
        equipamentoBom = lerSimNao("Equipamento em bom estado")
        temRepetidor = lerSimNao("Tem Repetidor de Sinal")
        temEquipamentosTV = lerSimNao("Tem Equipamentos de TV")
    } else {
        println("\n⚠️ Cliente não estava presente")
    }

    val obsAssinaturasData = obsAssinaturas(dados.clientePresente)

    // Criar objeto OS com os dados coletados
    val osReativacao = OrdemServico(
        tipo = "Reativação",
        dataAtendimento = dados.dataAtendimento,
        chegada = dados.chegada,
        saida = dados.saida,
        clientePresente = dados.clientePresente,
        instaladoEm = instaladoEm,
        modelo = modelo,
        sinal = sinal,
        equipamentoBom = equipamentoBom,
        temRepetidor = temRepetidor,
        temEquipamentosTV = temEquipamentosTV,
        porta = "",
        metragemUtilizada = "",
        sugeridoInstalacao = "",
        observacoes = obsAssinaturasData.observacoes,
        assinaturaTecnico = obsAssinaturasData.assinaturaTecnico,
        assinaturaCliente = obsAssinaturasData.assinaturaCliente,
        cpfCliente = obsAssinaturasData.cpfCliente
    )

    exibirOS(osReativacao)
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
    val porta: String,
    val metragemUtilizada: String,
    val sugeridoInstalacao: String,
    val observacoes: String,
    val assinaturaTecnico: String,
    val assinaturaCliente: String,
    val cpfCliente: String
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
    val dia = dataLimpa.take(2)
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

    // Só mostra campos de equipamento se houver conteúdo ou se cliente estava presente
    if (os.clientePresente == "SIM") {
        println("\nEquipamento instalado no(a): ${if (os.instaladoEm.isNotBlank()) os.instaladoEm else "Não informado"}")
        println("Modelo do Equipamento: ${if (os.modelo.isNotBlank()) os.modelo else "Não informado"}")
        println("Sinal: ${if (os.sinal.isNotBlank()) os.sinal else "Não informado"}")

        println("\nItens Avaliados")
        println("Equipamento em bom estado: ${if (os.equipamentoBom.isNotBlank()) os.equipamentoBom else "N/A"}")
        println("Tem Repetidor de Sinal: ${if (os.temRepetidor.isNotBlank()) os.temRepetidor else "N/A"}")
        println("Tem Equipamentos de TV: ${if (os.temEquipamentosTV.isNotBlank()) os.temEquipamentosTV else "N/A"}")
    } else {
        println("\n⚠️ Cliente não estava presente - sem dados de equipamento")
    }

    println("\nOBS: ${if (os.observacoes.isNotBlank()) os.observacoes else "---"}")
    println("\nAssinatura do Técnico: ${os.assinaturaTecnico}")

    val clienteCompleto = if (os.cpfCliente.isNotBlank()) {
        "${os.assinaturaCliente}   CPF: ${os.cpfCliente}"
    } else {
        os.assinaturaCliente
    }
    println("Assinatura do Cliente/Responsável: ${if (clienteCompleto.isNotBlank()) clienteCompleto else "---"}")

    println("\n" + "=".repeat(50))
    println("✅ OS gerada com sucesso!")
    println("=".repeat(50))
}


fun main() {
    println("\t\n >>> Olá, Bem vindo ao gerador de OS <<<\n")
    val nomeDoCliente = validaNome("> Digite o nome do cliente: ")
    menuDeOS()
}