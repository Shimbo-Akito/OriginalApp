package jp.techacademy.shimbo.akito.originalapp;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.reflect.Field;


public class Cell {
    ShapeRenderer mShapeRenderer;
    public static float CELL_STANDARD_COORDINATE_X = FieldScreen.WORLD_WIDTH / 2;
    public static float CELL_STANDARD_COORDINATE_Y = FieldScreen.WORLD_HEIGHT /2;//基準座標

    public static float CELL_RADIUS = 0.6f;



    float CoordinateX;//セルの"座標"
    float CoordinateY;

    int PositionX;//セルの"位置番号"
    int PositionY;


    float upperVertex;//上段の角のY座標
    float middleVertex;//中段の角のY座標
    float underVertex;//下段の角のY座標

    float rightVertex;//右の角のX座標
    float leftVertex;//左の角のX座標
    float middleRightVertex;//右側の角のX座標
    float middleLeftVertex;//左側の角のX座標

    Cell(int positionNumber){

        switch (positionNumber / 10){       //セルの列、X座標を設定
            case 0://0列目
                PositionX = 0;
                CoordinateX = CELL_STANDARD_COORDINATE_X - 3*CELL_RADIUS;
                break;
            case 1://1列目
                PositionX = 1;
                CoordinateX = (float)(CELL_STANDARD_COORDINATE_X - 1.5*CELL_RADIUS);
                break;
            case 2://2列目
                PositionX = 2;
                CoordinateX = CELL_STANDARD_COORDINATE_X;
                break;
            case 3://3列目
                PositionX = 3;
                CoordinateX = (float)(CELL_STANDARD_COORDINATE_X + 1.5*CELL_RADIUS);
                break;
            case 4://4列目
                PositionX = 4;
                CoordinateX = CELL_STANDARD_COORDINATE_X + 3*CELL_RADIUS;
                break;

        }

        switch(positionNumber % 10){
            case 0:
                PositionY = 0;
                CoordinateY = (float)(CELL_STANDARD_COORDINATE_Y - (Math.sin(Math.PI / 3) * CELL_RADIUS * 4));
                break;
            case 1:
                PositionY = 1;
                CoordinateY = (float)(CELL_STANDARD_COORDINATE_Y - (Math.sin(Math.PI / 3) * CELL_RADIUS * 2));
                //偶数列の場合
                if (PositionX % 2 == 1){
                    CoordinateY = (float)(CoordinateY - (Math.sin(Math.PI / 3) * CELL_RADIUS));
                }
                break;
            case 2:
                PositionY = 2;
                CoordinateY = CELL_STANDARD_COORDINATE_Y;
                if (PositionX % 2 == 1){
                    CoordinateY = (float)(CoordinateY - (Math.sin(Math.PI / 3) * CELL_RADIUS));
                }
                break;
            case 3:
                PositionY = 3;
                CoordinateY = (float)(CELL_STANDARD_COORDINATE_Y + (Math.sin(Math.PI / 3) * CELL_RADIUS * 2));
                if (PositionX % 2 == 1){
                    CoordinateY = (float)(CoordinateY - (Math.sin(Math.PI / 3) * CELL_RADIUS));
                }
                break;
            case 4:
                PositionY = 4;
                CoordinateY = (float)(CELL_STANDARD_COORDINATE_Y + (Math.sin(Math.PI / 3) * CELL_RADIUS * 4));
                if (PositionX % 2 == 1){
                    CoordinateY = (float)(CoordinateY - (Math.sin(Math.PI / 3) * CELL_RADIUS));
                }
        }



        //各頂点を計算
        upperVertex = (float)(Math.sin(Math.PI / 3) * CELL_RADIUS + CoordinateY);
        middleVertex = CoordinateY;
        underVertex = (float)( -Math.sin(Math.PI / 3) * CELL_RADIUS + CoordinateY);

        rightVertex = CoordinateX + CELL_RADIUS;
        leftVertex = CoordinateX - CELL_RADIUS;
        middleRightVertex = CoordinateX + (float)(Math.cos(Math.PI / 3) * CELL_RADIUS);
        middleLeftVertex = CoordinateX - (float)(Math.cos(Math.PI/3) * CELL_RADIUS);
    }

    public void draw(Camera camera){

        Gdx.gl.glLineWidth(4);
        mShapeRenderer = new ShapeRenderer();
        mShapeRenderer.setProjectionMatrix(camera.combined);
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
