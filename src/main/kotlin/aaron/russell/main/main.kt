package aaron.russell.main
import aaron.russell.controllers.DeviceController

val deviceController = DeviceController()

fun main(args: Array<String>) {
    deviceController.start()
}