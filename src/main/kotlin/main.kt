package src.main.kotlin

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

// Classe base para dados comuns a todas as OS
data class DadosBasicos(
    val tipo: String,
    val dataAtendimento: String,
    val chegada: String,
    val saida: String,
    val clientePresente: String
)

// Classe para Visita Técnica
data class VisitaTecnicaDados(
    val instaladoEm: String,
    val teste5Ghz: String,
    val parteFisica: String,
    val interferenciaCanal: String,
    val qtdAparelhos: String,
    val compatibilidadeAparelho: String,
    val interferenciaExterna: String,
    val testeApps: String,
    val problemasOutrosEquip: String,
    val faltaCompatibilidade: String,
    val problemaAmazon: String,
    val problemaDiferente: String,
    val interferencia: String,
    val necessidadeTroca: String,
    val necessidadeRealocacao: String,
    val necessidadeRepetidores: String,
    val orientacaoEquipNaoGerenciados: String,
    val orientacaoCompatibilidade: String,
    val mudancaCanal: String,
    val oferecidoRepetidores: String
)

// Classe para Manutenção Corretiva
data class ManutencaoCorretivaDados(
    val descricao: String,
    val equipamentoDanificado: String,
    val conectorDanificado: String,
    val equipamentoQueimado: String,
    val fonteQueimada: String,
    val rompimentoFibra: String,
    val atenuacao: String,
    val nivelSinalNormal: String,
    val necessidadeTaxa: String,
    val trocaEquipamento: String,
    val trocaConector: String,
    val trocaFonte: String,
    val fusao: String,
    val lancamentoFibra: String,
    val nivelSinalNormalizado: String,
    val usoCaixaEmenda: String
)

// Classe para Instalação
data class InstalacaoDados(
    val sugeridoInstalacao: String,
    val instaladoEm: String,
    val porta: String,
    val sinal: String
)

// Classe para Instalação de Repetidores
data class InstalacaoRepetidoresDados(
    val porta: String,
    val metragemUtilizada: String,
    val modelo: String,
    val sugeridoInstalacao: String,
    val instaladoEm: String
)

// Classe para Troca de Equipamento
data class TrocaEquipamentoDados(
    val equipamentoRetirado: String,
    val equipamentoInstalado: String
)

// Classe para Retirada de Equipamento
data class RetiradaEquipamentoDados(
    val autorizacaoRetirada: String,
    val equipamentoTV: String,
    val equipamentoInternet: String,
    val drop: String,
    val necessidadeNegociacao: String
)

// Classe para Reativação
data class ReativacaoDados(
    val instaladoEm: String,
    val modelo: String,
    val sinal: String,
    val equipamentoBom: String,
    val temRepetidor: String,
    val temEquipamentosTV: String
)

// Classe para Realocação
data class RealocacaoDados(
    val descricao: String,
    val instaladoEm: String,
    val sugeridoInstalacao: String,
    val optadoInstalacao: String,
    val necessidadeTaxa: String,
    val sinal: String
)

// Função para formatação da data
fun formatarData(data: String): String {
    if (data.contains("/")) {
        return data
    }

    val dataLimpa = data.trim()
    val dia = dataLimpa.take(2)
    val mes = dataLimpa.substring(2, 4)
    val ano = dataLimpa.substring(4, 6)

    return "$dia/$mes/$ano"
}

// Função para validar nome do cliente
fun validaNome(mensagem: String): String {
    while (true) {
        println(mensagem)
        val entrada = readlnOrNull()

        if (!entrada.isNullOrBlank()) {
            return entrada
        }
        println("> Nome inválido. Por favor, digite novamente.\n")
    }
}

// Função para ler entradas com validação
fun lerEntrada(mensagem: String, permitirVazio: Boolean = false): String {
    while (true) {
        print(mensagem)
        val entrada = readlnOrNull()?.trim()

        when {
            entrada == null -> println("Erro de leitura. Tente novamente.")
            entrada.isBlank() && !permitirVazio -> println("Campo obrigatório! Por favor, preencha.")
            else -> {
                if (mensagem.contains("Data", ignoreCase = true)) {
                    return formatarData(entrada)
                }
                return entrada
            }
        }
    }
}

