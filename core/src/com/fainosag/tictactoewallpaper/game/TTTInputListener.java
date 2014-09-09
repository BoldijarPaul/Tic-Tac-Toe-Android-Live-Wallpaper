package com.fainosag.tictactoewallpaper.game;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector3;
import com.fainosag.tictactoewallpaper.screens.GameScreen;

/**
 * Created by Paul on 9/9/2014.
 */
public class TTTInputListener extends InputAdapter {
    // this is your input listener, here we check to see if we touched any box or button
    private GameScreen gameCallBack;
    private static Vector3 tmpVector3=new Vector3();

    public TTTInputListener(GameScreen gameCallBack) {
        this.gameCallBack = gameCallBack;
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        tmpVector3.set(screenX, screenY, 0);
        gameCallBack.cameraWrapper.camera.unproject(tmpVector3);
        if(gameCallBack.gameState==0)
        for(int i=0; i<gameCallBack.boxes.length; i++){
            Box box=gameCallBack.boxes[i];
            if(box.touched(tmpVector3.x,tmpVector3.y)){
                // if we are touching any box (that was not touched yet) we set the x or o value to it
                // and change the turn
                if(box.value==' ') {
                    box.value = gameCallBack.turn ? 'x' : 'o';
                    gameCallBack.turn = !gameCallBack.turn;
                    gameCallBack.checkGameState();
                }
                break;
            }
        }
        if(gameCallBack.playerVsPlayer.getBoundingRectangle().contains(tmpVector3.x,tmpVector3.y)){
            gameCallBack.gameType=true;
            gameCallBack.newGame();
        }
        if(gameCallBack.playerVsPhone.getBoundingRectangle().contains(tmpVector3.x,tmpVector3.y)){
            gameCallBack.gameType=false;
            gameCallBack.newGame();
        }
        return super.touchDown(screenX, screenY, pointer, button);
    }
}
