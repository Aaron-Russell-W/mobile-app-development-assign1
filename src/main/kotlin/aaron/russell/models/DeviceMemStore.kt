import aaron.russell.models.DeviceModel
import aaron.russell.models.DeviceStore
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}
var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class   DeviceMemStore : DeviceStore {

    val devices = ArrayList<DeviceModel>()

    override fun findAll(): List<DeviceModel> {
        return devices
    }

    override fun findOne(dnsName: String) : DeviceModel? {
        var foundDevice: DeviceModel? = devices.find { p -> p.dnsName == dnsName }
        return foundDevice
    }

    override fun create(device: DeviceModel) {

        devices.add(device)
        logAll()
    }

    override fun update(device: DeviceModel) {
        var foundPlacemark = findOne(device.dnsName!!)
        if (foundPlacemark != null) {
            foundPlacemark.dnsName = device.dnsName
            foundPlacemark.manufacturer = device.manufacturer
        }
    }

    internal fun logAll() {
        devices.forEach { logger.info("${it}") }
    }
}