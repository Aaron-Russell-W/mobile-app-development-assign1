package aaron.russell


val deviceList=mutableListOf<Device>()

fun main(args: Array<String>){
    loadDummyData()
    mainMenu()
}

fun mainMenu(){
    println("Network Device Manager")
    println("-----------------------")
    println("Please Input Your Selection")
    println("------------------------")
    println("1) Add Device")
    println("2) Update Device")
    println("3) Delete Device")
    println("4) See all Devices")
    println("5) See all Devices by manufacturer")
    println("6) Add Link To Device")
    println("7) Find route between devices")
    println("0) Exit")
    var selection=readLine()
    when (selection){
        "1" -> createDevice() //add device
        "2" -> update()//update device
        "3" -> deleteDevice()
        "4" -> viewAll()
        "5" -> listDevicesByManufacturer()
        "6" -> createLink()
        "7" -> print("Hello")
        "8" -> print("Hello")
        "9" -> print("Hello")
        "0" -> print("Hello")
    }



}
fun loadDummyData(){
    val d1=Device("Cisco","1000d","carlie-wra1",1.0001,null)
    val d2=Device("Arista","1000x","carlie-asa1",1.0001,null)
    val d3=Device("Cisco","1000g","carlie-wrb1",1.0001,null)
    val d4=Device("Arista","1000h","carlie-asb1",1.0001,null)
    val d5=Device("Cisco","1000h","carlie-wrc1",1.0001,null)
    val d6=Device("Arista","1000h","carlie-asc1",1.0001,null)
    d1.linkedTo?.add(d2)
    d2.linkedTo?.add(d1)
    d3.linkedTo?.add(d1)
    d1.linkedTo?.add(d3)
    d2.linkedTo?.add(d4)
    d4.linkedTo?.add(d2)
    d5.linkedTo?.add(d3)
    d3.linkedTo?.add(d5)
    d4.linkedTo?.add(d2)
    d2.linkedTo?.add(d4)
    d6.linkedTo?.add(d5)
    d5.linkedTo?.add(d6)
    deviceList.add(d1)
    deviceList.add(d2)
    deviceList.add(d3)
    deviceList.add(d4)
    deviceList.add(d5)
    deviceList.add(d6)
}
fun createDevice() {
    println("Enter the manufacturer of the device:")
    var manufacturer=readLine()
    if(manufacturer!= null){
        println("Enter the model of the device:")
        var model=readLine()
        if(model!=null){
            println("Enter the DNS name of the device:")
            var dnsName=readLine()
            if(dnsName!=null){
                println("Enter the OS version of the device")
                var osVersion=readLine()
                if(osVersion!=null){
                    var osVersionFloat= osVersion.toString().toDouble()
                    deviceList.add(Device(manufacturer,model,dnsName,osVersionFloat,null))
                    println("Successfully entered new device:")
                    println("Manufacturer: $manufacturer")
                    println("Model: $model")
                    println("DNS Name: $dnsName")
                    println("OS Version: $osVersion")
                    mainMenu()
                }
                else{
                    print("OS Version was incorrect value. Try again")
                    createDevice()
                }
            }
           else{
                print("DNS name was an incorrect value. Try again")
                createDevice()
            }
        }
        else{
            print("Model was an incorrect value. Try again")
            createDevice()
        }

    }
    else{
        print("Manufacture was an incorrect value. Try again")
        createDevice()
    }


}
fun update(){
    println("Enter the DNS name of the device")
    var enteredName= readLine()
    for (device in deviceList){
        if (device.dnsName==enteredName){
            println("Enter the new Manufacturer of the device")
            val newManu= readLine()
            if(newManu!=null){
                device.manufacturer=newManu
                print("Enter the new Model of the device")
                val newModel= readLine()
                if(newModel!=null){
                    device.model=newModel
                    print("Enter the new DNS name of the device")
                    val newDns= readLine()
                    if(newDns!=null){
                        device.dnsName=newDns
                        print("Enter the new OS version")
                        var newOs= readLine()
                        if(newOs!=null){
                            newOs= newOs.toInt().toString()
                            var newOsInt=newOs.toDouble()
                            device.osVersion=newOsInt
                        }
                        else{
                            print("OS")
                        }
                    }
                    else{
                        print("DNS")
                    }
                }
                else{
                    print("Model")
                }
            }
            else{
                print("Manu")
            }
        }
    }

}

fun viewAll(){
    if(deviceList.isNotEmpty()) {
        for (device in deviceList) {
            println(device.dnsName)
            println(device.manufacturer)
            println(device.model)
            println(device.osVersion)
        }
        mainMenu()
    }
    else{
        println("Device List is empty")
        mainMenu()
    }
}
fun listDevicesByManufacturer(){
    var manufacturer:String?
    val manufacturerList= getManufacturers()
    for (manu in manufacturerList){
        println(manu)
        println("---------")
        for (device in deviceList){
        if(device.manufacturer==manu){
            println(device.toString())
        }
        }
    }
    mainMenu()
}
fun getManufacturers(): ArrayList<String> {
    val manufacturerList=arrayListOf<String>()
    for (device in deviceList){
        if(!manufacturerList.contains(device.manufacturer)){
            manufacturerList.add(device.manufacturer)
        }
    }
    return manufacturerList
}

fun deleteDevice(){
    println("Please enter the DNS name of the device you wish to delete")
    val dnsToFind= readLine()
    for (device in deviceList){
        if(device.dnsName==dnsToFind){
            deviceList.remove(device)
        }
    }
}
fun createLink() {
    println("Enter the DNS name of the device: ")
    val dns1 = readLine()
    println("Enter the device you wish to link to the device")
    val dns2 = readLine()
    if (dns1 != null) {
        if (dns2 != null) {
            for (device1 in deviceList) {
                if (device1.dnsName == dns1) {
                    for (device2 in deviceList) {
                        if (device1.dnsName == dns2) {
                            device1.linkedTo?.add(device2)
                            device2.linkedTo?.add(device1)
                        }
                    }
                }
            }
        }
    }
}