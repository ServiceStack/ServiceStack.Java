package net.servicestack.idea;

public class DartNativeTypesHandler extends BaseNativeTypesHandler {
    @Override
    public String getFileExtension() {
        return ".dart";
    }

    @Override
    public String getRelativeTypesUrl() {
        return "types/dart";
    }

    @Override
    public NativeTypesLanguage getTypesLanguage() {
        return NativeTypesLanguage.Dart;
    }
}
