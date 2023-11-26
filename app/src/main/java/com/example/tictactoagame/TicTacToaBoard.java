package com.example.tictactoagame;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class TicTacToaBoard extends View {
    private int boardColor;
    private int xColor;
    private int oColor;
    private int winningLineColor;
    private boolean winningLine = false;
    private final Paint paint = new Paint();
    private int cellSize = getWidth()/3;

    private GameLogic game;

    public TicTacToaBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        game = new GameLogic();

        TypedArray array = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.TicTacToaBoard,0 , 0);

        try {
            boardColor = array.getInteger(R.styleable.TicTacToaBoard_boardColor, 0);
            xColor = array.getInteger(R.styleable.TicTacToaBoard_XColor, 0);
            oColor = array.getInteger(R.styleable.TicTacToaBoard_OColor,0);
            winningLineColor = array.getInteger(R.styleable.TicTacToaBoard_winningLineColor,0);

        }finally {
            array.recycle();
        }
    }

    // define dimension of TicTacToaBoard
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int dimension = Math.min(getMeasuredWidth(), getMeasuredHeight());
        // divide by 3 because we have three boxes in each row and column
        cellSize = dimension/3;
        setMeasuredDimension(dimension, dimension);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);

        drawGameBoard(canvas);
        drawMarkers(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        int action = event.getAction();

        if (action == MotionEvent.ACTION_DOWN){
            int row = (int) Math.ceil(y / cellSize);
            int col = (int) Math.ceil(x / cellSize);

            if (!winningLine){
                if (game.updateGameBoard(row, col)){
                    invalidate();
                    if (game.gameWinner()){
                        winningLine = true;
                        invalidate();
                    }
                    // update the player turn
                    if (game.player % 2 == 0){
                        game.setPlayer(game.getPlayer() - 1);
                    }else {
                        game.setPlayer(game.getPlayer() + 1);
                    }
                }

            }
            invalidate();
            return true;
        }
        return false;
    }

    private void drawMarkers(Canvas canvas) {
        for (int r = 0; r < 3; r++){
            for (int c = 0; c < 3; c++){
                if (game.getGameBoard() [r][c] != 0){
                    if (game.getGameBoard() [r][c] == 1){
                        drawX(canvas, r, c);
                    }else {
                        drawO(canvas, r, c);
                    }
                }
            }
        }
    }
    private void drawGameBoard(Canvas canvas) {
        // set color to our paint object
        paint.setColor(boardColor);
        //define width for strok
        paint.setStrokeWidth(16);
        // draw in columns
        for (int i = 1; i < 3; i++){
            canvas.drawLine(cellSize * i, 0, cellSize * i, canvas.getWidth(), paint);
        }

        // draw in rows
        for (int j = 1; j < 3; j++){
            canvas.drawLine(0, cellSize * j, canvas.getWidth(), cellSize * j, paint);
        }
    }

    private void drawX(Canvas canvas, int row, int col){
        paint.setColor(xColor);
        canvas.drawLine(
                (float)((col+1) * cellSize - cellSize * 0.2),
                (float)(row * cellSize + cellSize * 0.2),
                (float) (col * cellSize + cellSize * 0.2),
                (float) ((row + 1) * cellSize - cellSize * 0.2),
                paint
        );

        canvas.drawLine(
                (float) (col * cellSize + cellSize * 0.2),
                (float)(row * cellSize + cellSize * 0.2),
                (float)((col+1) * cellSize - cellSize * 0.2),
                (float) ((row + 1) * cellSize - cellSize * 0.2),
                paint
        );
    }

    private void drawO(Canvas canvas, int row, int col){
        paint.setColor(oColor);

        canvas.drawOval(
                (float) (col * cellSize + cellSize * 0.2),
                (float) (row * cellSize + cellSize * 0.2),
                (float) ((col * cellSize + cellSize) - cellSize * 0.2),
                (float) ((row * cellSize + cellSize) - cellSize * 0.2),
                paint
        );
    }

    public void setupGame(Button playAgainButton, Button homeButton, TextView playerDisplay, String[] names){
        game.setPlayAgainButton(playAgainButton);
        game.setHomeButton(homeButton);
        game.setPlayerTurn(playerDisplay);
        game.setNames(names);
    }
    public void resetGame(){
        game.resetGame();
        winningLine = false;
    }

}
