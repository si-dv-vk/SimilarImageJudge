package vhky.application

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage
import vhky.resource.ResourceBundle
import vhky.resource.toImage
import vhky.resource.toURL

/**
 * No Description
 *
 * Created at 15:23 2017/8/15
 * @author VHKY
 */
class ApplicationMain : Application()
{
	companion object
	{
		fun launch(args : Array<String>) = Application.launch(ApplicationMain::class.java, *args)
	}
	override fun start(primaryStage : Stage)
	{
		primaryStage.scene = Scene(FXMLLoader.load(ResourceBundle.FXML.main.get().toURL()))
		primaryStage.title = "Fuck"
		primaryStage.icons.add(ResourceBundle.Image.icon.get().toImage())
		primaryStage.show()
	}
}