// Função para coletar dados básicos
fun coletarDadosBasicos(tipo: String): DadosBasicos {
    val dataAtendimento = lerEntrada("\nData do Atendimento (DD/MM/AA): ")
    val chegada = lerEntrada("Chegada (HH:MM): ")
    val saida = lerEntrada("Saída (HH:MM): ")
    val clientePresente = lerSimNao("Cliente estava no local")

    return DadosBasicos(tipo, dataAtendimento, chegada, saida, clientePresente)
}


// Função para ler respostas SIM/NÃO
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

// Classe para Observações e Assinaturas
data class ObsAssinaturas(
    val observacoes: String,
    val assinaturaTecnico: String,
    val assinaturaCliente: String,
    val cpfCliente: String
)

fun obsAssinaturas(clientePresente: String): ObsAssinaturas {
    println("\n📋 OBSERVAÇÕES:")
    val observacoes = lerEntrada("OBS: ", permitirVazio = true)

    println("\n✍️ ASSINATURAS:")
    val assinaturaTecnico = lerEntrada("Assinatura do Técnico: ")

    val assinaturaCliente = if (clientePresente == "SIM") {
        lerEntrada("Assinatura do Cliente/Responsável: ", permitirVazio = false)
    } else {
        lerEntrada("Assinatura do Cliente/Responsável (opcional): ", permitirVazio = true)
    }

    val cpfCliente = lerEntrada("CPF do Cliente/Responsável (opcional): ", permitirVazio = true)

    return ObsAssinaturas(observacoes, assinaturaTecnico, assinaturaCliente, cpfCliente)
}


fun coletarVisitaTecnica(clientePresente: String): VisitaTecnicaDados {
    var instaladoEm = ""
    var teste5Ghz = ""
    var parteFisica = ""
    var interferenciaCanal = ""
    var qtdAparelhos = ""
    var compatibilidadeAparelho = ""
    var interferenciaExterna = ""
    var testeApps = ""
    var problemasOutrosEquip = ""
    var faltaCompatibilidade = ""
    var problemaAmazon = ""
    var problemaDiferente = ""
    var interferencia = ""
    var necessidadeTroca = ""
    var necessidadeRealocacao = ""
    var necessidadeRepetidores = ""
    var orientacaoEquipNaoGerenciados = ""
    var orientacaoCompatibilidade = ""
    var mudancaCanal = ""
    var oferecidoRepetidores = ""

    if (clientePresente == "SIM") {
        instaladoEm = lerEntrada("Equipamento Instalado No(a): ")

        println("\n🔍 ITENS AVALIADOS:")
        teste5Ghz = lerSimNao("Teste de passagem de banda 5GHZ")
        parteFisica = lerSimNao("Avaliar a parte física do equipamento")
        interferenciaCanal = lerSimNao("Interferência no canal wireless")
        qtdAparelhos = lerEntrada("Quantidade de aparelhos conectados: ")
        compatibilidadeAparelho = lerSimNao("Compatibilidade do aparelho do cliente")
        interferenciaExterna = lerSimNao("Interferência externa")
        testeApps = lerSimNao("Teste de aplicativos")

        println("\n📊 CONCLUSÃO DA AVALIAÇÃO:")
        problemasOutrosEquip = lerSimNao("Problemas com outros equipamentos do cliente")
        faltaCompatibilidade = lerSimNao("Falta de compatibilidade")
        problemaAmazon = lerSimNao("Identificado problema no serviço Amazon +")
        problemaDiferente = lerSimNao("Problema diferente do abordado remotamente")
        interferencia = lerSimNao("Interferência")
        necessidadeTroca = lerSimNao("Necessidade de troca de equipamento")
        necessidadeRealocacao = lerSimNao("Necessidade de realocação")
        necessidadeRepetidores = lerSimNao("Necessidade de Repetidores")

        println("\n🔧 CORREÇÃO DO PROBLEMA:")
        orientacaoEquipNaoGerenciados = lerSimNao("Orientação sobre equipamentos não gerenciados")
        orientacaoCompatibilidade = lerSimNao("Orientação sobre compatibilidade")
        mudancaCanal = lerSimNao("Mudança de canal")
        oferecidoRepetidores = lerSimNao("Oferecido Repetidores")
    }

    return VisitaTecnicaDados(
        instaladoEm, teste5Ghz, parteFisica, interferenciaCanal, qtdAparelhos,
        compatibilidadeAparelho, interferenciaExterna, testeApps, problemasOutrosEquip,
        faltaCompatibilidade, problemaAmazon, problemaDiferente, interferencia,
        necessidadeTroca, necessidadeRealocacao, necessidadeRepetidores,
        orientacaoEquipNaoGerenciados, orientacaoCompatibilidade, mudancaCanal,
        oferecidoRepetidores
    )
}

