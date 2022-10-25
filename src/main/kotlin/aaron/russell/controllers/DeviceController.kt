package aaron.russell.controllers

import aaron.russell.models.DeviceMemStore
import aaron.russell.models.DeviceModel
import aaron.russell.views.DeviceView
import mu.KotlinLogging


class DeviceController {

    val devices = DeviceMemStore()
    val deviceView = DeviceView()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching Network Device Manager App" }
        println("Network Device Manager Kotlin App Version 3.0")
    }

    fun start() {
        var input: Int

        do {
            input = menu()
            when (input) {
                1 -> add()
                2 -> update()
                3 -> list()
                4 -> search()
                5 -> delete()
                6 -> listDevicesByManufacturer()
                7 -> createLink()
                -99 -> dummyData()
                -1 -> println("Exiting App")
                else -> println("Invalid Option")
            }
            println()
        } while (input != -1)
        logger.info { "Shutting Down Placemark Console App" }
    }

    fun menu(): Int {
        return deviceView.mainMenu()
    }

    fun add() {
        var newDevice = DeviceModel()

        if (deviceView.addDevice(newDevice))
            devices.create(newDevice)
        else
            logger.info("Device Not Added")
    }

    fun list() {
        deviceView.listDevices(devices)
    }

    fun update() {

        deviceView.listDevices(devices)
        var searchId = deviceView.getByDNS()
        val deviceToUpdate = search(searchId)

        if (deviceToUpdate != null) {
            if (deviceView.update(deviceToUpdate)) {
                devices.update(deviceToUpdate)
                deviceView.showDevice(deviceToUpdate)
                logger.info("Placemark Updated : [ $deviceToUpdate ]")
            } else
                logger.info("Placemark Not Updated")
        } else
            println("Placemark Not Updated...")
    }

    fun search() {
        val deviceToSearch = search(deviceView.getByDNS())!!
        deviceView.showDevice(deviceToSearch)
    }


    fun search(dns: String): DeviceModel? {
        var foundDevice = devices.findOne(dns)
        return foundDevice
    }

    fun dummyData() {
        devices.create(
            DeviceModel(
                manufacturer = "Cisco",
                osVersion = 1.001,
                model = "1900",
                dnsName = "carlie-wra1",
                linkedTo = null
            )
        )
        devices.create(
            DeviceModel(
                manufacturer = "Arista",
                osVersion = 1.021,
                model = "2100",
                dnsName = "carlie-asa1",
                linkedTo = null
            )
        )
        devices.create(
            DeviceModel(
                manufacturer = "Palo Alto",
                osVersion = 3.01,
                model = "5400",
                dnsName = "carlie-fwl1",
                linkedTo = null
            )
        )
    }

    fun delete() {
        deviceView.listDevices(devices)
        var searchId = deviceView.getByDNS()
        val deviceToBeDeleted = search(searchId)

        if (deviceToBeDeleted != null) {
            devices.delete(deviceToBeDeleted)
            println("Placemark Deleted...")
            deviceView.listDevices(devices)
        } else
            println("Placemark Not Deleted...")
    }

    fun listDevicesByManufacturer() {
        val tempDevices = DeviceMemStore()
        var manu = deviceView.listByManufacturer()
        for (device in devices.devices) {
            if (device.manufacturer == manu) {
                tempDevices.devices.add(device)
            }
        }
        deviceView.listDevices(tempDevices)
    }

    fun createLink() {
        //First side
        val devicesToBeLinked = deviceView.createLink()
        val deviceToBeLinked = search(devicesToBeLinked[0])
        if (deviceToBeLinked != null) {
            devices.link(deviceToBeLinked, devicesToBeLinked[1])
        }
        //Second Side
        val deviceToBeLinked2 = search(devicesToBeLinked[1])
        if (deviceToBeLinked2 != null) {
            devices.link(deviceToBeLinked2, devicesToBeLinked[0])
        }

    }
}