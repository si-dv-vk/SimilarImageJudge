package vhky.resource

import javafx.scene.image.Image

/**
 * No Description
 *
 * Created at 15:27 2017/8/15
 * @author VHKY
 */
object ResourceBundle
{
	private val root = "/vhky/resource"
	enum class FXML(private val path : String)
	{
		main("main.fxml");
		fun get() = "$root/fxml/$path"
	}
	enum class Image(private val path : String)
	{
		icon("icon.png");
		fun get() = "$root/image/$path"
	}
}
fun String.toURL() = javaClass.getResource(this)!!
fun String.toImage() = Image(this)