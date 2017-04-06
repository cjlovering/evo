package controller;

import game.GameEngine;
import game.Store;
import learning.BasicModel;
import learning.IModel;

/**
 * @brief this class is used to encapsulate the game
 * @notes the requirements aren't well defined, so this can be changed completely
 */
public class Session {

    public static void main(String[] args) {
        runBasicSession();
    }

    public static void runBasicSession() {
        int iterations = 1000000000;
        Store store = Store.predatorPeasant();
        IModel model = new BasicModel();
        for (int i  = 0; i < iterations; i++){
            GameEngine.run(store, model);
        }
    }

}
