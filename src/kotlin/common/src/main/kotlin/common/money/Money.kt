package common.money

import java.math.BigDecimal

data class NegativeMoneyAmountException(private val amount: BigDecimal): Exception("Money cannot be negative: $amount")

@JvmInline
value class Money(private val amount: BigDecimal): Comparable<Money> {
    init {
        require(amount >= BigDecimal.ZERO) { NegativeMoneyAmountException(amount) }
    }

    constructor(amount: Int) : this(BigDecimal(amount))

    operator fun minus(v: Money): Money = Money(amount - v.amount)
    override fun compareTo(other: Money): Int = amount.compareTo(other.amount)
    operator fun times(other: Money): Money = Money(amount.times(other.amount))

    companion object {
        val ZERO: Money = Money(BigDecimal.ZERO)

        fun from(amount: Int): Result<Money>{
            if (amount < 0){
                return Result.failure(NegativeMoneyAmountException(BigDecimal(amount)))
            }

            return Result.success(Money(amount))
        }

        fun from(amount: BigDecimal): Result<Money>{
            if (amount < BigDecimal.ZERO){
                return Result.failure(NegativeMoneyAmountException(amount))
            }

            return Result.success(Money(amount))
        }
    }
}
