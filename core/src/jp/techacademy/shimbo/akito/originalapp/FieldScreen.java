package jp.techacademy.shimbo.akito.originalapp;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * Created by Develop on 2017/04/13.
 */

public class FieldScreen extends ScreenAdapter{

    static final float CAMERA_WIDTH = 10;
    static final float CAMERA_HEIGHT = 15;//カメラのサイズ

    Cell testcell = new Cell();

    private OriginalApp mGame;

    OrthographicCamera mCamera;
    FitViewport mViewPort;

    public FieldScreen(OriginalApp game){           //コンストラクタ
        mGame = game;

        Gdx.gl.glClearColor(256,256,256,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //カメラの生成
        mCamera = new OrthographicCamera();
        mCamera.setToOrtho(false, CAMERA_WIDTH, CAMERA_HEIGHT);
        mViewPort = new FitViewport(CAMERA_WIDTH, CAMERA_HEIGHT, mCamera);

    }

    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(256,256,256,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mGame.batch.begin();
        //レンダー描画
        testcell.draw(mCamera);
        mGame.batch.end();
    }

}

