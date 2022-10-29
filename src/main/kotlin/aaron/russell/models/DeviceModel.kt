package aaron.russell.models

data class DeviceModel(
    var dnsName: String="",
    var manufacturer: String ="",
    var model: String="",
    var osVersion: Double=0.0,
    var linkedTo: MutableList<String> = mutableListOf()
)
