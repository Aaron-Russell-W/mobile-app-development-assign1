package aaron.russell
import aaron.russell.Device


val deviceList=mutableListOf<Device>()

fun main(args: Array<String>){
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
    println("0) Exit")
    var selection=readLine()
    when (selection){
        "1" -> createDevice() //add device
        "2" -> update()//update device
        "3" -> print("My")
        "4" -> print("Hello")
        "5" -> print("Hello")
        "6" -> print("Hello")
        "7" -> print("Hello")
        "8" -> print("Hello")
        "9" -> print("Hello")
        "0" -> print("Hello")
    }



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
                    var osVersionFloat= osVersion.toString().toFloat()
                    deviceList.add(Device(manufacturer,model,dnsName,osVersionFloat))
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
                            var newOsInt=newOs.toFloat()
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
            print(device.toString())
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
            //do something
        }
    }
}