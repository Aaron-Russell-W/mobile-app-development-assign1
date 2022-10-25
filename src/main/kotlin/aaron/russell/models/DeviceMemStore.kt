package aaron.russell.models

import aaron.russell.models.DeviceModel
import mu.KotlinLogging
import aaron.russell.models.DeviceStore

private val logger = KotlinLogging.logger {}
var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class DeviceMemStore : DeviceStore {

    val devices = ArrayList<DeviceModel>()

    override fun findAll(): List<DeviceModel> {
        return devices
    }

    override fun findOne(dnsName: String): DeviceModel? {
        var foundDevice: DeviceModel? = devices.find { p -> p.dnsName == dnsName }
        return foundDevice
    }

    override fun create(device: DeviceModel) {

        devices.add(device)
        logAll()
    }

    override fun update(device: DeviceModel) {
        var foundDevice = findOne(device.dnsName!!)
        if (foundDevice != null) {
            foundDevice.dnsName = device.dnsName
            foundDevice.manufacturer = device.manufacturer
        }
    }

    override fun delete(device: DeviceModel) {

    }

    override fun link(device: DeviceModel, linker: String?) {
        var foundDevice = findOne(device.dnsName!!)
        if (foundDevice != null) {
            if (linker != null) {
                device.linkedTo?.add(linker)
            }
        }
    }

    internal fun logAll() {
        devices.forEach { logger.info("${it}") }
    }
}