fun coletarManutencaoCorretiva(clientePresente: String): ManutencaoCorretivaDados {
    val descricao = lerEntrada("Descrição: ")

    var equipamentoDanificado = ""
    var conectorDanificado = ""
    var equipamentoQueimado = ""
    var fonteQueimada = ""
    var rompimentoFibra = ""
    var atenuacao = ""
    var nivelSinalNormal = ""
    var necessidadeTaxa = ""
    var trocaEquipamento = ""
    var trocaConector = ""
    var trocaFonte = ""
    var fusao = ""
    var lancamentoFibra = ""
    var nivelSinalNormalizado = ""
    var usoCaixaEmenda = ""

    if (clientePresente == "SIM") {
        println("\n🔍 AVALIAÇÃO DO PROBLEMA:")
        equipamentoDanificado = lerSimNao("Equipamento Danificado")
        conectorDanificado = lerSimNao("Conector Danificado")
        equipamentoQueimado = lerSimNao("Equipamento Queimado")
        fonteQueimada = lerSimNao("Fonte Queimada")
        rompimentoFibra = lerSimNao("Rompimento de Fibra")
        atenuacao = lerSimNao("Atenuação")
        nivelSinalNormal = lerSimNao("Nível de Sinal Normal")
        necessidadeTaxa = lerSimNao("Necessidade de Pagamento de Taxa")

        println("\n🔧 CORREÇÃO DO PROBLEMA:")
        trocaEquipamento = lerSimNao("Houve troca de equipamento")
        trocaConector = lerSimNao("Houve troca de conector")
        trocaFonte = lerSimNao("Houve troca de fonte")
        fusao = lerSimNao("Fusão")
        lancamentoFibra = lerSimNao("Lançamento de Fibra")
        nivelSinalNormalizado = lerSimNao("Nível de Sinal Normalizado")
        usoCaixaEmenda = lerSimNao("Uso de Caixa e Emenda")
    }

    return ManutencaoCorretivaDados(
        descricao, equipamentoDanificado, conectorDanificado, equipamentoQueimado,
        fonteQueimada, rompimentoFibra, atenuacao, nivelSinalNormal, necessidadeTaxa,
        trocaEquipamento, trocaConector, trocaFonte, fusao, lancamentoFibra,
        nivelSinalNormalizado, usoCaixaEmenda
    )
}

fun coletarInstalacao(clientePresente: String): InstalacaoDados {
    var sugeridoInstalacao = ""
    var instaladoEm = ""
    var porta = ""
    var sinal = ""

    if (clientePresente == "SIM") {
        sugeridoInstalacao = lerEntrada("Foi sugerido instalação no(a): ")
        instaladoEm = lerEntrada("Equipamento instalado no(a): ")
        porta = lerEntrada("Porta: ")
        sinal = lerEntrada("Sinal: ")
    }

    return InstalacaoDados(sugeridoInstalacao, instaladoEm, porta, sinal)
}

fun coletarInstalacaoRepetidores(clientePresente: String): InstalacaoRepetidoresDados {
    var porta = ""
    var metragemUtilizada = ""
    var modelo = ""
    var sugeridoInstalacao = ""
    var instaladoEm = ""

    if (clientePresente == "SIM") {
        porta = lerEntrada("Porta: ")
        metragemUtilizada = lerEntrada("Metragem utilizada: ")
        modelo = lerEntrada("Modelo: ")
        sugeridoInstalacao = lerEntrada("Foi sugerido instalação no(a): ")
        instaladoEm = lerEntrada("Equipamento instalado no(a): ")
    }

    return InstalacaoRepetidoresDados(porta, metragemUtilizada, modelo, sugeridoInstalacao, instaladoEm)
}

fun coletarTrocaEquipamento(clientePresente: String): TrocaEquipamentoDados {
    var equipamentoRetirado = ""
    var equipamentoInstalado = ""

    if (clientePresente == "SIM") {
        equipamentoRetirado = lerEntrada("Equipamento retirado: ")
        equipamentoInstalado = lerEntrada("Equipamento instalado: ")
    }

    return TrocaEquipamentoDados(equipamentoRetirado, equipamentoInstalado)
}

