package com.example.pants.data.repository

import com.example.pants.data.network.api.ColorApiService
import com.example.pants.domain.model.ColorModel
import com.example.pants.domain.model.ColorResponse
import com.example.pants.domain.model.Hsv
import com.example.pants.domain.model.Name
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ColorRepositoryImplTest {

    private val apiService = mockk<ColorApiService>()
    private val repositoryImpl = ColorRepositoryImpl(apiService)

    @Before
    fun setup() {
        coEvery { apiService.getColor(any()) } answers {
            listOf(
                ColorResponse(Name("beige"), Hsv(Hsv.Fraction(0.55F, 0.35F, 0.45F))),
                ColorResponse(Name("cyan"), Hsv(Hsv.Fraction(0.55F, 0.35F, 0.45F))),
                ColorResponse(Name("name1"), Hsv(Hsv.Fraction(0.55F, 0.35F, 0.45F))),
                ColorResponse(Name("name2"), Hsv(Hsv.Fraction(0.55F, 0.35F, 0.45F))),
                ColorResponse(Name("name3"), Hsv(Hsv.Fraction(0.55F, 0.35F, 0.45F))),
                ColorResponse(Name("name4"), Hsv(Hsv.Fraction(0.55F, 0.35F, 0.45F))),
                ColorResponse(Name("name5"), Hsv(Hsv.Fraction(0.55F, 0.35F, 0.45F))),
                ColorResponse(Name("name6"), Hsv(Hsv.Fraction(0.55F, 0.35F, 0.45F))),
                ColorResponse(Name("name7"), Hsv(Hsv.Fraction(0.55F, 0.35F, 0.45F))),
                ColorResponse(Name("name8"), Hsv(Hsv.Fraction(0.55F, 0.35F, 0.45F)))
            ).random()
        }
    }

    @Test
    fun colorsNumberReturnTest() {
        runBlocking {
            repeat(10) {
                val count = (1..8).random()
                val result = repositoryImpl.getRandomColors(count).getOrThrow()
                assertEquals(count, result.size)
            }
        }
    }

    @Test
    fun colorsReturnsTypeIsColorModelTest() {
        runBlocking {
            repeat(10) {
                val count = (1..8).random()
                val result = repositoryImpl.getRandomColors(count).getOrThrow()
                for (colorModel in result) {
                    assert(colorModel is ColorModel)
                }
            }
        }
    }

    @Test
    fun colorsReturnsUniqueTest() {
        runBlocking {
            repeat(10) {
                val count = (1..8).random()
                val result = repositoryImpl.getRandomColors(count).getOrThrow()
                assert(result is Set)
            }
        }
    }

    @Test
    fun colorsReturnsCorrectTest() {
        runBlocking {
            repeat(10) {
                val count = (1..8).random()
                val result = repositoryImpl.getRandomColors(count).getOrThrow()
                for (colorModel in result) {
                    assert(colorModel.realHue>0.4 && colorModel.saturation>0.3)
                }
            }
        }
    }

    @Test
    fun colorsNotContainCommonColorsTest(){
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
        runBlocking {
            repeat(10) {
                val count = (1..8).random()
                val result = repositoryImpl.getRandomColors(count).getOrThrow()
                for (colorModel in result) {
                    assert(!COMMON_USE_NAMES.contains(colorModel.name))
                }
            }
        }
    }

}