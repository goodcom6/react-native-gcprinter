package com.goodcom.react.GcPrinter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Base64;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableNativeArray;
import com.goodcom.gcprinter.GcPrinterUtils;

public class GcPrinterModule extends ReactContextBaseJavaModule {
    private static ReactApplicationContext reactContext;

    public GcPrinterModule(ReactApplicationContext context) {
        super(context);
        reactContext = context;
    }

    @NonNull
    @Override
    public String getName() {
        return "GcPrinter";
    }

    @ReactMethod
    public void printText(boolean isAutoFeed) {
        GcPrinterUtils.printText(reactContext, isAutoFeed);
    }

    @ReactMethod
    public void drawText(String strLeft, int fontLeft, String strMid, int fontMid, String strRight, int fontRight) {
        GcPrinterUtils.drawText(strLeft, fontLeft, strMid, fontMid, strRight, fontRight);
    }

    @ReactMethod
    public void drawLeftRight(String strLeft, int fontLeft, String strRight, int fontRight) {
        GcPrinterUtils.drawLeftRight(strLeft, fontLeft, strRight, fontRight);
    }

    @ReactMethod
    public void drawCustom(String string, int fontSize, int align) {
        GcPrinterUtils.drawCustom(string, fontSize, align);
    }

    @ReactMethod
    public void drawNewLine() {
        GcPrinterUtils.drawNewLine();
    }

    @ReactMethod
    public void drawOneLine(int fontSize) {
        GcPrinterUtils.drawOneLine(fontSize);
    }

    @ReactMethod
    public void drawOneLineDefault() {
        GcPrinterUtils.drawOneLine();
    }

    @ReactMethod
    public void drawBarcode(String string, int align, int type) {
        GcPrinterUtils.drawBarcode(string, align, type);
    }

    @ReactMethod
    public void drawBarcodeWithHeight(String string, int align, int type, int height) {
        GcPrinterUtils.drawBarcode(string, align, type, height);
    }
    @ReactMethod
    public void drawQrCode(String string, int align) {
        GcPrinterUtils.drawBarcode(string, align, GcPrinterUtils.barcodeQrCode);
    }
    @ReactMethod
    public void drawQrCodeWithHeight(String string, int align, int height) {
        GcPrinterUtils.drawBarcode(string, align, GcPrinterUtils.barcodeQrCode, height);
    }
    @ReactMethod
    public void isDeviceSupport(Promise promise) {
        if (GcPrinterUtils.isDeviceSupport()) {
            promise.resolve("true");
        } else {
            promise.resolve("false");
        }
    }
    @ReactMethod
    public void printBitmap(Bitmap bitmap, int align, Boolean isAutoFeed) {
        GcPrinterUtils.printBitmap(reactContext, bitmap, align, isAutoFeed);
    }

    @ReactMethod
    public void printImageByBase64(String base64, int align, Boolean isAutoFeed){
        byte[] decodedBytes = Base64.decode(base64.split(",")[1], Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
        GcPrinterUtils.printBitmap(reactContext, bitmap, align, isAutoFeed);
    }

    @ReactMethod
    public void printImageByArray(ReadableArray byteArray, int align, Boolean isAutoFeed){
        byte[] bytes = new byte[byteArray.size()];
        for (int i = 0; i < byteArray.size(); i++) {
            bytes[i] = (byte) byteArray.getInt(i);
        }
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        GcPrinterUtils.printBitmap(reactContext, bitmap, align, isAutoFeed);
    }

    @ReactMethod
    public void printJson(String json) {
        GcPrinterUtils.printJson(reactContext, json);
    }


}
