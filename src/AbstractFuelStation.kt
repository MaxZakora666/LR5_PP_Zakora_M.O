abstract class AbstractFuelStation(
    protected var reserve: Int = 1000,
    private val columnCount: Int
) {
    val capacity = 1000

    init {
        if (columnCount <= 0) throw Exception("Invalid column count")
        if (reserve > capacity || reserve < 0) throw Exception("Invalid reserve")
    }

    abstract fun fill(amount: Int)
    abstract fun tryRefuel(car: Car, volume: Int): Boolean
}