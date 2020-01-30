@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import kotlin.math.abs
import kotlin.math.sqrt
import lesson1.task1.sqr
import kotlin.math.pow
import lesson1.task1.pow
import lesson4.task1.intInList
import lesson1.task1.sqr
import kotlin.math.PI

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
    when {
        n == m -> 1
        n < 10 -> 0
        else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
    }

/**
 * Простая
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
    var sum = 0
    var num = n
    if (num == 0) return 1
    while (num != 0) {
        sum++
        num /= 10
    }
    return sum
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    var i = 2
    var num2: Int
    var num1 = 1
    var num = 1
    if (n == 1 || n == 2) return 1
    while (i != n) {
        num2 = num1
        num1 = num
        num = (num2 + num1)
        i++
    }
    return num
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    var i = m
    while (m % i != 0 || n % i != 0) {
        i--
    }
    println(abs(m * n) / i)
    return abs(m * n) / i
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    for (i in 2..sqrt(n.toDouble()).toInt())
        if (n % i == 0) return i
    return n
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int = n / minDivisor(n)

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    var i = m
    while (m % i != 0 || n % i != 0) {
        i--
    }
    return i == 1
}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    var i = 0
    while (sqr(i) < m) {
        i++
    }
    return when {
        i <= sqrt(Int.MAX_VALUE.toDouble()) && sqr(i) <= n -> true
        else -> false
    }
}

/**
 * Средняя
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    var i = 0
    var y = x
    while (y != 1) {
        if (y % 2 == 0) {
            y /= 2
        } else {
            y = y * 3 + 1
        }
        i++
    }
    return i
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю.
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.sin и другие стандартные реализации функции синуса в этой задаче запрещается.
 */

fun sin(x: Double, eps: Double): Double {
    var result = 0.0
    var value = 1
    var fac = 1.0
    var facArg = 1
    var num = x % (2 * PI)
    var firstNum = num
    val sqrNum = sqr(num)
    while (abs(firstNum) >= eps) {
        result += firstNum * value
        value *= -1
        num *= sqrNum
        facArg += 2
        fac *= facArg * (facArg - 1)
        firstNum = num / fac
    }
    return result
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.cos и другие стандартные реализации функции косинуса в этой задаче запрещается.
 */
fun cos(x: Double, eps: Double): Double {
    var result = 0.0
    var value = 1
    var fac = 1.0
    var facArg = 0
    val sqrNum = sqr(x % (2 * PI))
    var num = 1.0
    var firstNum = num
    while (abs(firstNum) >= eps) {
        result += firstNum * value
        value *= -1
        num *= sqrNum
        facArg += 2
        fac *= facArg * (facArg - 1)
        firstNum = num / fac
    }
    return result
}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
// длина числа
fun intLength(n: Int): Int = n.toString().length

fun revert(n: Int): Int {
    var num = n
    var number = 0
    val count = n.toString().length - 1

    for (i in count downTo 0) {
        number += (num % 10) * 10.pow(i)
        num /= 10
    }
    return number
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean = n == revert(n)

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    val count = intLength(n) - 1
    val num1 = intInList(n)
    for (i in 0 until count) {
        if (num1[i] != num1[i + 1]) return true
    }
    return false
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int {
    var x = 1
    var num = 0
    var count = 0

    for (i in 1..n) {
        count += intLength(sqr(i))
        if (count >= n) {
            num = count - n
            x = sqr(i)
            break
        }
    }
    count = intLength(x)
    for (i in 0 until count) {
        when (num) {
            i -> x = x / 10.pow(i) % 10
        }
    }
    return x
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int {
    var count = 2
    var number = 1
    var num2: Int
    var num1 = 1
    var num = 1
    if (n == 1 || n == 2) return 1
    while (count < n) {
        num2 = num1
        num1 = num
        num = (num2 + num1)
        count += intLength(num)
        if (count >= n) {
            number = count - n
        }
    }
    count = intLength(num)
    for (i in 0 until count) {
        when (number) {
            i -> num = num / 10.pow(i) % 10
        }
    }
    return num
}
