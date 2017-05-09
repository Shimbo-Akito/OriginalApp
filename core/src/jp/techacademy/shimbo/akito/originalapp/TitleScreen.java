package jp.techacademy.shimbo.akito.originalapp;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.awt.Color;

/**
 * Created by Develop on 2017/04/22.
 */

public class TitleScreen extends ScreenAdapter{

    static final float CAMERA_WIDTH = 1000;
    static final float CAMERA_HEIGHT = 1500;
    static final float WORLD_WIDTH = 1000;
    static final float WORLD_HEIGHT = 1500;

    OriginalApp mGame;
    OrthographicCamera mCamera;

    BitmapFont font;

    FreeTypeFontGenerator generator =
            new FreeTypeFontGenerator(Gdx.files.internal("consola.ttf"));

    FreeTypeFontGenerator.FreeTypeFontParameter parameter =
            new FreeTypeFontGenerator.FreeTypeFontParameter();



    FitViewport mViewPort;


    public TitleScreen(OriginalApp game){
        mGame = game;
        mCamera = new OrthographicCamera();
        mCamera.setToOrtho(false, CAMERA_WIDTH, CAMERA_HEIGHT);
        mViewPort = new FitViewport(CAMERA_WIDTH, CAMERA_HEIGHT, mCamera);

        parameter.size = 64;
        parameter.characters =  FreeTypeFontGenerator.DEFAULT_CHARS;

        font = generator.generateFont(parameter);

        generator.dispose();


        

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(256,256,256,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update();


        mGame.batch.setProjectionMatrix(mCamera.combined);
        mGame.batch.begin();

        font.setColor(com.badlogic.gdx.graphics.Color.BLACK);
        font.draw(mGame.batch, "Start", 3*WORLD_WIDTH/7,WORLD_HEIGHT/2);

        mGame.batch.end();

    }

    private void update(){
        if (Gdx.input.isTouched()){
        mGame.toFieldScreen();
        dispose();
        }
    }
}
