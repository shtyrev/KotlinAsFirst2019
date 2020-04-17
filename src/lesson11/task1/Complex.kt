@file:Suppress("UNUSED_PARAMETER")

package lesson11.task1

import lesson1.task1.sqr
import kotlin.math.pow

/**
 * Класс "комплексое число".
 *
 * Общая сложность задания -- лёгкая.
 * Объект класса -- комплексное число вида x+yi.
 * Про принципы работы с комплексными числами см. статью Википедии "Комплексное число".
 *
 * Аргументы конструктора -- вещественная и мнимая часть числа.
 */
class Complex(val re: Double, val im: Double) {

    companion object {
        private fun r(s: String): Double = s.filter { it != 'i' }.split(Regex("""\b[-|+]"""))[0].toDouble()
        private fun i(s: String): Double =
            s.filter { it != 'i' }.split(Regex("""\b[-|+]"""))[1].toDouble() * if (s.contains(Regex("""\b[-]"""))) -1 else 1
    }

    /**
     * Конструктор из вещественного числа
     */
    constructor(x: Double) : this(x, 0.0)

    /**
     * Конструктор из строки вида x+yi
     */
    constructor(s: String) : this(r(s), i(s))

    /**
     * Сложение.
     */
    operator fun plus(other: Complex): Complex = Complex(
        re + other.re,
        im + other.im
    )

    /**
     * Смена знака (у обеих частей числа)
     */
    operator fun unaryMinus(): Complex = Complex(
        -re,
        -im
    )

    /**
     * Вычитание
     */
    operator fun minus(other: Complex): Complex = Complex(
        re - other.re,
        im - other.im
    )

    /**
     * Умножение
     */
    operator fun times(other: Complex): Complex = Complex(
        re * other.re - im * other.im,
        im * other.re + re * other.im
    )

    /**
     * Деление
     */
    operator fun div(other: Complex): Complex = Complex(
        (re * other.re - im * other.im) / (sqr(other.re) + sqr(other.im)),
        (im * other.re + re * other.im) / (sqr(other.re) + sqr(other.im))
    )

    /**
     * Сравнение на равенство
     */
    override fun equals(other: Any?): Boolean = other is Complex && (re == other.re && im == other.im)

    /**
     * Преобразование в строку
     */
    override fun toString(): String = when {
        im == 0.0 -> "$re"
        im > 0.0 -> "$re+${im}i"
        else -> "$re${im}i"
    }
}