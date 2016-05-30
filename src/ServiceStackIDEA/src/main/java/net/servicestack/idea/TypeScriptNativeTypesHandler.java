package net.servicestack.idea;

/**
 * Created by Layoric on 29/05/2016.
 */
public class TypeScriptNativeTypesHandler extends BaseNativeTypesHandler {
    @Override
    public String getFileExtension() {
        return ".dtos.d.ts";
    }

    @Override
    public String getRelativeTypesUrl() {
        return "types/typescript.d";
    }

    @Override
    public NativeTypesLanguage getTypesLanguage() {
        return NativeTypesLanguage.TypeScript;
    }
}
