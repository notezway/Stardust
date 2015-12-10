package umiker9.stardust2d;

import umiker9.stardust2d.graphics.lwjgl2.Texture2D;

/**
 * Created by miker9 on 10/12/2015.
 */
public class TileSet {
    protected Texture2D texture;
    protected int tileWidth;
    protected int tileHeight;

    public TileSet(Texture2D texture, int tileWidth, int tileHeight) {
        this.texture = texture;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
    }


    public Tile getTile(int id) {
        return getTile(id, tileWidth, tileHeight);
    }

    public Tile getTile(int id, int customWidth, int customHegiht) {
        int tilesH = texture.getWidth() / tileWidth;
        int tilesV = texture.getHeight() / tileHeight;

        int tileS = (id % tilesH) * tileWidth;
        int tileT = (id / tilesV) * tileHeight;

        return getRegion(tileS, tileT, customWidth, customHegiht);
    }

    public Tile getRegion(int regionS, int regionT, int regionWidth, int regionHeight) {
        double s1 = regionS / (double) texture.getWidth();
        double t1 = regionT / (double) texture.getHeight();
        double s2 = s1 + regionWidth / (double) texture.getWidth();
        double t2 = t1 + regionHeight / (double) texture.getHeight();

        return new Tile(texture, s1, t1, s2, t2);
    }

    public int getTileWidth() {
        return tileWidth;
    }

    public void setTileWidth(int tileWidth) {
        this.tileWidth = tileWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }

    public void setTileHeight(int tileHeight) {
        this.tileHeight = tileHeight;
    }
}
