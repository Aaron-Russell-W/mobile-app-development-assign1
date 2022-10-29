package aaron.russell.controllers

import aaron.russell.models.DeviceJSONStore
import aaron.russell.models.DeviceMemStore
import aaron.russell.models.DeviceModel
import aaron.russell.views.DeviceView
import mu.KotlinLogging


class DeviceController {

    private val devices = DeviceJSONStore()
    private val deviceView = DeviceView()
    private val logger = KotlinLogging.logger {}

    init {

    }

    fun start() {
        var input: Int

        do {
            input = menu()
            when (input) {
                1 -> add()
                2 -> update()
                3 -> listAll()
                4 -> search()
                5 -> delete()
                6 -> listDevicesByManufacturer()
                7 -> createLink()
                8 -> findAllByLink()
                9 -> deleteLink()
                -99 -> dummyData()
                0 -> println("Exiting App")
                else -> println("Invalid Option")
            }
            println()
        } while (input != -1)
        logger.info { "Bye bye" }
    }

    private fun menu(): Int {
        return deviceView.mainMenu()
    }

    private fun add() {
        val newDevice = DeviceModel()

        if (deviceView.addDevice(newDevice))
            devices.create(newDevice)
        else
            println("Device Not Added")
    }

    private fun listAll() {
        deviceView.prettierList(devices.devices)
    }

    private fun update() {

        deviceView.prettierList(devices.devices)
        val searchId = deviceView.getByDNS()
        val deviceToUpdate = search(searchId)

        if (deviceToUpdate != null) {
            if (deviceView.update(deviceToUpdate)) {
                devices.update(deviceToUpdate)
                deviceView.showDevice(deviceToUpdate)
                println(("Device Updated : [ $deviceToUpdate ]"))
            } else
                println("Device Not Updated")
        } else
            println("Device Not Updated...")
    }

    private fun search() {
        val deviceToSearch = search(deviceView.getByDNS())!!
        deviceView.showDevice(deviceToSearch)
    }


    private fun search(dns: String): DeviceModel? {
        return devices.findOne(dns)
    }

    private fun dummyData() {
        devices.create(
            DeviceModel(
                manufacturer = "Cisco",
                osVersion = 1.001,
                model = "1900",
                dnsName = "carlie-wra1"
            )
        )
        devices.create(
            DeviceModel(
                manufacturer = "Arista",
                osVersion = 1.021,
                model = "2100",
                dnsName = "carlie-asa1",
            )
        )
        devices.create(
            DeviceModel(
                manufacturer = "Palo Alto",
                osVersion = 3.01,
                model = "5400",
                dnsName = "carlie-fwl1",

                )
        )
    }

    private fun delete() {
        deviceView.prettierList(devices.devices)
        val searchId = deviceView.getByDNS()
        val deviceToBeDeleted = search(searchId)

        if (deviceToBeDeleted != null) {
            devices.delete(deviceToBeDeleted)
            println("${deviceToBeDeleted.dnsName} deleted successfully")
            deviceView.prettierList(devices.devices)
        } else{
            println("Device doesnt exist")
        }

    }

    private fun listDevicesByManufacturer() {
        val tempDevices = mutableListOf<DeviceModel>()
        val manu = deviceView.listByManufacturer()
        for (device in devices.devices) {
            if (device.manufacturer.equals(manu)) {
                tempDevices.add(device)
            }
        }
        deviceView.prettierList(tempDevices)
    }

    private fun createLink() {
        //First side
        val devicesToBeLinked = deviceView.createLink()
        println("$devicesToBeLinked")
        val deviceToBeLinked = search(devicesToBeLinked[0])
        if (deviceToBeLinked != null) {
            println("Not null")
            devices.link(deviceToBeLinked, devicesToBeLinked[1])
        }
        //Second Side
        val deviceToBeLinked2 = search(devicesToBeLinked[1])
        if (deviceToBeLinked2 != null) {
            println("2nd not null")
            devices.link(deviceToBeLinked2, devicesToBeLinked[0])
        }

    }

    private fun deleteLink() {
        val devicesToBeUnlinked = deviceView.createLink()

        val deviceToBeUnlinked = search(devicesToBeUnlinked[0])!!
        val deviceToBeUnlinked2 = search(devicesToBeUnlinked[1])!!
        if ((deviceToBeUnlinked != null) && (deviceToBeUnlinked2 != null) && (deviceToBeUnlinked.linkedTo.contains(
                deviceToBeUnlinked2.dnsName
            )) && (deviceToBeUnlinked2.linkedTo.contains(deviceToBeUnlinked.dnsName))
        ) {
            println("Before : ")
            deviceView.showDevice(deviceToBeUnlinked)
            deviceView.showDevice(deviceToBeUnlinked2)
            devices.deleteLink(deviceToBeUnlinked, deviceToBeUnlinked2.dnsName)
            devices.deleteLink(deviceToBeUnlinked2, deviceToBeUnlinked.dnsName)
        }
        println("After : ")
        deviceView.showDevice(deviceToBeUnlinked)
        deviceView.showDevice(deviceToBeUnlinked2)
    }

    private fun findAllByLink() {
        val devicesContainingLink = mutableListOf<String>()
        deviceView.prettierList(devices.devices)
        val searchDevice = deviceView.getByDNS()
        val deviceToBeSearched = search(searchDevice)!!
        for (device in devices.devices) {
            if (device.linkedTo.contains(deviceToBeSearched.dnsName)) {
                devicesContainingLink.add(device.dnsName)
            }
        }
        if (devicesContainingLink.isNotEmpty()) {
            println("${deviceToBeSearched.dnsName} is contained in the follow devices:")
            for (name in devicesContainingLink) {
                println(name)
            }
        }
    }
}