fun coletarRetiradaEquipamento(clientePresente: String): RetiradaEquipamentoDados {
    var autorizacaoRetirada = ""
    var equipamentoTV = ""
    var equipamentoInternet = ""
    var drop = ""
    var necessidadeNegociacao = ""

    if (clientePresente == "SIM") {
        autorizacaoRetirada = lerSimNao("Cliente autorizou retirada")
        equipamentoTV = lerSimNao("Equipamento de TV")
        equipamentoInternet = lerSimNao("Equipamento de internet")
        drop = lerSimNao("Drop")
        necessidadeNegociacao = lerSimNao("Necessidade de negociação")
    }

    return RetiradaEquipamentoDados(autorizacaoRetirada, equipamentoTV, equipamentoInternet, drop, necessidadeNegociacao)
}

fun coletarReativacao(clientePresente: String): ReativacaoDados {
    var instaladoEm = ""
    var modelo = ""
    var sinal = ""
    var equipamentoBom = ""
    var temRepetidor = ""
    var temEquipamentosTV = ""

    if (clientePresente == "SIM") {
        instaladoEm = lerEntrada("Equipamento instalado no(a): ")
        modelo = lerEntrada("Modelo do Equipamento: ")
        sinal = lerEntrada("Sinal: ")

        println("\n🔍 ITENS AVALIADOS:")
        equipamentoBom = lerSimNao("Equipamento em bom estado")
        temRepetidor = lerSimNao("Tem Repetidor de Sinal")
        temEquipamentosTV = lerSimNao("Tem Equipamentos de TV")
    }

    return ReativacaoDados(instaladoEm, modelo, sinal, equipamentoBom, temRepetidor, temEquipamentosTV)
}

fun coletarRealocacao(clientePresente: String, tipo: String): RealocacaoDados {
    val descricao = if (tipo == "Realocação") lerEntrada("Descrição: ") else ""

    var instaladoEm = ""
    var sugeridoInstalacao = ""
    var optadoInstalacao = ""
    var necessidadeTaxa = ""
    var sinal = ""

    if (clientePresente == "SIM") {
        instaladoEm = lerEntrada("Equipamento está instalado no(a): ")
        sugeridoInstalacao = lerEntrada("Sugerido instalar no(a): ")
        optadoInstalacao = lerEntrada("Cliente optou por instalar no(a): ")
        necessidadeTaxa = lerSimNao("Necessidade de taxa")
        if (tipo == "Avaliação") {
            sinal = lerEntrada("Sinal: ")
        }
    }

    return RealocacaoDados(descricao, instaladoEm, sugeridoInstalacao, optadoInstalacao, necessidadeTaxa, sinal)
}

fun visitaTecnica() {
    println("\n" + "=".repeat(50))
    println("\t>>> VISITA TÉCNICA <<<")
    println("=".repeat(50))

    val dadosBasicos = coletarDadosBasicos("Visita Técnica")
    val dadosEspecificos = coletarVisitaTecnica(dadosBasicos.clientePresente)
    val assinaturas = obsAssinaturas(dadosBasicos.clientePresente)

    exibirVisitaTecnica(dadosBasicos, dadosEspecificos, assinaturas)
}

fun manutencaoCorretiva() {
    println("\n" + "=".repeat(50))
    println("\t>>> MANUTENÇÃO CORRETIVA <<<")
    println("=".repeat(50))

    val dadosBasicos = coletarDadosBasicos("Manutenção Corretiva")
    val dadosEspecificos = coletarManutencaoCorretiva(dadosBasicos.clientePresente)
    val assinaturas = obsAssinaturas(dadosBasicos.clientePresente)

    exibirManutencaoCorretiva(dadosBasicos, dadosEspecificos, assinaturas)
}

fun instalacao() {
    println("\n" + "=".repeat(50))
    println("\t>>> INSTALAÇÃO <<<")
    println("=".repeat(50))

    val dadosBasicos = coletarDadosBasicos("Instalação")
    val dadosEspecificos = coletarInstalacao(dadosBasicos.clientePresente)
    val assinaturas = obsAssinaturas(dadosBasicos.clientePresente)

    exibirInstalacao(dadosBasicos, dadosEspecificos, assinaturas)
}

