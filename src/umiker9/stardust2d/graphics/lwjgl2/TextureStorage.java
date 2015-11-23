package umiker9.stardust2d.graphics.lwjgl2;

import umiker9.stardust2d.systems.error.Error;
import umiker9.stardust2d.systems.error.ErrorBuilder;
import umiker9.stardust2d.systems.error.ErrorStack;

import java.util.HashMap;
import java.util.Map;

public class TextureStorage implements Storage<Texture> {
    protected final Map<String, Texture> textures = new HashMap<String, Texture>();
    protected String defaultTexture = "none";

    @Override
    public void add(String name, Texture texture) {
        textures.put(name, texture);
    }

    @Override
    public Texture get(String name) {
        return textures.get(name);
    }

    @Override
    public void clear() {
        for (Texture texture : textures.values()) {
            texture.destroy();
        }
        textures.clear();
        defaultTexture = "none";
    }

    @Override
    public int getSize() {
        return textures.size();
    }

    public void bindTexture(String name) {
        bindTexture(name, 0);
    }

    public void bindTexture(String name, int bank) {
        if (name.equals("none")) {
            Texture.none.bind(bank);
        } else if (textures.containsKey(name)) {
            if (textures.get(name) == null || !textures.get(name).isInitialized()) {
                bindDefaultTexture(bank);
            } else {
                textures.get(name).bind(bank);
            }
        } else {
            bindDefaultTexture(bank);
            add(name, null);
            ErrorStack.addError(new ErrorBuilder().setLevel(Error.WARNING).setMessage("Texture [" + name + "] was not found").finish());
        }
    }

    private void bindDefaultTexture(int bank) {
        Texture texture = textures.get(defaultTexture);
        if (texture != null && texture.isInitialized()) {
            texture.bind(bank);
        } else {
            Texture.none.bind(bank);
        }
    }

    public String getDefaultTexture() {
        return defaultTexture;
    }

    public void setDefaultTexture(String defaultTexture) {
        this.defaultTexture = defaultTexture;
    }
}
