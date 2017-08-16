package vhky.algorithm.convolution

import vhky.algorithm.data.ImageData

/**
 * No Description
 *
 * Created at 15:55 2017/8/16
 * @author VHKY
 */
object Sobel
{
	private val sqrt2 = Math.sqrt(2.0)
	private val SobelX = ConvolutionKernel(listOf(
			-1.0, 0.0, 1.0,
			-sqrt2, 0.0, sqrt2,
			-1.0, 0.0, 1.0
	).map { it / (2 + sqrt2) / 2 })
	private val SobelY = ConvolutionKernel(listOf(
			1.0, sqrt2, 1.0,
			0.0, 0.0, 0.0,
			-1.0, -sqrt2, -1.0
	).map { it / (2 + sqrt2) / 2 })
	operator fun invoke(data : ImageData) : ImageData
	{
		val x = data * SobelX
		val y = data * SobelY
		val result = ImageData(data.size)
		result.size.forEach { data[it] = Math.hypot(x[it], y[it]) }
		return result
	}
}
operator fun ImageData.times(sobel : Sobel) = sobel(this)
operator fun ImageData.timesAssign(sobel : Sobel) = this.set(sobel(this))