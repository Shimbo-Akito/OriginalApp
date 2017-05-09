package jp.techacademy.shimbo.akito.originalapp;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class OriginalApp extends Game {
	public SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new TitleScreen(this));
		//setScreen(new FieldScreen(this));
	}

	public void toFieldScreen(){
		batch = new SpriteBatch();
		setScreen(new FieldScreen(this));
	}

}
