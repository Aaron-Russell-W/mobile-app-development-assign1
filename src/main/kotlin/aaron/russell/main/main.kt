package aaron.russell.main

import mu.KotlinLogging
import aaron.russell.models.DeviceMemStore
import aaron.russell.models.DeviceModel
import aaron.russell.views.DeviceView
import aaron.russell.controllers.DeviceController

val devices = DeviceMemStore()
val deviceView = DeviceView()
private val logger = KotlinLogging.logger {}
val deviceController = DeviceController()

fun main(args: Array<String>) {
    deviceController.start()
}