fun instalacaoDeRepetidores() {
    println("\n" + "=".repeat(50))
    println("\t>>> INSTALAÇÃO DE REPETIDORES <<<")
    println("=".repeat(50))

    val dadosBasicos = coletarDadosBasicos("Instalação de Repetidores")
    val dadosEspecificos = coletarInstalacaoRepetidores(dadosBasicos.clientePresente)
    val assinaturas = obsAssinaturas(dadosBasicos.clientePresente)

    exibirInstalacaoRepetidores(dadosBasicos, dadosEspecificos, assinaturas)
}

fun reativacao() {
    println("\n" + "=".repeat(50))
    println("\t>>> REATIVAÇÃO <<<")
    println("=".repeat(50))

    val dadosBasicos = coletarDadosBasicos("Reativação")
    val dadosEspecificos = coletarReativacao(dadosBasicos.clientePresente)
    val assinaturas = obsAssinaturas(dadosBasicos.clientePresente)

    exibirReativacao(dadosBasicos, dadosEspecificos, assinaturas)
}

fun trocaDeEquipamento() {
    println("\n" + "=".repeat(50))
    println("\t>>> TROCA DE EQUIPAMENTO <<<")
    println("=".repeat(50))

    val dadosBasicos = coletarDadosBasicos("Troca de Equipamento")
    val dadosEspecificos = coletarTrocaEquipamento(dadosBasicos.clientePresente)
    val assinaturas = obsAssinaturas(dadosBasicos.clientePresente)

    exibirTrocaEquipamento(dadosBasicos, dadosEspecificos, assinaturas)
}

fun retiradaDeEquipamento() {
    println("\n" + "=".repeat(50))
    println("\t>>> RETIRADA DE EQUIPAMENTO <<<")
    println("=".repeat(50))

    val dadosBasicos = coletarDadosBasicos("Retirada de Equipamento")
    val dadosEspecificos = coletarRetiradaEquipamento(dadosBasicos.clientePresente)
    val assinaturas = obsAssinaturas(dadosBasicos.clientePresente)

    exibirRetiradaEquipamento(dadosBasicos, dadosEspecificos, assinaturas)
}

fun avaliacaoDeRealocacao() {
    println("\n" + "=".repeat(50))
    println("\t>>> AVALIAÇÃO DE REALOCAÇÃO <<<")
    println("=".repeat(50))

    val dadosBasicos = coletarDadosBasicos("Avaliação de Realocação")
    val dadosEspecificos = coletarRealocacao(dadosBasicos.clientePresente, "Avaliação")
    val assinaturas = obsAssinaturas(dadosBasicos.clientePresente)

    exibirAvaliacaoRealocacao(dadosBasicos, dadosEspecificos, assinaturas)
}

fun realocacaoDeEquipamento() {
    println("\n" + "=".repeat(50))
    println("\t>>> REALOCAÇÃO DE EQUIPAMENTO <<<")
    println("=".repeat(50))

    val dadosBasicos = coletarDadosBasicos("Realocação de Equipamento")
    val dadosEspecificos = coletarRealocacao(dadosBasicos.clientePresente, "Realocação")
    val assinaturas = obsAssinaturas(dadosBasicos.clientePresente)

    exibirRealocacaoEquipamento(dadosBasicos, dadosEspecificos, assinaturas)
}

fun exibirCabecalho(tipo: String) {
    println("\n" + "=".repeat(50))
    println("\t${tipo.uppercase()}")
    println("=".repeat(50))
}

fun exibirDadosBasicos(dados: DadosBasicos) {
    if (dados.tipo.isNotBlank()) println("Tipo: ${dados.tipo}")
    println("Data do Atendimento: ${dados.dataAtendimento}")
    println("Chegada: ${dados.chegada}")
    println("Saída: ${dados.saida}")
    println("Cliente estava no local: ${dados.clientePresente}")
}

fun exibirAssinaturas(assinaturas: ObsAssinaturas) {
    println("\nOBS: ${if (assinaturas.observacoes.isNotBlank()) assinaturas.observacoes else "---"}")
    println("\nAssinatura do Técnico: ${assinaturas.assinaturaTecnico}")

    val clienteCompleto = if (assinaturas.cpfCliente.isNotBlank()) {
        "${assinaturas.assinaturaCliente}   CPF: ${assinaturas.cpfCliente}"
    } else {
        assinaturas.assinaturaCliente
    }
    println("Assinatura do Cliente/Responsável: ${if (clienteCompleto.isNotBlank()) clienteCompleto else "---"}")
}

