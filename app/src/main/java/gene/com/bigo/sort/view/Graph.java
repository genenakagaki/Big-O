package gene.com.bigo.sort.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import gene.com.bigo.R;
import gene.com.bigo.sort.model.Data;

/**
 * Created by Gene on 2/27/2016.
 */
public class Graph extends View {

    private static final String TAG = Graph.class.getSimpleName();

    private static Bitmap arrow;

    private static final int LIST_SIZE = 10;

    private RectF rect;
    private Paint paint = new Paint();

    private float barWidth;
    private RectF bar;

    private List<Data> list = new ArrayList<>(LIST_SIZE);

    public Graph(Context context) {
        super(context);
        init(context);
    }

    public Graph(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public Graph(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metrics);

        float width = metrics.widthPixels * 3 / 4f;
        rect = new RectF(0, 0, width, width + width / LIST_SIZE);

        barWidth = rect.width() / LIST_SIZE;
        bar = new RectF(0, 0, barWidth, rect.bottom - barWidth);
        arrow = Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(getResources(), R.drawable.arrow),
                (int)barWidth,
                (int)barWidth, false);

        // create list
        for (int i = 0; i < LIST_SIZE; i++) {
            list.add(new Data(i+1));
        }
        Collections.shuffle(list);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension((int)rect.width(), (int)rect.height());
    }

    private void drawGraph(Canvas canvas) {
        for (int i = 0; i < LIST_SIZE; i++) {

            Data d = list.get(i);
            if (d.isFinalPos()) {
                paint.setColor(Color.GREEN);
            } else {
                paint.setColor(Color.BLUE);
            }

            bar.left  = i * barWidth;
            bar.right = i * barWidth + barWidth;
            bar.top   = bar.bottom - d.getValue() * barWidth;

            canvas.drawRect(bar, paint);

            if (d.isPointed()) {
                canvas.drawBitmap(arrow, bar.left, bar.bottom, paint);
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.WHITE);
        canvas.drawRect(rect, paint);
        drawGraph(canvas);
    }

    public List<Data> getList() {
        return list;
    }

    public void setList(List<Data> list) {
        this.list = list;
    }


}
