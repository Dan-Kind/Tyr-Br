/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeon.crawler.util;

/**
 *
 * @author 0607dacs
 */
public class GameObject {
    protected int x;
    protected int y;

    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Common methods and properties for all game objects
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
