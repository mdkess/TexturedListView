package xxx.rtin.texturedlistview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ListView;

public class TexturedListView extends ListView {

    private Bitmap mBackground;
    
    public TexturedListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    public TexturedListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }
    
    private void init(Context context, AttributeSet attrs) {
        mBackground = BitmapFactory.decodeResource(getResources(), R.drawable.background2);
    }
    
    //Rendering code adapted from this class in Shelves
    //http://code.google.com/p/shelves/source/browse/trunk/Shelves/src/org/curiouscreature/android/shelves/view/ShelvesView.java
    @Override
    protected void dispatchDraw(Canvas canvas) {
        if(mBackground != null) {
            int distanceFromTop = 
                    getChildCount() == 0 ? 0 :
                        ((CustomArrayAdapter.ViewHolder)getChildAt(0).getTag()).distanceFromTop;
    
            int top = getChildAt(0).getTop();
    
            int yTop = -(distanceFromTop % mBackground.getHeight()) + top - getDividerHeight() - getPaddingTop();
            Log.d("draw", "Drawing from " + yTop + " (distance from top is " + distanceFromTop + ")");
            for(int x = 0; x < canvas.getWidth(); x += mBackground.getWidth()) {
                for(int y = yTop; y < canvas.getHeight(); y += mBackground.getHeight()) {
                    canvas.drawBitmap(mBackground, x, y, null);    
                }
            }
        }
        
        super.dispatchDraw(canvas);
    }

}
