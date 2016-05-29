package net.servicestack.idea;

/**
 * Created by Layoric on 13/05/2016.
 */
public class TypeScriptConcreteNativeTypesHandler extends BaseNativeTypesHandler {
    @Override
    public String getFileExtension() {
        return ".dtos.ts";
    }

    @Override
    public String getRelativeTypesUrl() {
        return "types/typescript";
    }

    @Override
    public NativeTypesLanguage getTypesLanguage() {
        return NativeTypesLanguage.TypeScriptConcrete;
    }
}
