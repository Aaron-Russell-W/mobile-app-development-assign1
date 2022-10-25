package aaron.russell.models

data class DeviceModel(
    var manufacturer: String ="",
    var model: String="",
    var dnsName: String="",
    var osVersion: Double=0.0,
    var linkedTo: MutableList<String>?=null
)
