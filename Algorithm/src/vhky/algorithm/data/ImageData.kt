package vhky.algorithm.data

/**
 * Grey scale collection
 *
 * Created at 16:01 2017/8/15
 * @author VHKY
 */
data class ImageData(private val data : DoubleArray, val size : ImageSize) : Iterable<Double>
{
	init
	{
		require(data.size == size.size)
	}
	constructor(size : ImageSize) : this(DoubleArray(size.size), size)
	override fun equals(other : Any?) : Boolean
	{
		return data == other
	}
	override fun hashCode() = data.hashCode()
	operator fun get(cursor : ImageCursor) = data[cursor.index]
	operator fun set(cursor : ImageCursor, value : Double) = data.set(cursor.index, value)
	operator fun get(x : Int, y : Int) = this[_cursor.apply { set(x, y) }]
	operator fun set(x : Int, y : Int, value : Double) = this.set(_cursor.apply { set(x, y) }, value)
	override fun iterator() = data.iterator()
	private val _cursor = ImageCursor(size)
	fun copy() = ImageData(data.copyOf(), size)
	fun set(data : ImageData) = data.forEachIndexed { index, _data -> this.data[index] = _data }
}