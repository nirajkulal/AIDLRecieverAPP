// IMessageReciver.aidl
package com.raj.aidlsender.service;
import  com.raj.aidlsender.service.ICallback;
// Declare any non-default types here with import statements

interface IMessageReciver {
    void setMesssageReciever(ICallback callback);
}