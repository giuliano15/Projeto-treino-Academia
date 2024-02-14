// EntityRepository.kt
package com.example.academia.repository

import com.example.academia.MyApp
import com.example.academia.R
import com.example.academia.model.Entity

class EntityRepository() {
    private val listaEntities = mutableListOf<Entity>()

    // Lista estática de entidades (mock de dados)
    private val entities: List<Entity> = listOf(
        Entity(R.drawable.aparelho_para_gluteo, getString(R.string.aparelho_gluteo), getString(R.string.aparelho_gluteo_description)),
        Entity(R.drawable.abdominal_articulado, getString(R.string.abdominal_articulado), getString(R.string.abdominal_articulado_description)),
        Entity(R.drawable.abdominal_paralelo, getString(R.string.abdominal_paralelo), getString(R.string.abdominal_paralelo_description)),
        Entity(R.drawable.apolete, getString(R.string.apolete), getString(R.string.apolete_description)),
        Entity(R.drawable.barras_fixas_paralelas, getString(R.string.barras_fixas_paralelas), getString(R.string.barras_fixas_paralelas_description)),
        Entity(R.drawable.bicicleta_ergometrica, getString(R.string.bicicleta_ergometrica), getString(R.string.bicicleta_ergometrica_description)),
        Entity(R.drawable.barra_guiada, getString(R.string.barra_guiada), getString(R.string.barra_guiada_description)),
        Entity(R.drawable.cadeira_abdutora_adutora, getString(R.string.cadeira_abdutora_adutora), getString(R.string.cadeira_abdutora_adutora_description)),
        Entity(R.drawable.cadeira_extensora, getString(R.string.cadeira_extensora), getString(R.string.cadeira_extensora_description)),
        Entity(R.drawable.cadeira_flexora, getString(R.string.cadeira_flexora), getString(R.string.cadeira_flexora_description)),
        Entity(R.drawable.cross_over, getString(R.string.cross_over), getString(R.string.cross_over_description)),
        Entity(R.drawable.esteira, getString(R.string.esteira), getString(R.string.esteira_description)),
        Entity(R.drawable.eliptico, getString(R.string.eliptico), getString(R.string.eliptico_description)),
        Entity(R.drawable.aparelho_de_elevacao_pelvica, getString(R.string.elevacacao_pelvica), getString(R.string.elevacacao_pelvica_description)),
        Entity(R.drawable.aparelho_graviton, getString(R.string.graviton), getString(R.string.graviton_description)),
        Entity(R.drawable.hack_para_agachamento, getString(R.string.hack_para_agachamento), getString(R.string.hack_para_agachamento_description)),
        Entity(R.drawable.leg_press, getString(R.string.leg_press), getString(R.string.leg_press_description)),
        Entity(R.drawable.prancha_abdominal, getString(R.string.prancha_abdominal), getString(R.string.prancha_abdominal_description)),
        Entity(R.drawable.peck_deck, getString(R.string.peck_deck), getString(R.string.peck_deck_description)),
        Entity(R.drawable.panturrilha_sentada, getString(R.string.panturrilha_sentada), getString(R.string.panturrilha_sentada_description)),
        Entity(R.drawable.polia, getString(R.string.polia), getString(R.string.polia_description)),
        Entity(R.drawable.remo_indoor, getString(R.string.remo_indoor), getString(R.string.remo_indoor_description)),
        Entity(R.drawable.simulador_de_escada, getString(R.string.simulador_de_escada), getString(R.string.simulador_de_escada_description)),
        Entity(R.drawable.supino, getString(R.string.supino), getString(R.string.supino_description))
        // Adicione mais entidades conforme necessário
    )

    // Método para obter a lista de entidades
    fun getEntities(): List<Entity> {
        // Retorna a lista dinâmica se não estiver vazia, caso contrário, retorna a lista estática
        return if (listaEntities.isNotEmpty()) {
            listaEntities
        } else {
            listaEntities.addAll(entities)
            listaEntities
        }
    }

    private fun getString(resourceId: Int): String {
        return MyApp.getAppContext().getString(resourceId)
    }
}
