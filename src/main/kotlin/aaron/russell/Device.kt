package aaron.russell

data class Device(
    var manufacturer: String,
    var model: String,
    var dnsName: String,
    var osVersion: Double,
    val linkedTo: ArrayList<Device>?
)
{

}