/*
 * JQMidi  2015.12.28
 * Simple utility to list MIDI devices avaliable to Java.
 */

package jqmidi;

import javax.sound.midi.MidiDevice; 
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;

/**
 ** Provides extension to javax.sound,midi.MidiSystem 
 */

public abstract class JQMidi {
    
    /**
     ** Predicate test if device provides MIDI Receiver
     ** @param dev The device
     ** @return true if device provides MIDI Receiver.
     */
    public static boolean providesReceiver(MidiDevice dev){
    	try{
    	    dev.getReceiver();
    	    return true;
    	}catch(MidiUnavailableException ex){
    	    return false;
    	}
    }
    
    /**
     ** Predicate test if device provides MIDI transmitter
     ** @param dev The device
     ** @return true if device provides Midi transmitter
     */
    public static boolean providesTransmitter(MidiDevice dev){
    	try{
    	    dev.getTransmitter();
    	    return true;
    	}catch(MidiUnavailableException ex){
    	    return false;
    	}
    }


    /**
     ** Display a list of MIDI transmiiters.
     */
    public static void listSystemTransmitters(){
	MidiDevice.Info[] info = MidiSystem.getMidiDeviceInfo();
	System.out.println("MidiSystem Transmitters:");
	for(int i=0; i<info.length; i++){
	    try{
		MidiDevice mdev = MidiSystem.getMidiDevice(info[i]);
		if(providesTransmitter(mdev))
		    System.out.format("\t\"%s\"\n", info[i].getName());
	    }catch(MidiUnavailableException ex){
		System.out.format("\tUnavailable:\"%s\"\n", info[i].getName());
	    }
	}
    }

    /**
     ** Display a list of Midi receivers.
     */
    public static void listSystemReceivers(){
	MidiDevice.Info[] info = MidiSystem.getMidiDeviceInfo();
	System.out.println("MidiSystem Receivers:");
	for(int i=0; i<info.length; i++){
	    try{
		MidiDevice mdev = MidiSystem.getMidiDevice(info[i]);
		if(providesReceiver(mdev))
		    System.out.format("\t\"%s\"\n", info[i].getName());
	    }catch(MidiUnavailableException ex){
		System.out.format("\tUnavailable:\"%s\"\n", info[i].getName());
	    }
	}
    }
	
    public static void main(String[] argv){
	listSystemTransmitters();
	listSystemReceivers();
    }
}
