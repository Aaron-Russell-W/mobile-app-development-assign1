package aaron.russell.models

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}


class DeviceMemStore : DeviceStore {

    val devices = ArrayList<DeviceModel>()

    override fun findAll(): List<DeviceModel> {
        return devices
    }

    override fun findOne(dnsName: String): DeviceModel? {
        return devices.find { p -> p.dnsName == dnsName }
    }

    override fun create(device: DeviceModel) {

        devices.add(device)
        logAll()
    }

    override fun update(device: DeviceModel) {
        val foundDevice = findOne(device.dnsName)
        if (foundDevice != null) {
            foundDevice.dnsName = device.dnsName
            foundDevice.manufacturer = device.manufacturer
            foundDevice.osVersion = device.osVersion
            foundDevice.model=device.model
        }
    }

    override fun delete(device: DeviceModel) {

    }
    override fun deleteLink(device:DeviceModel,unlinker:String?){
        val foundDevice=findOne(device.dnsName)
        if(foundDevice!=null){
            if(unlinker!=null){
                foundDevice.linkedTo.remove(unlinker)
            }
        }
    }
    override fun link(device: DeviceModel, linker: String?) {
        val foundDevice = findOne(device.dnsName)
        println("$foundDevice")
        if (foundDevice != null) {
            print("Found Device DeviceMemStore")
            if (linker != null) {
                print("Linker not null")
                foundDevice.linkedTo.add(linker)
                println("Devicememstore Linked: ${foundDevice.linkedTo}")
            }
        }
    }

    internal fun logAll() {
        devices.forEach { logger.info("${it}") }
    }
}


