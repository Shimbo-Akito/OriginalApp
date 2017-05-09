package jp.techacademy.shimbo.akito.originalapp;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.ArrayList;



public class FieldScreen extends ScreenAdapter{

    int[][] HEX_COORDINATE = new int[][]{//セルの座標番号
               { 1, 2, 3},      //←を九十度回転して配置
             {11,12,13,14},
            {20,21,22,23,24},
             {31,32,33,34},
               {41,42,43}
    };
    static final float WORLD_WIDTH = 10;
    static final float WORLD_HEIGHT = 15;

    static final float CAMERA_WIDTH = 10;
    static final float CAMERA_HEIGHT = 15;//カメラのサイズ

    double dragAngle;

    private OriginalApp mGame;

    OrthographicCamera mCamera;
    FitViewport mViewPort;
    ArrayList<Cell> Cells;
    Player myPlayer;
    ShapeRenderer mShapeRenderer;

    Vector3 justTouchedPoint;
    Vector3 isTouchedPoint;

    public FieldScreen(OriginalApp game) {           //コンストラクタ
        mGame = game;

        Gdx.gl.glClearColor(256, 256, 256, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //カメラの生成
        mCamera = new OrthographicCamera();
        mCamera.setToOrtho(false, CAMERA_WIDTH, CAMERA_HEIGHT);
        mShapeRenderer = new ShapeRenderer();
        mViewPort = new FitViewport(CAMERA_WIDTH, CAMERA_HEIGHT, mCamera);
        Cells = new ArrayList<Cell>();

        myPlayer =  new Player(0);

        dragAngle = Math.PI / 2;
        justTouchedPoint = new Vector3();
        isTouchedPoint = new Vector3();

        if(Gdx.input.isTouched()) {
            mCamera.unproject(justTouchedPoint.set(Gdx.input.getX(),Gdx.input.getY(),0));
        }

        createField();

    }

    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(256,256,256,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        update(delta);

        //mGame.batch.begin();

        //mGame.batch.end();
    }

    private void createField(){
                                    //ゲーム画面を生成

        //六角形を生成
        for(int i = 0; i < 5; i++){
            switch (i){
                case 0:
                    for(int j = 0; j < 3; j++ ){
                        Cell mCell;
                        mCell = new Cell(HEX_COORDINATE[i][j]);
                        Cells.add(mCell);
                    }
                    break;
                case 1:
                    for(int j = 0; j < 4; j++ ){
                        Cell mCell;
                        mCell = new Cell(HEX_COORDINATE[i][j]);
                        Cells.add(mCell);
                    }
                    break;
                case 2:
                    for(int j = 0; j < 5; j++ ){
                        Cell mCell;
                        mCell = new Cell(HEX_COORDINATE[i][j]);
                        Cells.add(mCell);
                    }
                    break;
                case 3:
                    for(int j = 0; j <4; j++ ){
                        Cell mCell;
                        mCell = new Cell(HEX_COORDINATE[i][j]);
                        Cells.add(mCell);
                    }
                    break;
                case 4:
                    for(int j = 0; j < 3; j++ ){
                        Cell mCell;
                        mCell = new Cell(HEX_COORDINATE[i][j]);
                        Cells.add(mCell);
                    }
                    break;
                //↑までセル生成

            }
        }

    }

    private void drawCells(){
        for (int i = 0; i < Cells.size(); i++){
            Cells.get(i).draw(mCamera);
        }
    }

    private void update(float delta){


        if (Gdx.input.justTouched()){

            mCamera.unproject(justTouchedPoint.set(Gdx.input.getX(),Gdx.input.getY(),0));

        }
        if(Gdx.input.isTouched()){
            mCamera.unproject(isTouchedPoint.set(Gdx.input.getX(),Gdx.input.getY(),0));

            if(isTouchedPoint.y - justTouchedPoint.y != 0 || isTouchedPoint.x - justTouchedPoint.x != 0) {
                dragAngle = Math.atan2(isTouchedPoint.y - justTouchedPoint.y, isTouchedPoint.x - justTouchedPoint.x);

                myPlayer.update(delta, dragAngle);

            }

            mShapeRenderer.setProjectionMatrix(mCamera.combined);
            mShapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            mShapeRenderer.setColor(Color.LIGHT_GRAY);
            mShapeRenderer.circle(justTouchedPoint.x,justTouchedPoint.y, Cell.CELL_RADIUS,64);
            mShapeRenderer.setColor(Color.GRAY);
            mShapeRenderer.circle(justTouchedPoint.x,justTouchedPoint.y, Cell.CELL_RADIUS / 2,64);
            mShapeRenderer.end();
        }else{
            justTouchedPoint.set(0,0,0);
            isTouchedPoint.set(0,0,0);
        }

        drawCells();
        myPlayer.draw(mCamera);


    }

}

