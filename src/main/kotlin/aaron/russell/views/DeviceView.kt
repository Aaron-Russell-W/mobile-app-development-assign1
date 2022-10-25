package aaron.russell.views

import aaron.russell.models.DeviceMemStore
import aaron.russell.models.DeviceModel

class DeviceView {

    fun mainMenu() : Int {
        var option : Int
        var input: String?

        println("Network Device Manager")
        println("-----------------------")
        println("Please Input Your Selection")
        println("------------------------")
        println("1) Add Device")
        println("2) Update Device")
        println("3) See all Device")
        println("4) Search for Device By DNS")
        println("5) See all Devices by manufacturer")
        println("6) Find Devices By Manufacturer")
        println("7) Link device to device")
        println("8) Link devices together")
        println("0) Exit")
        println()
        print("Enter Option : ")
        input =readLine()!!
        option = if(input.toIntOrNull()!= null && !input.isEmpty())
            input.toInt()
        else
            -9
        return option
    }

    fun listDevices(devices:DeviceMemStore){
        println("List all Devices")
        println()
        devices.logAll()
        println()
    }

    fun showDevice(device:DeviceModel){
        if(device != null){
            println("Device Details [ $device]")
        }
        else{
            println("Placemark Not Found...")
        }
    }
    fun addDevice(device:DeviceModel) :Boolean{
        println()
        print("Enter a DNS name : ")
        device.dnsName = readLine()!!
        print("Enter a Manufacturer Name : ")
        device.manufacturer=readLine()!!
        print("Enter the Model of the Device : " )
        device.model=readLine()!!
        print("Enter the current OS version of the Device : ")
        device.osVersion=readLine()!!.toDouble()
        device.linkedTo= mutableListOf()
        return device.dnsName.isNotEmpty() && device.manufacturer.isNotEmpty() && device.model.isNotEmpty()
    }
    fun update(device : DeviceModel):Boolean{
        val tempDns : String?
        val tempManufacturer:String?
        val tempVersionOs:Double?
        val tempModel:String?
        if(device!=null){
            print("Enter a new DNS name for [ " + device.dnsName+ " ] : ")
            tempDns=readLine()!!
            print("Enter a new manufacturer for [ " + device.dnsName + " ] : ")
            tempManufacturer=readLine()!!
            print("Enter a new os-version name for [ " + device.dnsName+ " ] : ")
            tempVersionOs= readLine()!!.toDouble()
            print("Enter a new model name for [ " + device.dnsName+ " ] : ")
            tempModel=readLine()!!
            if(!tempDns.isNullOrEmpty() && !tempManufacturer.isNullOrEmpty() && !tempModel.isNullOrEmpty()){
                device.dnsName=tempDns
                device.manufacturer=tempManufacturer
                device.model=tempModel
                device.osVersion=tempVersionOs
                return true
            }
            else{
                print("You entered empty values. Try again")
                return false
            }
        }
        else{
            print("There are no devices stored")
            return false
        }
    }
    fun getByDNS():String{
        var str:String?
        var searchDns : String
        print("Enter the DNS name of the device you wish to search/update")
        str=readLine()!!
        if(!str.isNullOrEmpty()){
            searchDns=str
        }
        else
            searchDns="Fail"
        return searchDns
    }
    fun listByManufacturer():String{
        var str:String?
        var manu: String
        print("Enter the DNS name of the devices you wish to search/update")
        str=readLine()!!
        if(!str.isNullOrEmpty()){
            manu= str
        }
        else{
            manu="Fail"
        }
        return manu
    }
    fun createLink(): MutableList<String> {
        print("Enter the DNS of the device you wish to make a link to")
        val firstDevice:String = readLine()!!;
        print("Enter the DNS of the device you wish to make a link to")
        val secondDevice:String =readLine()!!
        val deviceNames= mutableListOf<String>()
        deviceNames.add(firstDevice)
        deviceNames.add(secondDevice)
        return deviceNames
    }
}