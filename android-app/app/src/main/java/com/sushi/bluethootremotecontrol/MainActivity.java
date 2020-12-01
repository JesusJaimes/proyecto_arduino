package com.sushi.bluethootremotecontrol;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    //MAC Address of Bluetooth Module
    private final String DEVICE_ADDRESS = "20:15:11:23:93:85";
    private final UUID PORT_UUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");

    private BluetoothDevice device;
    private BluetoothSocket socket;
    private OutputStream outputStream;

    //string variable that will store value to be transmitted to the bluetooth module
    String command;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void conectar(View view){
        if(BTinit()) {
            if(BTconnect()){
                Toast.makeText(getApplicationContext(), "Conexion exitosa", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(getApplicationContext(), "No se encontro dispositivo", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void moverAdelante(View view){
        command = "a";
        try {
            //transmits the value of command to the bluetooth module
            if(outputStream!=null){
                outputStream.write(command.getBytes());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void moverIzquierda(View view){
        command = "b";
        try {
            //transmits the value of command to the bluetooth module
            if(outputStream!=null){
                outputStream.write(command.getBytes());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void moverDerecha(View view){
        command = "d";
        try {
            //transmits the value of command to the bluetooth module
            if(outputStream!=null){
                outputStream.write(command.getBytes());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void moverReversa(View view){
        command = "e";
        try {
            //transmits the value of command to the bluetooth module
            if(outputStream!=null){
                outputStream.write(command.getBytes());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void detener(View view){
        command = "c";
        try {
            //transmits the value of command to the bluetooth module
            if(outputStream!=null){
                outputStream.write(command.getBytes());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Initializes bluetooth module
    public boolean BTinit() {
        boolean found = false;
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        //Checks if the device supports bluetooth
        if(bluetoothAdapter == null) {
            Toast.makeText(getApplicationContext(), "Dispositivo no soporta bluetooth", Toast.LENGTH_SHORT).show();
        }

        //Checks if bluetooth is enabled. If not, the program will ask permission from the user to enable it
        if(!bluetoothAdapter.isEnabled()) {
            Intent enableAdapter = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableAdapter,0);

            try {
                Thread.sleep(1000);
            }
            catch(InterruptedException e) {
                e.printStackTrace();
            }
        }

        Set<BluetoothDevice> bondedDevices = bluetoothAdapter.getBondedDevices();

        //Checks for paired bluetooth devices
        if(bondedDevices.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please pair the device first", Toast.LENGTH_SHORT).show();
        }
        else {
            for(BluetoothDevice iterator : bondedDevices) {
                if(iterator.getAddress().equals(DEVICE_ADDRESS)) {
                    device = iterator;
                    found = true;
                    break;
                }
            }
        }

        return found;
    }

    public boolean BTconnect() {
        boolean connected = true;

        try {
            //Creates a socket to handle the outgoing connection
            socket = device.createRfcommSocketToServiceRecord(PORT_UUID);
            socket.connect();
        }
        catch(IOException e) {
            e.printStackTrace();
            connected = false;
        }

        if(connected) {
            try {
                //gets the output stream of the socket
                outputStream = socket.getOutputStream();
            }
            catch(IOException e) {
                e.printStackTrace();
            }
        }
        return connected;
    }

}

