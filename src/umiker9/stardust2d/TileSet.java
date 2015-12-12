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
        int tileS = (id % getTilesH()) * tileWidth;
        int tileT = (id / getTilesV()) * tileHeight;

        return tile(tileS, tileT, customWidth, customHegiht);
    }

    public Tile tile(int tileS, int tileT, int customWidth, int customHeight) {
        double s1 = tileS / (double) texture.getWidth();
        double t1 = tileT / (double) texture.getHeight();
        double s2 = s1 + customWidth / (double) texture.getWidth();
        double t2 = t1 + customHeight / (double) texture.getHeight();

        return new Tile(texture, s1, t1, s2, t2);
    }

    public int getTilesH() {
        return texture.getWidth() / tileWidth;
    }

    public int getTilesV() {
        return texture.getHeight() / tileHeight;
    }

    public int getTilesTotal() {
        return getTilesH() * getTilesV();
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
