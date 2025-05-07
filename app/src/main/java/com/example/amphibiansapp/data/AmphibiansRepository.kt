package com.example.amphibiansapp.data

import com.example.amphibiansapp.model.Amphibian
import com.example.amphibiansapp.network.AmphibianApi

class AmphibianRepository {
    suspend fun getAmphibians(): List<Amphibian> = AmphibianApi.service.getAmphibians()
}