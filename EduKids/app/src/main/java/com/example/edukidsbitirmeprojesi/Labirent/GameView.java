package com.example.edukidsbitirmeprojesi.Labirent;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Stack;

import static android.content.Context.MODE_PRIVATE;

public class GameView extends View {
    Context context;
    private int COLUMNS = 3, ROWS = 3;
    private Hucre[][] cells;
    private Hucre player, exit;
    private static final float WALL_THICKNESS = 4;
    private float cellSize, hMargin, vMargin;
    private Paint wallPaint, playerPaint, exitPaint;
    private int puan=0;
    private enum Direction {
        UP, DOWN, LEFT, RIGHT
    }
    private SecureRandom random = new SecureRandom();
    public int sayac=0;

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        wallPaint = new Paint();
        wallPaint.setColor(Color.BLACK);
        wallPaint.setStrokeWidth(WALL_THICKNESS);
        playerPaint = new Paint();
        playerPaint.setColor(Color.RED);
        exitPaint = new Paint();
        exitPaint.setColor(Color.BLUE);
        createMaze();
    }
    private void createMaze() {
        Stack<Hucre> stack = new Stack<>();
        Hucre current, next;
        cells = new Hucre[COLUMNS][ROWS];
        for (int i = 0; i < COLUMNS; i++) {
            for (int j = 0; j < ROWS; j++) {
                cells[i][j] = new Hucre(i, j);
            }
        }
        player = cells[0][0];
        exit = cells[COLUMNS - 1][ROWS - 1];
        current = cells[0][0];
        current.visited = true;
        do {
            next = getKomsular(current);
            if (next != null) {
                removeDuvar(current, next);
                stack.push(current);
                current = next;
                current.visited = true;
            } else
                current = stack.pop();
        } while (!stack.empty());
    }
    private Hucre getKomsular(Hucre current) {
        ArrayList<Hucre> neighbours = new ArrayList<>();
        // left neighbour
        if (current.column > 0)
            if (!cells[current.column - 1][current.row].visited)
                neighbours.add(cells[current.column - 1][current.row]);

        // right neighbour
        if (current.column < COLUMNS - 1)
            if (!cells[current.column + 1][current.row].visited)
                neighbours.add(cells[current.column + 1][current.row]);

        // top neighbour
        if (current.row > 0)
            if (!cells[current.column][current.row - 1].visited)
                neighbours.add(cells[current.column][current.row - 1]);

        // bottom neighbour
        if (current.row < ROWS - 1)
            if (!cells[current.column][current.row + 1].visited)
                neighbours.add(cells[current.column][current.row + 1]);

        if (neighbours.size() > 0) {
            int index = random.nextInt(neighbours.size());
            return neighbours.get(index);
        }

        return null;
    }

    private void removeDuvar(Hucre current, Hucre next) {
        if (current.column == next.column && current.row == next.row + 1) {
            current.topWall = false;
            next.bottomWall = false;
        }

        if (current.column == next.column && current.row == next.row - 1) {
            current.bottomWall = false;
            next.topWall = false;
        }

        if (current.column == next.column + 1 && current.row == next.row) {
            current.leftWall = false;
            next.rightWall = false;
        }

        if (current.column == next.column - 1 && current.row == next.row) {
            current.rightWall = false;
            next.leftWall = false;
        }
    }

    //

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.GREEN);
        int width = getWidth();
        int height = getHeight();

        if (width / height < COLUMNS / ROWS)
            cellSize = width / (COLUMNS + 1);
        else
            cellSize = height / (ROWS + 1);

        hMargin = (width - COLUMNS * cellSize) / 2;
        vMargin = (height - ROWS * cellSize) / 2;

        canvas.translate(hMargin, vMargin);

        for (int x = 0; x < COLUMNS; x++) {
            for (int y = 0; y < ROWS; y++) {
                if (cells[x][y].topWall) {
                    canvas.drawLine(x * cellSize,
                            y * cellSize,
                            (x + 1) * cellSize,
                            y * cellSize,
                            wallPaint);
                }

                if (cells[x][y].leftWall) {
                    canvas.drawLine(x * cellSize,
                            y * cellSize,
                            x * cellSize,
                            (y + 1) * cellSize,
                            wallPaint);
                }

                if (cells[x][y].bottomWall) {
                    canvas.drawLine(x * cellSize,
                            (y + 1) * cellSize,
                            (x + 1) * cellSize,
                            (y + 1) * cellSize,
                            wallPaint);
                }

                if (cells[x][y].rightWall) {
                    canvas.drawLine((x + 1) * cellSize,
                            y * cellSize,
                            (x + 1) * cellSize,
                            (y + 1) * cellSize,
                            wallPaint);
                }
            }
        }

        float margin = cellSize / 10;

        canvas.drawRect(
                player.column * cellSize + margin,
                player.row * cellSize + margin,
                (player.column + 1) * cellSize - margin,
                (player.row + 1) * cellSize - margin,
                playerPaint);

        canvas.drawRect(
                exit.column * cellSize + margin,
                exit.row * cellSize + margin,
                (exit.column + 1) * cellSize - margin,
                (exit.row + 1) * cellSize - margin,
                exitPaint);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN)
            return true;

        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            float x = event.getX();
            float y = event.getY();

            float playerCenterX = hMargin + (player.column + 0.5f) * cellSize;
            float playerCenterY = vMargin + (player.row + 0.5f) * cellSize;

            float dx = x - playerCenterX;
            float dy = y - playerCenterY;

            float absDx = Math.abs(dx);
            float absDy = Math.abs(dy);

            if (absDx > cellSize || absDy > cellSize) {
                if (absDx > absDy) {
                    // x yonunde hareket
                    if (dx > 0)
                        movePlayer(Direction.RIGHT);
                    else
                        movePlayer(Direction.LEFT);
                } else {
                    // y yonunde hareket
                    if (dy > 0)
                        movePlayer(Direction.DOWN);
                    else
                        movePlayer(Direction.UP);
                }
            }

            return true;
        }

        return super.onTouchEvent(event);
    }
    private void movePlayer(Direction direction) {
        switch (direction) {
            case UP:
                if (!player.topWall)
                    player = cells[player.column][player.row - 1];
                break;
            case DOWN:
                if (!player.bottomWall)
                    player = cells[player.column][player.row + 1];
                break;
            case LEFT:
                if (!player.leftWall)
                    player = cells[player.column - 1][player.row];
                break;
            case RIGHT:
                if (!player.rightWall)
                    player = cells[player.column + 1][player.row];
                break;
        }
        checkExit();
        invalidate();
    }

    private void checkExit() {
        if (player == exit) {
            COLUMNS++;
            ROWS++;
            sayac++;
            createMaze();
        }

    }
    private class Hucre {
            boolean topWall = true, leftWall = true, rightWall = true, bottomWall = true;
            boolean visited = false;
            int row;
        int column;
        public Hucre(int column, int row) {
            this.column = column;
            this.row = row;
        }
    }

    public class labveri {
        private  Context context;

        public labveri(Context context){
            this.context = context;
        }

        public  void setInt( String key, int value) {
            SharedPreferences prefs = context.getSharedPreferences("labiretlvl",0);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("lablvl", sayac);
            editor.apply();
            editor.commit();
        }
    }
}
