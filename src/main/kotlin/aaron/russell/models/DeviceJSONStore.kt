package aaron.russell.models
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import mu.KotlinLogging
import java.util.*
import aaron.russell.helpers.*
private val logger = KotlinLogging.logger {}

val JSON_FILE = "devices.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<DeviceModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class DeviceJSONStore : DeviceStore {

    var devices = mutableListOf<DeviceModel>()

    init {
        if (exists(JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<DeviceModel> {
        return devices
    }

    override fun findOne(dnsName: String) :  DeviceModel? {
        var foundDevice: DeviceModel? = devices.find { p -> p.dnsName == dnsName }
        return foundDevice
    }

    override fun create(device: DeviceModel) {
        devices.add(device)
        serialize()
    }

    override fun update(device: DeviceModel) {
        var foundDevice = findOne(device.dnsName!!)
        if (foundDevice != null) {
            foundDevice.dnsName = device.dnsName
            foundDevice.manufacturer = device.manufacturer
            foundDevice.osVersion = device.osVersion
            foundDevice.model =device.model
        }
        serialize()
    }

    override fun delete(device: DeviceModel) {
    devices.remove(device)
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
        serialize()
    }

    override fun deleteLink(device: DeviceModel, unlinker: String?) {
        val foundDevice=findOne(device.dnsName)
        if(foundDevice!=null){
            if(unlinker!=null){
                foundDevice.linkedTo.remove(unlinker)
            }
        }
        serialize()
    }

    internal fun logAll() {
        devices.forEach { logger.info("${it}") }
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(devices, listType)
        write(JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(JSON_FILE)
        devices = Gson().fromJson(jsonString, listType)
    }
}