fun exibirVisitaTecnica(dados: DadosBasicos, especificos: VisitaTecnicaDados, assinaturas: ObsAssinaturas) {
    exibirCabecalho("VISITA TÉCNICA")
    exibirDadosBasicos(dados)

    if (dados.clientePresente == "SIM") {
        println("\nEquipamento Instalado No(a): ${especificos.instaladoEm}")

        println("\n🔍 ITENS AVALIADOS:")
        if (especificos.teste5Ghz.isNotBlank()) println("Teste de passagem de banda 5GHZ: ${especificos.teste5Ghz}")
        if (especificos.parteFisica.isNotBlank()) println("Avaliar a parte física do equipamento: ${especificos.parteFisica}")
        if (especificos.interferenciaCanal.isNotBlank()) println("Interferência no canal wireless: ${especificos.interferenciaCanal}")
        if (especificos.qtdAparelhos.isNotBlank()) println("Quantidade de aparelhos conectados: ${especificos.qtdAparelhos}")
        if (especificos.compatibilidadeAparelho.isNotBlank()) println("Compatibilidade do aparelho do cliente: ${especificos.compatibilidadeAparelho}")
        if (especificos.interferenciaExterna.isNotBlank()) println("Interferência externa: ${especificos.interferenciaExterna}")
        if (especificos.testeApps.isNotBlank()) println("Teste de aplicativos: ${especificos.testeApps}")

        println("\n📊 CONCLUSÃO DA AVALIAÇÃO:")
        if (especificos.problemasOutrosEquip.isNotBlank()) println("Problemas com outros equipamentos do cliente: ${especificos.problemasOutrosEquip}")
        if (especificos.faltaCompatibilidade.isNotBlank()) println("Falta de compatibilidade: ${especificos.faltaCompatibilidade}")
        if (especificos.problemaAmazon.isNotBlank()) println("Identificado problema no serviço Amazon +: ${especificos.problemaAmazon}")
        if (especificos.problemaDiferente.isNotBlank()) println("Problema diferente do abordado remotamente: ${especificos.problemaDiferente}")
        if (especificos.interferencia.isNotBlank()) println("Interferência: ${especificos.interferencia}")
        if (especificos.necessidadeTroca.isNotBlank()) println("Necessidade de troca de equipamento: ${especificos.necessidadeTroca}")
        if (especificos.necessidadeRealocacao.isNotBlank()) println("Necessidade de realocação: ${especificos.necessidadeRealocacao}")
        if (especificos.necessidadeRepetidores.isNotBlank()) println("Necessidade de Repetidores: ${especificos.necessidadeRepetidores}")

        println("\n🔧 CORREÇÃO DO PROBLEMA:")
        if (especificos.orientacaoEquipNaoGerenciados.isNotBlank()) println("Orientação sobre equipamentos não gerenciados: ${especificos.orientacaoEquipNaoGerenciados}")
        if (especificos.orientacaoCompatibilidade.isNotBlank()) println("Orientação sobre compatibilidade: ${especificos.orientacaoCompatibilidade}")
        if (especificos.mudancaCanal.isNotBlank()) println("Mudança de canal: ${especificos.mudancaCanal}")
        if (especificos.oferecidoRepetidores.isNotBlank()) println("Oferecido Repetidores: ${especificos.oferecidoRepetidores}")
    } else {
        println("\n⚠️ Cliente não estava presente - sem dados da visita")
    }

    exibirAssinaturas(assinaturas)
    println("\n" + "=".repeat(50))
    println("✅ OS gerada com sucesso!")
    println("=".repeat(50))
}

