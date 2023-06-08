package com.invisiblewrench.fluttermidicommand

import android.hardware.usb.UsbDevice
import io.flutter.plugin.common.MethodChannel
import jp.kshoji.driver.midi.device.MidiOutputDevice

class UsbDeviceAdapter(private val manager: UsbMidiManager, private val usbDevice: UsbDevice):
    Device(usbDevice.deviceId.toString(), "usbNative") {
    private var streamHandler: FMCStreamHandler? = null

    val name: String =
        if (usbDevice.productName != null)
            usbDevice.productName.toString()
        else usbDevice.deviceName

    fun onMidiData(data: ByteArray?) {
        data?.let {
            streamHandler?.send(
                mapOf(
                    "data" to it.toList(),
                    "timestamp" to 0, // todo
                    "device" to mapOf(
                        "id" to id,
                        "name" to name,
                        "type" to "usbNative"
                    ) // todo
                )
            )
        }

    }

    override fun connectWithStreamHandler(
        streamHandler: FMCStreamHandler,
        connectResult: MethodChannel.Result?
    ) {
        this.streamHandler = streamHandler
        connectResult?.success(null)
    }

    override fun send(data: ByteArray, timestamp: Long?) {
        val midiOut: MutableSet<MidiOutputDevice> = manager.getMidiOutputDevices(usbDevice)
        midiOut.forEach {it.sendMidiSystemExclusive(0, data)}
    }

    override fun close() {
        streamHandler = null
    }

}