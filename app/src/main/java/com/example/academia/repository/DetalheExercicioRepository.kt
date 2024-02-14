package com.example.ademia.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.academia.MyApp
import com.example.academia.R
import com.example.academia.model.DetalheExercicio

class DetalheExercicioRepository {
    private val listaDetalhe = mutableListOf<DetalheExercicio>()

    // Lista estática de treinos (mock de dados)
    @RequiresApi(Build.VERSION_CODES.O)
    private val detalheExercicio: List<DetalheExercicio> = listOf(
        DetalheExercicio("Aparelho para Gluteo", R.drawable.aparelho_gluteo,getString(R.string.aparelho_gluteo_description) ),
        DetalheExercicio("Abdominal articulado", R.drawable.abdominal_articulado_detalhe,"Ele permite que seus braços girem naturalmente, reduzindo qualquer dor no pulso"),
        DetalheExercicio("Abdominal paralelo", R.drawable.abdominal_paralelos,"Esse treino visa especialmente a ativação do músculo infraespinhal e redondo menor."),
        DetalheExercicio("Apolete", R.drawable.teste_apolete, "Ideal para o fortalecimento dos músculos dos ombros além de melhorar a postura do atleta"),
        DetalheExercicio("Barras fixas paralelas", R.drawable.barra_fixa_paralela_detalhe,"Melhora a força e a resistência dos membros superiores" ),
        DetalheExercicio("Bicicleta ergométrica", R.drawable.bbicicleta,"Fortifica os musculos da perna" ),
        DetalheExercicio("Barra guiada", R.drawable.barra_guiada_detalhe,getString(R.string.barra_guiada_description)),


                DetalheExercicio("Cadeira adultora",R.drawable.cadeira_abdutora_adutora, getString(R.string.cadeira_abdutora_adutora_description)),
        DetalheExercicio("Cadeira extensora",R.drawable.cadeira_extensora, getString(R.string.cadeira_extensora_description)),
            DetalheExercicio("Cadeira flexora",R.drawable.cadeira_flexora, getString(R.string.cadeira_flexora_description)),
                DetalheExercicio("Cross over",R.drawable.cross_over, getString(R.string.cross_over_description)),
                    DetalheExercicio("Esteira",R.drawable.esteira, getString(R.string.esteira_description)),
                        DetalheExercicio("Elipitico",R.drawable.eliptico,  getString(R.string.eliptico_description)),
                            DetalheExercicio("Elevação pélvica",R.drawable.aparelho_de_elevacao_pelvica, getString(R.string.elevacacao_pelvica_description)),
                                DetalheExercicio("Graviton",R.drawable.aparelho_graviton, getString(R.string.graviton_description)),
                                DetalheExercicio("Hack de agachamento",R.drawable.hack_para_agachamento, getString(R.string.hack_para_agachamento_description)),
                                DetalheExercicio("Leg press",R.drawable.leg_press, getString(R.string.leg_press_description)),
                                DetalheExercicio("Prancha abdominal",R.drawable.prancha_abdominal, getString(R.string.prancha_abdominal_description)),
                                DetalheExercicio("Peck deck",R.drawable.peck_deck, getString(R.string.peck_deck_description)),
                                DetalheExercicio("Panturrilha sentada",R.drawable.panturrilha_sentada, getString(R.string.panturrilha_sentada_description)),
                                DetalheExercicio("Polia",R.drawable.polia, getString(R.string.polia_description)),
                                DetalheExercicio("Remo indoor",R.drawable.remo_indoor, getString(R.string.remo_indoor_description)),
                                DetalheExercicio("Simuador de escada",R.drawable.simulador_de_escada, getString(R.string.simulador_de_escada_description)),
                                DetalheExercicio("Supino",R.drawable.supino, getString(R.string.supino_description))



//    DetalheExercicio("Supino sentado na maquina", R.drawable.supino_sentado_na_maquina,"Indicado para a realização de exercícios que trabalham a musculatura do bíceps" ),
//    DetalheExercicio("Extensão de triceps deitado", R.drawable.extensao_triceps,"Ele permite que seus braços girem naturalmente, reduzindo qualquer dor no pulso"),
//    DetalheExercicio("Rotação extera da polia", R.drawable.rotacao_de_polia,"Esse treino visa especialmente a ativação do músculo infraespinhal e redondo menor."),
//    DetalheExercicio("Elevação lateral em pé", R.drawable.elevacao_lateral_em_pe, "Ideal para o fortalecimento dos músculos dos ombros além de melhorar a postura do atleta"),
//    DetalheExercicio("Esteira", R.drawable.esteira,"Melhora a força e a resistência dos membros superiores" ),
//    DetalheExercicio("Flexão de pernas", R.drawable.flexao_de_pernas,"Fortifica os musculos da perna" ),
//    DetalheExercicio("Crucifixo em banco reto", R.drawable.crucifixo_de_banco_reto,"Trabalha as cabeças esternais dos seus principais músculos peitorais" )

                            )

    // Método para obter a lista de treinos
    fun getDetalheExercicio(): List<DetalheExercicio> {
        // Retorna a lista dinâmica se não estiver vazia, caso contrário, retorna a lista estática
        return if (listaDetalhe.isNotEmpty()) {
            listaDetalhe
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                detalheExercicio
            } else {
                TODO("VERSION.SDK_INT < O")
            }
        }
    }
    private fun getString(resourceId: Int): String {
        return MyApp.getAppContext().getString(resourceId)
    }
}