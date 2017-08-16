package vhky.algorithm.data

import javafx.scene.image.Image
import javafx.scene.image.WritableImage

/**
 * Grey scale collection
 *
 * Created at 16:01 2017/8/15
 * @author VHKY
 */
data class ImageData(private val data : DoubleArray, val size : ImageSize) : Iterable<Double>
{
	companion object
	{
		fun fromImage(image : Image) : ImageData
		{
			val size = ImageSize(image.width.toInt(), image.height.toInt())
			val data = DoubleArray(size.size)
			val reader = image.pixelReader
			size.forEach { data[it.index] = reader.getArgb(it.x, it.y).gray }
			return ImageData(data, size)
		}
		private val Int.gray get() = (this ushr 16 and 0xFF) * 0.2126 + (this ushr 8 and 0xFF) * 0.7152 + (this and 0xFF) * 0.0722
		val Double.argb get() = Math.round(this).toInt().let { gray -> (0..2).map { gray shl it }.sum() or (0xFF shl 0x18) }.toInt()
	}
	fun toImage() : Image = WritableImage(size.width, size.height).apply { size.forEach { pixelWriter.setArgb(it.x, it.y, data[it.index].argb) } }
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