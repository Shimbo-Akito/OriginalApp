package jp.techacademy.shimbo.akito.originalapp;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Line;

/**
 * Created by Develop on 2017/04/13.
 */

public class Cell {
    ShapeRenderer mShapeRenderer;
    public static float CELL_TEST_POSITION_X =5f;
    public static float CELL_TEST_POSITION_Y =8f;

    public static float CELL_RADIUS = 0.8f;



    float PositionX;
    float PositionY;

    float upperVertex;//上段の角のY座標
    float middleVertex;//中段の角のY座標
    float underVertex;//下段の角のY座標

    float rightVertex;//右の角のX座標
    float leftVertex;//左の角のX座標
    float middleRightVertex;//右側の角のX座標
    float middleLeftVertex;//左側の角のX座標

    Cell(){
        PositionX = CELL_TEST_POSITION_X;
        PositionY = CELL_TEST_POSITION_Y;

        upperVertex = (float)(Math.sin(Math.PI / 3) * CELL_RADIUS + PositionY);
        middleVertex = PositionY;
        underVertex = (float)( -Math.sin(Math.PI / 3) * CELL_RADIUS + PositionY);

        rightVertex = PositionX + CELL_RADIUS;
        leftVertex = PositionX - CELL_RADIUS;
        middleRightVertex = PositionX + (float)(Math.cos(Math.PI / 3) * CELL_RADIUS);
        middleLeftVertex = PositionX - (float)(Math.cos(Math.PI/3) * CELL_RADIUS);
    }

    public void draw(Camera camera){
        Camera mCamera = camera;

        mShapeRenderer = new ShapeRenderer();
        mShapeRenderer.setProjectionMatrix(mCamera.combined);
        mShapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        mShapeRenderer.setColor(0,0,0,0);
        mShapeRenderer.line(middleRightVertex, upperVertex, rightVertex, middleVertex);
        mShapeRenderer.line(rightVertex,middleVertex, middleRightVertex,underVertex);
        mShapeRenderer.line(middleRightVertex,underVertex,middleLeftVertex,underVertex);
        mShapeRenderer.line(middleLeftVertex,underVertex,leftVertex,middleVertex);
        mShapeRenderer.line(leftVertex,middleVertex,middleLeftVertex,upperVertex);
        mShapeRenderer.line(middleLeftVertex,upperVertex,middleRightVertex,upperVertex);
        mShapeRenderer.end();

    }


}