fun exibirManutencaoCorretiva(dados: DadosBasicos, especificos: ManutencaoCorretivaDados, assinaturas: ObsAssinaturas) {
    exibirCabecalho("MANUTENÇÃO CORRETIVA")
    exibirDadosBasicos(dados)

    println("\nDescrição: ${especificos.descricao}")

    if (dados.clientePresente == "SIM") {
        println("\n🔍 AVALIAÇÃO DO PROBLEMA:")
        if (especificos.equipamentoDanificado.isNotBlank()) println("Equipamento Danificado: ${especificos.equipamentoDanificado}")
        if (especificos.conectorDanificado.isNotBlank()) println("Conector Danificado: ${especificos.conectorDanificado}")
        if (especificos.equipamentoQueimado.isNotBlank()) println("Equipamento Queimado: ${especificos.equipamentoQueimado}")
        if (especificos.fonteQueimada.isNotBlank()) println("Fonte Queimada: ${especificos.fonteQueimada}")
        if (especificos.rompimentoFibra.isNotBlank()) println("Rompimento de Fibra: ${especificos.rompimentoFibra}")
        if (especificos.atenuacao.isNotBlank()) println("Atenuação: ${especificos.atenuacao}")
        if (especificos.nivelSinalNormal.isNotBlank()) println("Nível de Sinal Normal: ${especificos.nivelSinalNormal}")
        if (especificos.necessidadeTaxa.isNotBlank()) println("Necessidade de Pagamento de Taxa: ${especificos.necessidadeTaxa}")

        println("\n🔧 CORREÇÃO DO PROBLEMA:")
        if (especificos.trocaEquipamento.isNotBlank()) println("Houve troca de equipamento: ${especificos.trocaEquipamento}")
        if (especificos.trocaConector.isNotBlank()) println("Houve troca de conector: ${especificos.trocaConector}")
        if (especificos.trocaFonte.isNotBlank()) println("Houve troca de fonte: ${especificos.trocaFonte}")
        if (especificos.fusao.isNotBlank()) println("Fusão: ${especificos.fusao}")
        if (especificos.lancamentoFibra.isNotBlank()) println("Lançamento de Fibra: ${especificos.lancamentoFibra}")
        if (especificos.nivelSinalNormalizado.isNotBlank()) println("Nível de Sinal Normalizado: ${especificos.nivelSinalNormalizado}")
        if (especificos.usoCaixaEmenda.isNotBlank()) println("Uso de Caixa e Emenda: ${especificos.usoCaixaEmenda}")
    }

    exibirAssinaturas(assinaturas)
    println("\n" + "=".repeat(50))
    println("✅ OS gerada com sucesso!")
    println("=".repeat(50))
}

fun exibirInstalacao(dados: DadosBasicos, especificos: InstalacaoDados, assinaturas: ObsAssinaturas) {
    exibirCabecalho("INSTALAÇÃO")
    exibirDadosBasicos(dados)

    if (dados.clientePresente == "SIM") {
        if (especificos.sugeridoInstalacao.isNotBlank()) println("\nFoi sugerido instalação no(a): ${especificos.sugeridoInstalacao}")
        if (especificos.instaladoEm.isNotBlank()) println("Equipamento instalado no(a): ${especificos.instaladoEm}")
        if (especificos.porta.isNotBlank()) println("Porta: ${especificos.porta}")
        if (especificos.sinal.isNotBlank()) println("Sinal: ${especificos.sinal}")
    } else {
        println("\n⚠️ Cliente não estava presente")
    }

    exibirAssinaturas(assinaturas)
    println("\n" + "=".repeat(50))
    println("✅ OS gerada com sucesso!")
    println("=".repeat(50))
}

fun exibirInstalacaoRepetidores(dados: DadosBasicos, especificos: InstalacaoRepetidoresDados, assinaturas: ObsAssinaturas) {
    exibirCabecalho("INSTALAÇÃO DE REPETIDORES")
    exibirDadosBasicos(dados)

    if (dados.clientePresente == "SIM") {
        if (especificos.porta.isNotBlank()) println("\nPorta: ${especificos.porta}")
        if (especificos.metragemUtilizada.isNotBlank()) println("Metragem Utilizada: ${especificos.metragemUtilizada}")
        if (especificos.modelo.isNotBlank()) println("Modelo: ${especificos.modelo}")
        if (especificos.sugeridoInstalacao.isNotBlank()) println("\nFoi sugerido instalação no(a): ${especificos.sugeridoInstalacao}")
        if (especificos.instaladoEm.isNotBlank()) println("Equipamento instalado no(a): ${especificos.instaladoEm}")
    } else {
        println("\n⚠️ Cliente não estava presente")
    }

    exibirAssinaturas(assinaturas)
    println("\n" + "=".repeat(50))
    println("✅ OS gerada com sucesso!")
    println("=".repeat(50))
}

fun exibirTrocaEquipamento(dados: DadosBasicos, especificos: TrocaEquipamentoDados, assinaturas: ObsAssinaturas) {
    exibirCabecalho("TROCA DE EQUIPAMENTO")
    exibirDadosBasicos(dados)

    if (dados.clientePresente == "SIM") {
        println("\nEquipamento retirado: ${especificos.equipamentoRetirado}")
        println("Equipamento instalado: ${especificos.equipamentoInstalado}")
    } else {
        println("\n⚠️ Cliente não estava presente")
    }

    exibirAssinaturas(assinaturas)
    println("\n" + "=".repeat(50))
    println("✅ OS gerada com sucesso!")
    println("=".repeat(50))
}

