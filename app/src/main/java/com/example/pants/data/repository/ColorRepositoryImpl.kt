package com.example.pants.data.repository

import com.example.pants.data.network.api.ColorApiService
import com.example.pants.domain.model.ColorModel
import com.example.pants.utils.generateRandomColor
import com.example.pants.data.network.mapper.toColorModel
import java.util.Locale

class ColorRepositoryImpl(
    private val apiService: ColorApiService,
) : ColorRepository {

    override suspend fun getRandomColors(count: Int): Result<Set<ColorModel>> = runCatching {
        val colorList = mutableSetOf<ColorModel>()
        while (colorList.size < count) {
            val color = apiService.getColor(generateRandomColor()).toColorModel()
            if (color.name.lowercase(Locale.getDefault()) !in COMMON_USE_NAMES) colorList.add(color)
        }
        colorList
    }

    private companion object {
        val COMMON_USE_NAMES = setOf(
            "beige",
            "black",
            "blue violet",
            "blue",
            "brown",
            "crimson",
            "cyan",
            "gold",
            "gray",
            "green",
            "indigo",
            "khaki",
            "lavender",
            "lime green",
            "magenta",
            "maroon",
            "navy blue",
            "olive",
            "orange",
            "pink",
            "plum",
            "purple",
            "red",
            "salmon",
            "silver",
            "sky blue",
            "teal",
            "violet",
            "white",
            "yellow",
        )
    }
}
