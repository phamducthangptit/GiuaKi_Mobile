package com.example.midterm.data.api;

import android.util.Log;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IP {
    private String ip;

    public IP() {
        try {
            this.ip = InetAddress.getLocalHost().getHostAddress();
            System.out.println(ip);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIp() {

        return "http://" + ip + ":8080/api/";
    }
}
