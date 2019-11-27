package com.lawcboat;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

 class BTHandler{
     static String address = null, name = null;
     static BluetoothAdapter myBluetooth = null;
     static BluetoothSocket btSocket = null;
     static Set<BluetoothDevice> pairedDevices;
     static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
     static private OutputStream outputStream;
     static private InputStream inputStream;


     static boolean bluetooth_connect_device() throws IOException {

        try {
            myBluetooth = BluetoothAdapter.getDefaultAdapter();
            address = myBluetooth.getAddress();
            pairedDevices = myBluetooth.getBondedDevices();
            if (pairedDevices.size() > 0) {
                for (BluetoothDevice bt : pairedDevices) {
                    address = bt.getAddress();
                    name = bt.getName();
//                    Toast.makeText(getApplicationContext(), "Connected", Toast.LENGTH_SHORT).show();
                    Log.d("BTHandler", "Connected");
                }
            }

        } catch (Exception we) {
            Log.d("BTHandler", we.toString());
            return false;
        }
        myBluetooth = BluetoothAdapter.getDefaultAdapter();//get the mobile bluetooth device
        BluetoothDevice dispositivo = myBluetooth.getRemoteDevice(address);//connects to the device's address and checks if it's available
        btSocket = (BluetoothSocket) dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID);//create a RFCOMM (SPP) connection
        btSocket.connect();
        try {
//            Toast.makeText(getApplicationContext(), "BT Name: " + name + "\nBT Address: " + address, Toast.LENGTH_SHORT).show();
            Log.d("BTHandler", "BT Name: " + name + "\nBT Address: " + address);
            return true;
        } catch (Exception e) {
            Log.d("BTHandler", e.toString());
            return false;
        }
    }

     static boolean sendDataViaBluetooth(String s) {
        try {
            if (btSocket != null) {
                btSocket.getOutputStream().write(s.getBytes());
//                Toast.makeText(getApplicationContext(),"Sent Successfully",Toast.LENGTH_SHORT).show();
                return true;
            }

        } catch (Exception e) {
//            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

            return false;
        }
        return false;
    }
}
