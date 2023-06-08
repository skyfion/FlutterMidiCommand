package com.invisiblewrench.fluttermidicommand

import android.content.Context
import android.hardware.usb.UsbDevice
import android.util.Log
import jp.kshoji.driver.midi.device.MidiInputDevice
import jp.kshoji.driver.midi.device.MidiOutputDevice
import jp.kshoji.driver.midi.util.UsbMidiDriver

class UsbMidiManager(context: Context, val onDeviceRemove: (device: UsbDeviceAdapter)-> Unit):
    UsbMidiDriver(context) {

    private val usbDevices = HashSet<UsbDeviceAdapter>()

    fun openDevice(device: UsbDevice) {

    }

    fun getDevices(): HashSet<UsbDeviceAdapter> {
        return usbDevices
    }

    override fun onDeviceDetached(usbDevice: UsbDevice) {
        Log.d("FlutterMIDICommand", "onDeviceDetached")
      
    }

    override fun onMidiInputDeviceDetached(midiInputDevice: MidiInputDevice) {
        synchronized(usbDevices) {
            val device = getMidiDeviceById(midiInputDevice.usbDevice.deviceId.toString())
            usbDevices.remove(device)
            device?.let { onDeviceRemove(it) }
        }
    }

    private fun getMidiDeviceById(id: String): UsbDeviceAdapter? {
        return usbDevices.find { it.id == id }
    }

    override fun onMidiOutputDeviceDetached(midiOutputDevice: MidiOutputDevice) {
         
    }

    override fun onDeviceAttached(usbDevice: UsbDevice) {
        Log.d("FlutterMIDICommand", "onDeviceAttached")
      
    }

    override fun onMidiInputDeviceAttached(midiInputDevice: MidiInputDevice) {
        synchronized(usbDevices) {
            usbDevices.add(UsbDeviceAdapter(this, midiInputDevice.usbDevice))
        }
    }

    override fun onMidiOutputDeviceAttached(midiOutputDevice: MidiOutputDevice) {
        Log.d("FlutterMIDICommand", "onMidiOutputDeviceAttached")
      
    }

    override fun onMidiMiscellaneousFunctionCodes(
        sender: MidiInputDevice,
        cable: Int,
        byte1: Int,
        byte2: Int,
        byte3: Int
    ) {
     
    }

    override fun onMidiCableEvents(
        sender: MidiInputDevice,
        cable: Int,
        byte1: Int,
        byte2: Int,
        byte3: Int
    ) {
       
    }

    override fun onMidiSystemCommonMessage(sender: MidiInputDevice, cable: Int, bytes: ByteArray?) {
       
    }

    override fun onMidiSystemExclusive(
        sender: MidiInputDevice,
        cable: Int,
        systemExclusive: ByteArray?
    ) {
       // todo
       val device = getMidiDeviceById(sender.usbDevice.deviceId.toString())
       device?.onMidiData(systemExclusive)
    }

    override fun onMidiNoteOff(
        sender: MidiInputDevice,
        cable: Int,
        channel: Int,
        note: Int,
        velocity: Int
    ) {
       
    }

    override fun onMidiNoteOn(
        sender: MidiInputDevice,
        cable: Int,
        channel: Int,
        note: Int,
        velocity: Int
    ) {
        
    }

    override fun onMidiPolyphonicAftertouch(
        sender: MidiInputDevice,
        cable: Int,
        channel: Int,
        note: Int,
        pressure: Int
    ) {
        
    }

    override fun onMidiControlChange(
        sender: MidiInputDevice,
        cable: Int,
        channel: Int,
        function: Int,
        value: Int
    ) {
       
    }

    override fun onMidiProgramChange(
        sender: MidiInputDevice,
        cable: Int,
        channel: Int,
        program: Int
    ) {
        
    }

    override fun onMidiChannelAftertouch(
        sender: MidiInputDevice,
        cable: Int,
        channel: Int,
        pressure: Int
    ) {
         
    }

    override fun onMidiPitchWheel(sender: MidiInputDevice, cable: Int, channel: Int, amount: Int) {
         
    }

    override fun onMidiSingleByte(sender: MidiInputDevice, cable: Int, byte1: Int) {
         
    }

    override fun onMidiTimeCodeQuarterFrame(sender: MidiInputDevice, cable: Int, timing: Int) {
         
    }

    override fun onMidiSongSelect(sender: MidiInputDevice, cable: Int, song: Int) {
         
    }

    override fun onMidiSongPositionPointer(sender: MidiInputDevice, cable: Int, position: Int) {
         
    }

    override fun onMidiTuneRequest(sender: MidiInputDevice, cable: Int) {
         
    }

    override fun onMidiTimingClock(sender: MidiInputDevice, cable: Int) {
         
    }

    override fun onMidiStart(sender: MidiInputDevice, cable: Int) {
         
    }

    override fun onMidiContinue(sender: MidiInputDevice, cable: Int) {
         
    }

    override fun onMidiStop(sender: MidiInputDevice, cable: Int) {
         
    }

    override fun onMidiActiveSensing(sender: MidiInputDevice, cable: Int) {
         
    }

    override fun onMidiReset(sender: MidiInputDevice, cable: Int) {
         
    }

}
