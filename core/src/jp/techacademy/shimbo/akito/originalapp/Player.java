package jp.techacademy.shimbo.akito.originalapp;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Develop on 2017/04/16.
 */

public class Player {
    ShapeRenderer mShapeRenderer;
    static float PLAYER_VELOCITY = 5.0f;

    static int PLAYER_COLOR_BLUE = 0;
    static int PLAYER_COLOR_RED = 1;
    static float PLAYER_RADIUS = Cell.CELL_RADIUS * 0.8f;

    Vector2 velocity;

    float CoordinateX;
    float CoordinateY;
    double angle;
    float apexX;//頂点座標
    float apexY;
    float rightBaseX;//右側の底角
    float rightBaseY;
    float leftBaseX;//左側の底角
    float leftBaseY;
    int color;

    Player(int color){
        this.color = color;
        CoordinateX = FieldScreen.CAMERA_WIDTH / 2;
        CoordinateY = (float)(FieldScreen.CAMERA_HEIGHT / 2 - 4*Math.sin(Math.PI / 3) * Cell.CELL_RADIUS);
        angle = Math.PI / 2; //最初の角度
        apexX = (float)(CoordinateX + PLAYER_RADIUS * Math.cos(angle));
        apexY = (float)(CoordinateY + PLAYER_RADIUS * Math.sin(angle));
        rightBaseX = (float)(CoordinateX + PLAYER_RADIUS * Math.cos(angle - 5*Math.PI/6));
        rightBaseY = (float)(CoordinateY + PLAYER_RADIUS * Math.sin(angle - 5*Math.PI/6));
        leftBaseX = (float)(CoordinateX + PLAYER_RADIUS * Math.cos(angle + 5*Math.PI/6));
        leftBaseY = (float)(CoordinateY + PLAYER_RADIUS * Math.sin(angle + 5*Math.PI/6));
    }

    public void draw(OrthographicCamera camera){

        mShapeRenderer = new ShapeRenderer();
        mShapeRenderer.setProjectionMatrix(camera.combined);
        mShapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        mShapeRenderer.setColor(0,0,0,0);
        mShapeRenderer.line(apexX, apexY, rightBaseX, rightBaseY);
        mShapeRenderer.line(apexX, apexY, leftBaseX , leftBaseY);
        mShapeRenderer.line(rightBaseX, rightBaseY,(float)(CoordinateX - Math.cos(angle) * PLAYER_RADIUS * 2 /3),(float)(CoordinateY - Math.sin(angle) * PLAYER_RADIUS /2 ));
        mShapeRenderer.line( leftBaseX , leftBaseY,(float)(CoordinateX - Math.cos(angle) * PLAYER_RADIUS * 2 /3),(float)(CoordinateY - Math.sin(angle) * PLAYER_RADIUS /2 ));

        mShapeRenderer.end();
    }

}