fun exibirRetiradaEquipamento(dados: DadosBasicos, especificos: RetiradaEquipamentoDados, assinaturas: ObsAssinaturas) {
    exibirCabecalho("RETIRADA DE EQUIPAMENTO")
    exibirDadosBasicos(dados)

    if (dados.clientePresente == "SIM") {
        println("\nCliente autorizou retirada: ${especificos.autorizacaoRetirada}")
        println("Equipamento de TV: ${especificos.equipamentoTV}")
        println("Equipamento de internet: ${especificos.equipamentoInternet}")
        println("Drop: ${especificos.drop}")
        println("Necessidade de negociação: ${especificos.necessidadeNegociacao}")
    } else {
        println("\n⚠️ Cliente não estava presente")
    }

    exibirAssinaturas(assinaturas)
    println("\n" + "=".repeat(50))
    println("✅ OS gerada com sucesso!")
    println("=".repeat(50))
}

fun exibirReativacao(dados: DadosBasicos, especificos: ReativacaoDados, assinaturas: ObsAssinaturas) {
    exibirCabecalho("REATIVAÇÃO")
    exibirDadosBasicos(dados)

    if (dados.clientePresente == "SIM") {
        println("\nEquipamento instalado no(a): ${especificos.instaladoEm}")
        println("Modelo do Equipamento: ${especificos.modelo}")
        println("Sinal: ${especificos.sinal}")

        println("\n📋 ITENS AVALIADOS:")
        println("Equipamento em bom estado: ${especificos.equipamentoBom}")
        println("Tem Repetidor de Sinal: ${especificos.temRepetidor}")
        println("Tem Equipamentos de TV: ${especificos.temEquipamentosTV}")
    } else {
        println("\n⚠️ Cliente não estava presente - sem dados de equipamento")
    }

    exibirAssinaturas(assinaturas)
    println("\n" + "=".repeat(50))
    println("✅ OS gerada com sucesso!")
    println("=".repeat(50))
}

fun exibirAvaliacaoRealocacao(dados: DadosBasicos, especificos: RealocacaoDados, assinaturas: ObsAssinaturas) {
    exibirCabecalho("AVALIAÇÃO DE REALOCAÇÃO")
    exibirDadosBasicos(dados)

    if (dados.clientePresente == "SIM") {
        println("\nEquipamento está instalado no(a): ${especificos.instaladoEm}")
        println("Sugerido instalar no(a): ${especificos.sugeridoInstalacao}")
        println("Cliente optou por instalar no(a): ${especificos.optadoInstalacao}")
        println("\nNecessidade de taxa: ${especificos.necessidadeTaxa}")
        if (especificos.sinal.isNotBlank()) println("Sinal: ${especificos.sinal}")
    } else {
        println("\n⚠️ Cliente não estava presente")
    }

    exibirAssinaturas(assinaturas)
    println("\n" + "=".repeat(50))
    println("✅ OS gerada com sucesso!")
    println("=".repeat(50))
}

fun exibirRealocacaoEquipamento(dados: DadosBasicos, especificos: RealocacaoDados, assinaturas: ObsAssinaturas) {
    exibirCabecalho("REALOCAÇÃO DE EQUIPAMENTO")
    exibirDadosBasicos(dados)

    println("\nDescrição: ${especificos.descricao}")

    if (dados.clientePresente == "SIM") {
        println("\nEquipamento está instalado no(a): ${especificos.instaladoEm}")
        println("Sugerido instalar no(a): ${especificos.sugeridoInstalacao}")
        println("Cliente optou por instalar no(a): ${especificos.optadoInstalacao}")
        println("\nNecessidade de taxa: ${especificos.necessidadeTaxa}")
    } else {
        println("\n⚠️ Cliente não estava presente")
    }

    exibirAssinaturas(assinaturas)
    println("\n" + "=".repeat(50))
    println("✅ OS gerada com sucesso!")
    println("=".repeat(50))
}

fun main() {
    println("\t\n >>> Olá, Bem vindo ao gerador de OS <<<\n")
    val nomeDoCliente = validaNome("> Digite o nome do cliente: ")
    menuDeOS()
}