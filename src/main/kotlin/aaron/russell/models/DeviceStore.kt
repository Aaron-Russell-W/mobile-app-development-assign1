package aaron.russell.models

interface DeviceStore {
    fun findAll(): List<DeviceModel>
    fun findOne(dnsname:String): DeviceModel?
    fun create(device:DeviceModel)
    fun update(device: DeviceModel)
    fun delete(device: DeviceModel)
    fun link(device: DeviceModel,linker:String?)
}