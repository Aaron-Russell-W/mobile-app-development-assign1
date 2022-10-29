package aaron.russell.views

import aaron.russell.models.DeviceJSONStore
import aaron.russell.models.DeviceModel

class DeviceView {

    fun mainMenu(): Int {
        var option: Int
        var input: String?

        println("Network Device Manager")
        println("-----------------------")
        println("Please Input Your Selection")
        println("------------------------")
        println("1) Add Device")
        println("2) Update Device")
        println("3) See all Devices")
        println("4) Search for Device By DNS")
        println("5) Delete a Device")
        println("6) Find Devices By Manufacturer")
        println("7) Link device to device")
        println("8) Find all devices a device is linked to.")
        println("9) Unlink device")
        println("0) Exit")
        println()
        print("Enter Option : ")
        input = readLine()!!
        option = if (input.toIntOrNull() != null && input.isNotEmpty())
            input.toInt()
        else
            -9
        return option
    }

    fun listDevices(devices: DeviceJSONStore) {
        println("List all Devices")
        println()
        devices.logAll()
        println()
    }

    fun prettierList(devices: MutableList<DeviceModel>) {
        val green = "\u001b[32m"
        val red = "\u001b[31m"
        val blue = "\u001b[34m"
        val reset = "\u001b[0m"
        for (device in devices) {
            println(blue+"${device.dnsName} : "+reset)
            println(red+"Manufacturer: ${device.manufacturer} OS Version: ${device.osVersion} Model: ${device.model}"+reset)
            if (device.linkedTo.isNotEmpty()) {
                println(blue+"LinkedTo"+reset)
                for (linker in device.linkedTo) {
                    println(linker)
                }
            } else {
                println(green+"Device not Linked"+reset)
            }
        }
    }

    fun showDevice(device: DeviceModel) {
        println("Device Details [ $device]")
    }

    fun addDevice(device: DeviceModel): Boolean {
        println()
        print("Enter a DNS name : ")
        device.dnsName = readLine()!!
        print("Enter a Manufacturer Name : ")
        device.manufacturer = readLine()!!
        print("Enter the Model of the Device : ")
        device.model = readLine()!!
        print("Enter the current OS version of the Device : ")
        device.osVersion = readLine()!!.toDouble()
        device.linkedTo = mutableListOf()
        return device.dnsName.isNotEmpty() && device.manufacturer.isNotEmpty() && device.model.isNotEmpty()
    }

    fun update(device: DeviceModel): Boolean {
        val tempDns: String?
        val tempManufacturer: String?
        val tempVersionOs: Double?
        val tempModel: String?
        print("Enter a new DNS name for [ " + device.dnsName + " ] : ")
        tempDns = readLine()!!
        print("Enter a new manufacturer for [ " + device.dnsName + " ] : ")
        tempManufacturer = readLine()!!
        print("Enter a new os-version name for [ " + device.dnsName + " ] : ")
        tempVersionOs = readLine()!!.toDouble()
        print("Enter a new model name for [ " + device.dnsName + " ] : ")
        tempModel = readLine()!!
        if (tempDns.isNotEmpty() && tempManufacturer.isNotEmpty() && tempModel.isNotEmpty()) {
            device.dnsName = tempDns
            device.manufacturer = tempManufacturer
            device.model = tempModel
            device.osVersion = tempVersionOs
            return true
        } else {
            print("You entered empty values. Try again")
            return false
        }
    }

    fun getByDNS(): String {
        var searchDns: String
        print("Enter the DNS name of the device you wish to search/update")
        var str: String? = readLine()!!
        if (!str.isNullOrEmpty()) {
            searchDns = str
        } else
            searchDns = "Fail"
        return searchDns
    }

    fun listByManufacturer(): String {
        var str: String?
        var manu: String
        print("Enter the manufacturer you wish to find : ")
        str = readLine()!!
        if (!str.isNullOrEmpty()) {
            manu = str
        } else {
            manu = "Fail"
        }
        return manu
    }

    fun createLink(): MutableList<String> {
        println("Enter the DNS of the device you wish to make a link to ")
        val firstDevice: String = readLine()!!;
        println("Enter the 2nd DNS of the device you wish to make a link to ")
        val secondDevice: String = readLine()!!
        println("$firstDevice, $secondDevice")
        val deviceNames = mutableListOf<String>()
        deviceNames.add(firstDevice)
        deviceNames.add(secondDevice)
        return deviceNames
    }
    fun unlinkDevice(): MutableList<String>{
        println("Enter the DNS name of the device you wish to unlink")
        val firstDevice: String = readLine()!!
        println("Enter the second DNS of the devices you wish to unlink")
        val secondDevice: String = readLine()!!
        val deviceNames = mutableListOf<String>()
        deviceNames.add(firstDevice)
        deviceNames.add(secondDevice)
        return deviceNames
    }
}